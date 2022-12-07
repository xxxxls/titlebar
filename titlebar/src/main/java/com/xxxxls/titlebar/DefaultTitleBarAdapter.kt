package com.xxxxls.titlebar

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.text.TextUtils
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import androidx.core.widget.TextViewCompat

/**
 * 默认标题栏适配器
 * @author Max
 * @date 2020-01-03.
 */
open class DefaultTitleBarAdapter : StandardTitleBarAdapter() {

    override fun getId(): Int {
        return 0
    }

    override fun onCreateTitleView(context: Context): TextView {
        return TextView(context)
    }

    override fun onBindTitleView(view: TextView, typedArray: TypedArray) {
        val text = typedArray.getString(R.styleable.SuperTitleBar_titlebar_title)
        // 外观样式
        val textAppendable = SuperTitleBarTool.getTextAppendable(
            typedArray,
            R.styleable.SuperTitleBar_titlebar_title_text_appearance,
        )
        // 水平方向内边距
        val horizontalPadding = typedArray.getDimensionPixelSize(
            R.styleable.SuperTitleBar_titlebar_title_horizontal_padding, 0
        )
        val viewLayoutParams = FrameLayout.LayoutParams(
            FrameLayout.LayoutParams.WRAP_CONTENT,
            FrameLayout.LayoutParams.WRAP_CONTENT
        ).apply {
            gravity = Gravity.CENTER
        }
        view.apply {
            id = R.id.titlebar_title_view
            gravity = Gravity.CENTER
            isSingleLine = true
            ellipsize = TextUtils.TruncateAt.END
            setPadding(horizontalPadding, 0, horizontalPadding, 0)
            setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize)
            setText(text)
            if (textAppendable != null) {
                TextViewCompat.setTextAppearance(this, textAppendable)
            }
            layoutParams = viewLayoutParams
        }
    }

    override fun onCreateLeftView(context: Context): TextView {
        return TextView(context)
    }

    override fun onBindLeftView(view: TextView, typedArray: TypedArray) {
        // 文本
        val text = typedArray.getString(R.styleable.SuperTitleBar_titlebar_left_text)
        // 图标
        val icon = typedArray.getDrawable(R.styleable.SuperTitleBar_titlebar_left_icon)
        // 外观样式
        val textAppendable = SuperTitleBarTool.getTextAppendable(
            typedArray,
            R.styleable.SuperTitleBar_titlebar_left_text_appearance,
        )
        // 文本与图标间距
        val iconPadding = typedArray.getDimension(
            R.styleable.SuperTitleBar_titlebar_left_icon_padding,
            view.context.dip2px(2f)
        )
        // 背景
        val background = typedArray.getDrawable(R.styleable.SuperTitleBar_titlebar_left_background)
        // 图标宽度
        val iconWidth = typedArray.getDimensionPixelSize(
            R.styleable.SuperTitleBar_titlebar_left_icon_width, 0
        )
        // 图标高度
        val iconHeight = typedArray.getDimensionPixelSize(
            R.styleable.SuperTitleBar_titlebar_left_icon_height, 0
        )
        // 图标方向
        val iconGravity = typedArray.getInt(
            R.styleable.SuperTitleBar_titlebar_left_icon_gravity, Gravity.START
        )
        // 水平方向内边距
        val horizontalPadding = typedArray.getDimensionPixelSize(
            R.styleable.SuperTitleBar_titlebar_left_horizontal_padding, 0
        )
        val viewLayoutParams = FrameLayout.LayoutParams(
            FrameLayout.LayoutParams.WRAP_CONTENT,
            FrameLayout.LayoutParams.MATCH_PARENT
        ).apply {
            gravity = SuperTitleBarTool.getGravityLeft(isAdaptRTL()) or Gravity.CENTER_VERTICAL
        }
        view.apply {
            isSingleLine = true
            gravity = Gravity.CENTER
            ellipsize = TextUtils.TruncateAt.END
            compoundDrawablePadding = iconPadding.toInt()
            setPadding(horizontalPadding, 0, horizontalPadding, 0)
            setText(text)
            SuperTitleBarTool.setDrawableSize(icon, iconWidth, iconHeight)
            SuperTitleBarTool.setTextCompoundDrawable(this, icon, iconGravity)
            if (background != null) {
                setBackground(background)
            }
            if (textAppendable != null) {
                TextViewCompat.setTextAppearance(this, textAppendable)
            }
            layoutParams = viewLayoutParams
        }
    }

    override fun onCreateRightView(context: Context): TextView {
        return TextView(context)
    }

    override fun onBindRightView(view: TextView, typedArray: TypedArray) {
        // 文本
        val text = typedArray.getString(R.styleable.SuperTitleBar_titlebar_right_text)
        // 图标
        val icon = typedArray.getDrawable(R.styleable.SuperTitleBar_titlebar_right_icon)
        // 外观样式
        val textAppendable = SuperTitleBarTool.getTextAppendable(
            typedArray,
            R.styleable.SuperTitleBar_titlebar_right_text_appearance,
        )
        // 文本与图标间距
        val iconPadding = typedArray.getDimension(
            R.styleable.SuperTitleBar_titlebar_right_icon_padding,
            view.context.dip2px(2f)
        )
        // 背景
        val background = typedArray.getDrawable(R.styleable.SuperTitleBar_titlebar_right_background)
        // 图标宽度
        val iconWidth = typedArray.getDimensionPixelSize(
            R.styleable.SuperTitleBar_titlebar_right_icon_width, 0
        )
        // 图标高度
        val iconHeight = typedArray.getDimensionPixelSize(
            R.styleable.SuperTitleBar_titlebar_right_icon_height, 0
        )
        // 图标方向
        val iconGravity = typedArray.getInt(
            R.styleable.SuperTitleBar_titlebar_right_icon_gravity, Gravity.START
        )
        // 水平方向内边距
        val horizontalPadding = typedArray.getDimensionPixelSize(
            R.styleable.SuperTitleBar_titlebar_right_horizontal_padding, 0
        )
        val viewLayoutParams = FrameLayout.LayoutParams(
            FrameLayout.LayoutParams.WRAP_CONTENT,
            FrameLayout.LayoutParams.MATCH_PARENT
        ).apply {
            gravity = SuperTitleBarTool.getGravityRight(isAdaptRTL()) or Gravity.CENTER_VERTICAL
        }
        view.apply {
            isSingleLine = true
            gravity = Gravity.CENTER
            ellipsize = TextUtils.TruncateAt.END
            compoundDrawablePadding = iconPadding.toInt()
            setPadding(horizontalPadding, 0, horizontalPadding, 0)
            setText(text)
            SuperTitleBarTool.setDrawableSize(icon, iconWidth, iconHeight)
            SuperTitleBarTool.setTextCompoundDrawable(this, icon, iconGravity)
            if (background != null) {
                setBackground(background)
            }
            if (textAppendable != null) {
                TextViewCompat.setTextAppearance(this, textAppendable)
            }
            layoutParams = viewLayoutParams
        }
    }

    override fun onCreateLineView(context: Context): View {
        return View(context)
    }

    override fun onBindLineView(view: View, array: TypedArray) {
        val visible = array.getBoolean(R.styleable.SuperTitleBar_titlebar_line_visible, true)
        val drawable = array.getDrawable(R.styleable.SuperTitleBar_titlebar_line_background)
            ?: ColorDrawable(Color.GRAY)
        val height = array.getDimensionPixelOffset(
            R.styleable.SuperTitleBar_titlebar_line_height, 2
        )
        val viewLayoutParams = FrameLayout.LayoutParams(
            FrameLayout.LayoutParams.MATCH_PARENT,
            height
        ).apply {
            gravity = Gravity.BOTTOM
        }
        view.apply {
            background = drawable
            visibility = if (visible) View.VISIBLE else View.GONE
            layoutParams = viewLayoutParams
        }
    }

    override fun getDefaultHeight(context: Context): Int {
        return context.dip2px(48f).toInt()
    }
}