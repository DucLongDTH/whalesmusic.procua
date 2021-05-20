package com.devlongdth.musicapp.model

import android.net.Uri
import android.os.Parcel
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*
@Parcelize
data class SongOffline(
    var name: String?,
    var artist: String?,
    var pathImage: Uri?,
    var duration: String?,
    var path: String?
): Parcelable
