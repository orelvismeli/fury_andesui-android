package com.mercadolibre.android.andesui.card.hierarchy

import com.mercadolibre.android.andesui.R
import com.mercadolibre.android.andesui.color.AndesColor
import com.mercadolibre.android.andesui.color.toAndesColor

/**
 * Defines all types related properties that an [AndesCard] needs to be drawn properly.
 * Those properties change depending on the style of the andesCard.
 */
internal interface AndesCardHierarchyInterface {
    /**
     * Returns a [AndesColor] that contains the icon color data for the andesCard.
     */
    fun backgroundColor(): AndesColor
}

internal object AndesCardHierarchyPrimary : AndesCardHierarchyInterface {
    override fun backgroundColor() = R.color.andes_accent_color_500.toAndesColor()
}

internal object AndesCardHierarchySecondary : AndesCardHierarchyInterface {
    override fun backgroundColor() = R.color.andes_red_500.toAndesColor()
}