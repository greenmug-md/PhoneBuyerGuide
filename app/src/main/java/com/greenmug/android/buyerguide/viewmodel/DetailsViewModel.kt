package com.greenmug.android.buyerguide.viewmodel


import android.util.Log
import androidx.lifecycle.*
import com.greenmug.android.buyerguide.model.Favourites
import com.greenmug.android.buyerguide.model.MobileImagesData
import com.greenmug.android.buyerguide.model.MobileImagesDataItem
import com.greenmug.android.buyerguide.repository.MobileDataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel@Inject constructor(val mobileDataRepository: MobileDataRepository) : ViewModel() {

    private var _name = MutableLiveData<String>();
    val name : LiveData<String>
        get() = _name
    private var _brand = MutableLiveData<String>();
    val brand : LiveData<String>
        get() = _brand
    private var _description = MutableLiveData<String>();
    val description : LiveData<String>
        get() = _description
    private var _rating = MutableLiveData<Double>();
    val rating : LiveData<Double>
        get() = _rating
    private var _price = MutableLiveData<Double>();
    val price : LiveData<Double>
        get() = _price
     var imagesData = MutableLiveData<MobileImagesData>()


    fun setData(names:String, brands: String, descriptios:String,ratings:Double,prices:Double) {
        _name?.value = names
        _brand?.value = brands
        _description?.value = descriptios
        _rating?.value = ratings
        _price?.value = prices
    }

    fun getImages(id: String)   {
        viewModelScope?.launch {
            var mobilePhoneData = mobileDataRepository?.getMobileImages(id);
            imagesData?.postValue(mobilePhoneData?.body())
        }
    }


}


