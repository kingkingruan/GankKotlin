package com.kingkingduanduan.gankiokotlin.comp.network

import com.kingkingduanduan.gankiokotlin.BuildConfig
import com.kingkingduanduan.gankiokotlin.app.AppConstant
import com.kingkingduanduan.gankiokotlin.model.DataItem
import io.reactivex.Observable
import io.reactivex.plugins.RxJavaPlugins
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import java.util.concurrent.TimeUnit


/**
 * Created by ruanjinjing on 2017/6/1.
 */
interface ApiStore {
    //单例
    companion object Factory {
        fun create(): ApiStore {
            RxJavaPlugins.setErrorHandler({ e ->
                e.printStackTrace()
            })
            return Retrofit.Builder().baseUrl(AppConstant.API_ADDRESS)
                    .client(provideHttpClient())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build().create(ApiStore::class.java)
        }

        fun provideHttpClient(): OkHttpClient {
            val builder = OkHttpClient.Builder()
            builder.connectTimeout(15, TimeUnit.SECONDS)
            builder.readTimeout(20, TimeUnit.SECONDS)
            builder.writeTimeout(20, TimeUnit.SECONDS)
            builder.retryOnConnectionFailure(true)
            if (BuildConfig.DEBUG) {
                val loggingInterceptor = HttpLoggingInterceptor()
                loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
                builder.addInterceptor(loggingInterceptor)
            }
            return builder.build()
        }
    }

    @GET("data/{topic}/{pageSize}/{pager}")
    fun getData(@Path("topic") topic: String, @Path("pageSize") pageSize: Int, @Path("pager") pager: Int): Observable<GankResponse<ArrayList<DataItem>>>
}