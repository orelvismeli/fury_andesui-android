package com.mercadolibre.android.andesui.card.style

/**
 * Utility class that does two things: Defines the possible styles an [AndesCard] can take
 * because it's an enum, as you can see.
 * But as a bonus it gives you the proper implementation so you don't have to make any mapping.
 *
 * @property style Possible styles that an [AndesCard] may take.
 */
enum class AndesCardStyle {
    ELEVATED,
    OUTLINE;

    companion object {
        fun fromString(value: String): AndesCardStyle = valueOf(value.toUpperCase())
    }

    internal val style get() = getAndesCardStyle()

    private fun getAndesCardStyle(): AndesCardStyleInterface {
        return when (this) {
            ELEVATED -> AndesCardStyleElevated
            OUTLINE -> AndesCardStyleOutline
        }
    }
}
