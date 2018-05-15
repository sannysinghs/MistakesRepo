package com.samples.simplekotlin

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.samples.simplekotlin.data.Mistake
import com.samples.simplekotlin.utils.find
import com.samples.simplekotlin.utils.inflate

abstract class MistakesListingFragment: Fragment() , MainMvpView {

    abstract val title: String
    abstract fun getMistakes(mistake: List<Mistake>): List<Mistake>

    private var presenter = MainPresenter()
    private lateinit var mistakesAdapter: MistakeAdapter
    private lateinit var recyclerView: RecyclerView

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
        return v
    }

    override fun showMistakeList(mistakes: List<Mistake>) {
        mistakesAdapter.data = getMistakes(mistakes)
        mistakesAdapter.notifyDataSetChanged()
    }

    override fun showMistakeDetail(mistake: Mistake) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
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