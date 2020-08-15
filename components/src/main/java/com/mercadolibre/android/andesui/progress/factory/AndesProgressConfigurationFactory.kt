package com.mercadolibre.android.andesui.progress.factory

import android.content.Context
import com.mercadolibre.android.andesui.R
import com.mercadolibre.android.andesui.progress.size.AndesProgressSizeInterface

internal data class AndesProgressConfiguration(
        val tint: Int = 0,
        val size: Float
)

internal object AndesProgressConfigurationFactory {

    fun create(context: Context, andesProgressAttrs: AndesProgressAttrs): AndesProgressConfiguration {

        return AndesProgressConfiguration(
                tint = resolveColor(context, andesProgressAttrs.tint),
                size = resolveSize(context, andesProgressAttrs.andesProgressSize.size)
        )
    }

    private fun resolveSize(context: Context, andesProgressSize: AndesProgressSizeInterface) = andesProgressSize.size(context)

    private fun resolveColor(context: Context, tint:Int):Int{
        if (tint == 0){
            return context.resources.getColor(R.color.andes_blue_ml_500)
        }
        return tint
    }
}