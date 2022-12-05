package com.xxxxls.titlebar

import android.content.Context
import android.content.res.TypedArray
import android.view.View
import android.widget.TextView

/**
 * Created by Max on 2022/8/22 19:54
 * Desc: 标准风格适配器
 **/
abstract class StandardTitleBarAdapter : TitleBarAdapter {

    override fun onBindTitleBar(titleBar: SuperTitleBar, typedArray: TypedArray) {
        val leftView = createLeftView(titleBar.context, typedArray)
        val rightView = createRightView(titleBar.context, typedArray)
        val titleView = createTitleView(titleBar.context, typedArray)
        val lineView = createLineView(titleBar.context, typedArray)
        titleBar.addView(leftView, leftView.layoutParams)
        titleBar.addView(rightView, rightView.layoutParams)
        titleBar.addView(titleView, titleView.layoutParams)
        titleBar.addView(lineView, lineView.layoutParams)
    }

    /**
     * 创建标题视图
     */
    protected open fun createTitleView(context: Context, typedArray: TypedArray): TextView {
        val view = onCreateTitleView(context)
        view.id = R.id.titlebar_title_view
        onBindTitleView(view, typedArray)
        return view
    }

    protected abstract fun onCreateTitleView(context: Context): TextView

    protected abstract fun onBindTitleView(view: TextView, typedArray: TypedArray)

    /**
     * 创建左视图
     */
    protected open fun createLeftView(context: Context, typedArray: TypedArray): TextView {
        val view = onCreateLeftView(context)
        view.id = R.id.titlebar_left_view
        onBindLeftView(view, typedArray)
        return view
    }

    protected abstract fun onCreateLeftView(context: Context): TextView

    protected abstract fun onBindLeftView(view: TextView, typedArray: TypedArray)

    /**
     * 创建右视图
     */
    protected open fun createRightView(context: Context, array: TypedArray): TextView {
        val view = onCreateRightView(context)
        view.id = R.id.titlebar_right_view
        onBindRightView(view, array)
        return view
    }

    protected abstract fun onCreateRightView(context: Context): TextView

    protected abstract fun onBindRightView(view: TextView, array: TypedArray)

    /**
     * 创建分割线视图
     */
    protected open fun createLineView(context: Context, array: TypedArray): View {
        val view = onCreateLineView(context)
        view.id = R.id.titlebar_line_view
        onBindLineView(view, array)
        return view
    }

    protected abstract fun onCreateLineView(context: Context): View

    protected abstract fun onBindLineView(view: View, array: TypedArray)
}