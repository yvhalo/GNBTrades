package com.gnb.gnbtrades.presentation.trades.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gnb.gnbtrades.R
import com.gnb.gnbtrades.data.entities.Rate
import com.gnb.gnbtrades.data.entities.Transaction
import com.gnb.gnbtrades.domain.entities.Trade
import kotlinx.android.synthetic.main.item_trade.view.*

class TradesAdapter() : RecyclerView.Adapter<TradesAdapter.TradesViewHolder>() {

    var trades : List<Trade> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TradesViewHolder = TradesViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_trade, parent, false))

    override fun getItemCount(): Int = trades.size

    override fun onBindViewHolder(holder: TradesViewHolder, position: Int) = holder.bind(trades[position])


    fun update(list: List<Trade?>) {
        trades = list.mapNotNull { it }
        notifyDataSetChanged()
    }

    class TradesViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(trade: Trade) = with(itemView) {

            itemView.txtTradeAmount.text = String.format(itemView.context.getString(R.string.trade_amount), trade.amount)
        }
    }
}