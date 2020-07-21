package com.mercadolibre.android.andesui.card.type

/**
 * Utility class that does two things: Defines the possible styles an [AndesCard] can take
 * because it's an enum, as you can see.
 * But as a bonus it gives you the proper implementation so you don't have to make any mapping.
 *
 * @property type Possible styles that an [AndesCard] may take.
 */
enum class AndesCardType {
    NONE,
    HIGHLIGHT,
    ERROR,
    SUCCESS,
    WARNING;

    companion object {
        fun fromString(value: String): AndesCardType = valueOf(value.toUpperCase())
    }

    internal val type get() = getAndesCardType()

    private fun getAndesCardType(): AndesCardTypeInterface {
        return when (this) {
            NONE -> AndesCardTypeNone
            HIGHLIGHT -> AndesCardTypeHighlight
            ERROR -> AndesCardTypeError
            SUCCESS -> AndesCardTypeSuccess
            WARNING -> AndesCardTypeWarning
        }
    }

}