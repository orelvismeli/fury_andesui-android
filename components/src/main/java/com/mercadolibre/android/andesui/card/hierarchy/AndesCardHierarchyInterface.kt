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
    override fun backgroundColor() = R.color.andes_white.toAndesColor()
}

internal object AndesCardHierarchySecondary : AndesCardHierarchyInterface {
    override fun backgroundColor() = R.color.andes_gray_040_solid.toAndesColor()
}
internal object AndesCardHierarchySecondaryDark : AndesCardHierarchyInterface {
    override fun backgroundColor() = R.color.andes_gray_070_solid.toAndesColor()
}
