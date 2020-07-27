package com.mercadolibre.android.andesui.badge.factory

import android.content.Context
import com.mercadolibre.android.andesui.badge.border.AndesBadgeBorderInterface
import com.mercadolibre.android.andesui.badge.hierarchy.AndesBadgePillHierarchyInterface
import com.mercadolibre.android.andesui.badge.size.AndesBadgeSizeInterface
import com.mercadolibre.android.andesui.badge.type.AndesBadgeTypeInterface
import com.mercadolibre.android.andesui.color.AndesColor

internal data class AndesBadgePillConfiguration(
    val backgroundColor: AndesColor,
    val backgroundRadius: FloatArray,
    val textColor: AndesColor,
    val text: String? = null,
    val textSize: Float,
    val textMargin: Int,
    val height: Float
)

internal object AndesBadgePillConfigurationFactory {

    fun create(context: Context, andesMessageAttrs: AndesBadgePillAttrs): AndesBadgePillConfiguration {
        return with(andesMessageAttrs) {
            AndesBadgePillConfiguration(
                    backgroundColor = resolveBackgroundColor(andesBadgePillHierarchy.hierarchy, andesBadgeType.type),
                    backgroundRadius = resolveBackgroundRadius(
                            andesBadgePillSize.size,
                            andesBadgePillBorder.border,
                            context
                    ),
                    textColor = resolveTextColor(andesBadgePillHierarchy.hierarchy, andesBadgeType.type),
                    text = andesBadgeText,
                    textSize = resolveTextSize(andesBadgePillSize.size, context),
                    textMargin = resolveTextMargin(andesBadgePillSize.size, context),
                    height = resolveHeight(andesBadgePillSize.size, context)
            )
        }
    }

    private fun resolveBackgroundColor(
        hierarchy: AndesBadgePillHierarchyInterface,
        type: AndesBadgeTypeInterface
    ) = hierarchy.backgroundColor(type)
    private fun resolveBackgroundRadius(
        size: AndesBadgeSizeInterface,
        border: AndesBadgeBorderInterface,
        context: Context
    ) =
             floatArrayOf(border.upStartCornerRadius(size, context), border.upEndCornerRadius(size, context),
                     border.bottomEndCornerRadius(size, context), border.bottomStartCornerRadius(size, context))
    private fun resolveTextColor(
        hierarchy: AndesBadgePillHierarchyInterface,
        type: AndesBadgeTypeInterface
    ) = hierarchy.textColor(type)
    private fun resolveTextSize(size: AndesBadgeSizeInterface, context: Context) = size.textSize(context)
    private fun resolveTextMargin(size: AndesBadgeSizeInterface, context: Context) = size.textMargin(context)
    private fun resolveHeight(size: AndesBadgeSizeInterface, context: Context) = size.height(context)
}
