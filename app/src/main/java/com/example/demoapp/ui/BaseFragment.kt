package com.example.datingapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import io.reactivex.disposables.CompositeDisposable

abstract class BaseFragment<T : ViewDataBinding> : Fragment() {

    abstract fun getLayoutId(): Int

    lateinit var binding: T

    abstract fun onViewSetup()

    val mCompositeDisposable = CompositeDisposable()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        if (!::binding.isInitialized) {
            binding = DataBindingUtil.inflate(layoutInflater, getLayoutId(), container, false)
            binding.lifecycleOwner = this
            onViewSetup()
        }
        return binding.root
    }

    override fun onDestroy() {
        mCompositeDisposable.dispose()

        super.onDestroy()
    }
}