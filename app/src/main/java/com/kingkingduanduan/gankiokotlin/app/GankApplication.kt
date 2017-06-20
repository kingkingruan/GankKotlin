package com.kingkingduanduan.gankiokotlin.app

import android.app.Application

/**
 * Created by ruanjinjing on 2017/5/27.
 */
class GankApplication : Application() {
    companion object {
        lateinit var application: GankApplication
    }

    override fun onCreate() {
        super.onCreate()
        application = this
    }

}