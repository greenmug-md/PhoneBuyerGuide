package com.greenmug.android.buyerguide.adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.greenmug.android.buyerguide.R
import com.greenmug.android.buyerguide.activity.DetailsActivity
import com.greenmug.android.buyerguide.databinding.FavouriteContainerViewBinding

import com.greenmug.android.buyerguide.model.Favourites
import com.greenmug.android.buyerguide.utils.Constants
import com.greenmug.android.buyerguide.utils.MobileDataParcel
import java.util.*


/*
Adapter for FavouriteAdapter
*/
class FavouriteAdapter(var activity: FragmentActivity, private val favourites: MutableList<Favourites>) : RecyclerView.Adapter<FavouriteAdapter.FavouriteShowViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouriteShowViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val binding = DataBindingUtil.inflate<FavouriteContainerViewBinding>(
            inflater,
            R.layout.favourite_container_view,
            parent,
            false
        )

        return FavouriteShowViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return  favourites.size;
    }

    inner class FavouriteShowViewHolder(private val binding: FavouriteContainerViewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindTvShow(phoneDataItem: Favourites) {
            binding.favourites = phoneDataItem
            binding.executePendingBindings()

            binding?.favouriteView?.setOnClickListener {
                val intent = Intent(activity, DetailsActivity::class.java)
                var phoneDataItem  =   MobileDataParcel(phoneDataItem?.id,
                    phoneDataItem?.brand,phoneDataItem?.name, phoneDataItem?.price,
                    phoneDataItem?.thumbImageURL, phoneDataItem?.description,
                    phoneDataItem?.rating)
                intent.putExtra(Constants.PARCEL_DATA_PHONE, phoneDataItem)
                activity.startActivity(intent)
            }
        }
    }


    override fun onBindViewHolder(holder: FavouriteShowViewHolder, position: Int) {
        holder.bindTvShow(favourites.get(position))
    }




}