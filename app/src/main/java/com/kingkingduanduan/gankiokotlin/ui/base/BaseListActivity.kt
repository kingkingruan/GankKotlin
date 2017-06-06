package com.kingkingduanduan.gankiokotlin.base

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.kingkingduanduan.gankiokotlin.R

/**
 * Created by ruanjinjing on 2017/5/31.
 */
abstract class BaseListActivity : BaseActivity() {
    override fun getLayoutId(): Int {
        return R.layout.activity_base_list
    }

    override fun initView() {

    }

    override fun initData() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    inner class BaseViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    }

    inner class BaseAdapter : RecyclerView.Adapter<BaseViewHolder>() {
        override fun onCreateViewHolder(p0: ViewGroup?, p1: Int): BaseViewHolder {
            return BaseViewHolder(layoutInflater.inflate(getItemLayoutId(), null))
        }

        override fun getItemCount(): Int {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun onBindViewHolder(p0: BaseViewHolder?, p1: Int) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

    }

    abstract fun getItemLayoutId(): Int
}