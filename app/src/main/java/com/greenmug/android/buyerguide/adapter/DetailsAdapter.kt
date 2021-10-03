package com.greenmug.android.buyerguide.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.greenmug.android.buyerguide.R
import com.greenmug.android.buyerguide.databinding.ItemImagesBinding
import com.greenmug.android.buyerguide.model.*

/*
Adapter for ProductAdapter
*/
class DetailsAdapter(private val image: MobileImagesData) : RecyclerView.Adapter<DetailsAdapter.MemberShowViewHolder>(){

    private var images: MutableList<MobileImagesDataItem> = image as MutableList<MobileImagesDataItem>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemberShowViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val binding = DataBindingUtil.inflate<ItemImagesBinding>(
            inflater,
            R.layout.item_images,
            parent,
            false
        )
        return MemberShowViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return  images.size;
    }

    inner class MemberShowViewHolder(private val binding: ItemImagesBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindTvShow(phoneDataItem:  MobileImagesDataItem) {
            binding.mobileImagesDataItem = phoneDataItem
            binding.executePendingBindings()
        }
    }

    override fun onBindViewHolder(holder: MemberShowViewHolder, position: Int) {
        holder.bindTvShow(images[position])
    }

}