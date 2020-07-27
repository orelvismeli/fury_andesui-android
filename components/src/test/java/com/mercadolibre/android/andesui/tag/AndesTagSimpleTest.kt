package com.mercadolibre.android.andesui.tag

import android.os.Build
import com.mercadolibre.android.andesui.BuildConfig
import com.mercadolibre.android.andesui.R
import com.mercadolibre.android.andesui.color.toAndesColor
import com.mercadolibre.android.andesui.tag.factory.AndesSimpleTagConfigurationFactory
import com.mercadolibre.android.andesui.tag.factory.AndesTagSimpleAttrs
import com.mercadolibre.android.andesui.tag.leftcontent.AndesTagLeftContent
import com.mercadolibre.android.andesui.tag.leftcontent.LeftContent
import com.mercadolibre.android.andesui.tag.leftcontent.LeftContentDot
import com.mercadolibre.android.andesui.tag.size.AndesTagSize
import com.mercadolibre.android.andesui.tag.type.AndesTagType
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.spy
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(constants = BuildConfig::class, sdk = [Build.VERSION_CODES.LOLLIPOP])
class AndesTagSimpleTest {

    private var context = RuntimeEnvironment.application
    private val configFactory = spy(AndesSimpleTagConfigurationFactory)
    private lateinit var attrs: AndesTagSimpleAttrs

    @Test
    fun `Simple, Large, Success background color`() {
        attrs = AndesTagSimpleAttrs(AndesTagType.SUCCESS, AndesTagSize.LARGE, "Body")
        val config = configFactory.create(attrs)
        assertEquals(config.backgroundColor, R.color.andes_green_100.toAndesColor())
    }

    @Test
    fun `Simple, Large, Error background color`() {
        attrs = AndesTagSimpleAttrs(AndesTagType.ERROR, AndesTagSize.LARGE, "Body")
        val config = configFactory.create(attrs)
        assertEquals(config.backgroundColor, R.color.andes_red_100.toAndesColor())
    }

    @Test
    fun `Simple, Large, Warning background color`() {
        attrs = AndesTagSimpleAttrs(AndesTagType.WARNING, AndesTagSize.LARGE, "Body")
        val config = configFactory.create(attrs)
        assertEquals(config.backgroundColor, R.color.andes_orange_100.toAndesColor())
    }

    @Test
    fun `Simple, Large, Highlight background color`() {
        attrs = AndesTagSimpleAttrs(AndesTagType.HIGHLIGHT, AndesTagSize.LARGE, "Body")
        val config = configFactory.create(attrs)
        assertEquals(config.backgroundColor, R.color.andes_accent_color_100.toAndesColor())
    }

    @Test
    fun `Simple, Large, Default background color`() {
        attrs = AndesTagSimpleAttrs(AndesTagType.NEUTRAL, AndesTagSize.LARGE, "Body")
        val config = configFactory.create(attrs)
        assertEquals(config.backgroundColor, R.color.andes_transparent.toAndesColor())
    }

    @Test
    fun `Simple, Small, Success border color`() {
        attrs = AndesTagSimpleAttrs(AndesTagType.SUCCESS, AndesTagSize.SMALL, "Body")
        val config = configFactory.create(attrs)
        assertEquals(config.borderColor, R.color.andes_green_500.toAndesColor())
    }

    @Test
    fun `Simple, Small, Error border color`() {
        attrs = AndesTagSimpleAttrs(AndesTagType.ERROR, AndesTagSize.SMALL, "Body")
        val config = configFactory.create(attrs)
        assertEquals(config.borderColor, R.color.andes_red_500.toAndesColor())
    }

    @Test
    fun `Simple, Small, Warning border color`() {
        attrs = AndesTagSimpleAttrs(AndesTagType.WARNING, AndesTagSize.SMALL, "Body")
        val config = configFactory.create(attrs)
        assertEquals(config.borderColor, R.color.andes_orange_500.toAndesColor())
    }

    @Test
    fun `Simple, Small, Highlight border color`() {
        attrs = AndesTagSimpleAttrs(AndesTagType.HIGHLIGHT, AndesTagSize.SMALL, "Body")
        val config = configFactory.create(attrs)
        assertEquals(config.borderColor, R.color.andes_accent_color_500.toAndesColor())
    }

    @Test
    fun `Simple, Small, Default border color`() {
        attrs = AndesTagSimpleAttrs(AndesTagType.NEUTRAL, AndesTagSize.SMALL, "Body")
        val config = configFactory.create(attrs)
        assertEquals(config.borderColor, R.color.andes_gray_250_solid.toAndesColor())
    }

    @Test
    fun `Simple, Small, Success text color`() {
        attrs = AndesTagSimpleAttrs(AndesTagType.SUCCESS, AndesTagSize.SMALL, "Body")
        val config = configFactory.create(attrs)
        assertEquals(config.textColor, R.color.andes_green_500.toAndesColor())
    }

    @Test
    fun `Simple, Small, Error text color`() {
        attrs = AndesTagSimpleAttrs(AndesTagType.ERROR, AndesTagSize.SMALL, "Body")
        val config = configFactory.create(attrs)
        assertEquals(config.textColor, R.color.andes_red_500.toAndesColor())
    }

    @Test
    fun `Simple, Small, Warning text color`() {
        attrs = AndesTagSimpleAttrs(AndesTagType.WARNING, AndesTagSize.SMALL, "Body")
        val config = configFactory.create(attrs)
        assertEquals(config.textColor, R.color.andes_orange_500.toAndesColor())
    }

    @Test
    fun `Simple, Small, Highlight text color`() {
        attrs = AndesTagSimpleAttrs(AndesTagType.HIGHLIGHT, AndesTagSize.SMALL, "Body")
        val config = configFactory.create(attrs)
        assertEquals(config.textColor, R.color.andes_accent_color_500.toAndesColor())
    }

    @Test
    fun `Simple, Small, Default text color`() {
        attrs = AndesTagSimpleAttrs(AndesTagType.NEUTRAL, AndesTagSize.SMALL, "Body")
        val config = configFactory.create(attrs)
        assertEquals(config.textColor, R.color.andes_gray_800_solid.toAndesColor())
    }

    @Test
    fun `Simple, Default title text`() {
        attrs = AndesTagSimpleAttrs(AndesTagType.NEUTRAL, AndesTagSize.SMALL, "Body")
        val config = configFactory.create(attrs)
        assertEquals(config.text, "Body")
    }

    @Test
    fun `Simple, Highlight title text`() {
        attrs = AndesTagSimpleAttrs(AndesTagType.HIGHLIGHT, AndesTagSize.SMALL, "Body")
        val config = configFactory.create(attrs)
        assertEquals(config.text, "Body")
    }

    @Test
    fun `Simple, Warning title text`() {
        attrs = AndesTagSimpleAttrs(AndesTagType.WARNING, AndesTagSize.SMALL, "Body")
        val config = configFactory.create(attrs)
        assertEquals(config.text, "Body")
    }

    @Test
    fun `Simple, Error title text`() {
        attrs = AndesTagSimpleAttrs(AndesTagType.ERROR, AndesTagSize.SMALL, "Body")
        val config = configFactory.create(attrs)
        assertEquals(config.text, "Body")
    }

    @Test
    fun `Simple, Success title text`() {
        attrs = AndesTagSimpleAttrs(AndesTagType.SUCCESS, AndesTagSize.SMALL, "Body")
        val config = configFactory.create(attrs)
        assertEquals(config.text, "Body")
    }

    @Test
    fun `Simple, Success left content size`() {
        val leftContent = LeftContent(dot = LeftContentDot("#BABABA", "AB", "#FFFFFF"))
        attrs = AndesTagSimpleAttrs(
                AndesTagType.SUCCESS,
                AndesTagSize.LARGE,
                "Body",
                leftContent,
                AndesTagLeftContent.DOT
        )
        val config = configFactory.create(attrs)
        assertEquals(config.leftContent?.content!!.size(context), context.resources.getDimension(R.dimen.andes_tag_icon_size).toInt())
    }

    @Test
    fun `Simple, Error left content size`() {
        val leftContent = LeftContent(dot = LeftContentDot("#BABABA", "AB", "#FFFFFF"))
        attrs = AndesTagSimpleAttrs(
                AndesTagType.ERROR,
                AndesTagSize.LARGE,
                "Body",
                leftContent,
                AndesTagLeftContent.DOT
        )
        val config = configFactory.create(attrs)
        assertEquals(config.leftContent?.content!!.size(context), context.resources.getDimension(R.dimen.andes_tag_icon_size).toInt())
    }

    @Test
    fun `Simple, Warning left content size`() {
        val leftContent = LeftContent(dot = LeftContentDot("#BABABA", "AB", "#FFFFFF"))
        attrs = AndesTagSimpleAttrs(
                AndesTagType.WARNING,
                AndesTagSize.LARGE,
                "Body",
                leftContent,
                AndesTagLeftContent.DOT
        )
        val config = configFactory.create(attrs)
        assertEquals(config.leftContent?.content!!.size(context), context.resources.getDimension(R.dimen.andes_tag_icon_size).toInt())
    }

    @Test
    fun `Simple, Highlight left content size`() {
        val leftContent = LeftContent(dot = LeftContentDot("#BABABA", "AB", "#FFFFFF"))
        attrs = AndesTagSimpleAttrs(
                AndesTagType.HIGHLIGHT,
                AndesTagSize.LARGE,
                "Body",
                leftContent,
                AndesTagLeftContent.DOT
        )
        val config = configFactory.create(attrs)
        assertEquals(config.leftContent?.content!!.size(context), context.resources.getDimension(R.dimen.andes_tag_icon_size).toInt())
    }

    @Test
    fun `Simple, Default left content size`() {
        val leftContent = LeftContent(dot = LeftContentDot("#BABABA", "AB", "#FFFFFF"))
        attrs = AndesTagSimpleAttrs(
                AndesTagType.NEUTRAL,
                AndesTagSize.LARGE,
                "Body",
                leftContent,
                AndesTagLeftContent.DOT
        )
        val config = configFactory.create(attrs)
        assertEquals(config.leftContent?.content!!.size(context), context.resources.getDimension(R.dimen.andes_tag_icon_size).toInt())
    }

    @Test
    fun `Simple, Success border size`() {
        val leftContent = LeftContent(dot = LeftContentDot("#BABABA", "AB", "#FFFFFF"))
        attrs = AndesTagSimpleAttrs(
                AndesTagType.SUCCESS,
                AndesTagSize.LARGE,
                "Body",
                leftContent,
                AndesTagLeftContent.DOT
        )
        val config = configFactory.create(attrs)
        assertEquals(config.leftContent?.content!!.border(context), context.resources.getDimension(R.dimen.andes_tag_icon_radius))
    }

    @Test
    fun `Simple, Error border size`() {
        val leftContent = LeftContent(dot = LeftContentDot("#BABABA", "AB", "#FFFFFF"))
        attrs = AndesTagSimpleAttrs(
                AndesTagType.ERROR,
                AndesTagSize.LARGE,
                "Body",
                leftContent,
                AndesTagLeftContent.DOT
        )
        val config = configFactory.create(attrs)
        assertEquals(config.leftContent?.content!!.border(context), context.resources.getDimension(R.dimen.andes_tag_icon_radius))
    }

    @Test
    fun `Simple, Warning border size`() {
        val leftContent = LeftContent(dot = LeftContentDot("#BABABA", "AB", "#FFFFFF"))
        attrs = AndesTagSimpleAttrs(
                AndesTagType.WARNING,
                AndesTagSize.LARGE,
                "Body",
                leftContent,
                AndesTagLeftContent.DOT
        )
        val config = configFactory.create(attrs)
        assertEquals(
                config.leftContent?.content!!.border(context),
                context.resources.getDimension(R.dimen.andes_tag_icon_radius)
        )
    }

    @Test
    fun `Simple, Highlight border size`() {
        val leftContent = LeftContent(dot = LeftContentDot("#BABABA", "AB", "#FFFFFF"))
        attrs = AndesTagSimpleAttrs(
                AndesTagType.HIGHLIGHT,
                AndesTagSize.LARGE,
                "Body",
                leftContent,
                AndesTagLeftContent.DOT
        )
        val config = configFactory.create(attrs)
        assertEquals(config.leftContent?.content!!.border(context), context.resources.getDimension(R.dimen.andes_tag_icon_radius))
    }

    @Test
    fun `Simple, Default border size`() {
        val leftContent = LeftContent(dot = LeftContentDot("#BABABA", "AB", "#FFFFFF"))
        attrs = AndesTagSimpleAttrs(
                AndesTagType.NEUTRAL,
                AndesTagSize.LARGE,
                "Body",
                leftContent,
                AndesTagLeftContent.DOT
        )
        val config = configFactory.create(attrs)
        assertEquals(config.leftContent?.content!!.border(context), context.resources.getDimension(R.dimen.andes_tag_icon_radius))
    }
}
