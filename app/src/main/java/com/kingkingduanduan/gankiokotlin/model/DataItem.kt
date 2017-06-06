package com.kingkingduanduan.gankiokotlin.model

import com.google.gson.annotations.SerializedName

/**
 * Created by ruanjinjing on 2017/6/6.
 */
class DataItem {
    @SerializedName("_id")
    lateinit var id: String

    @SerializedName("createdAt")
    lateinit var createdAt: String

    @SerializedName("desc")
    lateinit var des: String

    @SerializedName("images")
    var images: List<String>? = null

    @SerializedName("publishedAt")
    lateinit var publishedAt: String

    @SerializedName("source")
    lateinit var source: String

    @SerializedName("type")
    lateinit var type: String

    @SerializedName("url")
    lateinit var url: String

    @SerializedName("used")
    var used: Boolean = false

    @SerializedName("who")
    lateinit var who: String


}