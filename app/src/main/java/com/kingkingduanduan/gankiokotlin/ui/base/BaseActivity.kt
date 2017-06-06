package com.kingkingduanduan.gankiokotlin.ui.base

import android.content.Intent
import android.os.Bundle
import android.support.annotation.StringRes
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import com.kingkingduanduan.gankiokotlin.comp.network.ApiStore
import com.umeng.analytics.MobclickAgent
import org.jetbrains.annotations.NotNull
import javax.inject.Inject

/**
 * Created by ruanjinjing on 2017/5/27.
 */
abstract class BaseActivity : AppCompatActivity(), View.OnClickListener {
    @Inject
    lateinit protected var apiStore: ApiStore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        apiStore = ApiStore.create()
        try {
            setContentView(getLayoutId())
            initView()
            initData()
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    abstract fun getLayoutId(): Int
    abstract fun initView()
    abstract fun initData()

    protected fun showToast(@NotNull content: String) {
        Toast.makeText(this, content, Toast.LENGTH_SHORT).show()
    }

    protected fun showToast(@StringRes res: Int) {
        Toast.makeText(this, res, Toast.LENGTH_SHORT).show()
    }

    protected fun <T : Any> go(t: Class<T>) {
        startActivity(Intent(this, t))
    }

    override fun onClick(v: View?) {
    }

    override fun onResume() {
        super.onResume()
        MobclickAgent.onResume(this)
    }

    override fun onPause() {
        super.onPause()
        MobclickAgent.onPause(this)
    }
}