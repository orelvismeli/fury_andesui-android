package com.mercadolibre.android.andesui.radiobuttongroup

import android.content.Context
import android.support.annotation.Nullable
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import com.mercadolibre.android.andesui.R
import com.mercadolibre.android.andesui.radiobutton.AndesRadioButton
import com.mercadolibre.android.andesui.radiobutton.align.AndesRadioButtonAlign
import com.mercadolibre.android.andesui.radiobutton.status.AndesRadioButtonStatus
import com.mercadolibre.android.andesui.radiobuttongroup.distribution.AndesRadioButtonGroupDistribution
import com.mercadolibre.android.andesui.radiobuttongroup.factory.AndesRadioButtonGroupConfigurationFactory
import com.mercadolibre.android.andesui.radiobuttongroup.factory.AndesRadioButtonGroupAttrs
import com.mercadolibre.android.andesui.radiobuttongroup.factory.AndesRadioButtonGroupAttrsParser
import com.mercadolibre.android.andesui.radiobuttongroup.factory.AndesRadioButtonGroupConfiguration

class AndesRadioButtonGroup : LinearLayout {

    /**
     * Getter and setter for [align].
     */
    var align: AndesRadioButtonAlign
        get() = andesRadioButtonGroupAttrs.andesRadioButtonGroupAlign
        set(value) {
            andesRadioButtonGroupAttrs = andesRadioButtonGroupAttrs.copy(andesRadioButtonGroupAlign = value)
            setupRadioButtons()
        }

    /**
     * Getter and setter for [distribution].
     */
    var distribution: AndesRadioButtonGroupDistribution
        get() = andesRadioButtonGroupAttrs.andesRadioButtonGroupDistribution
        set(value) {
            andesRadioButtonGroupAttrs = andesRadioButtonGroupAttrs.copy(andesRadioButtonGroupDistribution = value)
            setupOrientation(createConfig())
        }

    /**
     * Getter and setter for [selected].
     * Value -1 if there is no selected element yet.
     */
    var selected: Int
        get() = andesRadioButtonGroupAttrs.andesRadioButtonGroupSelected
        set(value) {
            andesRadioButtonGroupAttrs = andesRadioButtonGroupAttrs.copy(andesRadioButtonGroupSelected = value)
            setupSelected(createConfig())
        }

    /**
     * Getter and setter for [selected].
     */
    var radioButtons: ArrayList<RadioButtonItem>
        get() = andesRadioButtonGroupAttrs.andesRadioButtonGroupRadioButtons
        set(value) {
            andesRadioButtonGroupAttrs = andesRadioButtonGroupAttrs.copy(andesRadioButtonGroupRadioButtons = value)
            setupRadioButtons()
        }

    interface OnRadioButtonCheckedChanged {
        fun onRadioButtonCheckedChanged(index: Int)
    }

    /**
     * Setter [OnRadioButtonCheckedChanged].
     */
    fun setupCallback(@Nullable listener: OnRadioButtonCheckedChanged) {
        if (this.privateListener != listener) {
            this.privateListener = listener
        }
    }

    private var oldItemSelected: Int? = -1
    private var privateListener: OnRadioButtonCheckedChanged? = null
    private lateinit var andesRadioButtonGroupAttrs: AndesRadioButtonGroupAttrs

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initAttrs(attrs)
    }

    constructor(
        context: Context,
        align: AndesRadioButtonAlign,
        distribution: AndesRadioButtonGroupDistribution,
        radioButtons: ArrayList<RadioButtonItem>,
        selected: Int?
    ) : super(context) {
        val selectedValue = selected ?: ANDES_RADIOBUTTON_SELECTED_DEFAULT_VALUE
        initAttrs(align, distribution, selectedValue, radioButtons)
    }

    /**
     * Sets the proper [config] for this message based on the [attrs] received via XML.
     *
     * @param attrs attributes from the XML.
     */
    private fun initAttrs(attrs: AttributeSet?) {
        andesRadioButtonGroupAttrs = AndesRadioButtonGroupAttrsParser.parse(context, attrs)
        val config = AndesRadioButtonGroupConfigurationFactory.create(andesRadioButtonGroupAttrs)
        setupComponents(config)
    }

    private fun initAttrs(
        align: AndesRadioButtonAlign = ANDES_RADIOBUTTON_ALIGN_DEFAULT_VALUE,
        distribution: AndesRadioButtonGroupDistribution = ANDES_RADIOBUTTON_DISTRIBUTION_DEFAULT_VALUE,
        selected: Int = ANDES_RADIOBUTTON_SELECTED_DEFAULT_VALUE,
        radiobuttons: ArrayList<RadioButtonItem> = arrayListOf()
    ) {
        andesRadioButtonGroupAttrs = AndesRadioButtonGroupAttrs(align, distribution, selected, radiobuttons)
        val config = AndesRadioButtonGroupConfigurationFactory.create(andesRadioButtonGroupAttrs)
        setupComponents(config)
    }

    private fun setupComponents(config: AndesRadioButtonGroupConfiguration) {
        initComponents()
        setupViewId()

        setupOrientation(config)
        setupRadioButtons()
    }

    private fun initComponents() {
        orientation = VERTICAL
    }

    /**
     * Sets a view id to this radiobutton.
     */
    private fun setupViewId() {
        if (id == NO_ID) { // If this view has no id
            id = View.generateViewId()
        }
    }

    private fun setupOrientation(config: AndesRadioButtonGroupConfiguration) {
        orientation = when (config.distribution) {
            AndesRadioButtonGroupDistribution.VERTICAL -> VERTICAL
            AndesRadioButtonGroupDistribution.HORIZONTAL -> HORIZONTAL
        }
    }

    private fun setupRadioButtons() {
        removeAllViews()
        this.radioButtons.forEachIndexed { index, item ->
            val selectedValue = if (index == selected) {
                AndesRadioButtonStatus.SELECTED
            } else {
                AndesRadioButtonStatus.UNSELECTED
            }

            val andesRadioButton = AndesRadioButton(
                context,
                item.text,
                align,
                selectedValue,
                item.type
            )

            if (distribution == AndesRadioButtonGroupDistribution.VERTICAL) {
                andesRadioButton.setPadding(
                        0,
                        context.resources.getDimension(R.dimen.andes_radiobutton_padding_vertical).toInt(),
                        0,
                        context.resources.getDimension(R.dimen.andes_radiobutton_padding_vertical).toInt()
                )
            } else {
                andesRadioButton.setPadding(
                        context.resources.getDimension(R.dimen.andes_radiobutton_padding_vertical).toInt(),
                        0,
                        context.resources.getDimension(R.dimen.andes_radiobutton_padding_vertical).toInt(),
                        0
                )
            }

            andesRadioButton.setupCallback(OnClickListener {
                this.selected = index
            })

            addView(andesRadioButton)
        }
    }

    private fun setupSelected(config: AndesRadioButtonGroupConfiguration) {
        config.radioButtons.forEachIndexed { index, _ ->
            if (index == oldItemSelected) {
                (getChildAt(index) as AndesRadioButton).status = AndesRadioButtonStatus.UNSELECTED
            }
            if (index == selected) {
                (getChildAt(index) as AndesRadioButton).status = AndesRadioButtonStatus.SELECTED
            }
        }
        oldItemSelected = selected
        privateListener?.onRadioButtonCheckedChanged(selected)
    }

    private fun createConfig() = AndesRadioButtonGroupConfigurationFactory.create(andesRadioButtonGroupAttrs)

    companion object {
        private val ANDES_RADIOBUTTON_ALIGN_DEFAULT_VALUE = AndesRadioButtonAlign.LEFT
        private val ANDES_RADIOBUTTON_SELECTED_DEFAULT_VALUE = -1
        private val ANDES_RADIOBUTTON_DISTRIBUTION_DEFAULT_VALUE = AndesRadioButtonGroupDistribution.VERTICAL
    }
}
