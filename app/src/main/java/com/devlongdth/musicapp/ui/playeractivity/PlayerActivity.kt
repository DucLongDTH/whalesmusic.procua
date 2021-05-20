package com.devlongdth.musicapp.ui.playeractivity

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Color
import android.media.MediaPlayer
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.PersistableBundle
import android.text.TextUtils
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.SeekBar
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import com.devlongdth.musicapp.R
import com.devlongdth.musicapp.databinding.ActivityMainBinding
import com.devlongdth.musicapp.databinding.ActivityPlayerBinding
import com.devlongdth.musicapp.databinding.ListItemSongBinding
import com.devlongdth.musicapp.model.SongOffline
import com.devlongdth.musicapp.ui.base.BaseActivity
import com.devlongdth.musicapp.ui.custom.ThemeActive
import java.lang.Exception
import java.lang.Thread.sleep

class PlayerActivity : BaseActivity(), View.OnClickListener, MusicPlayer.IMusicPlayer {
    private var binding: ActivityPlayerBinding? = null
    private var listSongs: ArrayList<SongOffline>? = null
    private var pos = 0
    var handler:Handler? = null
    companion object {
        private var musicManager: MusicPlayer? = null
    }

    private var song: SongOffline? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        ThemeActive.activeTheme(this)
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_player)
        binding?.btnPlay?.setOnClickListener(this)
        binding?.btnNext?.setOnClickListener(this)
        binding?.btnPrev?.setOnClickListener(this)
        binding?.tvTitle?.ellipsize = TextUtils.TruncateAt.MARQUEE
        binding?.tvArtist?.ellipsize = TextUtils.TruncateAt.MARQUEE
        binding?.tvArtist?.isSelected = true
        binding?.tvTitle?.isSelected = true

        if (musicManager == null) {
            musicManager = MusicPlayer(this)
        }
        getParam()
        prepareSong()
        binding!!.seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                musicManager!!.seekSongTo(seekBar!!.progress)
            }
        })
        initSeekBar()

    }

    private fun initSeekBar() {
        binding!!.seekBar.max = musicManager!!.getDurationSong()
        handler = Handler()
        handler!!.postDelayed(object : Runnable {
            override fun run() {
                try {
                    binding!!.seekBar.progress = musicManager!!.getCurrentPositionSong()
                    binding!!.tvCurrentTime.text =
                        makeTextTime(musicManager!!.getCurrentPositionSong().toLong())
                    handler!!.postDelayed(this, 1000)
                } catch (e: Exception) {
                    binding!!.seekBar.progress = 0
                }
            }
        }, 0)

    }

    private fun getParam() {
        var i = intent
        listSongs = i.getParcelableArrayListExtra("listsong")
        pos = i.getIntExtra("position", 0)!!
        song = listSongs?.get(pos)
        binding!!.data = song
    }

    private fun prepareSong() {
        musicManager!!.setDataSong(song!!.path!!)
        musicManager!!.startSong()
        binding!!.btnPlay.setImageResource(R.drawable.baseline_pause_circle_teal_500_48dp)
        binding!!.tvMaxTime.text = makeTextTime(musicManager!!.getDurationSong().toLong())

    }

    override fun onClick(v: View) {
        var next_pos = pos + 1
        var prev_pos = pos - 1
        when (v.id) {
            R.id.btn_play -> {
                if (!musicManager!!.isSongPlaying()) {
                    musicManager!!.startSong()
                    binding!!.btnPlay.setImageResource(R.drawable.baseline_pause_circle_teal_500_48dp)
                } else {
                    musicManager!!.pauseSong()
                    binding!!.btnPlay.setImageResource(R.drawable.baseline_play_circle_teal_500_48dp)
                }
            }
            R.id.btn_next -> {
                if (next_pos == listSongs!!.size) {
                    next_pos = 0
                }
                updateSongAndUI(next_pos)

            }
            R.id.btn_prev -> {
                if (prev_pos < 0) {
                    prev_pos = listSongs!!.size - 1
                }
                updateSongAndUI(prev_pos)
            }
        }
    }

    private fun updateSongAndUI(newPos: Int) {
        song = listSongs!![newPos]
        pos = newPos
        musicManager!!.doNextOrPrev(song!!)
        binding!!.data = song
        binding!!.tvMaxTime.text = makeTextTime(musicManager!!.getDurationSong().toLong())
        animationImg(binding!!.ivThumb,1000)
        checkIconPlay()
        handler = null
        initSeekBar()
    }

    private fun checkIconPlay() {
        if (musicManager!!.isSongPlaying()) {
            binding!!.btnPlay.setImageResource(R.drawable.baseline_pause_circle_teal_500_48dp)
        } else {
            binding!!.btnPlay.setImageResource(R.drawable.baseline_play_circle_teal_500_48dp)
        }
    }

    private fun animationImg(v: View,duration: Long) {
        var animator = ObjectAnimator.ofFloat(binding!!.ivThumb, "rotation", 0f, 360f)
        animator.duration = duration
        var aniSet = AnimatorSet()
        aniSet.playTogether(animator)
        aniSet.start()
    }

    private fun makeTextTime(duration: Long): String {
        var time = ""
        var hour = duration / (1000 * 60 * 60)
        var min = (duration / (1000 * 60)) % (60)
        var sec = (duration / (1000)) % 60
        if (duration >= (1000 * 60 * 60)) {
            time += "$hour:"
        }
        time += if (min < 10) {
            "0$min:"
        } else {
            "$min:"
        }
        time += if (sec < 10) {
            "0$sec"
        } else {
            "$sec"
        }
        return time

    }

    override fun autoNextSong() {
        Log.e("autoNextSong", "NEXT SONGGGGGGGGGG")
        var next_pos = pos + 1
        if (next_pos == listSongs!!.size) {
            next_pos = 0
        }
        updateSongAndUI(next_pos)
    }
}