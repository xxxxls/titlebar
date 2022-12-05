package com.xxxxls.titlebar

import android.app.Activity
import android.view.View
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment

/**
 *
 * @author Max
 * @date 2020-01-07.
 */

/**
 * Activity - 获取标题栏
 */
fun Activity?.findTitleBar(@IdRes id: Int = R.id.titlebar): SuperTitleBar? {
    return this?.findViewById<SuperTitleBar>(id)
}

/**
 * Fragment - 获取标题栏
 */
fun Fragment?.findTitleBar(@IdRes id: Int = R.id.titlebar): SuperTitleBar? {
    return this?.view?.findViewById<SuperTitleBar>(id)
}

/**
 * 设置左视图点击事件（注意：会覆盖其他点击事件，否则请使用@setOnTitleBarListener）
 */
fun SuperTitleBar?.setOnLeftClickListener(onClick: (View) -> Unit) {
    this?.setOnTitleBarListener(object : OnTitleBarListener {
        override fun onLeftClick(view: View) {
            super.onLeftClick(view)
            onClick.invoke(view)
        }
    })
}

/**
 * 设置右视图点击事件（注意：会覆盖其他点击事件，否则请使用@setOnTitleBarListener）
 */
fun SuperTitleBar?.setOnRightClickListener(onClick: (View) -> Unit) {
    this?.setOnTitleBarListener(object : OnTitleBarListener {
        override fun onRightClick(view: View) {
            super.onRightClick(view)
            onClick.invoke(view)
        }
    })
}

/**
 * 设置标题视图点击事件（注意：会覆盖其他点击事件，否则请使用@setOnTitleBarListener）
 */
fun SuperTitleBar?.setOnTitleClickListener(onClick: (View) -> Unit) {
    this?.setOnTitleBarListener(object : OnTitleBarListener {
        override fun onTitleClick(view: View) {
            super.onTitleClick(view)
            onClick.invoke(view)
        }
    })
}

/**
 * 设置视图点击事件
 */
fun SuperTitleBar?.setOnTitleBarListener(
    onLeftClick: ((View) -> Unit)? = null,
    onTitleClick: ((View) -> Unit)? = null,
    onRightClick: ((View) -> Unit)? = null
) {
    this?.setOnTitleBarListener(object : OnTitleBarListener {
        override fun onLeftClick(view: View) {
            super.onLeftClick(view)
            onLeftClick?.invoke(view)
        }

        override fun onTitleClick(view: View) {
            super.onTitleClick(view)
            onTitleClick?.invoke(view)
        }

        override fun onRightClick(view: View) {
            super.onRightClick(view)
            onRightClick?.invoke(view)
        }
    })
}