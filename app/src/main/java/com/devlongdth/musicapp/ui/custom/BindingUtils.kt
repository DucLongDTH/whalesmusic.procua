package com.devlongdth.musicapp.ui.custom

import android.net.Uri
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.devlongdth.musicapp.R

object BindingUtils {
    @JvmStatic
    @BindingAdapter("loadImageUri")
    fun loadImageUri(iv: ImageView, uri: Uri?) {
        if (uri == null){
            Glide.with(iv)
                .load(R.drawable.baseline_music_note_teal_300_24dp)
                .placeholder(R.drawable.baseline_music_note_teal_300_24dp)
                .error(R.drawable.baseline_music_note_teal_300_24dp)
                .into(iv)
            return
        }
        Glide.with(iv)
            .load(uri)
            .placeholder(R.drawable.baseline_music_note_teal_300_24dp)
            .error(R.drawable.baseline_music_note_teal_300_24dp)
            .into(iv)
    }

    @JvmStatic
    @BindingAdapter("loadImageLink")
    fun loadImageInt(iv: ImageView, link: String?) {
        if (link == null) {
            Glide.with(iv)
                .load(R.drawable.ao_dai1)
                .placeholder(R.drawable.ao_dai1)
                .error(R.drawable.ao_dai1)
                .into(iv)
            return
        }
        Glide.with(iv)
            .load(link)
            .placeholder(R.drawable.ao_dai1)
            .error(R.drawable.ao_dai1)
            .into(iv)
    }

    @JvmStatic
    @BindingAdapter("setText")
    fun setText(tv: TextView, value: String?) {
        tv.setText(value)
    }
}