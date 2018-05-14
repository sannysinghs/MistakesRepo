package com.samples.simplekotlin

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class MainPagerFragment : Fragment() {

    private lateinit var tabLayout: TabLayout
    private lateinit var mainPager: ViewPager
    private lateinit var mainToolbar: Toolbar

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mainPager.adapter = object : FragmentPagerAdapter(fragmentManager) {

            val fragments = listOf(MistakeListingsFragment(), SettingFragment())

            override fun getCount(): Int = fragments.size

            override fun getItem(position: Int): Fragment {
                return fragments[position]
            }

            override fun getPageTitle(position: Int): CharSequence? {
                return if (position == 0) "Mistakes" else "Settings"
            }
        }

        tabLayout.setupWithViewPager(mainPager)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val v: View = inflater.inflate(R.layout.main_pager_fragment, container, false)

        v.apply {
            tabLayout = v.findViewById<View>(R.id.main_tab_layout) as TabLayout
            mainPager = v.findViewById<View>(R.id.main_pager) as ViewPager
            mainToolbar = v.findViewById<View>(R.id.main_toolbar) as android.support.v7.widget.Toolbar
        }

        return  v
    }

    companion object {
       const val TAG = "MainPagerFragment"
    }


}