package com.mercadolibre.android.andesui.progress.size

import android.content.Context
import com.mercadolibre.android.andesui.R

/**
 * Defines all size related properties that an [AndesProgress] needs to be drawn properly.
 * Those properties change depending on the size of the button.
 *
 */
internal interface AndesProgressSizeInterface {

    fun size(context: Context): Float
}

internal class AndesXLargeProgressSize : AndesProgressSizeInterface {
    override fun size(context: Context) = context.resources.getDimension(R.dimen.andes_progress_xlarge)
}

internal class AndesLargeProgressSize : AndesProgressSizeInterface {
    override fun size(context: Context) = context.resources.getDimension(R.dimen.andes_progress_large)
}

internal class AndesMediumProgressSize : AndesProgressSizeInterface {
    override fun size(context: Context) = context.resources.getDimension(R.dimen.andes_progress_medium)
}

internal class AndesSmallPogressSize : AndesProgressSizeInterface {
    override fun size(context: Context) = context.resources.getDimension(R.dimen.andes_progress_small)
}
