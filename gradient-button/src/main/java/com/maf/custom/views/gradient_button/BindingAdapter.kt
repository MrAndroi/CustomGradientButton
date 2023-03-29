package com.maf.custom.views.gradient_button

import androidx.databinding.BindingAdapter

// function to support click in data binding
@BindingAdapter("onDebounceClick")
fun CustomGradientButton.setCustomClick(listener: (() -> Unit)) {
    this.setOnDebounceClickListener(listener)
}

// function to support textTitle in data binding
@BindingAdapter("textTitle")
fun CustomGradientButton.setTextTitle(text: String) {
    this.buttonText = text
}

@BindingAdapter("startIconLink")
fun CustomGradientButton.setStartIconLink(url: String) {
    this.startIconLink = url
}

@BindingAdapter("endIconLink")
fun CustomGradientButton.setEndIconLink(url: String) {
    this.endIconLink = url
}

@BindingAdapter("buttonBorderWidth")
fun CustomGradientButton.setButtonBorderWidth(width: Int) {
    this.borderWidth = width
}

