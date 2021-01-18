package com.gnb.gnbtrades.presentation.products.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.gnb.gnbtrades.R
import com.gnb.gnbtrades.domain.entities.Product
import com.gnb.gnbtrades.presentation.base.BaseActivity
import com.gnb.gnbtrades.presentation.products.adapter.ProductListAdapter
import com.gnb.gnbtrades.presentation.products.viewmodel.ProductListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_products.*

/**
 * ProductFragment
 * Fragment for showing list of products
 */
@AndroidEntryPoint
class ProductsFragment : Fragment() {

    val viewModel : ProductListViewModel by viewModels <ProductListViewModel> ()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_products, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupProductList()
    }

    /**
     * setupProductList. initializes and populates data into product list
     */
    private fun setupProductList() {
        var itemListener = {productId: String ->
            val action = ProductsFragmentDirections.actionProductsFragmentToProductTradesFragment(productId)
            (activity as BaseActivity).navController?.let {it.navigate(action)}
        }
        rvProductList.hasFixedSize()
        rvProductList.layoutManager = LinearLayoutManager(context)
        rvProductList.itemAnimator = DefaultItemAnimator()
        rvProductList.adapter = ProductListAdapter(itemListener)

        viewModel.getProducts()?.observe(viewLifecycleOwner, Observer<List<Product>> { products ->
            (rvProductList.adapter as ProductListAdapter).update(products)
            (activity as BaseActivity).hideLoading()
        })


    }
}