package com.huijieiou.comp.network

import com.google.gson.annotations.SerializedName

/**
 * Created by ruanjinjing on 2017/6/1.
 */
class GankResponse<T> {

    @SerializedName("error")
    var error: Boolean = true

    @SerializedName("results")
    var data: T? = null

}