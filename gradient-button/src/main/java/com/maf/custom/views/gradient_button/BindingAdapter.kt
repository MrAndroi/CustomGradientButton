package com.maf.custom.views.gradient_button

import androidx.databinding.BindingAdapter

// function to support click in data binding
@BindingAdapter("onDebounceClick")
fun CustomGradientButton.setCustomClick(listener: (() -> Unit)) {
    this.setOnDebounceClickListener(listener)
}