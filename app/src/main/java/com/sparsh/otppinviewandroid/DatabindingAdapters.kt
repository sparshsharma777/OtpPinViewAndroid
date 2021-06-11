package com.sparsh.otppinviewandroid

import android.view.View
import androidx.databinding.BindingAdapter


/**
 * Binding adapter used to display alpha  value dynamically
 */
@BindingAdapter("isEnable")
fun setIsEnable(view: View, value: Boolean) {
    view.isEnabled=value
}
/**
 * Binding adapter used to display alpha  value dynamically
 */
@BindingAdapter("alphaValue")
fun setAlphaValue(view: View, value: Float) {
    view.alpha=value
}