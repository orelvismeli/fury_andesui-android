package com.mercadolibre.android.andesui.bottomsheet.state

enum class AndesBottomSheetState {
    COLLAPSED,
    EXPANDED;

    companion object {
        fun fromString(value: String): AndesBottomSheetState = valueOf(value.toUpperCase())
    }
}
