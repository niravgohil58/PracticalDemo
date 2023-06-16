package com.example.demoapp.ui.fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import com.example.datingapp.ui.BaseFragment
import com.example.demoapp.R
import com.example.demoapp.databinding.FragmentHomeBinding
import com.example.demoapp.remote.Constant
import com.example.demoapp.responses.MainListModel
import com.example.demoapp.ui.adapter.ListAdapter
import com.example.demoapp.utils.*
import com.example.demoapp.viewmodel.ListViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*
import kotlin.collections.ArrayList

class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    private val listviewmodel: ListViewModel by viewModel()

    var list: ArrayList<MainListModel> = arrayListOf()

    private val adapter: ListAdapter = ListAdapter(list, object : AdapterClickListener() {
        override fun getclickedList(item: MainListModel) {
            super.getclickedList(item)
            findNavController().navigate(
                HomeFragmentDirections.actionHomeFragmentToDetailFragment(
                    mainList = item
                )
            )
        }
    })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Publishers.bottomNavState.onNext(true)
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_home
    }

    override fun onViewSetup() {
        initView()
        clickListener()
        setResultListener()
        getList()
        setTextWatcher()
        setRecyclerData(list)
    }

    private fun setResultListener() {
        setFragmentResultListener(Constant.BACK_DATA) { _, bundle ->
            setupBackStackData(bundle)
        }

        setFragmentResultListener(Constant.BACK_DATA_PRICE) { _, bundle ->
            setupBackStackDataPrice(bundle)
        }
    }

    private fun setupBackStackDataPrice(bundle: Bundle) {
        bundle.let {
            bundle.apply {
                val first = getInt(Constant.IS_PRICE_SELECT_FIRST)
                val last = getInt(Constant.IS_PRICE_SELECT_LAST)

                val newList: ArrayList<MainListModel> = arrayListOf()

                for (item in list) {
                    if (item.price!! > first && item.price < last) {
                        newList.add(item)
                    }
                }

                adapter.updateAllData(newList)
                binding.noOfProduct.text =
                    "" + newList.size + " " + getString(R.string._0_products_found)
            }
        }
    }

    private fun setupBackStackData(bundle: Bundle) {
        bundle.let {
            bundle.apply {
                val string = getString(Constant.IS_CATEGORY_SELECT)
                val list1 = list
                val filter = list1.filter { itemData -> itemData.category == string }
                adapter.updateAllData(filter as ArrayList<MainListModel>)
                binding.noOfProduct.text =
                    "" + filter.size + " " + getString(R.string._0_products_found)
            }
        }
    }

    private fun clickListener() {
        binding.apply {
            ivFilter.setOnClickListener {
                findNavController().navigate(
                    HomeFragmentDirections.actionHomeFragmentToCategoryDialogFragment(
                        mainList = list.toTypedArray()
                    )
                )
            }
            ivPriceFilter.setOnClickListener {
                findNavController().navigate(
                    HomeFragmentDirections.actionHomeFragmentToPriceDialogFragment(
                        mainList = list.toTypedArray()
                    )
                )
            }
        }
    }

    private fun initView() {
    }

    private fun getList() {
        listviewmodel.getLists().observe(this) {
            when (it) {
                is ResponseList.Loading -> {
                }

                is ResponseList.Success<*> -> {
                    if (it.data != null) {
                        if (it.data.size > 0) {
                            binding.rvListProduct.show()
                            list = it.data as ArrayList<MainListModel>
                            adapter.updateAllData(it.data)
                            binding.noOfProduct.text =
                                "" + list.size + " " + getString(R.string._0_products_found)
                        } else {
                            binding.rvListProduct.hide()
                        }
                    }
                }

                is ResponseList.Failed -> {
                }
                else -> {}
            }
        }
    }

    private fun setTextWatcher() {
        binding.edtSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                Log.e("TAG", "onTextChanged: $p0   $p1  $p2  $p3")
            }

            override fun afterTextChanged(p0: Editable?) {
                if (p0?.isNotEmpty() == true) {
                    setLogic(p0.toString())
                } else {
                    adapter.updateAllData(list)
                    binding.noOfProduct.text =
                        "" + list.size + " " + getString(R.string._0_products_found)
                }
            }
        })
    }

    private fun setLogic(toString: String) {
        val listNew: ArrayList<MainListModel> = arrayListOf()
        for (item in list) {
            val categoryCheck = item.category?.lowercase(Locale.ROOT)
            val titleCheck = item.category?.lowercase(Locale.ROOT)
            if (categoryCheck?.contains(toString) == true || titleCheck?.contains(toString) == true) {
                listNew.add(item)
            }
        }
        adapter.updateAllData(listNew)
        binding.noOfProduct.text =
            "" + listNew.size + " " + getString(R.string._0_products_found)
    }

    private fun setRecyclerData(data: ArrayList<MainListModel>) {
        binding.rvListProduct.adapter = adapter
        adapter.updateAllData(data)
        binding.noOfProduct.text =
            "" + data.size + " " + getString(R.string._0_products_found)
    }
}