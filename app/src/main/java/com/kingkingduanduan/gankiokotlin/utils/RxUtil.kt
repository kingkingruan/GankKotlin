package com.huijieiou.utils

import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


/**
 * Created by ruanjinjing on 2017/6/1.
 */
class RxUtil {
    companion object{
        fun <T> rxSchedulerHelper(): ObservableTransformer<T, T> {//compose简化线程
            return ObservableTransformer { upstream ->
                upstream.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
            }
        }
    }
}