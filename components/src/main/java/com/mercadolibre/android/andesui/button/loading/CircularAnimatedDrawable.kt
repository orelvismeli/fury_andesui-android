package com.mercadolibre.android.andesui.button.loading

import android.animation.Animator
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.graphics.*
import android.graphics.drawable.Animatable
import android.graphics.drawable.Drawable
import android.util.Property
import android.view.animation.DecelerateInterpolator
import android.view.animation.Interpolator
import android.view.animation.LinearInterpolator

internal class CircularAnimatedDrawable(color: Int, private val mBorderWidth: Float) : Drawable(), Animatable {

    private val ANGLE_INTERPOLATOR: Interpolator = LinearInterpolator()
    private val SWEEP_INTERPOLATOR: Interpolator = DecelerateInterpolator()
    private val ANGLE_ANIMATOR_DURATION = 1000
    private val SWEEP_ANIMATOR_DURATION = 1000
    private val MIN_SWEEP_ANGLE = 30

    private val fBounds = RectF()
    private lateinit var mObjectAnimatorSweep: ObjectAnimator
    private lateinit var mObjectAnimatorAngle: ObjectAnimator
    private var mModeAppearing = false
    private val mPaint: Paint = Paint()
    private var mCurrentGlobalAngleOffset = 0f
    private var mCurrentGlobalAngle = 0f
    private var mCurrentSweepAngle = 0f
    private var mRunning = false
    private var mAngle = 360

    override fun draw(canvas: Canvas) {
        var startAngle = mCurrentGlobalAngle - mCurrentGlobalAngleOffset
        var sweepAngle = mCurrentSweepAngle
        if (!mModeAppearing) {
            startAngle += sweepAngle
            sweepAngle = mAngle - sweepAngle - MIN_SWEEP_ANGLE
        } else {
            sweepAngle += MIN_SWEEP_ANGLE.toFloat()
        }
        canvas.drawArc(fBounds, startAngle, sweepAngle, false, mPaint)
    }

    override fun setAlpha(alpha: Int) {
        mPaint.alpha = alpha
    }

    override fun setColorFilter(cf: ColorFilter) {
        mPaint.colorFilter = cf
    }

    override fun getOpacity(): Int {
        return PixelFormat.TRANSPARENT
    }

    private fun toggleAppearingMode() {
        mModeAppearing = !mModeAppearing
        if (mModeAppearing) {
            mCurrentGlobalAngleOffset = (mCurrentGlobalAngleOffset + MIN_SWEEP_ANGLE * 2) % mAngle
        }
    }

    override fun onBoundsChange(bounds: Rect) {
        super.onBoundsChange(bounds)
        fBounds.left = bounds.left + mBorderWidth / 2f + .5f
        fBounds.right = bounds.right - mBorderWidth / 2f - .5f
        fBounds.top = bounds.top + mBorderWidth / 2f + .5f
        fBounds.bottom = bounds.bottom - mBorderWidth / 2f - .5f
    }

    private val mAngleProperty: Property<CircularAnimatedDrawable, Float> = object : Property<CircularAnimatedDrawable, Float>(Float::class.java, null) {
        override fun get(`object`: CircularAnimatedDrawable): Float {
            return `object`.currentGlobalAngle
        }

        override fun set(`object`: CircularAnimatedDrawable, value: Float) {
            `object`.currentGlobalAngle = value
        }
    }
    private val mSweepProperty: Property<CircularAnimatedDrawable, Float> = object : Property<CircularAnimatedDrawable, Float>(Float::class.java, null) {
        override fun get(`object`: CircularAnimatedDrawable): Float {
            return `object`.currentSweepAngle
        }

        override fun set(`object`: CircularAnimatedDrawable, value: Float) {
            `object`.currentSweepAngle = value
        }
    }

    private fun setupAnimations() {
        mObjectAnimatorAngle = ObjectAnimator.ofFloat(this, mAngleProperty, mAngle.toFloat())
        mObjectAnimatorAngle.interpolator = ANGLE_INTERPOLATOR
        mObjectAnimatorAngle.duration = ANGLE_ANIMATOR_DURATION.toLong()
        mObjectAnimatorAngle.repeatMode = ValueAnimator.RESTART
        mObjectAnimatorAngle.repeatCount = ValueAnimator.INFINITE
        mObjectAnimatorSweep = ObjectAnimator.ofFloat(this, mSweepProperty, mAngle.toFloat() - MIN_SWEEP_ANGLE * 2)
        mObjectAnimatorSweep.interpolator = SWEEP_INTERPOLATOR
        mObjectAnimatorSweep.duration = SWEEP_ANIMATOR_DURATION.toLong()
        mObjectAnimatorSweep.repeatMode = ValueAnimator.RESTART
        mObjectAnimatorSweep.repeatCount = ValueAnimator.INFINITE
        mObjectAnimatorSweep.addListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator) {}
            override fun onAnimationEnd(animation: Animator) {}
            override fun onAnimationCancel(animation: Animator) {}
            override fun onAnimationRepeat(animation: Animator) {
                toggleAppearingMode()
            }
        })
    }

    override fun start() {
        if (isRunning) {
            return
        }
        mRunning = true
        mObjectAnimatorAngle!!.start()
        mObjectAnimatorSweep!!.start()
        invalidateSelf()
    }

    override fun stop() {
        if (!isRunning) {
            return
        }
        mRunning = false
        mObjectAnimatorAngle!!.cancel()
        mObjectAnimatorSweep!!.cancel()
        invalidateSelf()
    }

    override fun isRunning(): Boolean {
        return mRunning
    }

    var currentGlobalAngle: Float
        get() = mCurrentGlobalAngle
        set(currentGlobalAngle) {
            mCurrentGlobalAngle = currentGlobalAngle
            invalidateSelf()
        }

    var currentSweepAngle: Float
        get() = mCurrentSweepAngle
        set(currentSweepAngle) {
            mCurrentSweepAngle = currentSweepAngle
            invalidateSelf()
        }

    init {
        mPaint.isAntiAlias = true
        mPaint.style = Paint.Style.STROKE
        mPaint.strokeWidth = mBorderWidth
        mPaint.color = color
        setupAnimations()
    }
}