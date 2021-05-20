package com.devlongdth.musicapp.ui.favouritefragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.devlongdth.musicapp.databinding.FragmentFavouriteBinding
import com.devlongdth.musicapp.ui.base.BaseFragment

class FavouriteFragment : BaseFragment() {
    private var binding: FragmentFavouriteBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavouriteBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}