package com.gnb.gnbtrades.presentation.view

import android.os.Bundle
import android.os.UserHandle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.gnb.gnbtrades.R
import com.gnb.gnbtrades.domain.usecase.ProductUseCase
import com.gnb.gnbtrades.presentation.viewmodel.ProductListViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

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
        viewModel.getProducts().observe(viewLifecycleOwner, Observer { products ->

        })
    }

}