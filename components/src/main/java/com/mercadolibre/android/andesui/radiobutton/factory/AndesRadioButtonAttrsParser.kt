package com.mercadolibre.android.andesui.radiobutton.factory

import android.content.Context
import android.util.AttributeSet
import com.mercadolibre.android.andesui.R
import com.mercadolibre.android.andesui.radiobutton.status.AndesRadioButtonStatus
import com.mercadolibre.android.andesui.radiobutton.align.AndesRadioButtonAlign
import com.mercadolibre.android.andesui.radiobutton.type.AndesRadioButtonType

internal data class AndesRadioButtonAttrs(
    val andesRadioButtonAlign: AndesRadioButtonAlign,
    val andesRadioButtonText: String?,
    val andesRadioButtonStatus: AndesRadioButtonStatus,
    val andesRadioButtonType: AndesRadioButtonType
)

/**
 * This object parse the attribute set and return an instance of AndesRadioButtonAttrs to be used by AndesRadioButton
 */
internal object AndesRadioButtonAttrParser {

    private const val ANDES_RADIOBUTTON_ALIGN_LEFT = "1000"
    private const val ANDES_RADIOBUTTON_ALIGN_RIGHT = "1001"

    private const val ANDES_RADIOBUTTON_STATUS_SELECTED = "2000"
    private const val ANDES_RADIOBUTTON_STATUS_UNSELECTED = "2001"

    private const val ANDES_RADIOBUTTON_TYPE_IDLE = "3000"
    private const val ANDES_RADIOBUTTON_TYPE_DISABLED = "3001"
    private const val ANDES_RADIOBUTTON_TYPE_ERROR = "3002"

    fun parse(context: Context, attr: AttributeSet?): AndesRadioButtonAttrs {
        val typedArray = context.obtainStyledAttributes(attr, R.styleable.AndesRadioButton)

        val align = when (typedArray.getString(R.styleable.AndesRadioButton_andesRadioButtonAlign)) {
            ANDES_RADIOBUTTON_ALIGN_LEFT -> AndesRadioButtonAlign.LEFT
            ANDES_RADIOBUTTON_ALIGN_RIGHT -> AndesRadioButtonAlign.RIGHT
            else -> AndesRadioButtonAlign.LEFT
        }

        val status = when (typedArray.getString(R.styleable.AndesRadioButton_andesRadioButtonStatus)) {
            ANDES_RADIOBUTTON_STATUS_SELECTED -> AndesRadioButtonStatus.SELECTED
            ANDES_RADIOBUTTON_STATUS_UNSELECTED -> AndesRadioButtonStatus.UNSELECTED
            else -> AndesRadioButtonStatus.UNSELECTED
        }

        val type = when (typedArray.getString(R.styleable.AndesRadioButton_andesRadioButtonType)) {
            ANDES_RADIOBUTTON_TYPE_IDLE -> AndesRadioButtonType.IDLE
            ANDES_RADIOBUTTON_TYPE_DISABLED -> AndesRadioButtonType.DISABLED
            ANDES_RADIOBUTTON_TYPE_ERROR -> AndesRadioButtonType.ERROR
            else -> AndesRadioButtonType.IDLE
        }

        val validatedStatus = if (type == AndesRadioButtonType.ERROR) {
            AndesRadioButtonStatus.UNSELECTED
        } else {
            status
        }

        return AndesRadioButtonAttrs(
                andesRadioButtonAlign = align,
                andesRadioButtonText = typedArray.getString(R.styleable.AndesRadioButton_andesRadioButtonText),
                andesRadioButtonStatus = validatedStatus,
                andesRadioButtonType = type
        ).also { typedArray.recycle() }
    }
}
