package com.gnb.gnbtrades.domain.usecase

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.gnb.gnbtrades.domain.entities.Product
import com.gnb.gnbtrades.domain.repository.CommonRepository
import javax.inject.Inject

/**
 * ProductUseCase
 * contains business logic of products
 * @param commonRepository dataSource manager
 */
class ProductUseCase @Inject constructor(private val commonRepository: CommonRepository ) {

    /**
     * Gets unique products id from all transactions
     * @return products list
     */
    fun getProductList() : LiveData<List<Product>> {
        return Transformations.map(commonRepository.getTransactions()) { transactions ->
            val transformations = transactions.distinct()
            val products  = transformations.map { uniqueTransaction ->
                return@map Product(uniqueTransaction.sku)
            }
            products
        }
    }
}