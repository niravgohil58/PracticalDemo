package com.example.demoapp.ui.fragment

import android.os.Bundle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.datingapp.ui.BaseFragment
import com.example.demoapp.R
import com.example.demoapp.databinding.FragmentDetailFragmentBinding
import com.example.demoapp.utils.Publishers

class DetailFragment : BaseFragment<FragmentDetailFragmentBinding>() {

    val args: DetailFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Publishers.bottomNavState.onNext(false)
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_detail_fragment
    }

    override fun onViewSetup() {
        initView()
        clickListener()
    }

    private fun clickListener() {
        binding.apply {
            ivBack.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

    private fun initView() {
        setScreenData()

    }

    private fun setScreenData() {
        binding.apply {
            txtName.text = args.mainList?.category
            txtDec.text = args.mainList?.title
            txtPrice.text = "$" + args.mainList?.price.toString()
            txtDecMain.text = args.mainList?.description
            ratingBar.rating = args.mainList?.rating?.rate?.toFloat()!!
            txtCount.text = "(" + args.mainList?.rating?.count + ")"
            Glide.with(root.context)
                .load(args.mainList?.image)
                .into(ivProduct)
        }
    }
}