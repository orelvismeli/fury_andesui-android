package com.mercadolibre.android.andesui.badge.size

/**
 * Utility class that does two things: Defines the possible sizes an [AndesBadge] can take because it's an enum,
 * as you can see.
 * But as a bonus it gives you the proper implementation so you don't have to make any mapping.
 *
 * You ask me with, let's say 'SMALL', and then I'll give you a proper implementation of that size.
 *
 * @property size Possible sizes that an [AndesBadge] may take.
 */
enum class AndesBadgePillSize {
    SMALL,
    LARGE;

    companion object {
        fun fromString(value: String): AndesBadgePillSize = valueOf(value.toUpperCase())
    }

    internal val size get() = getAndesBadgeSize()

    private fun getAndesBadgeSize(): AndesBadgeSizeInterface {
        return when (this) {
            SMALL -> AndesSmallBadgeSize()
            LARGE -> AndesLargeBadgeSize()
        }
    }
}
