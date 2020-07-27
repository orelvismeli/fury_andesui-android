package com.mercadolibre.android.andesui.badge

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.mercadolibre.android.andesui.R
import com.mercadolibre.android.andesui.badge.factory.AndesBadgeDotAttrs
import com.mercadolibre.android.andesui.badge.factory.AndesBadgeDotAttrsParser
import com.mercadolibre.android.andesui.badge.factory.AndesBadgeDotConfiguration
import com.mercadolibre.android.andesui.badge.factory.AndesBadgeDotConfigurationFactory
import com.mercadolibre.android.andesui.badge.type.AndesBadgeType

class AndesBadgeDot : FrameLayout {

    /**
     * Getter and setter for [type].
     */
    var type: AndesBadgeType
        get() = andesBadgeAttrs.andesBadgeType
        set(value) {
            andesBadgeAttrs = andesBadgeAttrs.copy(andesBadgeType = value)
            setupColorComponents(createConfig())
        }

    private lateinit var andesBadgeAttrs: AndesBadgeDotAttrs

    @Suppress("unused")
    private constructor(context: Context) : super(context) {
        throw IllegalStateException(
                "Constructor without parameters in Andes Badge is not allowed. You must provide some attributes."
        )
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initAttrs(attrs)
    }

    @Suppress("unused")
    constructor(context: Context, type: AndesBadgeType = STATE_DEFAULT) : super(context) {
        initAttrs(type)
    }

    /**
     * Sets the proper [config] for this message based on the [attrs] received via XML.
     *
     * @param attrs attributes from the XML.
     */
    private fun initAttrs(attrs: AttributeSet?) {
        andesBadgeAttrs = AndesBadgeDotAttrsParser.parse(context, attrs)
        val config = AndesBadgeDotConfigurationFactory.create(context, andesBadgeAttrs)
        setupComponents(config)
    }

    private fun initAttrs(type: AndesBadgeType) {
        andesBadgeAttrs = AndesBadgeDotAttrs(type)
        val config = AndesBadgeDotConfigurationFactory.create(context, andesBadgeAttrs)
        setupComponents(config)
    }

    /**
     * Responsible for setting up all properties of each component that is part of this badge.
     * Is like a choreographer ;)
     */
    private fun setupComponents(config: AndesBadgeDotConfiguration) {
        initComponents()
        setupViewId()

        setupColorComponents(config)
    }

    /**
     * Creates all the views that are part of this badge.
     * After a view is created then a view id is added to it.
     */
    private fun initComponents() {
        LayoutInflater.from(context).inflate(R.layout.andes_layout_badge_dot, this)
    }

    /**
     * Sets a view id to this badge.
     */
    private fun setupViewId() {
        if (id == NO_ID) { // If this view has no id
            id = View.generateViewId()
        }
    }

    private fun setupColorComponents(config: AndesBadgeDotConfiguration) {
        val shape = GradientDrawable()
        (shape.mutate() as GradientDrawable).cornerRadius = config.size
        shape.setColor(config.backgroundColor.colorInt(context))

        background = shape

        if (layoutParams == null) {
            layoutParams = ViewGroup.LayoutParams(config.size.toInt(), config.size.toInt())
        }
    }

    private fun createConfig() = AndesBadgeDotConfigurationFactory.create(context, andesBadgeAttrs)

    companion object {
        private val STATE_DEFAULT = AndesBadgeType.NEUTRAL
    }
}
