package com.mercadolibre.android.andesui.badge.size

import android.content.Context
import com.mercadolibre.android.andesui.R

/**
 * Defines all size related properties that an [AndesBadge] needs to be drawn properly.
 * Those properties change depending on the size of the badge.
 */
internal interface AndesBadgeSizeInterface {
    /**
     * Returns a [Float] representing the text size to be used.
     *
     * @param context needed for accessing some resources.
     * @return a [Float] representing the text size to be used.
     */
    fun textSize(context: Context): Float

    /**
     * Returns a [Float] representing the height size to be used.
     *
     * @param context needed for accessing some resources.
     * @return a [Float] representing the height size to be used.
     */
    fun height(context: Context): Float

    /**
     * Returns an [Int] representing the margin to be used.
     *
     * @param context needed for accessing some resources.
     * @return an [Int] representing the margin to be used.
     */
    fun textMargin(context: Context): Int

    /**
     * Returns a [Float] representing the corner radius to be used.
     *
     * @param context needed for accessing some resources.
     * @return a [Float] representing the corner radius to be used.
     */
    fun cornerRadius(context: Context): Float
}

/**
 * Implementation of [AndesBadgeSizeInterface] that returns the required data but personalized for the Large Size,
 * according to Andes specifications.
 *
 */
internal class AndesLargeBadgeSize : AndesBadgeSizeInterface {
    override fun textSize(context: Context) = context.resources.getDimension(R.dimen.andes_badge_large_text_size)
    override fun height(context: Context) = context.resources.getDimension(R.dimen.andes_badge_large_height)
    override fun textMargin(
        context: Context
    ) = context.resources.getDimension(R.dimen.andes_badge_large_text_margin).toInt()
    override fun cornerRadius(
        context: Context
    ) = context.resources.getDimension(R.dimen.andes_badge_large_corner_radius)
}

/**
 * Implementation of [AndesBadgeSizeInterface] that returns the required data but personalized for the Small Size,
 * according to Andes specifications.
 */
internal class AndesSmallBadgeSize : AndesBadgeSizeInterface {
    override fun textSize(context: Context) = context.resources.getDimension(R.dimen.andes_badge_small_text_size)
    override fun height(context: Context) = context.resources.getDimension(R.dimen.andes_badge_small_height)
    override fun textMargin(context: Context) = context.resources.getDimension(R.dimen.andes_badge_small_text_margin).toInt()
    override fun cornerRadius(context: Context) = context.resources.getDimension(R.dimen.andes_badge_small_corner_radius)
}
