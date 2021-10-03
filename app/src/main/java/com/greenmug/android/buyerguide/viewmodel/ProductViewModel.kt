package com.greenmug.android.buyerguide.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.greenmug.android.buyerguide.model.Favourites
import com.greenmug.android.buyerguide.model.MobilePhoneData
import com.greenmug.android.buyerguide.repository.FavouritesRepository
import com.greenmug.android.buyerguide.repository.MobileDataRepository
import com.greenmug.android.buyerguide.utils.NetworkHandler
import com.greenmug.android.buyerguide.utils.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class ProductViewModel@Inject constructor(var application: Application,
                                          val mobileDataRepository: MobileDataRepository,
                                          val favouritesRepository: FavouritesRepository
) : ViewModel() {


    var _productList : MutableLiveData<Response<MobilePhoneData>> = MutableLiveData()
    val productList : LiveData<Response<MobilePhoneData>>
        get() = _productList
    private var mobilePhone = MobilePhoneData();

    suspend fun getContent(criteria: Int)   {
        _productList.value = Response.Loading("Loading")
        when(NetworkHandler.isNetworkAvailable(application)) {
            true -> {
                try {
                    var mobilePhoneData = mobileDataRepository?.getAllMobile();
                    sorting(criteria, mobilePhoneData?.body());
                    if (mobilePhoneData?.isSuccessful) {
                        mobilePhone = sorting(criteria, mobilePhoneData?.body());
                        _productList.value = Response.Success(mobilePhone)
                    }
                } catch (e: Exception) {
                    _productList.value = Response.Error(e?.message)
                }
            }
            false -> {
                _productList.value  = Response.Error("No Network Available")
            }
        }
    }

    fun sorting(sortCriteria: Int) {
        var mobilePhone = sorting(sortCriteria, _productList?.value?.data)
        _productList.value = Response.Success(mobilePhone)
    }

    fun sorting(sortCriteria: Int, data: MobilePhoneData?) : MobilePhoneData {
            when  (sortCriteria) {
                0 -> {
                    data?.let {
                        mobilePhone = sortPricingLowToHigh(it);
                    }
                }
                1 -> { data?.let{
                  mobilePhone = sortPricingHighToLow(it);
                }
                    }
                2 -> {
                    data?.let {
                        mobilePhone =  sortRating(it);
                    }
                }
                    else -> {
                        mobilePhone = data!!
                    }
                }
            return mobilePhone;

        }




    fun sortRating( data: MobilePhoneData) : MobilePhoneData {
        var dataSorted = data.toList()?.sortedByDescending {it.rating }
        mobilePhone?.clear();
        mobilePhone?.addAll(dataSorted)
        return mobilePhone
    }

    fun sortPricingHighToLow(data: MobilePhoneData) : MobilePhoneData {
        var dataSorted = data.toList()?.sortedByDescending {it.price } ;
        mobilePhone?.clear();
        mobilePhone?.addAll(dataSorted)
        return mobilePhone
    }

    fun sortPricingLowToHigh(data: MobilePhoneData): MobilePhoneData  {
        var dataSorted = data.toList()?.sortedBy {it.price } ;
        mobilePhone?.clear();
        mobilePhone?.addAll(dataSorted)
        return mobilePhone
    }

     fun remove(id: Int) {
         favouritesRepository?.favouriteShowDatabase?.mobileFavouritesDao()?.removeFavourites(id)
             ?.subscribeOn(Schedulers.io())
             ?.observeOn(AndroidSchedulers.mainThread())
             ?.subscribe {
             }
     }

    fun save(favourites: Favourites) {
        favouritesRepository?.favouriteShowDatabase?.mobileFavouritesDao()?.addToFavourites(favourites)
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe {
            }
    }

}