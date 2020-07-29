package com.mercadolibre.android.andesui.radiobutton

import android.os.Build
import com.mercadolibre.android.andesui.BuildConfig
import com.mercadolibre.android.andesui.radiobutton.align.AndesRadioButtonAlign
import com.mercadolibre.android.andesui.radiobutton.type.AndesRadioButtonType
import com.mercadolibre.android.andesui.radiobuttongroup.RadioButtonItem
import com.mercadolibre.android.andesui.radiobuttongroup.distribution.AndesRadioButtonGroupDistribution
import com.mercadolibre.android.andesui.radiobuttongroup.factory.AndesRadioButtonGroupAttrs
import com.mercadolibre.android.andesui.radiobuttongroup.factory.AndesRadioButtonGroupConfigurationFactory
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.spy
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(constants = BuildConfig::class, sdk = [Build.VERSION_CODES.LOLLIPOP])
class AndesRadioButtonGroupTest {

    private var context = RuntimeEnvironment.application
    private val configFactory = spy(AndesRadioButtonGroupConfigurationFactory)
    private lateinit var attrs: AndesRadioButtonGroupAttrs
    private val radioButtons = arrayListOf(
            RadioButtonItem("Item 1", AndesRadioButtonType.IDLE),
            RadioButtonItem("Item 2", AndesRadioButtonType.IDLE),
            RadioButtonItem("Item 3", AndesRadioButtonType.IDLE),
            RadioButtonItem("Item 4", AndesRadioButtonType.IDLE)
    )

    @Test
    fun `RadioButtonGroup, Vertical`() {
        attrs = AndesRadioButtonGroupAttrs(
                andesRadioButtonGroupAlign = AndesRadioButtonAlign.LEFT,
                andesRadioButtonGroupDistribution = AndesRadioButtonGroupDistribution.VERTICAL,
                andesRadioButtonGroupSelected = 1,
                andesRadioButtonGroupRadioButtons = radioButtons
        )
        val config = configFactory.create(attrs)
        assertEquals(
                config.distribution,
                AndesRadioButtonGroupDistribution.VERTICAL
        )
    }

    @Test
    fun `RadioButtonGroup, Horizontal`() {
        attrs = AndesRadioButtonGroupAttrs(
                andesRadioButtonGroupAlign = AndesRadioButtonAlign.LEFT,
                andesRadioButtonGroupDistribution = AndesRadioButtonGroupDistribution.HORIZONTAL,
                andesRadioButtonGroupSelected = 1,
                andesRadioButtonGroupRadioButtons = radioButtons
        )
        val config = configFactory.create(attrs)
        assertEquals(
                config.distribution,
                AndesRadioButtonGroupDistribution.HORIZONTAL
        )
    }

    @Test
    fun `RadioButtonGroup, AlignLeft`() {
        attrs = AndesRadioButtonGroupAttrs(
                andesRadioButtonGroupAlign = AndesRadioButtonAlign.LEFT,
                andesRadioButtonGroupDistribution = AndesRadioButtonGroupDistribution.VERTICAL,
                andesRadioButtonGroupSelected = 1,
                andesRadioButtonGroupRadioButtons = radioButtons
        )
        val config = configFactory.create(attrs)
        assertEquals(config.align, AndesRadioButtonAlign.LEFT)
    }

    @Test
    fun `RadioButtonGroup, AlignRight`() {
        attrs = AndesRadioButtonGroupAttrs(
                andesRadioButtonGroupAlign = AndesRadioButtonAlign.RIGHT,
                andesRadioButtonGroupDistribution = AndesRadioButtonGroupDistribution.HORIZONTAL,
                andesRadioButtonGroupSelected = 1,
                andesRadioButtonGroupRadioButtons = radioButtons
        )
        val config = configFactory.create(attrs)
        assertEquals(config.align, AndesRadioButtonAlign.RIGHT)
    }

    @Test
    fun `RadioButtonGroup, ItemSelected`() {
        attrs = AndesRadioButtonGroupAttrs(
                andesRadioButtonGroupAlign = AndesRadioButtonAlign.RIGHT,
                andesRadioButtonGroupDistribution = AndesRadioButtonGroupDistribution.HORIZONTAL,
                andesRadioButtonGroupSelected = 1,
                andesRadioButtonGroupRadioButtons = radioButtons
        )
        val config = configFactory.create(attrs)
        assertEquals(config.selected, 1)
    }
}
