package com.mercadolibre.android.andesui.radiobuttongroup.distribution

enum class AndesRadioButtonGroupDistribution {
    VERTICAL,
    HORIZONTAL;

    companion object {
        fun fromString(value: String): AndesRadioButtonGroupDistribution = valueOf(value.toUpperCase())
    }

    internal val type get() = getAndesRadioButtonGroupAlign()

    private fun getAndesRadioButtonGroupAlign(): AndesRadioButtonGroupDistributionInterface {
        return when (this) {
            VERTICAL -> AndesRadioButtonGroupVertical
            HORIZONTAL -> AndesRadioButtonGroupHorizontal
        }
    }
}
