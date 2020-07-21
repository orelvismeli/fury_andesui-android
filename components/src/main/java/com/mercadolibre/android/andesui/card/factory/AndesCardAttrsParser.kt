package com.mercadolibre.android.andesui.card.factory

import com.mercadolibre.android.andesui.R
import android.content.Context
import android.util.AttributeSet
import android.view.View
import com.mercadolibre.android.andesui.card.type.AndesCardType
import com.mercadolibre.android.andesui.card.hierarchy.AndesCardHierarchy
import com.mercadolibre.android.andesui.card.padding.AndesCardPadding
import com.mercadolibre.android.andesui.card.style.AndesCardStyle

internal data class AndesCardAttrs(
        val andesCardView: View?,
        val andesCardType: AndesCardType,
        val andesCardPadding: AndesCardPadding,
        val andesCardStyle: AndesCardStyle,
        val andesCardTitle: String?,
        val andesCardHierarchy: AndesCardHierarchy
)

/**
 * This object parse the attribute set and return an instance of AndesCardAttrs
 * to be used by AndesCard
 */
internal object AndesCardAttrParser {

    private const val ANDES_CARD_TYPE_NONE = "1000"
    private const val ANDES_CARD_TYPE_HIGHLIGHT = "1001"
    private const val ANDES_CARD_TYPE_ERROR = "1002"
    private const val ANDES_CARD_TYPE_SUCCESS = "1003"
    private const val ANDES_CARD_TYPE_WARNING = "1004"

    private const val ANDES_CARD_HIERARCHY_PRIMARY = "3000"
    private const val ANDES_CARD_HIERARCHY_SECONDARY = "3001"

    private const val ANDES_CARD_PADDING_NONE = "5000"
    private const val ANDES_CARD_PADDING_SMALL = "5001"
    private const val ANDES_CARD_PADDING_MEDIUM = "5002"
    private const val ANDES_CARD_PADDING_LARGE = "5003"
    private const val ANDES_CARD_PADDING_XLARGE = "5004"

    private const val ANDES_CARD_STYLE_ELEVATED = "6000"
    private const val ANDES_CARD_STYLE_OUTLINE = "6001"

    fun parse(context: Context, attr: AttributeSet?): AndesCardAttrs {
        val typedArray = context.obtainStyledAttributes(attr, R.styleable.AndesCard)

        val hierarchy = when (typedArray.getString(R.styleable.AndesCard_andesCardHierarchy)) {
            ANDES_CARD_HIERARCHY_PRIMARY -> AndesCardHierarchy.PRIMARY
            ANDES_CARD_HIERARCHY_SECONDARY -> AndesCardHierarchy.SECONDARY
            else -> AndesCardHierarchy.PRIMARY
        }

        val padding = when (typedArray.getString(R.styleable.AndesCard_andesCardPadding)) {
            ANDES_CARD_PADDING_NONE -> AndesCardPadding.NONE
            ANDES_CARD_PADDING_SMALL -> AndesCardPadding.SMALL
            ANDES_CARD_PADDING_MEDIUM -> AndesCardPadding.MEDIUM
            ANDES_CARD_PADDING_LARGE -> AndesCardPadding.LARGE
            ANDES_CARD_PADDING_XLARGE -> AndesCardPadding.XLARGE
            else -> AndesCardPadding.MEDIUM
        }

        val type = when (typedArray.getString(R.styleable.AndesCard_andesCardType)) {
            ANDES_CARD_TYPE_NONE -> AndesCardType.NONE
            ANDES_CARD_TYPE_HIGHLIGHT -> AndesCardType.HIGHLIGHT
            ANDES_CARD_TYPE_ERROR -> AndesCardType.ERROR
            ANDES_CARD_TYPE_SUCCESS -> AndesCardType.SUCCESS
            ANDES_CARD_TYPE_WARNING -> AndesCardType.WARNING
            else -> AndesCardType.NONE
        }

        val style = when (typedArray.getString(R.styleable.AndesCard_andesCardStyle)) {
            ANDES_CARD_STYLE_ELEVATED -> AndesCardStyle.ELEVATED
            ANDES_CARD_STYLE_OUTLINE -> AndesCardStyle.OUTLINE
            else -> AndesCardStyle.ELEVATED
        }

        return AndesCardAttrs(
                andesCardView = null,
                andesCardType = type,
                andesCardPadding = padding,
                andesCardStyle = style,
                andesCardTitle = typedArray.getString(R.styleable.AndesCard_andesCardTitle),
                andesCardHierarchy = hierarchy
        ).also { typedArray.recycle() }
    }

}