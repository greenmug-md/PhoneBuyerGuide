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
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.greenmug.android.buyerguide.R
import com.greenmug.android.buyerguide.adapter.FavouriteAdapter
import com.greenmug.android.buyerguide.viewmodel.FavouriteViewModel
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import androidx.recyclerview.widget.RecyclerView.ViewHolder

import androidx.recyclerview.widget.RecyclerView
import com.greenmug.android.buyerguide.databinding.FragmentFavouriteBinding
import com.greenmug.android.buyerguide.activity.MainActivity
import com.greenmug.android.buyerguide.utils.Response
import kotlinx.coroutines.async
import kotlinx.coroutines.launch


@AndroidEntryPoint
class FavouriteFragment : Fragment() {

    private var favouriteBinding: FragmentFavouriteBinding? = null
    private lateinit var viewModel: FavouriteViewModel
    private lateinit var ctx: MainActivity;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        favouriteBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_favourite, container, false)
        return favouriteBinding?.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        ctx = context as MainActivity
    }

    companion object {
        fun create(): FavouriteFragment {
            return FavouriteFragment()
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(FavouriteViewModel::class.java)
        setUpRecyclerView()
        setUpObservers()
    }

    override fun onResume() {
        super.onResume()
        viewLifecycleOwner?.lifecycleScope?.launch {
            viewModel?.getFavourites(ctx?.preference?.getInt("SORT_CRTIERIA"))
        }
    }

    fun setUpRecyclerView() {
        favouriteBinding?.favouriteData?.setLayoutManager(LinearLayoutManager(getActivity()));
        favouriteBinding?.favouriteData?.let {
            setUpSwipe(it)
        }
    }

    fun setUpObservers() {
        viewModel?.favourite?.observe(viewLifecycleOwner, {
            var s = when (it) {
                is Response.Success -> {
                    favouriteBinding?.loading?.visibility =View.GONE
                    favouriteBinding?.favouriteData?.adapter =
                        FavouriteAdapter(requireActivity(), it?.data?.toMutableList()!!)
                    favouriteBinding?.favouriteData?.adapter?.notifyDataSetChanged()
                }
                is Response.Loading -> {

                }
                is Response.Error -> {
                    favouriteBinding?.loading?.visibility =View.VISIBLE
                    favouriteBinding?.loading?.text = it?.message
                }
                is Response.Empty -> {
                    favouriteBinding?.loading?.visibility =View.VISIBLE
                    favouriteBinding?.loading?.text = it?.message
                }
            }
        })
    }

    fun sort( sortCriteria: Int) {
        viewModel?.sorting(sortCriteria);
    }

    fun remove(position:Int) {
        viewModel?.favourite?.value?.data?.get(position)?.id?.let {
            viewModel?.favouritesRepository?.favouriteShowDatabase?.mobileFavouritesDao()
                ?.removeFavourites(
                    viewModel?.favourite?.value?.data?.get(position)?.id
                )
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe {
                    Toast.makeText(
                        activity,
                        "Removed from Favorites Section ",
                        Toast.LENGTH_SHORT
                    ).show()
                };
        }
    }

    fun  setUpSwipe(mobileData: RecyclerView) {
        ItemTouchHelper(
            object : ItemTouchHelper.SimpleCallback(
                ItemTouchHelper.UP or ItemTouchHelper.DOWN,
                ItemTouchHelper.LEFT
            ) {
                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: ViewHolder, target: ViewHolder
                ): Boolean {
                    return true
                }

                override fun onSwiped(viewHolder: ViewHolder, direction: Int) {
                    remove(viewHolder?.absoluteAdapterPosition);
                    viewModel?.remove(viewHolder?.absoluteAdapterPosition)
                }
            }).attachToRecyclerView(mobileData)
    }

}