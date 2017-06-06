package com.kingkingduanduan.gankiokotlin.comp.network

import io.reactivex.Observer
import io.reactivex.disposables.Disposable


/**
 * Created by ruanjinjing on 2017/6/1.
 */
abstract class CommonObserver<T> : Observer<T> {
    override fun onComplete() {
    }

    override fun onSubscribe(d: Disposable?) {
    }

    override fun onError(e: Throwable?) {
        e?.printStackTrace()
    }

    override fun onNext(t: T) {
        when (t) {
            is GankResponse<*> -> {
                if (!t.error) {
                    onSuccessResult(t)
                } else {
                    onBusinessError()
                }
            }
        }
    }

    fun onBusinessError() {

    }

    abstract fun onSuccessResult(data: T)

}