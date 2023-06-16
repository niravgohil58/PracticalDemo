package com.example.demoapp.ui.activity

import android.os.Bundle
import androidx.core.view.isVisible
import androidx.navigation.findNavController
import com.example.datingapp.ui.BaseActivity
import com.example.demoapp.R
import com.example.demoapp.databinding.ActivityMainBinding
import com.example.demoapp.utils.Publishers
import io.reactivex.android.schedulers.AndroidSchedulers

class MainActivity : BaseActivity<ActivityMainBinding>() {
    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mCompositeDisposable.apply {
            add(Publishers.bottomNavState.observeOn(AndroidSchedulers.mainThread()).subscribe {
                binding.bottomNavigationView.isVisible = it
            })
        }
    }

}