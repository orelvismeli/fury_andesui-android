package com.mercadolibre.android.andesui.card.style

import android.content.Context
import com.mercadolibre.android.andesui.R
import com.mercadolibre.android.andesui.card.hierarchy.AndesCardHierarchy

/**
 * Defines all types related properties that an [AndesCard] needs to be drawn properly.
 * Those properties change depending on the style of the andesCard.
 */
internal interface AndesCardStyleInterface {
    fun elevation(context: Context, hierarchy: AndesCardHierarchy): Float
}

internal object AndesCardStyleElevated : AndesCardStyleInterface {
    override fun elevation(context: Context, hierarchy: AndesCardHierarchy): Float {
        return if (hierarchy == AndesCardHierarchy.PRIMARY) {
            context.resources.getDimension(R.dimen.andes_card_elevated_shadow)
        } else {
            0f
        }
    }
}

internal object AndesCardStyleOutline : AndesCardStyleInterface {
    override fun elevation(context: Context, hierarchy: AndesCardHierarchy) = 0f
}
