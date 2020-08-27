package com.mercadolibre.android.andesui.progress

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.support.constraint.ConstraintSet
import android.util.AttributeSet
import android.view.View
import com.mercadolibre.android.andesui.progress.factory.AndesProgressAttrs
import com.mercadolibre.android.andesui.progress.factory.AndesProgressAttrsParser
import com.mercadolibre.android.andesui.progress.factory.AndesProgressConfiguration
import com.mercadolibre.android.andesui.progress.factory.AndesProgressConfigurationFactory
import com.mercadolibre.android.andesui.progress.size.AndesProgressSize


class AndesProgressIndicatorIndeterminate: ConstraintLayout {

    private lateinit var andesProgressAttr: AndesProgressAttrs
    internal lateinit var progressComponent: LoadingSpinner

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
        andesProgressAttr = AndesProgressAttrs(progressSize, tint, false)
        setupComponents(createConfig())
    }

    private fun initComponents(config: AndesProgressConfiguration) {
        progressComponent = LoadingSpinner(context)
        if (config.start) start()
    }

    private fun setupComponents(config: AndesProgressConfiguration) {
        initComponents(config)

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

    /**
     * Sets a view id to this Progress.
     *
     */
    private fun setupViewId() {
        if (id == NO_ID) {
            id = View.generateViewId()
        }
    }

    private fun setupProgressComponent() {
        progressComponent.id = View.generateViewId()
    }

    private fun setupColor(config: AndesProgressConfiguration) {
        progressComponent.setStrokeSize(config.stroke)
        progressComponent.setPrimaryColor(config.tint)
    }

    private fun setupSize(config: AndesProgressConfiguration) {
        val params = LayoutParams(config.size.toInt(), config.size.toInt())
        progressComponent.layoutParams = params
    }

    fun start(){
        progressComponent.start()
    }

    companion object {
        private const val TINT = 0
        private val SIZE_DEFAULT = AndesProgressSize.SMALL
    }
}