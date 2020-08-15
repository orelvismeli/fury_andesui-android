package com.mercadolibre.android.andesui.progress

import android.os.Build
import com.mercadolibre.android.andesui.BuildConfig
import com.mercadolibre.android.andesui.progress.size.AndesLargeProgressSize
import com.mercadolibre.android.andesui.progress.size.AndesMediumProgressSize
import com.mercadolibre.android.andesui.progress.size.AndesSmallPogressSize
import com.mercadolibre.android.andesui.progress.size.AndesXLargeProgressSize
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(constants = BuildConfig::class, sdk = [Build.VERSION_CODES.LOLLIPOP])
class AndesLargeButtonSizeTest {
    private var andesXLargeProgressSize = Mockito.spy(AndesXLargeProgressSize())
    private var andesLargeProgressSize = Mockito.spy(AndesLargeProgressSize())
    private var andesMediumProgressSize = Mockito.spy(AndesMediumProgressSize())
    private var andesSmallPogressSize = Mockito.spy(AndesSmallPogressSize())
    private var context = RuntimeEnvironment.application

    @Test
    fun `XLarge progress size`() {
        assertEquals(andesXLargeProgressSize.size(context), 48F)
    }

    @Test
    fun `Large progress size`() {
        assertEquals(andesLargeProgressSize.size(context), 32F)
    }

    @Test
    fun `Medium progress size`() {
        assertEquals(andesMediumProgressSize.size(context), 24F)
    }

    @Test
    fun `Small progress size`() {
        assertEquals(andesSmallPogressSize.size(context), 16F)
    }

}