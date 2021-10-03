package com.greenmug.android.buyerguide.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.greenmug.android.buyerguide.model.Favourites
import com.greenmug.android.buyerguide.repository.FavouritesRepository
import com.greenmug.android.buyerguide.utils.NetworkHandler
import com.greenmug.android.buyerguide.utils.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavouriteViewModel @Inject constructor(
    var application: Application,
    val favouritesRepository: FavouritesRepository
) : ViewModel() {

    private var _favouriteList = MutableLiveData<Response<List<Favourites>>>()
    val favourite : LiveData<Response<List<Favourites>>>
            get() = _favouriteList

    suspend fun getFavourites(criteria: Int) {
        _favouriteList.value = Response.Loading("Loading")

        try {
            var favouritePhoneData =
                favouritesRepository?.favouriteShowDatabase?.mobileFavouritesDao()
                    ?.getAllFavourites()
                var favourite = sorting(criteria, favouritePhoneData);
                _favouriteList.value = Response.Success(favourite)

        } catch (e: Exception) {
            _favouriteList.value = Response.Error(e?.message)
        }

    }

    fun remove(position: Int) {
        var data = _favouriteList?.value?.data?.toMutableList()?.apply {
            removeAt(position)
        }
        data?.let {
            _favouriteList.value = Response.Success(it)
        }
    }

    fun sorting(sortCriteria: Int) {
        var favouriteData = sorting(sortCriteria, _favouriteList?.value?.data)
        _favouriteList?.value = Response.Success(favouriteData)
    }

    fun sorting(sortCriteria: Int, data: List<Favourites>?): List<Favourites> {
        var favourites: List<Favourites> = ArrayList<Favourites>();
        when (sortCriteria) {
            0 -> {
                data?.let {
                    favourites = sortPricingLowToHigh(it);
                }
            }
            1 -> {
                data?.let {
                    favourites = sortPricingHighToLow(it);
                }
            }
            2 -> {
                data?.let {
                    favourites = sortRating(it);
                }
            }
            else -> {
                favourites = data!!
            }
        }
        return favourites

    }


    fun sortRating(data: List<Favourites>): List<Favourites> {
        return data?.sortedByDescending { it.rating }
    }

    fun sortPricingHighToLow(data: List<Favourites>): List<Favourites> {
        return data?.sortedByDescending { it.price };
    }

    fun sortPricingLowToHigh(data: List<Favourites>): List<Favourites> {
        return data?.sortedBy { it.price };
    }

    fun exists(productId: Int): Boolean {
        var s = _favouriteList?.value?.data?.filter { it.id.equals(productId) };
        return (s?.size != 0)
    }

}