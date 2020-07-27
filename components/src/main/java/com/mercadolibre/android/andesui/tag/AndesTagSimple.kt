package com.mercadolibre.android.andesui.tag

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.support.constraint.ConstraintLayout
import android.support.constraint.ConstraintSet
import android.util.AttributeSet
import android.util.Log
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import com.mercadolibre.android.andesui.R
import com.mercadolibre.android.andesui.tag.factory.AndesSimpleTagConfigurationFactory
import com.mercadolibre.android.andesui.tag.factory.AndesTagSimpleAttrs
import com.mercadolibre.android.andesui.tag.factory.AndesTagSimpleAttrsParser
import com.mercadolibre.android.andesui.tag.factory.AndesTagSimpleConfiguration
import com.mercadolibre.android.andesui.tag.leftcontent.*
import com.mercadolibre.android.andesui.tag.rightcontent.AndesTagRightContent
import com.mercadolibre.android.andesui.tag.rightcontent.RightContent
import com.mercadolibre.android.andesui.tag.rightcontent.RightContentDismiss
import com.mercadolibre.android.andesui.tag.size.AndesTagSize
import com.mercadolibre.android.andesui.tag.type.AndesTagType
import com.mercadolibre.android.andesui.typeface.getFontOrDefault
import kotlinx.android.synthetic.main.andes_layout_simple_tag.view.*

class AndesTagSimple : ConstraintLayout {

    /**
     * Getter and setter for [type].
     */
    var type: AndesTagType
        get() = andesTagAttrs.andesTagType
        set(value) {
            andesTagAttrs = andesTagAttrs.copy(andesTagType = value)
            setupBackgroundComponents(createConfig())
        }

    /**
     * Getter and setter for [size].
     */
    var size: AndesTagSize
        get() = andesTagAttrs.andesTagSize
        set(value) {
            andesTagAttrs = andesTagAttrs.copy(andesTagSize = value)
            setupBackgroundComponents(createConfig())
            setupTitleComponent(createConfig())
        }

    /**
     * Getter and setter for [text].
     */
    var text: String?
        get() = andesTagAttrs.andesSimpleTagText
        set(value) {
            andesTagAttrs = andesTagAttrs.copy(andesSimpleTagText = value)
            setupTitleComponent(createConfig())
        }

    /**
     * Getter and setter for [leftContent].
     */
    var leftContent: LeftContent?
        get() = andesTagAttrs.leftContentData
        set(value) {
            if (size == AndesTagSize.SMALL) {
                andesTagAttrs = andesTagAttrs.copy(leftContentData = null)
                andesTagAttrs = andesTagAttrs.copy(leftContent = null)
                Log.e("TAG", "LeftContent can only be used with tag large")
            } else {
                val andesTagLeftContent = when {
                    value?.dot != null -> AndesTagLeftContent.DOT
                    value?.icon != null -> AndesTagLeftContent.ICON
                    value?.image != null -> AndesTagLeftContent.IMAGE
                    else -> AndesTagLeftContent.NONE
                }
                andesTagAttrs = andesTagAttrs.copy(leftContentData = value)
                andesTagAttrs = andesTagAttrs.copy(leftContent = andesTagLeftContent)
            }

            val config = createConfig()
            setupLeftContent(config)
            setupTitleComponent(config)
        }

    /**
     * Getter and setter for [isDismissable].
     */
    var isDismissable: Boolean
        get() = andesTagAttrs.rightContent == AndesTagRightContent.DISMISS
        set(value) {
            if (value) {
                andesTagAttrs = andesTagAttrs.copy(rightContentData = RightContent(dismiss = RightContentDismiss()))
                andesTagAttrs = andesTagAttrs.copy(rightContent = AndesTagRightContent.DISMISS)
            } else {
                andesTagAttrs = andesTagAttrs.copy(rightContentData = null)
                andesTagAttrs = andesTagAttrs.copy(rightContent = null)
            }

            val config = createConfig()
            setupRightContent(config)
            setupTitleComponent(config)
        }

    fun setupDismsissableCallback(onClickListener: OnClickListener) {
        if (isDismissable && andesTagAttrs.rightContentData?.dismiss != null) {
            andesTagAttrs.rightContentData?.dismiss?.onClickListener = onClickListener
        }
    }

    private lateinit var andesTagAttrs: AndesTagSimpleAttrs
    private lateinit var containerTag: ConstraintLayout

    @Suppress("unused")
    private constructor(context: Context) : super(context) {
        throw IllegalStateException(
                "Constructor without parameters in Andes Badge is not allowed. You must provide some attributes."
        )
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initAttrs(attrs)
    }

    @Suppress("unused")
    constructor(
        context: Context,
        type: AndesTagType = TYPE_DEFAULT,
        size: AndesTagSize = SIZE_DEFAULT,
        text: String? = TEXT_DEFAULT
    ) : super(context) {
        initAttrs(type, size, text)
    }

    /**
     * Sets the proper [config] for this message based on the [attrs] received via XML.
     * @param attrs attributes from the XML.
     */
    private fun initAttrs(attrs: AttributeSet?) {
        andesTagAttrs = AndesTagSimpleAttrsParser.parse(context, attrs)
        val config = AndesSimpleTagConfigurationFactory.create(andesTagAttrs)
        setupComponents(config)
    }

    private fun initAttrs(
        type: AndesTagType,
        size: AndesTagSize,
        text: String?,
        leftContent: AndesTagLeftContent? = null,
        leftContentData: LeftContent? = null
    ) {
        andesTagAttrs = AndesTagSimpleAttrs(type, size, text, leftContentData, leftContent)
        val config = AndesSimpleTagConfigurationFactory.create(andesTagAttrs)
        setupComponents(config)
    }

    /**
     * Responsible for setting up all properties of each component that is part of this badge.
     * Is like a choreographer ;)
     */
    private fun setupComponents(config: AndesTagSimpleConfiguration) {
        initComponents()
        setupViewId()

        setupBackgroundComponents(config)
        setupTitleComponent(config)
        setupLeftContent(config)
        setupRightContent(config)
    }

    /**
     * Creates all the views that are part of this badge.
     * After a view is created then a view id is added to it.
     */
    private fun initComponents() {
        val container = LayoutInflater.from(context).inflate(R.layout.andes_layout_simple_tag, this)
        containerTag = container.findViewById(R.id.andes_tag_container)
    }

    /**
     * Sets a view id to this badge.
     */
    private fun setupViewId() {
        if (id == NO_ID) { // If this view has no id
            id = View.generateViewId()
        }
    }

    private fun setupBackgroundComponents(config: AndesTagSimpleConfiguration) {
        val shape = GradientDrawable()
        shape.cornerRadius = size.size.border(context)
        shape.setColor(config.backgroundColor.colorInt(context))
        val borderSize = resources.getDimension(R.dimen.andes_tag_border)
        shape.setStroke(borderSize.toInt(), config.borderColor.colorInt(context))
        background = shape

        containerTag.minHeight = size.size.height(context).toInt()
        containerTag.maxHeight = size.size.height(context).toInt()
        containerTag.minWidth = size.size.height(context).toInt()
    }

    /**
     * Gets data from the config and sets to the title component of this tag.
     */
    private fun setupTitleComponent(config: AndesTagSimpleConfiguration) {
        if (config.text == null || config.text.isEmpty()) {
            containerTag.visibility = View.GONE
        } else {
            containerTag.visibility = View.VISIBLE

            simpleTagText.text = config.text
            simpleTagText.typeface = context.getFontOrDefault(R.font.andes_font_regular)
            simpleTagText.setTextSize(TypedValue.COMPLEX_UNIT_PX, size.size.textSize(context))
            simpleTagText.setTextColor(config.textColor.colorInt(context))

            val constraintSet = ConstraintSet()
            constraintSet.clone(containerTag)
            if (leftContent == null) {
                constraintSet.setMargin(
                        R.id.simpleTagText,
                        ConstraintSet.START,
                        size.size.leftMargin(context)
                )
            } else {
                constraintSet.setMargin(
                        R.id.simpleTagText,
                        ConstraintSet.START,
                        config.leftContent!!.content.rightMargin(context)
                )
            }
            if (config.rightContent == null || config.rightContent == AndesTagRightContent.NONE) {
                constraintSet.setMargin(R.id.simpleTagText, ConstraintSet.END, size.size.rightMargin(context))
            } else {
                constraintSet.setMargin(
                        R.id.simpleTagText,
                        ConstraintSet.END,
                        config.rightContent.content.leftMargin(context, size)
                )
            }
            constraintSet.applyTo(containerTag)
        }
    }

    private fun setupLeftContent(config: AndesTagSimpleConfiguration) {
        val leftContent = findViewById<FrameLayout>(R.id.leftContent)
        if (config.leftContent != null && config.leftContentData != null && config.leftContent != AndesTagLeftContent.NONE) {
            leftContent.removeAllViews()
            leftContent.addView(config.leftContent.content.view(context, config.leftContentData))
            leftContent.visibility = View.VISIBLE
        } else {
            leftContent.visibility = View.GONE
        }
    }

    private fun setupRightContent(config: AndesTagSimpleConfiguration) {
        val rightContent = findViewById<FrameLayout>(R.id.rightContent)
        if (config.rightContent != null && config.rightContent != AndesTagRightContent.NONE) {
            rightContent.removeAllViews()
            rightContent.addView(config.rightContent.content.view(
                    context,
                    config.dismissColor,
                    config.rightContentData!!,
                    OnClickListener {
                        containerTag.visibility = View.GONE
                        if (config.rightContentData.dismiss?.onClickListener != null) {
                            config.rightContentData.dismiss?.onClickListener!!.onClick(this)
                        }
                    }
            ))

            val params = rightContent.layoutParams as MarginLayoutParams
            params.marginEnd = config.rightContent.content.rightMargin(context, size)
            rightContent.layoutParams = params

            rightContent.visibility = View.VISIBLE
        } else {
            rightContent.visibility = View.GONE
        }
    }

    private fun createConfig() = AndesSimpleTagConfigurationFactory.create(andesTagAttrs)

    companion object {
        private val TYPE_DEFAULT = AndesTagType.NEUTRAL
        private val SIZE_DEFAULT = AndesTagSize.LARGE
        private val TEXT_DEFAULT = null
    }
}
