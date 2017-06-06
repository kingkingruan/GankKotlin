package com.kingkingduanduan.gankiokotlin.utils

import java.text.SimpleDateFormat
import java.util.*


/**
 * Created by ruanjinjing on 2017/6/1.
 */
class CommonUtil {
    companion object {
        fun formatTime(time: String?): String {
            val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.CHINA)
            val date = simpleDateFormat.parse(time)
            return SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA).format(date)
        }
    }
}