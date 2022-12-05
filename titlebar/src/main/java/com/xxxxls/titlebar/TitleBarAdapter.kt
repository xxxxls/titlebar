package com.xxxxls.titlebar

import android.content.Context
import android.content.res.TypedArray
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.TextView

/**
 * titlebar 样式
 * @author Max
 * @date 2020-01-03.
 */
interface TitleBarAdapter {
    /**
     * 适配器ID
     */
    fun getId(): Int

    /**
     * 初始化绑定视图
     */
    fun onBindTitleBar(titleBar: SuperTitleBar, array: TypedArray)

    /**
     * 标题栏高度（默认）
     */
    fun getDefaultHeight(context: Context): Int

    /**
     * 是否适配系统语言（布局方向）
     */
    fun isAdaptRTL(): Boolean = true
}