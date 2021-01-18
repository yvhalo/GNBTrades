package com.gnb.gnbtrades.domain.usecase

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.gnb.gnbtrades.data.repository.CommonRepository
import com.gnb.gnbtrades.domain.entities.Product
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
    fun getProductList() : LiveData<List<Product>>? {
        return Transformations.map(commonRepository.getTransactions()) { transactions ->
            transactions.map { transaction ->
                Product(transaction.sku)
            }
        }
    }
}