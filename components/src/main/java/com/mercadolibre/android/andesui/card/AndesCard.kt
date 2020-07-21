package com.mercadolibre.android.andesui.card

import android.content.Context
import android.support.v7.widget.CardView
import android.util.AttributeSet
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import com.mercadolibre.android.andesui.R
import com.mercadolibre.android.andesui.card.factory.AndesCardAttrParser
import com.mercadolibre.android.andesui.card.factory.AndesCardAttrs
import com.mercadolibre.android.andesui.card.factory.AndesCardConfiguration
import com.mercadolibre.android.andesui.card.factory.AndesCardConfigurationFactory
import com.mercadolibre.android.andesui.card.hierarchy.AndesCardHierarchy
import com.mercadolibre.android.andesui.card.padding.AndesCardPadding
import com.mercadolibre.android.andesui.card.style.AndesCardStyle
import com.mercadolibre.android.andesui.card.type.AndesCardType
import kotlinx.android.synthetic.main.andes_layout_card.view.*


class AndesCard : CardView {

    /**
     * Getter and setter for [hierarchy].
     */
    var hierarchy: AndesCardHierarchy
        get() = andesCardAttrs.andesCardHierarchy
        set(value) {
            andesCardAttrs = andesCardAttrs.copy(andesCardHierarchy = value)
            val config = createConfig()
            //TODO AndesCard: Update UI
        }

    /**
     * Getter and setter for [padding].
     */
    var padding: AndesCardPadding
        get() = andesCardAttrs.andesCardPadding
        set(value) {
            andesCardAttrs = andesCardAttrs.copy(andesCardPadding = value)
            val config = createConfig()
            //TODO AndesCard: Update UI
        }

    /**
     * Getter and setter for [title].
     */
    var title: String?
        get() = andesCardAttrs.andesCardTitle
        set(value) {
            andesCardAttrs = andesCardAttrs.copy(andesCardTitle = value)
            val config = createConfig()
            setupTitleComponent(config)
        }

    /**
     * Getter and setter for [cardView].
     */
    var cardView: View?
        get() = andesCardAttrs.andesCardView
        set(value) {
            andesCardAttrs = andesCardAttrs.copy(andesCardView = value)
            val config = createConfig()
            //TODO AndesCard: Update UI
        }

    /**
     * Getter and setter for [type].
     */
    var type: AndesCardType
        get() = andesCardAttrs.andesCardType
        set(value) {
            andesCardAttrs = andesCardAttrs.copy(andesCardType = value)
            val config = createConfig()
            //TODO AndesCard: Update UI
        }

    private lateinit var andesCardAttrs: AndesCardAttrs

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initAttrs(attrs)
    }

    constructor(
            context: Context,
            view: View,
            type: AndesCardType = TYPE_DEFAULT,
            padding: AndesCardPadding = PADDING_DEFAULT,
            title: String? = TITLE_DEFAULT,
            style: AndesCardStyle = STYLE_DEFAULT,
            hierarchy: AndesCardHierarchy = HIERARCHY_DEFAULT
    ) : super(context) {
        initAttrs(view, type, padding, style, title, hierarchy)
    }

    /**
     * Sets the proper [config] for this component based on the [attrs] received via XML.
     *
     * @param attrs attributes from the XML.
     */
    private fun initAttrs(attrs: AttributeSet?) {
        andesCardAttrs = AndesCardAttrParser.parse(context, attrs)
        val config = AndesCardConfigurationFactory.create(context, andesCardAttrs)
        setupComponents(config)
    }

    private fun initAttrs(
            view: View,
            type: AndesCardType,
            padding: AndesCardPadding,
            style: AndesCardStyle,
            title: String?,
            hierarchy: AndesCardHierarchy
    ) {
        andesCardAttrs = AndesCardAttrs(view, type, padding, style, title, hierarchy)
        val config = AndesCardConfigurationFactory.create(context, andesCardAttrs)
        setupComponents(config)
    }

    /**
     * Responsible for setting up all properties of each component that is part of this andesCard.
     * Is like a choreographer 😉
     */
    private fun setupComponents(config: AndesCardConfiguration) {
        radius = context.resources.getDimension(R.dimen.andes_message_corner_radius)
        cardElevation = 6f
        preventCornerOverlap = true

        initComponents()
        setupViewId()

        setupMarginsComponent(config)
        setupTitleComponent(config)
    }

    /**
     * Creates all the views that are part of this andesCard.
     * After a view is created then a view id is added to it.
     */
    private fun initComponents() {
        val container = LayoutInflater.from(context).inflate(R.layout.andes_layout_card, this)
    }

    /**
     * Sets a view id to this andesCard.
     */
    private fun setupViewId() {
        if (id == NO_ID) { // If this view has no id
            id = View.generateViewId()
        }
    }

    /**
     * Gets data from the config and sets to the title component of this card.
     */
    private fun setupTitleComponent(config: AndesCardConfiguration) {
        if (andesCardAttrs.andesCardTitle.isNullOrEmpty()) {
            andesCardTitle.visibility = View.GONE
        } else {
            andesCardTitle.visibility = View.VISIBLE
            andesCardTitle.text = andesCardAttrs.andesCardTitle
            andesCardTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX, config.titleSize)
            andesCardTitle.typeface = config.titleTypeface
            andesCardTitle.setTextColor(config.titleColor.colorInt(context))
        }
    }

    /**
     * Gets data from the config and sets to the title component of this card.
     */
    private fun setupMarginsComponent(config: AndesCardConfiguration) {
//        val cardViewMarginParams = andesCardContainer.layoutParams as MarginLayoutParams
//        cardViewMarginParams.setMargins(50, 0, 0, 0)
//        andesCardContainer.requestLayout()


    }









    private fun createConfig() = AndesCardConfigurationFactory.create(context, andesCardAttrs)

    companion object {
        private val STYLE_DEFAULT = AndesCardStyle.ELEVATED
        private val TYPE_DEFAULT = AndesCardType.NONE
        private val PADDING_DEFAULT = AndesCardPadding.NONE
        private val TITLE_DEFAULT = null
        private val HIERARCHY_DEFAULT = AndesCardHierarchy.PRIMARY
    }

}