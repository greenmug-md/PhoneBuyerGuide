package com.greenmug.android.buyerguide.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.greenmug.android.buyerguide.R
import com.greenmug.android.buyerguide.activity.DetailsActivity
import com.greenmug.android.buyerguide.databinding.ItemContainerViewBinding
import com.greenmug.android.buyerguide.listeners.FavouriteListener
import com.greenmug.android.buyerguide.model.Favourites
import com.greenmug.android.buyerguide.model.MobilePhoneData
import com.greenmug.android.buyerguide.model.MobilePhoneDataItem
import com.greenmug.android.buyerguide.utils.MobileDataParcel

/*
Adapter for ProductAdapter
*/
class ProductAdapter(var activity:  FragmentActivity, private val product: MobilePhoneData,val favListener: FavouriteListener) : RecyclerView.Adapter<ProductAdapter.MemberShowViewHolder>(){

    private var members: MutableList<MobilePhoneDataItem> = product as MutableList<MobilePhoneDataItem>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemberShowViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val binding = DataBindingUtil.inflate<ItemContainerViewBinding>(
            inflater,
            R.layout.item_container_view,
            parent,
            false
        )
        return MemberShowViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return  members.size;
    }

    inner class MemberShowViewHolder(private val binding: ItemContainerViewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindTvShow(phoneDataItem:  MobilePhoneDataItem) {
            binding.mobilePhoneDataItem = phoneDataItem

            when( binding?.mobilePhoneDataItem?.favs) {
                true -> {
                    favShow(binding)
                }
                else -> {
                    favHide(binding)
                }
            }
            setupClickListeners(binding,phoneDataItem)

            binding.executePendingBindings()
        }
    }

    override fun onBindViewHolder(holder: MemberShowViewHolder, position: Int) {
        holder.bindTvShow(members[position])
    }

    fun setupClickListeners(binding: ItemContainerViewBinding, phoneDataItem: MobilePhoneDataItem) {
        binding?.cardPhone?.setOnClickListener {
            val intent = Intent(activity, DetailsActivity::class.java)
            var phoneDataItem  =   getMobileItem(phoneDataItem)
            intent.putExtra("phoneData", phoneDataItem)
            activity.startActivity(intent)
        }
        binding?.fav?.setOnClickListener {
            favShow(binding)
            favListener?.save(getFavouriteItem(phoneDataItem))
        }
        binding?.favFilled?.setOnClickListener {
            favHide(binding)
            favListener?.remove(getFavouriteItem(phoneDataItem)?.id)
        }
    }

    fun getMobileItem(phoneDataItem:  MobilePhoneDataItem) : MobileDataParcel {
        return MobileDataParcel(phoneDataItem?.id,
            phoneDataItem?.brand,phoneDataItem?.name, phoneDataItem?.price,
            phoneDataItem?.thumbImageURL, phoneDataItem?.description,
            phoneDataItem?.rating)
    }

    fun getFavouriteItem(phoneDataItem:  MobilePhoneDataItem) : Favourites {
        return Favourites(name=phoneDataItem.name,thumbImageURL= phoneDataItem.thumbImageURL,rating = phoneDataItem.rating, price = phoneDataItem.price,
            id=phoneDataItem.id, description=phoneDataItem.description,brand = phoneDataItem.brand)
    }

    fun favHide(binding: ItemContainerViewBinding) {
        binding?.favFilled.visibility = View.GONE
        binding?.fav.visibility = View.VISIBLE
    }

    fun favShow(binding: ItemContainerViewBinding) {
        binding?.favFilled.visibility = View.VISIBLE
        binding?.fav.visibility = View.GONE
    }

}