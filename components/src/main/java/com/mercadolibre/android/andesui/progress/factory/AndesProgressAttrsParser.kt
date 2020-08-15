package com.mercadolibre.android.andesui.progress.factory

import android.content.Context
import android.util.AttributeSet
import com.mercadolibre.android.andesui.R
import com.mercadolibre.android.andesui.progress.size.AndesProgressSize


/**
 * The data class that contains the public components of the button.
 */
internal data class AndesProgressAttrs(
        val andesProgressSize: AndesProgressSize,
        val tint: Int
)

internal object AndesProgressAttrsParser{

    private const val ANDES_PROGRESS_SIZE_X_LARGE = "200"
    private const val ANDES_PROGRESS_SIZE_LARGE = "201"
    private const val ANDES_PROGRESS_SIZE_MEDIUM = "202"
    private const val ANDES_PROGRESS_SIZE_SMALL = "203"

    fun parse(context: Context, attr: AttributeSet?): AndesProgressAttrs {
        val typedArray = context.obtainStyledAttributes(attr, R.styleable.AndesProgress)

        val size = when (typedArray.getString(R.styleable.AndesProgress_andesProgressSize)) {
            ANDES_PROGRESS_SIZE_X_LARGE -> AndesProgressSize.XLARGE
            ANDES_PROGRESS_SIZE_LARGE -> AndesProgressSize.LARGE
            ANDES_PROGRESS_SIZE_MEDIUM -> AndesProgressSize.MEDIUM
            ANDES_PROGRESS_SIZE_SMALL -> AndesProgressSize.SMALL
            else -> AndesProgressSize.SMALL
        }

        return AndesProgressAttrs(
                andesProgressSize = size,
                tint = typedArray.getInt(R.styleable.AndesProgress_andesProgressTint, 0)
        ).also { typedArray.recycle() }
    }
}