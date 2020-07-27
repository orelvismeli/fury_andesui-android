package com.mercadolibre.android.andesui.demoapp

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.support.v4.content.ContextCompat.startActivity

private const val HOME = "https://company-161429.frontify.com/d/kxHCRixezmfK"
private const val COMPONENT_PREFIX = "$HOME/n-a#/componentes"

internal enum class AndesSpecs(val link: String) {
    HOME_PAGE(HOME),
    CARD("$COMPONENT_PREFIX/card-1567452592"),
    CHECKBOX("$COMPONENT_PREFIX/checkbox"),
    TAG("$COMPONENT_PREFIX/tag"),
    BADGE("$COMPONENT_PREFIX/badge"),
    BUTTON("$COMPONENT_PREFIX/button"),
    MESSAGE("$COMPONENT_PREFIX/message"),
    RADIOBUTTON("$COMPONENT_PREFIX/radiobutton"),
    THUMBNAIL("$COMPONENT_PREFIX/thumbnail-1589997379");
}

internal fun launchSpecs(context: Context, specs: AndesSpecs) {
    startActivity(context, Intent(Intent.ACTION_VIEW, Uri.parse(specs.link)), null)
}
