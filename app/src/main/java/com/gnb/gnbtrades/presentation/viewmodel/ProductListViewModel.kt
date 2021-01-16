package com.gnb.gnbtrades.presentation.viewmodel

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.gnb.gnbtrades.domain.entities.Product
import com.gnb.gnbtrades.domain.usecase.ProductUseCase
import javax.inject.Inject

/**
 * ViewModel for ProductsFragment
 */
class ProductListViewModel @ViewModelInject constructor(
    private val productUseCase: ProductUseCase
) : ViewModel() {

    /**
     * Gets all products from domain layer
     */
    fun getProducts() : LiveData<List<Product>> {
        return productUseCase.getProductList()
    }
}