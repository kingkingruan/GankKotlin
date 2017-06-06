package com.huijieiou.utils

import android.util.Log
import com.kingkingduanduan.gankiokotlin.BuildConfig

/**
 * Created by ruanjinjing on 2017/6/1.
 */
class LogUtil {
    companion object {
        fun e(tag: String, message: String) {
            if (BuildConfig.DEBUG) {
                Log.e(tag, message)
            }
        }
    }
}