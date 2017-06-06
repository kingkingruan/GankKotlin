package com.kingkingduanduan.gankiokotlin.ui.fragment

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.kingkingduanduan.gankiokotlin.R
import com.kingkingduanduan.gankiokotlin.comp.network.CommonObserver
import com.kingkingduanduan.gankiokotlin.comp.network.GankResponse
import com.kingkingduanduan.gankiokotlin.model.DataItem
import com.kingkingduanduan.gankiokotlin.ui.WebActivity
import com.kingkingduanduan.gankiokotlin.ui.base.BaseFragment
import com.kingkingduanduan.gankiokotlin.utils.CommonUtil
import com.kingkingduanduan.gankiokotlin.utils.RxUtil

/**
 * Created by ruanjinjing on 2017/6/1.
 */
class HomeFragment : BaseFragment() {

    val TYPE_NORMAL = 1
    val TYPE_WITHOUT_PIC = 2

    lateinit var recyclerView: RecyclerView
    var dataItemList: List<DataItem>? = null
    val PAGE_SIZE = 20
    var pager = 1

    override fun getLayoutId(): Int {
        return R.layout.fragment_home
    }

    override fun initView(view: View) {
        recyclerView = view.findViewById(R.id.recyclerView) as RecyclerView
        recyclerView.adapter = ItemAdapter()
    }

    override fun initData() {
        apiStore.getData("all", PAGE_SIZE, pager)
                .compose(RxUtil.rxSchedulerHelper<GankResponse<ArrayList<DataItem>>>())
                .subscribe(object : CommonObserver<GankResponse<ArrayList<DataItem>>>() {
                    override fun onSuccessResult(data: GankResponse<ArrayList<DataItem>>) {
                        dataItemList = data.data
                        recyclerView.adapter.notifyDataSetChanged()
                    }
                })
    }

    inner class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var title: TextView
        var image: ImageView
        var whoWhen: TextView
        var type: TextView

        init {
            title = view.findViewById(R.id.txtTitle) as TextView
            image = view.findViewById(R.id.image) as ImageView
            whoWhen = view.findViewById(R.id.txtWhoWhen) as TextView
            type = view.findViewById(R.id.txtType) as TextView
        }
    }

    inner class ItemWithOutPicViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var title: TextView
        var whoWhen: TextView
        var type: TextView

        init {
            title = view.findViewById(R.id.txtTitle) as TextView
            whoWhen = view.findViewById(R.id.txtWhoWhen) as TextView
            type = view.findViewById(R.id.txtType) as TextView
        }
    }

    inner class ItemAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
        override fun getItemViewType(position: Int): Int {
            val item = dataItemList?.get(position)
            if (item?.images != null) {
                return TYPE_NORMAL
            } else {
                return TYPE_WITHOUT_PIC
            }
        }

        override fun getItemCount(): Int {
            return dataItemList?.size ?: 0
        }

        override fun onBindViewHolder(p0: RecyclerView.ViewHolder?, p1: Int) {
            val item = dataItemList?.get(p1)
            when (p0) {
                is ItemViewHolder -> {
                    p0?.title?.text = item?.des
                    Glide.with(this@HomeFragment).load(item?.images?.get(0)).into(p0?.image)
                    p0?.whoWhen?.text = "${item?.who} ${CommonUtil.formatTime(item?.publishedAt)}"
                    p0?.type?.text = item?.type
                    p0?.itemView?.setOnClickListener({ _ ->
                        val intent = Intent(activity, WebActivity::class.java)
                        intent.putExtra(WebActivity.EXTRA_URL, item?.url)
                        startActivity(intent)
                    })
                }
                is ItemWithOutPicViewHolder -> {
                    p0?.title?.text = item?.des
                    p0?.whoWhen?.text = "${item?.who} ${CommonUtil.formatTime(item?.publishedAt)}"
                    p0?.type?.text = item?.type
                    p0?.itemView?.setOnClickListener({ _ ->
                        val intent = Intent(activity, WebActivity::class.java)
                        intent.putExtra(WebActivity.EXTRA_URL, item?.url)
                        startActivity(intent)
                    })
                }
            }

        }

        override fun onCreateViewHolder(p0: ViewGroup?, p1: Int): RecyclerView.ViewHolder {
            when (p1) {
                TYPE_NORMAL -> return ItemViewHolder(activity.layoutInflater.inflate(R.layout.item_data, null))
                else -> return ItemWithOutPicViewHolder(activity.layoutInflater.inflate(R.layout.item_data_without_pic, null))
            }
        }

    }
}