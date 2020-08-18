package com.mercadolibre.android.andesui.progress

import android.os.Build
import com.mercadolibre.android.andesui.BuildConfig
import com.mercadolibre.android.andesui.R
import com.mercadolibre.android.andesui.progress.factory.AndesProgressAttrs
import com.mercadolibre.android.andesui.progress.factory.AndesProgressConfigurationFactory
import com.mercadolibre.android.andesui.progress.size.AndesProgressSize
import com.nhaarman.mockitokotlin2.spy
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(constants = BuildConfig::class, sdk = [Build.VERSION_CODES.LOLLIPOP])
class AndesProgressIndicatorIndeterminateTest {
    private var context = RuntimeEnvironment.application
    private lateinit var andesProgress: AndesProgressIndicatorIndeterminate
    private lateinit var andesProgressAttrs: AndesProgressAttrs
    private val configFactory = spy(AndesProgressConfigurationFactory)

    @Test
    fun `Only context constructor`() {
        andesProgress = AndesProgressIndicatorIndeterminate(context)
        assertEquals(andesProgress.size, AndesProgressSize.SMALL)
        assertEquals(andesProgress.tint, 0)
    }

    @Test
    fun `Size XLarge and color default`() {
        andesProgressAttrs = AndesProgressAttrs(AndesProgressSize.XLARGE, 0)
        var config = configFactory.create(context, andesProgressAttrs)

        assertEquals(config.size, 48F)
        assertEquals(config.tint, context.resources.getColor(R.color.andes_blue_ml_500))
    }

    @Test
    fun `Size Large and color default`() {
        andesProgressAttrs = AndesProgressAttrs(AndesProgressSize.LARGE, 0)
        var config = configFactory.create(context, andesProgressAttrs)

        assertEquals(config.size, 32F)
        assertEquals(config.tint, context.resources.getColor(R.color.andes_blue_ml_500))
    }

    @Test
    fun `Size Medium and color default`() {
        andesProgressAttrs = AndesProgressAttrs(AndesProgressSize.MEDIUM, 0)
        var config = configFactory.create(context, andesProgressAttrs)

        assertEquals(config.size, 24F)
        assertEquals(config.tint, context.resources.getColor(R.color.andes_blue_ml_500))
    }

    @Test
    fun `Size Small and color default`() {
        andesProgressAttrs = AndesProgressAttrs(AndesProgressSize.SMALL, 0)
        var config = configFactory.create(context, andesProgressAttrs)

        assertEquals(config.size, 16F)
        assertEquals(config.tint, context.resources.getColor(R.color.andes_blue_ml_500))
    }

    @Test
    fun `Size Small and color `() {
        andesProgressAttrs = AndesProgressAttrs(AndesProgressSize.SMALL,
                context.resources.getColor(R.color.andes_yellow_ml_500))
        var config = configFactory.create(context, andesProgressAttrs)

        assertEquals(config.size, 16F)
        assertEquals(config.tint, context.resources.getColor(R.color.andes_yellow_ml_500))
    }
}
