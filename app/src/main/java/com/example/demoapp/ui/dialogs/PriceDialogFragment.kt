package com.example.demoapp.ui.dialogs

import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.demoapp.R
import com.example.demoapp.databinding.CategoryListDialogBinding
import com.example.demoapp.remote.Constant
import com.example.demoapp.responses.MainListModel
import com.example.demoapp.ui.BaseBottomSheetDialogFragment
import com.example.demoapp.ui.adapter.CategoryAdapter
import com.example.demoapp.ui.adapter.ListAdapter
import com.example.demoapp.ui.adapter.PriceAdapter
import com.example.demoapp.ui.fragment.HomeFragmentDirections
import com.example.demoapp.utils.AdapterClickListener

class PriceDialogFragment : BaseBottomSheetDialogFragment<CategoryListDialogBinding>() {

    val args: PriceDialogFragmentArgs by navArgs()

    var list: ArrayList<String> = arrayListOf()
    var priceList: ArrayList<String> = arrayListOf()

    private val adapter: PriceAdapter = PriceAdapter(list, object : AdapterClickListener() {
        override fun getclickedPrice(first: Int, last: Int) {
            super.getclickedPrice(first, last)
            val bundle = args.toBundle()
            bundle.apply {
                putInt(Constant.IS_PRICE_SELECT_FIRST, first)
                putInt(Constant.IS_PRICE_SELECT_LAST, last)
            }
            setFragmentResult(Constant.BACK_DATA_PRICE, bundle)
            findNavController().navigateUp()
        }
    })

    override fun getTheme(): Int = R.style.BottomSheetDialogTheme
    override fun getLayoutId(): Int {
        return R.layout.category_list_dialog
    }

    override fun onViewSetup() {
        setData()
        priceListAdd()
    }

    private fun priceListAdd() {
        priceList.add("0-100")
        priceList.add("101-500")
        priceList.add("501-1000")
        priceList.add("1000-2000")
        adapter.updateAllData(priceList)
    }

    private fun setData() {
        binding.rvCategory.adapter = adapter
        adapter.updateAllData(list)
    }
}
