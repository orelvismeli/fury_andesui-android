package com.mercadolibre.android.andesui.button.loading

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.View

class LoadingView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int? = 0) : View(context, attrs, defStyleAttr!!) {

    private lateinit var mAnimatedDrawable: CircularAnimatedDrawable
    private val mStrokeWidth = 8
    private var mParentHeight = 0
    private var mLoadingColor = 0

    var isLoading: Boolean = false

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        if (canvas != null && isLoading)
            drawIndeterminateProgress(canvas)
    }

    fun drawIndeterminateProgress(canvas: Canvas) {
        if (!this::mAnimatedDrawable.isInitialized) {
            val offset = (width - height) / 2
            val right = width - offset
            mAnimatedDrawable = CircularAnimatedDrawable(mLoadingColor, mStrokeWidth.toFloat())
            mAnimatedDrawable.setBounds(offset, 0, right, height)
            mAnimatedDrawable.callback = this
            mAnimatedDrawable.start()
        } else {
            mAnimatedDrawable.draw(canvas)
        }
        invalidate()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        var size = mParentHeight / 2
        setMeasuredDimension(size, size)
    }

    fun setLoadingColor(colorStateList: ColorStateList) {
        mLoadingColor = colorStateList.getColorForState(drawableState, 0)
    }

    fun setParentHeight(height: Float) {
        mParentHeight = height.toInt()
    }
}