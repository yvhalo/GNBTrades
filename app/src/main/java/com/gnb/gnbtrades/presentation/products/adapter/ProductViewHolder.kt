package com.gnb.gnbtrades.presentation.products.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.gnb.gnbtrades.domain.entities.Product
import kotlinx.android.synthetic.main.item_product.view.*

/**
 * ProductViewHolder. manages product list items
 * @param itemView list item
 */
class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    /**
     * Bind. binds data and behaviour of an specific list item by position
     */
    fun bind(
        product: Product,
        listener: (String) -> Unit?
    ) = with(itemView) {
        tvProductId.text = product.productId
        itemView.setOnClickListener {listener(product.productId)}
    }
}