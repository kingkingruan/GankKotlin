package com.kingkingduanduan.gankiokotlin.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kingkingduanduan.gankiokotlin.comp.network.ApiStore
import javax.inject.Inject

/**
 * Created by ruanjinjing on 2017/5/31.
 */
abstract class BaseFragment : Fragment() {
    @Inject
    lateinit protected var apiStore: ApiStore

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View? = inflater?.inflate(getLayoutId(), null)
        apiStore = ApiStore.create()
        try {
            initView(view!!)
            initData()
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            return view
        }
    }

    abstract fun getLayoutId(): Int
    abstract fun initView(view: View)
    abstract fun initData()
}