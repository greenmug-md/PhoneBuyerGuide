package com.greenmug.android.buyerguide.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.greenmug.android.buyerguide.R
import com.greenmug.android.buyerguide.activity.MainActivity
import com.greenmug.android.buyerguide.adapter.ProductAdapter
import com.greenmug.android.buyerguide.databinding.FragmentProductBinding
import com.greenmug.android.buyerguide.listeners.FavouriteListener
import com.greenmug.android.buyerguide.model.Favourites
import com.greenmug.android.buyerguide.utils.Response
import com.greenmug.android.buyerguide.viewmodel.FavouriteViewModel
import com.greenmug.android.buyerguide.viewmodel.ProductViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProductFragment: Fragment(), FavouriteListener {

    private var productFragmentBinding: FragmentProductBinding? = null
    private lateinit var viewModel: ProductViewModel
    private lateinit var ctx: MainActivity;
    private lateinit var favouriteViewModel: FavouriteViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        productFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_product, container, false)
        return productFragmentBinding?.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        ctx = context as MainActivity
    }
    companion object {
        fun create(): ProductFragment {
            return ProductFragment()
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(ProductViewModel::class.java)
        favouriteViewModel = ViewModelProvider(requireActivity()).get(FavouriteViewModel::class.java)
        updateVisibility(View.VISIBLE,View.VISIBLE,View.GONE)
        viewModel?.productList?.observe(viewLifecycleOwner, {
            var s =  when (it) {
                is Response.Success -> {
                    updateVisibility(View.GONE,View.GONE,View.VISIBLE)
                    var mobileData = productFragmentBinding?.mobileData
                    productFragmentBinding?.mobileData?.setLayoutManager(LinearLayoutManager(getActivity()));
                    it?.data?.forEach{
                        var s = favouriteViewModel?.exists(it.id)
                        if(s) {
                            it?.favs = true;
                        }
                    }
                    mobileData?.adapter = ProductAdapter(requireActivity(), it?.data!!,this)
                    mobileData?.adapter?.notifyDataSetChanged()
                }
                is Response.Error -> {
                    updateVisibility(View.VISIBLE,View.GONE,View.GONE)
                    productFragmentBinding?.loading?.text = it?.message
                }
                is Response.Loading -> {

                }
                is Response.Empty -> {
                    updateVisibility(View.VISIBLE,View.GONE,View.GONE)
                    productFragmentBinding?.loading?.text = it?.message
                }
            }
        })

    }

    override fun onResume() {
        super.onResume()
        viewLifecycleOwner?.lifecycleScope?.launch {
            favouriteViewModel?.getFavourites(ctx?.preference?.getInt("SORT_CRTIERIA"))
            viewModel?.getContent(ctx?.preference?.getInt("SORT_CRTIERIA"))
        }
    }
    override fun remove(id: Int) {
        Toast.makeText(
            activity,
            "Removed from Favorites Section ",
            Toast.LENGTH_SHORT
        ).show()
        viewModel?.remove(id)
    }

    override fun save(favourites: Favourites) {
        Toast.makeText(
            activity,
            "Added to Favorites Section ",
            Toast.LENGTH_SHORT
        ).show()
        viewModel?.save(favourites)
    }

    fun sort( sortCriteria: Int) {
        viewModel?.sorting(sortCriteria);
    }

    fun updateVisibility(message: Int, Progress :Int, list: Int) {
        productFragmentBinding?.loading?.visibility =message
        productFragmentBinding?.progressBarCyclic?.visibility = Progress
        productFragmentBinding?.mobileData?.visibility = list
    }

}