package com.topnews.utils

import android.content.res.ColorStateList
import android.graphics.Color
import android.util.Log
import android.view.View
import androidx.databinding.BindingAdapter
import com.google.android.material.button.MaterialButton
import com.topnews.utils.BindingAdaptersVars.disabledColor
import com.topnews.utils.BindingAdaptersVars.enabledColor

object BindingAdaptersVars {
    val disabledColor = Color.parseColor("#fff8e1")
    val enabledColor = Color.parseColor("#807F7C")

}

@BindingAdapter("visibleBy")
fun visibleIf(view: View, boolean: Boolean) {
    view.visibility = if (boolean) View.VISIBLE else View.INVISIBLE
}


@BindingAdapter("enabledBy")
fun enabledBy(button: View, obj: Any?) {
    // if user has chosen country, change color and enable
    val boolean = obj != null
    val color =
        if (boolean) ColorStateList.valueOf(disabledColor) else ColorStateList.valueOf(
            enabledColor
        )
    button.backgroundTintList = color
    button.isEnabled = boolean
}

@BindingAdapter("enabledCategories")
fun enabledCategories(button: View, list: List<*>?) {
    var boolean = true
    if (list == null || list.isEmpty()) boolean = false

    val color =
        if (boolean) ColorStateList.valueOf(disabledColor) else ColorStateList.valueOf(
            enabledColor
        )
    button.backgroundTintList = color
    button.isEnabled = boolean
}