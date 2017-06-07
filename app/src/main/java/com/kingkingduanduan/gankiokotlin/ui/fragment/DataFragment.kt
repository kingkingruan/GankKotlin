package com.kingkingduanduan.gankiokotlin.ui.fragment

import android.content.Intent
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
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
class DataFragment : BaseFragment(), SwipeRefreshLayout.OnRefreshListener {

    companion object {
        val EXTRA_TOPIC = "extra_topic"
    }

    val TYPE_NORMAL = 1
    val TYPE_WITHOUT_PIC = 2

    lateinit var recyclerView: RecyclerView
    lateinit var refreshLayout: SwipeRefreshLayout

    var dataItemList: ArrayList<DataItem> = arrayListOf()
    val PAGE_SIZE = 20
    var pager = 1

    lateinit var topic: String
    var isLoading = false

    override fun getLayoutId(): Int {
        return R.layout.fragment_data
    }

    override fun initView(view: View) {
        refreshLayout = view.findViewById(R.id.refreshLayout) as SwipeRefreshLayout
        refreshLayout.setOnRefreshListener(this)
        refreshLayout.setColorSchemeResources(R.color.colorAccent, R.color.colorPrimary, R.color.colorPrimaryDark)
        recyclerView = view.findViewById(R.id.recyclerView) as RecyclerView
        recyclerView.adapter = ItemAdapter()
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = recyclerView!!.layoutManager as LinearLayoutManager
                val lastVisible = layoutManager.findLastVisibleItemPosition()
                val totalItemCount = layoutManager.itemCount
                if (lastVisible >= totalItemCount - 3 && dy > 0 && !isLoading) {
                    isLoading = true
                    pager++
                    requestData()
                }
            }
        })
    }

    override fun initData() {
        topic = arguments.getString(EXTRA_TOPIC)
        onRefresh()
    }

    fun requestData() {
        apiStore.getData(topic, PAGE_SIZE, pager)
                .compose(RxUtil.rxSchedulerHelper<GankResponse<ArrayList<DataItem>>>())
                .subscribe(object : CommonObserver<GankResponse<ArrayList<DataItem>>>() {
                    override fun onSuccessResult(data: GankResponse<ArrayList<DataItem>>) {
                        refreshLayout.isRefreshing = false
                        if (pager == 1) {
                            dataItemList.clear()
                        }
                        if (data.data != null && data.data!!.size > 0) {
                            isLoading = false
                        }
                        dataItemList.addAll(data.data ?: arrayListOf())
                        recyclerView.adapter.notifyDataSetChanged()
                    }
                })
    }

    override fun onRefresh() {
        pager = 1
        requestData()
    }

    inner class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView
        val image: ImageView
        val whoWhen: TextView
        val type: TextView

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
                    Glide.with(this@DataFragment).load(item?.images?.get(0)).into(p0?.image)
                    p0?.whoWhen?.text = "${item?.who ?: "匿名"} ${CommonUtil.formatTime(item?.publishedAt)}"
                    p0?.type?.text = item?.type
                    p0?.itemView?.setOnClickListener({ _ ->
                        val intent = Intent(activity, WebActivity::class.java)
                        intent.putExtra(WebActivity.EXTRA_URL, item?.url)
                        startActivity(intent)
                    })
                }
                is ItemWithOutPicViewHolder -> {
                    p0?.title?.text = item?.des
                    p0?.whoWhen?.text = "${item?.who ?: "匿名"} ${CommonUtil.formatTime(item?.publishedAt)}"
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