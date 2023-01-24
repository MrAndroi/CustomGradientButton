package com.maf.custom.views.customgradientbutton

import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.createLifecycleAwareWindowRecomposer
import androidx.lifecycle.ViewTreeLifecycleOwner
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.maf.custom.views.customgradientbutton.databinding.ActivityMainBinding
import com.maf.custom.views.customgradientbutton.databinding.BottomSheetLayoutBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()

    @OptIn(ExperimentalComposeUiApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        ViewTreeLifecycleOwner.set(window.decorView, this)
        setContentView(binding.root)

        binding.main = mainViewModel
        binding.lifecycleOwner = this

        binding.customButtonHv.setOnDebounceClickListener {
             BottomSheet().show(this.supportFragmentManager, "")
        }


    }
}