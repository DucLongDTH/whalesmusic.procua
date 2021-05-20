package com.devlongdth.musicapp.ui.base

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

class BaseAdapter {
    open interface IBaseAdaper<T>{
        fun getCount(): Int
        fun getData(position: Int): T
        fun onItemClick(position: Int)
    }
    open class BaseViewHolder<VD:ViewDataBinding>(val binding: VD):RecyclerView.ViewHolder(binding.root)
}