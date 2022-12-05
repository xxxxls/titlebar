package com.xxxxls.titlebar

import android.content.res.TypedArray
import android.graphics.drawable.Drawable
import android.text.TextUtils
import android.view.Gravity
import android.widget.TextView

/**
 * Created by Max on 2022/8/22 18:15
 * Desc:工具
 **/
object SuperTitleBarTool {

    fun setMaxWidth(view: TextView, maxWidth: Int) {
        if (view.maxWidth == maxWidth) {
            return
        }
        view.maxWidth = maxWidth
    }

    /**
     * 非空时启动
     */
    fun checkEnableIfNotEmpty(textView: TextView) {
        textView.isEnabled = isNotEmptyContent(textView)
    }

    /**
     * TextView 是否空内容
     */
    fun isNotEmptyContent(textView: TextView): Boolean {
        val text = textView.text
        if (!TextUtils.isEmpty(text)) {
            return true
        }
        val drawables = textView.compoundDrawables
        for (drawable in drawables) {
            if (drawable != null) {
                return true
            }
        }
        return false
    }

    /**
     * 根据给定的大小限制 Drawable 宽高
     */
    fun setDrawableSize(drawable: Drawable?, width: Int, height: Int) {
        if (drawable == null) {
            return
        }
        if (width <= 0 && height <= 0) {
            drawable.setBounds(0, 0, drawable.intrinsicWidth, drawable.intrinsicHeight)
            return
        }
        if (width > 0 && height > 0) {
            drawable.setBounds(0, 0, width, height)
            return
        }
        var drawableWidth = drawable.intrinsicWidth
        var drawableHeight = drawable.intrinsicHeight
        if (drawableWidth <= 0) {
            drawableWidth = width
        }
        if (drawableHeight <= 0) {
            drawableHeight = height
        }

        // 将 Drawable 等比缩放
        if (width > 0) {
            drawable.setBounds(0, 0, width, width * drawableHeight / drawableWidth)
        } else {
            drawable.setBounds(0, 0, drawableWidth * height / drawableHeight, height)
        }
    }

    /**
     * 根据图标重心设置 TextView 某个位置的 Drawable
     */
    fun setTextCompoundDrawable(textView: TextView, drawable: Drawable?, gravity: Int) {
        when (gravity) {
            Gravity.START -> {
                if (textView.isRtl()) {
                    textView.setCompoundDrawables(null, null, drawable, null)
                } else {
                    textView.setCompoundDrawables(drawable, null, null, null)
                }
            }
            Gravity.END -> {
                if (textView.isRtl()) {
                    textView.setCompoundDrawables(drawable, null, null, null)
                } else {
                    textView.setCompoundDrawables(null, null, drawable, null)
                }
            }
            Gravity.LEFT -> textView.setCompoundDrawables(drawable, null, null, null)
            Gravity.TOP -> textView.setCompoundDrawables(null, drawable, null, null)
            Gravity.RIGHT -> textView.setCompoundDrawables(null, null, drawable, null)
            Gravity.BOTTOM -> textView.setCompoundDrawables(null, null, null, drawable)
            else -> textView.setCompoundDrawables(null, null, null, null)
        }
    }

    /**
     * 获取TextAppendable
     */
    fun getTextAppendable(typedArray: TypedArray, index: Int): Int? {
        return if (typedArray.hasValue(index)) {
            typedArray.getResourceId(index, android.R.style.TextAppearance)
        } else {
            null
        }
    }

    /**
     * 是否使用多语言（RTL）
     */
    fun getGravityLeft(isAdaptRTL: Boolean): Int {
        return if (isAdaptRTL) {
            Gravity.START
        } else {
            Gravity.LEFT
        }
    }

    /**
     * 是否使用多语言（RTL）
     */
    fun getGravityRight(isAdaptRTL: Boolean): Int {
        return if (isAdaptRTL) {
            Gravity.END
        } else {
            Gravity.RIGHT
        }
    }
}