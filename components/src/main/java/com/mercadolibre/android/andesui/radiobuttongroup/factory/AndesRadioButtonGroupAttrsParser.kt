package com.mercadolibre.android.andesui.radiobuttongroup.factory

import android.content.Context
import android.util.AttributeSet
import com.mercadolibre.android.andesui.R
import com.mercadolibre.android.andesui.radiobutton.align.AndesRadioButtonAlign
import com.mercadolibre.android.andesui.radiobuttongroup.RadioButtonItem
import com.mercadolibre.android.andesui.radiobuttongroup.distribution.AndesRadioButtonGroupDistribution

internal data class AndesRadioButtonGroupAttrs(
    val andesRadioButtonGroupAlign: AndesRadioButtonAlign,
    val andesRadioButtonGroupDistribution: AndesRadioButtonGroupDistribution,
    val andesRadioButtonGroupSelected: Int,
    val andesRadioButtonGroupRadioButtons: ArrayList<RadioButtonItem>
)

/**
 * This object parse the attribute set and return an instance of AndesRadioButtonGroupAttrs
 * to be used by AndesRadioButtonGroup
 */
internal object AndesRadioButtonGroupAttrsParser {

    private const val ANDES_RADIOBUTTON_ALIGN_LEFT = "1000"
    private const val ANDES_RADIOBUTTON_ALIGN_RIGHT = "1001"

    private const val ANDES_RADIOBUTTON_DISTRIBUTION_VERTICAL = "2000"
    private const val ANDES_RADIOBUTTON_DISTRIBUTION_HORIZONTAL = "2001"

    fun parse(context: Context, attr: AttributeSet?): AndesRadioButtonGroupAttrs {
        val typedArray = context.obtainStyledAttributes(attr, R.styleable.AndesRadioButtonGroup)

        val align = when (typedArray.getString(R.styleable.AndesRadioButtonGroup_andesRadioButtonGroupAlign)) {
            ANDES_RADIOBUTTON_ALIGN_LEFT -> AndesRadioButtonAlign.LEFT
            ANDES_RADIOBUTTON_ALIGN_RIGHT -> AndesRadioButtonAlign.RIGHT
            else -> AndesRadioButtonAlign.LEFT
        }

        val distribution = when (typedArray.getString(R.styleable.AndesRadioButtonGroup_andesRadioButtonGroupDistribution)) {
            ANDES_RADIOBUTTON_DISTRIBUTION_VERTICAL -> AndesRadioButtonGroupDistribution.VERTICAL
            ANDES_RADIOBUTTON_DISTRIBUTION_HORIZONTAL -> AndesRadioButtonGroupDistribution.HORIZONTAL
            else -> AndesRadioButtonGroupDistribution.VERTICAL
        }

        return AndesRadioButtonGroupAttrs(
                andesRadioButtonGroupAlign = align,
                andesRadioButtonGroupDistribution = distribution,
                andesRadioButtonGroupSelected = -1,
                andesRadioButtonGroupRadioButtons = arrayListOf()
        ).also { typedArray.recycle() }
    }
}
