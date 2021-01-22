package com.gnb.gnbtrades.presentation.trades.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.gnb.gnbtrades.R
import com.gnb.gnbtrades.domain.entities.ProductTrades
import com.gnb.gnbtrades.presentation.base.BaseActivity
import com.gnb.gnbtrades.presentation.trades.adapter.TradesAdapter
import com.gnb.gnbtrades.presentation.trades.viewmodel.ProductTradesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_product_trades.*
import kotlinx.android.synthetic.main.item_trade.view.*
import java.math.RoundingMode

/**
 * ProductTradesFragment
 * Fragment for showing trades of an specific product
 */
@AndroidEntryPoint
class ProductTradesFragment : Fragment() {

    val viewModel : ProductTradesViewModel by viewModels <ProductTradesViewModel> ()

    val args: ProductTradesFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_product_trades, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViews()
    }

    /**
     * Initializes fragment views
     */
    private fun setupViews() {
        txtProductId.text = args.productId

        rvTransactionsList.hasFixedSize()
        rvTransactionsList.layoutManager = LinearLayoutManager(context)
        rvTransactionsList.itemAnimator = DefaultItemAnimator()
        rvTransactionsList.adapter = TradesAdapter()

        viewModel.getTrades(args.productId).observe(viewLifecycleOwner, Observer { productTrades ->
            val amount = productTrades.totalAmount
            txtTotalAmount.text  = String.format("%s €", amount)

            (rvTransactionsList.adapter as TradesAdapter).update(productTrades.trades)
            (activity as BaseActivity).hideLoading()
        })
    }
}