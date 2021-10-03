package com.greenmug.android.buyerguide.adapter


import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
    override fun getItemCount(): Int = ResourceStore.tabList.size

    override fun createFragment(position: Int): Fragment {
        return ResourceStore.pagerFragments[position]
    }
}