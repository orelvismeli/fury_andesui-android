package com.mercadolibre.android.andesui.card.padding

/**
 * Utility class that does two things: Defines the possible styles an [AndesCard] can take
 * because it's an enum, as you can see.
 * But as a bonus it gives you the proper implementation so you don't have to make any mapping.
 *
 * @property padding Possible styles that an [AndesCard] may take.
 */
enum class AndesCardPadding {
    NONE,
    SMALL,
    MEDIUM,
    LARGE,
    XLARGE;

    companion object {
        fun fromString(value: String): AndesCardPadding = valueOf(value.toUpperCase())
    }

    internal val padding get() = getAndesCardPadding()

    private fun getAndesCardPadding(): AndesCardPaddingInterface {
        return when (this) {
            NONE -> AndesCardPaddingNone
            SMALL -> AndesCardPaddingSmall
            MEDIUM -> AndesCardPaddingMedium
            LARGE -> AndesCardPaddingLarge
            XLARGE -> AndesCardPaddingXLarge
        }
    }
}
