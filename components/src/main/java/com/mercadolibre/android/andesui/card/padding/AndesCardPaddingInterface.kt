package com.mercadolibre.android.andesui.card.padding

import android.content.Context
import com.mercadolibre.android.andesui.R

/**
 * Defines all types related properties that an [AndesCard] needs to be drawn properly.
 * Those properties change depending on the style of the andesCard.
 */
internal interface AndesCardPaddingInterface {
    fun paddingSize(context: Context): Int
    fun titleHeight(context: Context): Int
}

internal object AndesCardPaddingNone : AndesCardPaddingInterface {
    override fun paddingSize(context: Context): Int = context.resources.getDimension(R.dimen.andes_card_padding_none).toInt()
    override fun titleHeight(context: Context): Int = context.resources.getDimension(R.dimen.andes_card_title_height_none).toInt()
}

internal object AndesCardPaddingSmall : AndesCardPaddingInterface {
    override fun paddingSize(context: Context): Int = context.resources.getDimension(R.dimen.andes_card_padding_small).toInt()
    override fun titleHeight(context: Context): Int = context.resources.getDimension(R.dimen.andes_card_title_height_small).toInt()
}

internal object AndesCardPaddingMedium : AndesCardPaddingInterface {
    override fun paddingSize(context: Context): Int = context.resources.getDimension(R.dimen.andes_card_padding_medium).toInt()
    override fun titleHeight(context: Context): Int = context.resources.getDimension(R.dimen.andes_card_title_height_medium).toInt()
}

internal object AndesCardPaddingLarge : AndesCardPaddingInterface {
    override fun paddingSize(context: Context): Int = context.resources.getDimension(R.dimen.andes_card_padding_large).toInt()
    override fun titleHeight(context: Context): Int = context.resources.getDimension(R.dimen.andes_card_title_height_large).toInt()
}

internal object AndesCardPaddingXLarge : AndesCardPaddingInterface {
    override fun paddingSize(context: Context): Int = context.resources.getDimension(R.dimen.andes_card_padding_xlarge).toInt()
    override fun titleHeight(context: Context): Int = context.resources.getDimension(R.dimen.andes_card_title_height_xlarge).toInt()
}
