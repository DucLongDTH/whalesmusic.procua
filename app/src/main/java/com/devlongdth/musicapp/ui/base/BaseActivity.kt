package com.devlongdth.musicapp.ui.base

import androidx.appcompat.app.AppCompatActivity

open class BaseActivity : AppCompatActivity(){
    fun backRoot(){
        super.onBackPressed()
    }
}