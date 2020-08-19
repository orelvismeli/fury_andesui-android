package com.mercadolibre.android.andesui.progress

import android.R.attr.mode
import android.content.Context
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.os.Build
import android.support.constraint.ConstraintLayout
import android.support.constraint.ConstraintSet
import android.util.AttributeSet
import android.view.View
import android.widget.ProgressBar
import com.mercadolibre.android.andesui.progress.factory.AndesProgressAttrs
import com.mercadolibre.android.andesui.progress.factory.AndesProgressAttrsParser
import com.mercadolibre.android.andesui.progress.factory.AndesProgressConfiguration
import com.mercadolibre.android.andesui.progress.factory.AndesProgressConfigurationFactory
import com.mercadolibre.android.andesui.progress.size.AndesProgressSize


class AndesProgressIndicatorIndeterminate: ConstraintLayout {

    private lateinit var andesProgressAttr: AndesProgressAttrs
    internal lateinit var progressComponent: ProgressBar

    var tint: Int
        get() = andesProgressAttr.tint
        set(value) {
            andesProgressAttr = andesProgressAttr.copy(tint = value)
            createConfig().also {
                setupColor(it)
            }
        }

    var size: AndesProgressSize
        get() = andesProgressAttr.andesProgressSize
        set(value) {
            andesProgressAttr = andesProgressAttr.copy(andesProgressSize = value)
            createConfig().also {
                setupSize(it)
            }
        }

    /**
     * Simplest constructor for creating an AndesProgress programmatically.
     */
    constructor(context: Context) : super(context){
        initAttrs(SIZE_DEFAULT, TINT)
    }


    /**
     * Constructor for creating an AndesProgress via XML.
     * The [attrs] are the attributes specified in the parameters of XML.
     *
     */
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initAttrs(attrs)
    }

    /**
     * Constructor for creating an AndesProgress via XML.
     * The [attrs] are the attributes specified in the parameters of XML.
     * The [defStyleAttr] is not considered because we take care of all Andes styling for you.
     */
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : this(context, attrs)

    private fun initAttrs(attrs: AttributeSet?) {
        andesProgressAttr = AndesProgressAttrsParser.parse(context, attrs)
        setupComponents(createConfig())
    }

    private fun initAttrs(
            progressSize: AndesProgressSize,
            tint: Int
    ) {
        andesProgressAttr = AndesProgressAttrs(progressSize, tint)
        setupComponents(createConfig())
    }

    private fun initComponents() {
        progressComponent = ProgressBar(context)
    }

    private fun setupComponents(config: AndesProgressConfiguration) {
        initComponents()

        setupViewId()
        setupProgressComponent()
        setupColor(config)
        setupSize(config)

        addView(progressComponent)

        setupConstraints()
    }

    private fun createConfig() = AndesProgressConfigurationFactory.create(context, andesProgressAttr)

    private fun setupConstraints() {
        val set = ConstraintSet()
        set.clone(this)

        set.centerHorizontally(progressComponent.id, ConstraintSet.PARENT_ID)
        set.centerVertically(progressComponent.id, ConstraintSet.PARENT_ID)

        set.applyTo(this)
    }

    private fun setupViewId() {
        if (id == NO_ID) {
            id = View.generateViewId()
        }
    }

    private fun setupProgressComponent() {
        progressComponent.id = View.generateViewId()
        progressComponent.isIndeterminate = true
    }

    private fun setupColor(config: AndesProgressConfiguration) {

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {

            val progressDrawable: Drawable = (if (progressComponent.isIndeterminate)
                progressComponent.indeterminateDrawable
            else
                progressComponent.progressDrawable).mutate()

            progressDrawable.setColorFilter(config.tint, PorterDuff.Mode.SRC_ATOP)
            progressComponent.progressDrawable = progressDrawable
        } else {
            progressComponent.indeterminateDrawable.setColorFilter(config.tint, PorterDuff.Mode.SRC_ATOP)
        }
    }

    private fun setupSize(config: AndesProgressConfiguration) {
        val params = LayoutParams(config.size.toInt(), config.size.toInt())
        progressComponent.layoutParams = params
    }

    companion object {
        private const val TINT = 0
        private val SIZE_DEFAULT = AndesProgressSize.SMALL
    }
}