package com.mercadolibre.android.andesui.card.style

import android.content.Context
import com.mercadolibre.android.andesui.R

/**
 * Defines all types related properties that an [AndesCard] needs to be drawn properly.
 * Those properties change depending on the style of the andesCard.
 */
internal interface AndesCardStyleInterface {
    fun elevation(context: Context): Int
}

internal object AndesCardStyleElevated : AndesCardStyleInterface {
    override fun elevation(context: Context): Int = context.resources.getDimension(R.dimen.andes_card_title_size).toInt()
}

internal object AndesCardStyleOutline : AndesCardStyleInterface {
    override fun elevation(context: Context): Int = 0
}