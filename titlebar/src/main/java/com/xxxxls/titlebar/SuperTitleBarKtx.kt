package com.xxxxls.titlebar

import android.view.View

/**
 * 设置左视图点击事件
 */
fun SuperTitleBar?.setOnLeftClickListener(onClick: (View) -> Unit) {
    this?.getLeftView()?.setOnClickListener(onClick)
}

/**
 * 设置右视图点击事件
 */
fun SuperTitleBar?.setOnRightClickListener(onClick: (View) -> Unit) {
    this?.getRightView()?.setOnClickListener(onClick)
}

/**
 * 设置标题视图点击事件
 */
fun SuperTitleBar?.setOnTitleClickListener(onClick: (View) -> Unit) {
    this?.getTitleView()?.setOnClickListener(onClick)
}
