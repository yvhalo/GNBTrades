package com.gnb.gnbtrades.presentation.trades.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gnb.gnbtrades.R
import com.gnb.gnbtrades.data.entities.Rate
import com.gnb.gnbtrades.data.entities.Transaction
import com.gnb.gnbtrades.domain.entities.Trade
import com.gnb.gnbtrades.domain.usecase.TradesUseCase.Companion.EURO
import kotlinx.android.synthetic.main.item_trade.view.*
import java.math.RoundingMode

/**
 * Adapter for product transactions list
 */
class TradesAdapter() : RecyclerView.Adapter<TradesAdapter.TradesViewHolder>() {

    var trades : List<Trade> = listOf()

    /**
     * OnCreateViewHolder
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TradesViewHolder = TradesViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_trade, parent, false))

    /**
     * GetItemCount
     */
    override fun getItemCount(): Int = trades.size

    /**
     * Binds data in ViewHolder specific position
     */
    override fun onBindViewHolder(holder: TradesViewHolder, position: Int) = holder.bind(trades[position])


    /**
     * Updates list data if required
     */
    fun update(list: List<Trade?>) {
        trades = list.mapNotNull { it }
        notifyDataSetChanged()
    }

    /**
     * ViewHolder
     */
    class TradesViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        /**
         * Bind. bind data into current itemView
         */
        fun bind(trade: Trade) = with(itemView) {
            val amount = trade.amount.setScale(2, RoundingMode.HALF_EVEN).toString()
            itemView.txtTradeAmount.text = String.format("%s €", amount)
        }
    }
}