package com.kingkingduanduan.gankiokotlin.ui

import android.os.Bundle
import com.kingkingduanduan.gankiokotlin.R
import com.kingkingduanduan.gankiokotlin.base.BaseActivity

/**
 * Created by ruanjinjing on 2017/6/6.
 */
class WebActivity : BaseActivity() {
    companion object {
        val EXTRA_URL = "extra_url"
    }

    val webFragment = WebFragment()
    lateinit var url: String

    override fun getLayoutId(): Int {
        return R.layout.activity_web
    }

    override fun initView() {
        url = intent.getStringExtra(EXTRA_URL)
        val bundle = Bundle()
        bundle.putString(WebFragment.EXTRA_URL, url)
        webFragment.arguments = bundle
        supportFragmentManager.beginTransaction().add(R.id.frameLayout, webFragment).commit()
    }

    override fun initData() {

    }
}