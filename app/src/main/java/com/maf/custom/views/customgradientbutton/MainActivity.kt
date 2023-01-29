package com.maf.custom.views.customgradientbutton

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewTreeLifecycleOwner
import androidx.lifecycle.ViewTreeViewModelStoreOwner
import androidx.savedstate.findViewTreeSavedStateRegistryOwner
import androidx.savedstate.setViewTreeSavedStateRegistryOwner
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.maf.custom.views.customgradientbutton.databinding.ActivityMainBinding
import com.maf.custom.views.customgradientbutton.databinding.BottomSheetLayoutBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : FragmentActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        ViewTreeLifecycleOwner.set(window.decorView, this)
        setContentView(binding.root)

        binding.main = mainViewModel
        binding.lifecycleOwner = this

        binding.customButtonHv.setOnDebounceClickListener {
            val dialog = BottomSheetDialog(
                this@MainActivity,
                com.google.android.material.R.style.Theme_Design_BottomSheetDialog
            )
            val layout = BottomSheetLayoutBinding.inflate(layoutInflater)
            dialog.setContentView(layout.root)
            val decorView: View = dialog.window?.decorView ?: throw IllegalStateException("Failed to get decorview")

            ViewTreeLifecycleOwner.set(decorView, this)
            ViewTreeViewModelStoreOwner.set(decorView, this)
            layout.root.setViewTreeSavedStateRegistryOwner(decorView.findViewTreeSavedStateRegistryOwner())

            dialog.show()
        }


    }
}