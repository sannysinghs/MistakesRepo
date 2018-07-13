package com.samples.simplekotlin.ui.notes

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.samples.simplekotlin.R
import com.samples.simplekotlin.data.model.Mistake
import com.samples.simplekotlin.data.source.MistakesRepository
import com.samples.simplekotlin.data.source.local.MistakesLocalDataSource
import com.samples.simplekotlin.databinding.MistakeLayoutItemBinding
import com.samples.simplekotlin.databinding.MistakesListingFragmentBinding
import com.samples.simplekotlin.utils.onRefresh
import java.util.*

abstract class MistakesListingFragment: Fragment() {

    abstract val title: String
    abstract fun getMistakes(mistake: List<Mistake>): List<Mistake>

    private lateinit var mistakesAdapter: MistakeAdapter
    private lateinit var refreshLayout : SwipeRefreshLayout
    private lateinit var mFactory: ViewModelProvider.AndroidViewModelFactory

    private val mistakeRepo by lazy {
        MistakesRepository(
                MistakesLocalDataSource(context!!.getSharedPreferences(MistakesLocalDataSource.PREF_FILE_NAME, Context.MODE_PRIVATE)))
    }

    private lateinit var mistakesViewModel: MistakesViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mistakesViewModel.loadAllMistakes(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = MistakesListingFragmentBinding.inflate(inflater, container, false)
        binding.view = this

        val factory = MistakesViewModelFactory(activity?.application!!, mistakeRepo)
        mistakesViewModel = ViewModelProviders.of (this, factory)
                .get(MistakesViewModel::class.java)

        binding.viewModel = mistakesViewModel

        mistakesAdapter = MistakeAdapter(context)
        binding.mistakesRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = mistakesAdapter
        }

        refreshLayout = binding.refreshLayout

        refreshLayout.apply {
            onRefresh {
                mistakesViewModel.loadAllMistakes(true)
            }
        }

        subscribeToLiveData()

        return binding.root
    }

    private fun subscribeToLiveData() {
        mistakesViewModel.liveItems.observe(this, Observer {
            mistakesAdapter.data = it ?: emptyList()
            mistakesAdapter.notifyDataSetChanged()
        })
    }

    class MistakeAdapter(var context: Context?): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

        var data: List<Mistake> = emptyList()
            set(value) {
                field = value
                notifyDataSetChanged()
            }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            return MistakeViewHolder(MistakeLayoutItemBinding.inflate(LayoutInflater.from(parent.context)))
        }

        override fun getItemCount(): Int = data.size

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            if (holder is MistakeViewHolder) {
                holder.bind(data[position])
            } else {
                println("Binding eror")
            }
        }


        class MistakeViewHolder(private val mBinding: ViewDataBinding) : RecyclerView.ViewHolder(mBinding.root) {
            fun bind(item: Mistake) {
                ( mBinding as MistakeLayoutItemBinding ).mistake = item
                mBinding.executePendingBindings()
            }
        }

    }
}