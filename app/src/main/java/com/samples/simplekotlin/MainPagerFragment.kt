package com.samples.simplekotlin

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.samples.simplekotlin.data.Mistake
import com.samples.simplekotlin.utils.find
import com.samples.simplekotlin.utils.onRefresh

class MainPagerFragment : Fragment() {

    private lateinit var tabLayout: TabLayout
    private lateinit var mainPager: ViewPager
    private lateinit var mainToolbar: Toolbar

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mainPager.adapter = object : FragmentPagerAdapter(fragmentManager) {

            val fragments = listOf(AllMistakesListingFragment(), FavouriteMistakesListingFragment())

            override fun getCount(): Int = fragments.size

            override fun getItem(position: Int): Fragment {
                return fragments[position]
            }

            override fun getPageTitle(position: Int): CharSequence? {
                return fragments[position].title
            }
        }

        tabLayout.setupWithViewPager(mainPager)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val v: View = inflater.inflate(R.layout.main_pager_fragment, container, false)

        v.apply {
            tabLayout = v.find(R.id.main_tab_layout)
            mainPager = v.find(R.id.main_pager)
            mainToolbar = v.find(R.id.main_toolbar)
        }

        return  v
    }

    class AllMistakesListingFragment : MistakesListingFragment() {
        override fun getMistakes(mistake: List<Mistake>): List<Mistake> = mistake
        override val title = "All"
    }

    class FavouriteMistakesListingFragment: MistakesListingFragment() {
        override fun getMistakes(mistake: List<Mistake>): List<Mistake> = mistake.filter {
            it.isFavourite
        }
        override val title = "Favourites"
    }

    companion object {
       const val TAG = "MainPagerFragment"
    }


}