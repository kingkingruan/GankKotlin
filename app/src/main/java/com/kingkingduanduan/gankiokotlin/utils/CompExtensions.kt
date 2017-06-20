package com.kingkingduanduan.gankiokotlin.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Paint
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide

/**
 * Created by ruanjinjing on 2017/6/18.
 */

/**
 *======================================Activity extensions  =========================
 */

fun Activity.toast(content: String) {
    Toast.makeText(this, content, Toast.LENGTH_SHORT).show()
}

fun Activity.toast(content: String, length: Int) {
    Toast.makeText(this, content, length).show()
}

//跳转
fun <T : Any> Activity.go(t: Class<T>) {
    startActivity(Intent(this, t))
}

fun Activity.inflate(@LayoutRes res: Int): View {
    return LayoutInflater.from(this).inflate(res, null)
}

/**
 *======================================Fragment extensions  =========================
 */

fun Fragment.toast(content: String) {
    Toast.makeText(context, content, Toast.LENGTH_SHORT).show()
}

fun Fragment.toast(content: String, length: Int) {
    Toast.makeText(context, content, length).show()
}

fun <T : Any> Fragment.go(t: Class<T>) {
    startActivity(Intent(context, t))
}

fun Fragment.inflate(@LayoutRes res: Int): View {
    return LayoutInflater.from(context).inflate(res, null)
}

/**
 *======================================View extensions  =========================
 */

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun ImageView.loadUrl(context: Context, url: String?) {
    Glide.with(context).load(url).into(this)
}

fun ImageView.loadUrl(fragment: Fragment, url: String?) {
    Glide.with(fragment).load(url).into(this)
}

fun TextView.middleLine() {
    this.paint.flags = Paint.STRIKE_THRU_TEXT_FLAG
}