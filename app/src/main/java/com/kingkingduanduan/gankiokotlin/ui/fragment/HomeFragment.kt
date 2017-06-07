package com.kingkingduanduan.gankiokotlin.ui.fragment

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.view.View
import com.kingkingduanduan.gankiokotlin.R
import com.kingkingduanduan.gankiokotlin.ui.base.BaseFragment

/**
 * Created by ruanjinjing on 2017/6/1.
 */
class HomeFragment : BaseFragment() {
    val TOPICS = arrayOf("Android", "iOS", "休息视频", "福利", "拓展资源", "前端")

    lateinit var tabLayout: TabLayout
    lateinit var viewPager: ViewPager
    override fun getLayoutId(): Int {
        return R.layout.fragment_home
    }

    override fun initView(view: View) {
        tabLayout = view.findViewById(R.id.tabLayout) as TabLayout
        viewPager = view.findViewById(R.id.viewPager) as ViewPager
        viewPager.adapter = TopicAdapter(fragmentManager)
        tabLayout.setupWithViewPager(viewPager)
    }

    override fun initData() {

    }

    inner class TopicAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {
        override fun getItem(p0: Int): Fragment {
            val dataFragment = DataFragment()
            val bundle = Bundle()
            bundle.putString(DataFragment.EXTRA_TOPIC, TOPICS[p0])
            dataFragment.arguments = bundle
            return dataFragment
        }

        override fun getCount(): Int {
            return TOPICS.size
        }

        override fun getPageTitle(position: Int): CharSequence {
            return TOPICS[position]
        }

    }

}