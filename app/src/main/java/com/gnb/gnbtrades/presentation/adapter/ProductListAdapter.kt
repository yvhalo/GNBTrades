package com.gnb.gnbtrades.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gnb.gnbtrades.R
import com.gnb.gnbtrades.data.entities.Transaction
import com.gnb.gnbtrades.domain.entities.Product

/**
 * ProductListAdapter. adapter for main product list
 */
class ProductListAdapter() : RecyclerView.Adapter<ProductViewHolder>() {

    private var products = listOf<Product>()

    /**
     * onCreateViewHolder.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder = ProductViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false))

    /**
     * getItemCount.
     */
    override fun getItemCount(): Int = products.size

    /**
     * Binds data in ViewHolder specific position
     */
    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) = holder.bind(products[position])

    /**
     *  updates products list
     */
    fun update(list: List<Product>?) {
        list?.let {
            products = list
            notifyDataSetChanged()
        }

    }

}