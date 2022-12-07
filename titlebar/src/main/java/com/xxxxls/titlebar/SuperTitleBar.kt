package com.xxxxls.titlebar

import android.content.Context
import android.content.res.TypedArray
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import java.util.*

/**
 * TitleBar
 * @author Max
 * @date 2020-01-03.
 */
open class SuperTitleBar : FrameLayout, View.OnLayoutChangeListener {

    companion object {

        // 适配器列表
        private val adapters: ArrayList<TitleBarAdapter> by lazy { ArrayList() }

        // 默认适配器
        private var defaultAdapter: TitleBarAdapter? = null

        /**
         * 设置默认适配器（修改后只对新视图生效）
         */
        fun setDefaultAdapter(adapter: TitleBarAdapter) {
            defaultAdapter = adapter
        }

        /**
         * 添加适配器
         */
        fun addAdapter(adapter: TitleBarAdapter) {
            adapters.add(adapter)
        }

        /**
         * 默认视图
         */
        fun getDefaultAdapterNotNull(): TitleBarAdapter {
            var adapter = defaultAdapter
            if (adapter == null) {
                adapter = DefaultTitleBarAdapter()
                defaultAdapter = adapter
            }
            return adapter
        }
    }

    // 当前适配器
    private lateinit var adapter: TitleBarAdapter

    // 左右图标宽高
    private var leftIconWidth = 0
    private var leftIconHeight = 0
    private var leftIconGravity = Gravity.START
    private var rightIconWidth = 0
    private var rightIconHeight = 0
    private var rightIconGravity = Gravity.START

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init(context, attrs, defStyleAttr)
    }

    protected open fun init(context: Context, attrs: AttributeSet?, defStyleAttr: Int) {
        val typedArray =
            getContext().obtainStyledAttributes(attrs, R.styleable.SuperTitleBar, defStyleAttr, 0)
        loadAttrs(typedArray)
        initAdapter(typedArray)
        getAdapter().onBindTitleBar(this, typedArray)
        typedArray.recycle()
        addOnLayoutChangeListener(this)
    }

    protected open fun initAdapter(typedArray: TypedArray) {
        adapter = if (typedArray.hasValue(R.styleable.SuperTitleBar_titlebar_adapter)) {
            val adapterId = typedArray.getInt(R.styleable.SuperTitleBar_titlebar_adapter, 0)
            adapters.firstOrNull {
                it.getId() == adapterId
            } ?: getDefaultAdapterNotNull()
        } else {
            getDefaultAdapterNotNull()
        }
    }

    protected open fun loadAttrs(typedArray: TypedArray) {
        leftIconWidth =
            typedArray.getDimensionPixelSize(
                R.styleable.SuperTitleBar_titlebar_left_icon_width,
                0
            )
        leftIconHeight =
            typedArray.getDimensionPixelSize(
                R.styleable.SuperTitleBar_titlebar_left_icon_height,
                0
            )
        leftIconGravity = typedArray.getInt(
            R.styleable.SuperTitleBar_titlebar_left_icon_gravity, Gravity.START
        )
        rightIconWidth =
            typedArray.getDimensionPixelSize(
                R.styleable.SuperTitleBar_titlebar_right_icon_width,
                0
            )
        rightIconHeight =
            typedArray.getDimensionPixelSize(
                R.styleable.SuperTitleBar_titlebar_right_icon_height,
                0
            )
        rightIconGravity = typedArray.getInt(
            R.styleable.SuperTitleBar_titlebar_right_icon_gravity, Gravity.START
        )
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val height = measureHeight(heightMeasureSpec)
        val heightSpan = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY)
        super.onMeasure(widthMeasureSpec, heightSpan)
    }

    protected open fun measureHeight(measureSpec: Int): Int {
        val specMode = MeasureSpec.getMode(measureSpec)
        var specSize = MeasureSpec.getSize(measureSpec)
        if (specMode != MeasureSpec.EXACTLY) {
            specSize = getAdapter().getDefaultHeight(context)
        }
        return specSize
    }

    /**
     * 获取左条目
     */
    open fun getLeftView(): TextView {
        return findViewById(R.id.titlebar_left_view)
    }

    /**
     * 获取右条目
     */
    open fun getRightView(): TextView {
        return findViewById(R.id.titlebar_right_view)
    }

    /**
     * 获取标题条目
     */
    open fun getTitleView(): TextView {
        return findViewById(R.id.titlebar_title_view)
    }

    /**
     * 获取分割线视图
     */
    open fun getLineView(): View {
        return findViewById(R.id.titlebar_line_view)
    }

    /**
     * 设置标题
     */
    open fun setTitleText(@StringRes resId: Int): SuperTitleBar {
        return setTitleText(content = context.getString(resId))
    }

    /**
     * 设置标题
     */
    open fun setTitleText(content: String?): SuperTitleBar {
        getTitleView().apply {
            text = content
            visibility = if (content.isNullOrEmpty()) View.GONE else View.VISIBLE
        }
        return this
    }

    /**
     * 设置左标题
     */
    open fun setLeftText(@StringRes resId: Int): SuperTitleBar {
        return setLeftText(content = context.getString(resId))
    }

    /**
     * 设置左标题
     */
    open fun setLeftText(content: String?): SuperTitleBar {
        getLeftView().text = content
        return this
    }

    /**
     * 设置左图标
     */
    open fun setLeftIcon(drawable: Drawable?, gravity: Int = leftIconGravity): SuperTitleBar {
        SuperTitleBarTool.setDrawableSize(drawable, leftIconWidth, leftIconHeight)
        SuperTitleBarTool.setTextCompoundDrawable(getLeftView(), drawable, gravity)
        return this
    }

    /**
     * 设置左图标
     */
    open fun setLeftIcon(
        @DrawableRes resId: Int,
        gravity: Int = leftIconGravity
    ): SuperTitleBar {
        return setLeftIcon(context.getDrawableById(resId), gravity)
    }

    /**
     * 设置左背景
     */
    open fun setLeftBackground(drawable: Drawable?): SuperTitleBar {
        getLeftView().background = drawable
        return this
    }

    /**
     * 设置右标题
     */
    open fun setRightText(@StringRes resId: Int): SuperTitleBar {
        return setRightText(content = context.getString(resId))
    }

    /**
     * 设置右标题
     */
    open fun setRightText(content: String?): SuperTitleBar {
        getRightView().text = content
        return this
    }

    /**
     * 设置右图标
     */
    open fun setRightIcon(drawable: Drawable?, gravity: Int = rightIconGravity): SuperTitleBar {
        SuperTitleBarTool.setDrawableSize(drawable, leftIconWidth, leftIconHeight)
        SuperTitleBarTool.setTextCompoundDrawable(getRightView(), drawable, gravity)
        return this
    }

    /**
     * 设置右图标
     */
    open fun setRightIcon(
        @DrawableRes resId: Int,
        gravity: Int = rightIconGravity
    ): SuperTitleBar {
        return setRightIcon(context.getDrawableById(resId), gravity)
    }

    /**
     * 设置右背景
     */
    open fun setRightBackground(drawable: Drawable?): SuperTitleBar {
        getRightView().background = drawable
        return this
    }

    /**
     * 当前适配器
     */
    internal open fun getAdapter(): TitleBarAdapter {
        return adapter
    }

    override fun onLayoutChange(
        v: View?,
        left: Int,
        top: Int,
        right: Int,
        bottom: Int,
        oldLeft: Int,
        oldTop: Int,
        oldRight: Int,
        oldBottom: Int
    ) {
        removeOnLayoutChangeListener(this)
        val titleBarWidth = right - left
        val sideWidth = getLeftView().width.coerceAtLeast(getRightView().width)
        SuperTitleBarTool.setMaxWidth(getTitleView(), titleBarWidth - sideWidth * 2)
        checkEnable()
        post {
            addOnLayoutChangeListener(this)
        }
    }

    protected open fun checkEnable() {
        SuperTitleBarTool.checkEnableIfNotEmpty(getLeftView())
        SuperTitleBarTool.checkEnableIfNotEmpty(getTitleView())
        SuperTitleBarTool.checkEnableIfNotEmpty(getRightView())
    }
}