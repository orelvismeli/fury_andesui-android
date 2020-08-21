package com.mercadolibre.android.andesui.demoapp.feature

import android.content.Context
import android.os.Bundle
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.mercadolibre.android.andesui.button.AndesButton
import com.mercadolibre.android.andesui.button.hierarchy.AndesButtonHierarchy
import com.mercadolibre.android.andesui.button.hierarchy.AndesButtonIcon
import com.mercadolibre.android.andesui.button.hierarchy.AndesButtonIconOrientation
import com.mercadolibre.android.andesui.button.size.AndesButtonSize
import com.mercadolibre.android.andesui.demoapp.AndesSpecs
import com.mercadolibre.android.andesui.demoapp.PageIndicator
import com.mercadolibre.android.andesui.demoapp.R
import com.mercadolibre.android.andesui.demoapp.launchSpecs

class ProgressShowcaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.andesui_showcase_main)

        setSupportActionBar(findViewById(R.id.andesui_nav_bar))
        supportActionBar?.title = resources.getString(R.string.andesui_demoapp_screen_button)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val viewPager = findViewById<ViewPager>(R.id.andesui_viewpager)
        viewPager.adapter = AndesShowcasePagerAdapter(this)
        val indicator = findViewById<PageIndicator>(R.id.page_indicator)
        indicator.attach(viewPager)

        val adapter = viewPager.adapter as AndesShowcasePagerAdapter
        //addProgress(adapter.views[0])
    }

    private fun addProgress(container: View) {
        val andesButtonSmall = AndesButton(
                this, AndesButtonSize.SMALL, AndesButtonHierarchy.LOUD, null
        )
        andesButtonSmall.text = getString(R.string.loud_small_button_programmatic)
        andesButtonSmall.isEnabled = false

        val andesButtonMedium = AndesButton(
                this,
                AndesButtonSize.MEDIUM, AndesButtonHierarchy.LOUD,
                AndesButtonIcon("andesui_icon", AndesButtonIconOrientation.LEFT)
        )
        andesButtonMedium.text = getString(R.string.loud_medium_button_programmatic)

        val andesButtonLarge = AndesButton(this, AndesButtonSize.LARGE, AndesButtonHierarchy.QUIET,
                AndesButtonIcon("andesui_icon", AndesButtonIconOrientation.LEFT))
        andesButtonLarge.text = getString(R.string.loud_large_button_programmatic)
        andesButtonLarge.hierarchy = AndesButtonHierarchy.LOUD
        andesButtonLarge.setOnClickListener {
            andesButtonLarge.text = getString(R.string.loud_large_button_text_updated)
            andesButtonLarge.size = AndesButtonSize.SMALL
        }

        val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        )
        params.setMargins(0, 0, 0, resources.getDimension(R.dimen.button_margin_vertical).toInt())

        andesButtonLarge.layoutParams = params
        andesButtonMedium.layoutParams = params
        andesButtonSmall.layoutParams = params

        val linearLoud = container.findViewById<LinearLayout>(R.id.andes_loud_container)
        linearLoud.addView(andesButtonLarge, linearLoud.childCount - 1)
        linearLoud.addView(andesButtonMedium, linearLoud.childCount - 1)
        linearLoud.addView(andesButtonSmall, linearLoud.childCount - 1)

        bindAndesSpecsButton(container)
    }


    private fun bindAndesSpecsButton(container: View) {
        container.findViewById<AndesButton>(R.id.andesui_demoapp_andes_specs_button).setOnClickListener {
            launchSpecs(container.context, AndesSpecs.BUTTON)
        }
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
            val layoutLoudButtons = inflater.inflate(
                    R.layout.andesui_progress_showcase, null, false
            )
            return listOf<View>(layoutLoudButtons)
        }
    }

    fun click(v:View){
        if (v is AndesButton){
            v.isLoading = !v.isLoading
        }
    }
}
