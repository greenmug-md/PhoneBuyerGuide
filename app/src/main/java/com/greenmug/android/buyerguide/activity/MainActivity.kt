package com.greenmug.android.buyerguide.activity

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import com.google.android.material.tabs.TabLayoutMediator
import com.greenmug.android.buyerguide.R
import com.greenmug.android.buyerguide.adapter.ResourceStore
import com.greenmug.android.buyerguide.adapter.ViewPagerAdapter
import com.greenmug.android.buyerguide.databinding.ActivityMainBinding
import com.greenmug.android.buyerguide.fragments.FavouriteFragment
import com.greenmug.android.buyerguide.fragments.ProductFragment
import com.greenmug.android.buyerguide.utils.Constants
import com.greenmug.android.buyerguide.utils.PreferenceManager
import com.greenmug.android.buyerguide.utils.SortingCriteria
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : FragmentActivity() {

    lateinit var preference: PreferenceManager
     private var activityMainBinding: ActivityMainBinding? = null
     private lateinit var sortDialog: AlertDialog

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            activityMainBinding =  DataBindingUtil.setContentView(this, R.layout.activity_main)
            preference = PreferenceManager(this);
            renderViewPager();
            renderTabLayer();
            renderSorting();
        }

        private fun renderViewPager(){
            val pagerAdapter = ViewPagerAdapter(this)
            activityMainBinding?.viewpager?.adapter = pagerAdapter
        }

        private fun renderTabLayer() {
            TabLayoutMediator(activityMainBinding?.tabs!!, activityMainBinding?.viewpager!!) { tab, position ->
                tab.text =  ResourceStore.tabList[position]
            }.attach()
        }

    private fun renderSorting() {
        activityMainBinding?.sort?.setOnClickListener({
            showDialogBox()
        })
    }

    fun showDialogBox() {
        val values = SortingCriteria.values
        val builder = AlertDialog.Builder(this)
        builder.setSingleChoiceItems(values, preference?.getInt(Constants.SORT_CRTIERIA), DialogInterface.OnClickListener { _, item ->
            preference?.putInt(Constants.SORT_CRTIERIA,item)
            if (activityMainBinding?.viewpager?.getCurrentItem() === 0) {
                var fragment =  this.supportFragmentManager.getFragments().get(activityMainBinding?.viewpager?.currentItem!!) as ProductFragment
                fragment?.sort(item);
            } else  {
                var fragment =  this.supportFragmentManager.getFragments().get(activityMainBinding?.viewpager?.currentItem!!) as FavouriteFragment
                fragment?.sort(item);
            }
            sortDialog?.dismiss()
        })
        sortDialog = builder.create()
        sortDialog.show()
    }

}