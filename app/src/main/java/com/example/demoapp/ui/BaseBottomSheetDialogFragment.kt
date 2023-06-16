package com.example.demoapp.ui

import android.app.Dialog
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.example.demoapp.R
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

abstract class BaseBottomSheetDialogFragment<T : ViewDataBinding> : BottomSheetDialogFragment() {

    lateinit var binding: T

    abstract fun getLayoutId(): Int

    abstract fun onViewSetup()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.BottomSheetDialogTheme)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val bottomSheet = super.onCreateDialog(savedInstanceState) as BottomSheetDialog

        //inflating layout
        val view = View.inflate(context, getLayoutId(), null)

        //binding views to data binding.
        binding = DataBindingUtil.bind(view)!!

        //setting layout with bottom sheet
        bottomSheet.setContentView(view)

        val bottomSheetBehavior = BottomSheetBehavior.from(view.parent as View)

        bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
        bottomSheetBehavior.skipCollapsed = true

        onViewSetup()

        return bottomSheet
    }
}