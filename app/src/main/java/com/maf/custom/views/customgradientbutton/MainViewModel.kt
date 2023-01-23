package com.maf.custom.views.customgradientbutton

import android.util.Log
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {

    fun onButtonOneClick() {
        Log.e("Test", "Called")
    }
}