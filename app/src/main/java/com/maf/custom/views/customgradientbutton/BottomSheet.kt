//package com.maf.custom.views.customgradientbutton
//
//import android.content.Context
//import android.view.View
//import androidx.annotation.StyleRes
//import androidx.lifecycle.*
//import androidx.savedstate.SavedStateRegistry
//import androidx.savedstate.SavedStateRegistryController
//import androidx.savedstate.SavedStateRegistryOwner
//import com.google.android.material.bottomsheet.BottomSheetDialog
//
//class BottomSheetWithLifeCycle(
//    context: Context,
//    @StyleRes style: Int,
//) : BottomSheetDialog(context, style),
//    LifecycleOwner, ViewModelStoreOwner, SavedStateRegistryOwner {
//
//    fun onCreate() {
//        savedStateRegistryController.performRestore(null)
//        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_CREATE)
//    }
//
//    fun onResume() {
//        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_RESUME)
//    }
//
//    fun onPause() {
//        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_PAUSE)
//    }
//
//    fun onDestroy() {
//        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_DESTROY)
//        store.clear()
//    }
//
//    /**
//    Compose uses the Window's decor view to locate the
//    Lifecycle/ViewModel/SavedStateRegistry owners.
//    Therefore, we need to set this class as the "owner" for the decor view.
//     */
//    fun attachToDecorView(decorView: View?) {
//        if (decorView == null) return
//
//        ViewTreeLifecycleOwner.set(decorView, this)
//        ViewTreeViewModelStoreOwner.set(decorView, this)
//        //ViewTreeSavedStateRegistryOwner.set(decorView, this)
//    }
//
//    // LifecycleOwner methods
//    private val lifecycleRegistry: LifecycleRegistry = LifecycleRegistry(this)
//    override val savedStateRegistry: SavedStateRegistry
//        get() = TODO("Not yet implemented")
//
//    override fun getLifecycle(): Lifecycle = lifecycleRegistry
//
//    // ViewModelStore methods
//    private val store = ViewModelStore()
//    override fun getViewModelStore(): ViewModelStore = store
//
//    // SavedStateRegistry methods
//    private val savedStateRegistryController = SavedStateRegistryController.create(this)
//    override fun getSavedStateRegistry(): SavedStateRegistry =
//        savedStateRegistryController.savedStateRegistry
//
//
//}