package com.kingkingduanduan.gankiokotlin.ui

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.kingkingduanduan.gankiokotlin.R
import com.kingkingduanduan.gankiokotlin.ui.base.BaseActivity
import com.kingkingduanduan.gankiokotlin.ui.fragment.DataFragment
import com.kingkingduanduan.gankiokotlin.ui.fragment.HomeFragment
import com.kingkingduanduan.gankiokotlin.ui.fragment.MineFragment
import com.kingkingduanduan.gankiokotlin.utils.toast
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseActivity() {
    companion object {
        private val TITLES = arrayOf("首页", "发现", "我的")
        private val ICONS = arrayOf(R.drawable.tab_main_selector, R.drawable.tab_discovery_selector, R.drawable.tab_mine_selector)
    }

    var tabs = arrayOfNulls<TabLayout.Tab>(3)
    val fragments = arrayOf(HomeFragment(), DataFragment(), MineFragment())

    var currentFragment: Fragment = fragments[0]

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun initView() {
        for (index in 0..TITLES.size - 1) {
            tabs[index] = tabLayout.newTab().setText(TITLES[index])
            val tabItem = layoutInflater.inflate(R.layout.item_tab, null)
            tabs[index]?.customView = tabItem
            (tabItem.findViewById(R.id.tv_title) as TextView).text = TITLES[index]
            (tabItem.findViewById(R.id.iv_tab) as ImageView).setImageResource(ICONS[index])
            tabLayout.addTab(tabs[index]!!)
        }
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(p0: TabLayout.Tab?) {
                p0?.customView?.isSelected = true
                val clickFragment = fragments[p0!!.position]
                if (currentFragment == clickFragment) {
                    return
                }
                supportFragmentManager.beginTransaction().hide(currentFragment).show(clickFragment).commit()
                currentFragment = clickFragment
            }

            override fun onTabUnselected(p0: TabLayout.Tab?) {
                p0?.customView?.isSelected = false
            }

            override fun onTabReselected(p0: TabLayout.Tab?) {

            }
        })
        initFragment()
    }

    fun initFragment() {
        val bundle = Bundle()
        bundle.putString(DataFragment.EXTRA_TOPIC, "瞎推荐")
        fragments[1].arguments = bundle
        supportFragmentManager.beginTransaction().add(R.id.frameLayout, fragments[0], HomeFragment::class.simpleName)
                .add(R.id.frameLayout, fragments[1], DataFragment::class.simpleName).hide(fragments[1])
                .add(R.id.frameLayout, fragments[2], MineFragment::class.simpleName).hide(fragments[2])
                .commit()
        currentFragment = fragments[0]
    }

    override fun initData() {

    }

    override fun onClick(v: View?) {
        toast("haha")
    }

}
