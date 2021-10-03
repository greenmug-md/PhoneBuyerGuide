package com.greenmug.android.buyerguide.activity

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.greenmug.android.buyerguide.R
import com.greenmug.android.buyerguide.adapter.DetailsAdapter
import com.greenmug.android.buyerguide.databinding.ActivityDetailBinding
import com.greenmug.android.buyerguide.utils.Constants
import com.greenmug.android.buyerguide.utils.MobileDataParcel
import com.greenmug.android.buyerguide.viewmodel.DetailsViewModel
import dagger.hilt.android.AndroidEntryPoint

/*
Detail Activity for Mobiles
 */
@AndroidEntryPoint
class DetailsActivity : FragmentActivity() {

    private var activityDetailBinding: ActivityDetailBinding? = null
    private lateinit var viewModel: DetailsViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityDetailBinding =  DataBindingUtil.setContentView(this, R.layout.activity_detail)
    }

    override fun onStart() {
        super.onStart()
        val data = intent.extras
        val product = data?.getParcelable<MobileDataParcel>(Constants.PARCEL_DATA_PHONE)
        setUpRecyclerView(product)
        setUpObservers(product)
    }

    fun setUpRecyclerView(product: MobileDataParcel?) {
        viewModel = ViewModelProvider(this).get(DetailsViewModel::class.java)
        activityDetailBinding?.detailsViewModel = viewModel
        product?.let {
            viewModel?.setData(product?.name!!, product?.brand, product?.description, product?.rating, product?.price)
        }
        var mobileData = activityDetailBinding?.recyclerView
        mobileData?.setLayoutManager(LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
    }

    fun setUpObservers(product : MobileDataParcel?) {
        viewModel?.imagesData?.observe(this, {
            var mobileData = activityDetailBinding?.recyclerView
            mobileData?.adapter = DetailsAdapter( it)
            mobileData?.adapter?.notifyDataSetChanged()
        })
        product?.id?.let {
            viewModel?.getImages(it.toString())
        }
    }

}