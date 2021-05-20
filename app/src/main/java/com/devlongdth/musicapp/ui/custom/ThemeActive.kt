package com.devlongdth.musicapp.ui.custom

import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import com.devlongdth.musicapp.R

class ThemeActive {
    companion object{
        fun activeTheme(ct:Context){
            if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES){
                ct.setTheme(R.style.Theme_MusicAppNight)
            }else{
                ct.setTheme(R.style.Theme_MusicApp)
            }
        }

        fun checkTheme():Boolean{
            return AppCompatDelegate.getDefaultNightMode() != AppCompatDelegate.MODE_NIGHT_YES
        }

    }
}