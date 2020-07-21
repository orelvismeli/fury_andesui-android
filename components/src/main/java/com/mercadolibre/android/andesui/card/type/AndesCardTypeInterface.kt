package com.mercadolibre.android.andesui.card.type

import com.mercadolibre.android.andesui.R
import com.mercadolibre.android.andesui.color.AndesColor
import com.mercadolibre.android.andesui.color.toAndesColor

/**
 * Defines all types related properties that an [AndesCard] needs to be drawn properly.
 * Those properties change depending on the style of the andesCard.
 */
internal interface AndesCardTypeInterface {
    /**
     * Returns a [AndesColor] that contains the primary color data for the andesCard.
     */
    fun primaryColor(): AndesColor
}

internal object AndesCardTypeNone : AndesCardTypeInterface {
    override fun primaryColor() = R.color.andes_transparent.toAndesColor()
}

internal object AndesCardTypeHighlight : AndesCardTypeInterface {
    override fun primaryColor() = R.color.andes_blue_ml_500.toAndesColor()
}

internal object AndesCardTypeError : AndesCardTypeInterface {
    override fun primaryColor() = R.color.andes_red_500.toAndesColor()
}

internal object AndesCardTypeSuccess : AndesCardTypeInterface {
    override fun primaryColor() = R.color.andes_green_500.toAndesColor()
}

internal object AndesCardTypeWarning : AndesCardTypeInterface {
    override fun primaryColor() = R.color.andes_orange_500.toAndesColor()
}