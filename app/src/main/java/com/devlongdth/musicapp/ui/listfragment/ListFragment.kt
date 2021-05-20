package com.devlongdth.musicapp.ui.listfragment

import android.content.ContentUris
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Parcelable
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.devlongdth.musicapp.databinding.FragmentListBinding
import com.devlongdth.musicapp.model.SongOffline
import com.devlongdth.musicapp.ui.base.BaseFragment
import com.devlongdth.musicapp.ui.playeractivity.PlayerActivity
import java.util.*
import kotlin.collections.ArrayList

class ListFragment : BaseFragment(), ListAdapter.IListAdapter {
    private var binding: FragmentListBinding? = null
    private var listSongs = mutableListOf<SongOffline>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.rc?.layoutManager = LinearLayoutManager(context)
        binding?.rc?.adapter = ListAdapter(this)
        loadSong()
    }

    private fun loadSong() {
        val c = context!!.contentResolver.query(
            MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null, null, null
        )
        if(c == null)return
        c.moveToFirst()
        val indexPath = c.getColumnIndex(MediaStore.Audio.Media.DATA)
        val indexArtist = c.getColumnIndex(MediaStore.Audio.Media.ARTIST)
        val indexAb = c.getColumnIndex(MediaStore.Audio.Media.ALBUM_ID)
        val indexTitle = c.getColumnIndex(MediaStore.Audio.Media.TITLE)
        val indexDuration = c.getColumnIndex(MediaStore.Audio.Media.DURATION)
        while (!c.isAfterLast){
            val path = c.getString(indexPath)
            val artist = c.getString(indexArtist)
            val ab = c.getLong(indexAb)
            val title = c.getString(indexTitle)
            val duration = c.getString(indexDuration)
            val uri = ContentUris.withAppendedId(
                Uri.parse("content://media/external/audio/albumart"),
                ab)
            listSongs.add(
                SongOffline(
                    title, artist, uri, duration,
                    path
                )
            )
            c.moveToNext()
        }
        binding?.rc?.adapter?.notifyDataSetChanged()
    }

    override fun getCount(): Int {
        return listSongs.size
    }

    override fun getData(position: Int): SongOffline {
        return listSongs[position]
    }

    override fun onItemClick(position: Int) {
        var intent = Intent(activity!!.application,PlayerActivity::class.java)
        intent.putExtra("listsong",ArrayList(listSongs))
        intent.putExtra("position",position)
        startActivity(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}