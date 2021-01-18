package com.gnb.gnbtrades.presentation.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.gnb.gnbtrades.data.entities.Transaction
import com.gnb.gnbtrades.domain.entities.Product
import kotlinx.android.synthetic.main.item_product.view.*

/**
 * ProductViewHolder. manages product list items
 * @param itemView list item
 */
class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    /**
     * Bind. binds data a behaviour of an specific list item by position
     */
    fun bind(product: Product) = with(itemView) {
        tvProductId.text = product.productId
    }
}