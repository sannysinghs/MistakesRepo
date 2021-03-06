package com.samples.simplekotlin

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.samples.simplekotlin.data.model.Mistake
import com.samples.simplekotlin.data.source.MistakesDataSource
import com.samples.simplekotlin.data.source.MistakesRepository
import com.samples.simplekotlin.data.source.local.MistakesLocalDataSource
import com.samples.simplekotlin.utils.find
import com.samples.simplekotlin.utils.inflate
import com.samples.simplekotlin.utils.onRefresh

abstract class MistakesListingFragment: Fragment() , MainMvpView {

    abstract val title: String
    abstract fun getMistakes(mistake: List<Mistake>): List<Mistake>

    private var presenter = MainPresenter()
    private lateinit var mistakesAdapter: MistakeAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var refreshLayout: SwipeRefreshLayout
    private val mistakeRepo by lazy {
        MistakesRepository(
                MistakesLocalDataSource(context!!.getSharedPreferences(MistakesLocalDataSource.PREF_FILE_NAME, Context.MODE_PRIVATE)))
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter.attachView(this)
        presenter.loadMistakes()

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.mistakes_listing_fragment, container, false)
        recyclerView = v.find(R.id.mistakes_recycler_view)
        mistakesAdapter = MistakeAdapter(context)
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = mistakesAdapter
        }

        refreshLayout = v.find(R.id.refresh_layout)
        refreshLayout.onRefresh {
            mistakeRepo.getAll(object: MistakesDataSource.LoadMistakesCallback {
                override fun onTasksLoaded(tasks: List<Mistake>) {
                    refreshLayout.isRefreshing = false
                    showMistakeList(tasks)
                }

                override fun onDataNotAvailable() {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }
            })
        }
        return v
    }



    override fun showMistakeList(mistakes: List<Mistake>) {
        mistakesAdapter.data = getMistakes(mistakes)
        mistakesAdapter.notifyDataSetChanged()
    }

    override fun showMistakeDetail(mistake: Mistake) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showLoading(b: Boolean) {
        refreshLayout.apply {
            isRefreshing = b
        }
    }

    fun addNewMistake() {
        mistakeRepo.save(Mistake(title = "What baby", desc = "This is coding time!"))
    }

    class MistakeAdapter(var context: Context?): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

        var data: List<Mistake> = emptyList()
            set(value) {
                field = value
                notifyDataSetChanged()
            }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            return MistakeViewHolder(parent.inflate(R.layout.mistake_layout_item))
        }

        override fun getItemCount(): Int = data.size

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            if (holder is MistakeViewHolder) holder.bind(data[position])
            else println("Binding eror")
        }


        class MistakeViewHolder(v: View) : RecyclerView.ViewHolder(v) {
            private val mistakeTitle = v.find<TextView>(R.id.mistake_title_text)
            private val mistakeDesc = v.find<TextView>(R.id.mistake_desc_text)
            fun bind(item: Mistake) {
                mistakeTitle.text = item.title
                mistakeDesc.text = item.desc
            }
        }

    }
}