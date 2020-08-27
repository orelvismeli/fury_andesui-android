package com.mercadolibre.android.andesui.progress.factory

import android.content.Context
import com.mercadolibre.android.andesui.R
import com.mercadolibre.android.andesui.progress.size.AndesProgressSize
import com.mercadolibre.android.andesui.progress.size.AndesProgressSizeInterface

internal data class AndesProgressConfiguration(
    val tint: Int = 0,
    val size: Float,
    val stroke: Int,
    val start: Boolean
)

internal object AndesProgressConfigurationFactory {

    fun create(context: Context, andesProgressAttrs: AndesProgressAttrs): AndesProgressConfiguration {

        return AndesProgressConfiguration(
                tint = resolveColor(context, andesProgressAttrs.tint),
                size = resolveSize(context, andesProgressAttrs.andesProgressSize.size),
                stroke = resolveStroke(context, andesProgressAttrs.andesProgressSize),
                start = andesProgressAttrs.start
        )
    }

    private fun resolveSize(context: Context, andesProgressSize: AndesProgressSizeInterface) =
            andesProgressSize.size(context)

    private fun resolveColor(context: Context, tint: Int): Int {
        if (tint == 0) {
            return context.resources.getColor(R.color.andes_blue_ml_500)
        }
        return tint
    }

    private fun resolveStroke(context: Context, andesProgressSize: AndesProgressSize): Int {
        return when(andesProgressSize) {
            AndesProgressSize.XLARGE ->
                context.resources.getDimension(R.dimen.andes_progress_stroke_xlarge).toInt()
            AndesProgressSize.LARGE ->
                context.resources.getDimension(R.dimen.andes_progress_stroke_large).toInt()
            AndesProgressSize.MEDIUM ->
                context.resources.getDimension(R.dimen.andes_progress_stroke_medium).toInt()
            AndesProgressSize.SMALL ->
                context.resources.getDimension(R.dimen.andes_progress_stroke_small).toInt()
            else -> context.resources.getDimension(R.dimen.andes_progress_stroke_small).toInt()
        }
    }
}
