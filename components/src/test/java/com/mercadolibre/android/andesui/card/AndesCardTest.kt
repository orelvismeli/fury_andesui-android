package com.mercadolibre.android.andesui.card

import android.os.Build
import android.view.View
import com.mercadolibre.android.andesui.BuildConfig
import com.mercadolibre.android.andesui.R
import com.mercadolibre.android.andesui.card.factory.AndesCardAttrs
import com.mercadolibre.android.andesui.card.factory.AndesCardConfigurationFactory
import com.mercadolibre.android.andesui.card.hierarchy.AndesCardHierarchy
import com.mercadolibre.android.andesui.card.padding.AndesCardPadding
import com.mercadolibre.android.andesui.card.style.AndesCardStyle
import com.mercadolibre.android.andesui.card.type.AndesCardType
import com.mercadolibre.android.andesui.color.toAndesColor
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.spy
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(constants = BuildConfig::class, sdk = [Build.VERSION_CODES.LOLLIPOP])
class AndesCardTest {

    private var context = RuntimeEnvironment.application
    private val configFactory = spy(AndesCardConfigurationFactory)
    private lateinit var attrs: AndesCardAttrs

    @Test
    fun `Card title`() {
        attrs = AndesCardAttrs(
                View(context),
                AndesCardType.NONE,
                AndesCardPadding.NONE,
                AndesCardStyle.ELEVATED,
                "Title",
                AndesCardHierarchy.PRIMARY
        )
        val config = configFactory.create(context, attrs)
        assertEquals(config.titleSize, context.resources.getDimension(R.dimen.andes_card_title_size))
    }

    @Test
    fun `Elevation elevated`() {
        attrs = AndesCardAttrs(
                View(context),
                AndesCardType.NONE,
                AndesCardPadding.NONE,
                AndesCardStyle.ELEVATED,
                "Title",
                AndesCardHierarchy.PRIMARY
        )
        val config = configFactory.create(context, attrs)
        assertEquals(config.elevation, context.resources.getDimension(R.dimen.andes_card_elevated_shadow))
    }

    @Test
    fun `Elevation outline`() {
        attrs = AndesCardAttrs(
                View(context),
                AndesCardType.NONE,
                AndesCardPadding.NONE,
                AndesCardStyle.OUTLINE,
                "Title",
                AndesCardHierarchy.PRIMARY
        )
        val config = configFactory.create(context, attrs)
        assertEquals(config.elevation, 0f)
    }

    @Test
    fun `Secondary, elevation elevated`() {
        attrs = AndesCardAttrs(
                View(context),
                AndesCardType.NONE,
                AndesCardPadding.NONE,
                AndesCardStyle.ELEVATED,
                "Title",
                AndesCardHierarchy.SECONDARY
        )
        val config = configFactory.create(context, attrs)
        assertEquals(config.elevation, 0f)
    }

    @Test
    fun `Secondary, elevation outline`() {
        attrs = AndesCardAttrs(
                View(context),
                AndesCardType.NONE,
                AndesCardPadding.NONE,
                AndesCardStyle.OUTLINE,
                "Title",
                AndesCardHierarchy.SECONDARY
        )
        val config = configFactory.create(context, attrs)
        assertEquals(config.elevation, 0f)
    }

    @Test
    fun `Padding none`() {
        attrs = AndesCardAttrs(
                View(context),
                AndesCardType.NONE,
                AndesCardPadding.NONE,
                AndesCardStyle.OUTLINE,
                "Title",
                AndesCardHierarchy.SECONDARY
        )
        val config = configFactory.create(context, attrs)
        assertEquals(config.titleLateralMargins, context.resources.getDimension(R.dimen.andes_card_padding_small).toInt())
    }

    @Test
    fun `Padding small`() {
        attrs = AndesCardAttrs(
                View(context),
                AndesCardType.NONE,
                AndesCardPadding.SMALL,
                AndesCardStyle.OUTLINE,
                "Title",
                AndesCardHierarchy.SECONDARY
        )
        val config = configFactory.create(context, attrs)
        assertEquals(config.titleLateralMargins, context.resources.getDimension(R.dimen.andes_card_padding_small).toInt())
    }

    @Test
    fun `Padding medium`() {
        attrs = AndesCardAttrs(
                View(context),
                AndesCardType.NONE,
                AndesCardPadding.MEDIUM,
                AndesCardStyle.OUTLINE,
                "Title",
                AndesCardHierarchy.SECONDARY
        )
        val config = configFactory.create(context, attrs)
        assertEquals(
                config.titleLateralMargins,
                context.resources.getDimension(R.dimen.andes_card_padding_medium).toInt()
        )
    }

    @Test
    fun `Padding large`() {
        attrs = AndesCardAttrs(
                View(context),
                AndesCardType.NONE,
                AndesCardPadding.LARGE,
                AndesCardStyle.OUTLINE,
                "Title",
                AndesCardHierarchy.SECONDARY
        )
        val config = configFactory.create(context, attrs)
        assertEquals(config.titleLateralMargins, context.resources.getDimension(R.dimen.andes_card_padding_large).toInt())
    }

    @Test
    fun `Padding xlarge`() {
        attrs = AndesCardAttrs(
                View(context),
                AndesCardType.NONE,
                AndesCardPadding.XLARGE,
                AndesCardStyle.OUTLINE,
                "Title",
                AndesCardHierarchy.SECONDARY
        )
        val config = configFactory.create(context, attrs)
        assertEquals(config.titleLateralMargins, context.resources.getDimension(R.dimen.andes_card_padding_xlarge).toInt())
    }

    @Test
    fun `Type none`() {
        attrs = AndesCardAttrs(
                View(context),
                AndesCardType.NONE,
                AndesCardPadding.XLARGE,
                AndesCardStyle.OUTLINE,
                "Title",
                AndesCardHierarchy.SECONDARY
        )
        val config = configFactory.create(context, attrs)
        assertEquals(config.pipeColor, R.color.andes_transparent.toAndesColor())
    }

    @Test
    fun `Type success`() {
        attrs = AndesCardAttrs(
                View(context),
                AndesCardType.SUCCESS,
                AndesCardPadding.XLARGE,
                AndesCardStyle.OUTLINE,
                "Title",
                AndesCardHierarchy.SECONDARY
        )
        val config = configFactory.create(context, attrs)
        assertEquals(config.pipeColor, R.color.andes_green_500.toAndesColor())
    }

    @Test
    fun `Type error`() {
        attrs = AndesCardAttrs(
                View(context),
                AndesCardType.ERROR,
                AndesCardPadding.XLARGE,
                AndesCardStyle.OUTLINE,
                "Title",
                AndesCardHierarchy.SECONDARY
        )
        val config = configFactory.create(context, attrs)
        assertEquals(config.pipeColor, R.color.andes_red_500.toAndesColor())
    }

    @Test
    fun `Type warning`() {
        attrs = AndesCardAttrs(
                View(context),
                AndesCardType.WARNING,
                AndesCardPadding.XLARGE,
                AndesCardStyle.OUTLINE,
                "Title",
                AndesCardHierarchy.SECONDARY
        )
        val config = configFactory.create(context, attrs)
        assertEquals(config.pipeColor, R.color.andes_orange_500.toAndesColor())
    }

    @Test
    fun `Type highlight`() {
        attrs = AndesCardAttrs(
                View(context),
                AndesCardType.HIGHLIGHT,
                AndesCardPadding.XLARGE,
                AndesCardStyle.OUTLINE,
                "Title",
                AndesCardHierarchy.SECONDARY
        )
        val config = configFactory.create(context, attrs)
        assertEquals(config.pipeColor, R.color.andes_blue_ml_500.toAndesColor())
    }
}
