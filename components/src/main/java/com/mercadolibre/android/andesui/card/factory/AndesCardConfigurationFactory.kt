package com.mercadolibre.android.andesui.card.factory

import android.content.Context
import android.graphics.Typeface
import com.mercadolibre.android.andesui.R
import com.mercadolibre.android.andesui.color.AndesColor
import com.mercadolibre.android.andesui.color.toAndesColor
import com.mercadolibre.android.andesui.typeface.getFontOrDefault

internal data class AndesCardConfiguration(
        val titleSize: Float,
        val titleTypeface: Typeface,
        val titleColor: AndesColor
)

internal object AndesCardConfigurationFactory {

    fun create(context: Context, andesCardAttrs: AndesCardAttrs): AndesCardConfiguration {
        return with(andesCardAttrs) {
            AndesCardConfiguration(
                    titleSize = resolveTitleSize(context),
                    titleTypeface = resolveTitleTypeface(context),
                    titleColor = resolveTextColor()
            )
        }
    }

    private fun resolveTitleSize(context: Context) = context.resources.getDimension(R.dimen.andes_card_title_size)
    private fun resolveTitleTypeface(context: Context) = context.getFontOrDefault(R.font.andes_font_semibold)
    private fun resolveTextColor() = R.color.andes_gray_800.toAndesColor()

}