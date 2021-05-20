package com.devlongdth.musicapp.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import androidx.viewpager.widget.ViewPager
import com.devlongdth.musicapp.R
import com.devlongdth.musicapp.databinding.ActivityMainBinding
import com.devlongdth.musicapp.ui.base.BaseActivity
import com.devlongdth.musicapp.ui.categoryfragment.CategoryFragment
import com.devlongdth.musicapp.ui.custom.ThemeActive
import com.devlongdth.musicapp.ui.favouritefragment.FavouriteFragment
import com.devlongdth.musicapp.ui.listfragment.ListFragment
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : BaseActivity(), ViewPager.OnPageChangeListener,
    BottomNavigationView.OnNavigationItemSelectedListener {
    private var binding: ActivityMainBinding? = null
    private var navigationBottom: BottomNavigationView? = null
    private var viewPager: ViewPager? = null
    companion object{
        private var isDark:Boolean = false
        @JvmStatic
        fun checkIsDark() = isDark
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        ThemeActive.activeTheme(this)
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        navigationBottom = binding!!.bottomNav
        navigationBottom!!.setOnNavigationItemSelectedListener(this)
        viewPager = binding!!.viewPager
        initViewPager()
        setIconChangeTheme()
        binding!!.ivChangeTheme.setOnClickListener {
            if (!isDark){
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                Log.e("APP_NIGHT","$isDark")
                isDark = true
                recreate()
            }else{
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                Log.e("APP_LIGHT","$isDark")
                isDark = false
                recreate()
            }
        }
    }

    private fun setIconChangeTheme() {
        if (!isDark){
            binding!!.ivChangeTheme.setImageResource(R.drawable.baseline_brightness_5_black_24dp)
        }else{
            binding!!.ivChangeTheme.setImageResource(R.drawable.baseline_brightness_4_white_24dp)
        }
    }

    private fun initViewPager() {
        val vpAdapter = ViewPagerAdapter(supportFragmentManager)
        viewPager!!.adapter = vpAdapter
        viewPager!!.addOnPageChangeListener(this)
    }

    override fun onPageScrollStateChanged(state: Int) {

    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

    }

    override fun onPageSelected(position: Int) {
        when(position){
            0 ->{
                navigationBottom!!.menu.findItem(R.id.action_category).isChecked = true
            }
            1 ->{ navigationBottom!!.menu.findItem(R.id.action_list).isChecked = true }
            2 ->{ navigationBottom!!.menu.findItem(R.id.action_like).isChecked = true  }
        }

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_category -> {
                viewPager!!.currentItem = 0
            }
            R.id.action_list -> {
                viewPager!!.currentItem = 1
            }
            R.id.action_like -> {
                viewPager!!.currentItem = 2
            }
        }
        return true
    }
}