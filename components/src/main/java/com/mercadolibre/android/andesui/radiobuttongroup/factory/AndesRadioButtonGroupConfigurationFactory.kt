package com.mercadolibre.android.andesui.radiobuttongroup.factory

import com.mercadolibre.android.andesui.radiobutton.align.AndesRadioButtonAlign
import com.mercadolibre.android.andesui.radiobuttongroup.RadioButtonItem
import com.mercadolibre.android.andesui.radiobuttongroup.distribution.AndesRadioButtonGroupDistribution

internal data class AndesRadioButtonGroupConfiguration(
    val align: AndesRadioButtonAlign,
    val distribution: AndesRadioButtonGroupDistribution,
    val selected: Int?,
    val radioButtons: ArrayList<RadioButtonItem>
)

internal object AndesRadioButtonGroupConfigurationFactory {

    fun create(andesRadioButtonAttrs: AndesRadioButtonGroupAttrs): AndesRadioButtonGroupConfiguration {
        return with(andesRadioButtonAttrs) {
            AndesRadioButtonGroupConfiguration(
                    align = andesRadioButtonAttrs.andesRadioButtonGroupAlign,
                    distribution = andesRadioButtonAttrs.andesRadioButtonGroupDistribution,
                    selected = andesRadioButtonAttrs.andesRadioButtonGroupSelected,
                    radioButtons = andesRadioButtonAttrs.andesRadioButtonGroupRadioButtons
            )
        }
    }
}
