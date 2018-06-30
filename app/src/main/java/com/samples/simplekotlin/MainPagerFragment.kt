package com.samples.simplekotlin

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.samples.simplekotlin.data.model.Mistake
import com.samples.simplekotlin.databinding.MainPagerFragmentBinding
import com.samples.simplekotlin.utils.find

class MainPagerFragment : Fragment() {

    private lateinit var tabLayout: TabLayout
    private lateinit var mainPager: ViewPager
    private lateinit var mainToolbar: Toolbar
    private lateinit var fab: FloatingActionButton

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val adapter = object : FragmentPagerAdapter(fragmentManager) {

            val fragments = listOf(AllMistakesListingFragment(),
                    FavouriteMistakesListingFragment())

            override fun getCount(): Int = fragments.size

            override fun getItem(position: Int): Fragment {
                return fragments[position]
            }

            override fun getPageTitle(position: Int): CharSequence? {
                return fragments[position].title
            }
        }

        mainPager.adapter = adapter
        tabLayout.setupWithViewPager(mainPager)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val binding = MainPagerFragmentBinding.inflate(inflater, container, false)

        mainPager = binding.mainPager
        tabLayout = binding.mainTabLayout
        mainToolbar = binding.mainToolbar

        return  binding.root
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