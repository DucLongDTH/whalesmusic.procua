package com.devlongdth.musicapp.ui.listfragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.devlongdth.musicapp.databinding.ListItemSongBinding
import com.devlongdth.musicapp.model.SongOffline
import com.devlongdth.musicapp.ui.base.BaseAdapter

class ListAdapter: RecyclerView.Adapter<ListAdapter.ListViewHolder> {
    private val inter: IListAdapter
    constructor(inter: IListAdapter){
        this.inter = inter
    }

    interface IListAdapter: BaseAdapter.IBaseAdaper<SongOffline>
    class ListViewHolder(binding:ListItemSongBinding):BaseAdapter.BaseViewHolder<ListItemSongBinding>(binding)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        return ListViewHolder(
            ListItemSongBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        )
    }

    override fun getItemCount(): Int {
       return inter.getCount()
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.binding.data = inter.getData(position)
        holder.binding.root.setOnClickListener {
            inter.onItemClick(position)
        }
    }
}