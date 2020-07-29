package com.mercadolibre.android.andesui.demoapp.feature

import android.content.Context
import android.os.Bundle
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ScrollView
import android.widget.Spinner
import android.widget.Toast
import com.mercadolibre.android.andesui.button.AndesButton
import com.mercadolibre.android.andesui.radiobutton.AndesRadioButton
import com.mercadolibre.android.andesui.radiobutton.align.AndesRadioButtonAlign
import com.mercadolibre.android.andesui.radiobutton.status.AndesRadioButtonStatus
import com.mercadolibre.android.andesui.radiobutton.type.AndesRadioButtonType
import com.mercadolibre.android.andesui.demoapp.AndesSpecs
import com.mercadolibre.android.andesui.demoapp.PageIndicator
import com.mercadolibre.android.andesui.demoapp.R
import com.mercadolibre.android.andesui.demoapp.launchSpecs
import com.mercadolibre.android.andesui.radiobuttongroup.AndesRadioButtonGroup
import com.mercadolibre.android.andesui.radiobuttongroup.RadioButtonItem
import com.mercadolibre.android.andesui.textfield.AndesTextfield
import com.mercadolibre.android.andesui.textfield.state.AndesTextfieldState

class RadioButtonShowcaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.andesui_showcase_main)

        setSupportActionBar(findViewById(R.id.andesui_nav_bar))
        supportActionBar?.title = resources.getString(R.string.andesui_demoapp_screen_radiobutton)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val viewPager = findViewById<ViewPager>(R.id.andesui_viewpager)
        viewPager.adapter = AndesShowcasePagerAdapter(this)
        val indicator = findViewById<PageIndicator>(R.id.page_indicator)
        indicator.attach(viewPager)
    }

    class AndesShowcasePagerAdapter(private val context: Context) : PagerAdapter() {

        var views: List<View>

        init {
            views = initViews()
        }

        override fun instantiateItem(container: ViewGroup, position: Int): View {
            container.addView(views[position])
            return views[position]
        }

        override fun destroyItem(container: ViewGroup, position: Int, view: Any) {
            container.removeView(view as View?)
        }

        override fun isViewFromObject(view: View, other: Any): Boolean {
            return view == other
        }

        override fun getCount(): Int = views.size

        private fun initViews(): List<View> {
            val inflater = LayoutInflater.from(context)

            val staticRadioButtonLayout = addStaticRadioButton(inflater)
            val dynamicRadioButtonLayout = addDynamicRadioButton(inflater)
            val dynamicRadioButtonGroupLayout = adddynamicRadioButtonGroupLayout(inflater)

            return listOf(dynamicRadioButtonLayout, staticRadioButtonLayout, dynamicRadioButtonGroupLayout)
        }

        private fun addDynamicRadioButton(inflater: LayoutInflater): View {
            val layoutRadioButton = inflater.inflate(
                    R.layout.andesui_dynamic_radiobutton_showcase, null, false
            ) as ScrollView

            val radioButton: AndesRadioButton = layoutRadioButton.findViewById(R.id.andesRadioButton)

            val spinnerType: Spinner = layoutRadioButton.findViewById(R.id.spinnerType)
            ArrayAdapter.createFromResource(
                    context, R.array.type_radiobutton_spinner, android.R.layout.simple_spinner_item)
                    .also { adapter ->
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                        spinnerType.adapter = adapter
                    }

            val spinnerAlign: Spinner = layoutRadioButton.findViewById(R.id.spinnerAlign)
            ArrayAdapter.createFromResource(
                    context, R.array.align_radiobutton_spinner, android.R.layout.simple_spinner_item)
                    .also { adapter ->
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                        spinnerAlign.adapter = adapter
                    }

            val spinnerStatus: Spinner = layoutRadioButton.findViewById(R.id.spinnerStatus)
            ArrayAdapter.createFromResource(
                    context, R.array.status_radiobutton_spinner, android.R.layout.simple_spinner_item)
                    .also { adapter ->
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                        spinnerStatus.adapter = adapter
                    }

            val clearButton: AndesButton = layoutRadioButton.findViewById(R.id.buttonClear)
            val changeButton: AndesButton = layoutRadioButton.findViewById(R.id.buttonUpdate)
            val andesTextfield: AndesTextfield = layoutRadioButton.findViewById(R.id.andesTextfield)

            clearButton.setOnClickListener {
                spinnerType.setSelection(0)
                spinnerAlign.setSelection(0)
                spinnerStatus.setSelection(0)

                andesTextfield.text = ""
                andesTextfield.state = AndesTextfieldState.IDLE
                andesTextfield.helper = null

                radioButton.align = AndesRadioButtonAlign.LEFT
                radioButton.type = AndesRadioButtonType.IDLE
                radioButton.status = AndesRadioButtonStatus.UNSELECTED
                radioButton.text = context.resources.getString(R.string.andes_radiobutton_text)
            }

            changeButton.setOnClickListener {
                if (andesTextfield.text.isNullOrEmpty()) {
                    andesTextfield.state = AndesTextfieldState.ERROR
                    andesTextfield.helper = "Este campo es requerido"
                    return@setOnClickListener
                } else {
                    andesTextfield.state = AndesTextfieldState.IDLE
                    andesTextfield.helper = null
                }

                val type = when (spinnerType.selectedItem) {
                    "Idle" -> AndesRadioButtonType.IDLE
                    "Error" -> AndesRadioButtonType.ERROR
                    "Disabled" -> AndesRadioButtonType.DISABLED
                    else -> AndesRadioButtonType.IDLE
                }
                val align = when (spinnerAlign.selectedItem) {
                    "Left" -> AndesRadioButtonAlign.LEFT
                    "Right" -> AndesRadioButtonAlign.RIGHT
                    else -> AndesRadioButtonAlign.LEFT
                }
                val status = when (spinnerStatus.selectedItem) {
                    "Unselected" -> AndesRadioButtonStatus.UNSELECTED
                    "Selected" -> AndesRadioButtonStatus.SELECTED
                    else -> AndesRadioButtonStatus.UNSELECTED
                }
                radioButton.align = align
                radioButton.type = type
                radioButton.status = status
                radioButton.text = andesTextfield.text
            }

            return layoutRadioButton
        }

        private fun addStaticRadioButton(inflater: LayoutInflater): View {
            val layoutRadioButton = inflater.inflate(
                    R.layout.andesui_radiobutton_showcase, null, false
            ) as ScrollView

            layoutRadioButton.findViewById<AndesButton>(
                    R.id.andesui_demoapp_andes_radiobutton_specs_button
            ).setOnClickListener {
                launchSpecs(it.context, AndesSpecs.RADIOBUTTON)
            }

            return layoutRadioButton
        }

        private fun adddynamicRadioButtonGroupLayout(inflater: LayoutInflater): View {
            val layoutRadioButton = inflater.inflate(
                    R.layout.andesui_dynamic_radiobuttongroup_showcase, null, false
            ) as ScrollView

            val radioButtons = arrayListOf<RadioButtonItem>()
            radioButtons.add(RadioButtonItem("item 1", AndesRadioButtonType.DISABLED))
            radioButtons.add(RadioButtonItem("item 2", AndesRadioButtonType.IDLE))
            radioButtons.add(RadioButtonItem("item 3", AndesRadioButtonType.IDLE))
            radioButtons.add(RadioButtonItem("item 4", AndesRadioButtonType.IDLE))
            radioButtons.add(RadioButtonItem("item 5", AndesRadioButtonType.IDLE))

            val radioButtonGroup = layoutRadioButton.findViewById<AndesRadioButtonGroup>(R.id.radioButtonGroup1)
            radioButtonGroup.selected = 1
            radioButtonGroup.radioButtons = radioButtons
            radioButtonGroup.setupCallback(object : AndesRadioButtonGroup.OnRadioButtonCheckedChanged {
                override fun onRadioButtonCheckedChanged(index: Int) {
                    Toast.makeText(context, "Radiobutton clicked, index: $index", Toast.LENGTH_LONG).show()
                }
            })

            val radioButtonGroupHorizontal = layoutRadioButton.findViewById<AndesRadioButtonGroup>(R.id.radioButtonGroup2)
            radioButtonGroupHorizontal.selected = 1
            radioButtonGroupHorizontal.radioButtons = radioButtons
            radioButtonGroupHorizontal.setupCallback(object : AndesRadioButtonGroup.OnRadioButtonCheckedChanged {
                override fun onRadioButtonCheckedChanged(index: Int) {
                    Toast.makeText(context, "Radiobutton clicked, index: $index", Toast.LENGTH_LONG).show()
                }
            })

            return layoutRadioButton
        }
    }
}
