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
import com.example.demoapp.ui.fragment.HomeFragmentDirections
import com.example.demoapp.utils.AdapterClickListener

class CategoryDialogFragment : BaseBottomSheetDialogFragment<CategoryListDialogBinding>() {

    val args: CategoryDialogFragmentArgs by navArgs()

    var list: ArrayList<String> = arrayListOf()

    private val adapter: CategoryAdapter = CategoryAdapter(list, object : AdapterClickListener() {
        override fun getclickedCategory(item: String) {
            super.getclickedCategory(item)
            val bundle = args.toBundle()
            bundle.apply {
                putString(Constant.IS_CATEGORY_SELECT, item)
            }
            setFragmentResult(Constant.BACK_DATA, bundle)
            findNavController().navigateUp()
        }
    })

    override fun getTheme(): Int = R.style.BottomSheetDialogTheme
    override fun getLayoutId(): Int {
        return R.layout.category_list_dialog
    }

    override fun onViewSetup() {
        filterCategory()
        setData()
    }

    private fun filterCategory() {
        val mainList: ArrayList<MainListModel> = arrayListOf()
        args.mainList?.toList()?.let { mainList.addAll(it) }
        for (item in mainList) {
            if (list.size > 0) {
                var counter = 0
                for (item1 in list) {
                    if (item.category.equals(item1)) {
                        counter++
                        break
                    }
                }
                if (counter == 0) {
                    item.category?.let { list.add(it) }
                }
            } else {
                item.category?.let { list.add(it) }
            }
        }
        adapter.updateAllData(list)
    }

    private fun setData() {
        binding.rvCategory.adapter = adapter
        adapter.updateAllData(list)
    }
}
