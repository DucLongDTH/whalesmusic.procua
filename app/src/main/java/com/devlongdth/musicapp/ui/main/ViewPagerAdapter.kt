package com.devlongdth.musicapp.ui.main


import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.devlongdth.musicapp.ui.base.BaseFragment
import com.devlongdth.musicapp.ui.categoryfragment.CategoryFragment
import com.devlongdth.musicapp.ui.favouritefragment.FavouriteFragment
import com.devlongdth.musicapp.ui.listfragment.ListFragment

class ViewPagerAdapter : FragmentPagerAdapter {
    constructor(fm: FragmentManager) : super(fm)
    override fun getItem(position: Int): BaseFragment {
        when(position){
            0 ->{ return CategoryFragment() }
            1 ->{ return ListFragment() }
            2 ->{ return FavouriteFragment() }
            else ->{ return CategoryFragment() }
        }

    }

    override fun getCount(): Int = 3
}