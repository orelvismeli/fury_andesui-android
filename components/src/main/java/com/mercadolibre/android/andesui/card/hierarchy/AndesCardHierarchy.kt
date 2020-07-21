package com.mercadolibre.android.andesui.card.hierarchy

/**
 * Utility class that does two things: Defines the possible styles an [AndesCard] can take
 * because it's an enum, as you can see.
 * But as a bonus it gives you the proper implementation so you don't have to make any mapping.
 *
 * @property type Possible styles that an [AndesCard] may take.
 */
enum class AndesCardHierarchy {
    PRIMARY,
    SECONDARY;

    companion object {
        fun fromString(value: String): AndesCardHierarchy = valueOf(value.toUpperCase())
    }

    internal val type get() = getAndesCardHierarchy()

    private fun getAndesCardHierarchy(): AndesCardHierarchyInterface {
        return when (this) {
            PRIMARY -> AndesCardHierarchyPrimary
            SECONDARY -> AndesCardHierarchySecondary
        }
    }

}