package com.shubham.techugo.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Result(
    val description_title: String,
    val decription_image: String,
    val description_body: String,
    val banner: ArrayList<String>,
    val latitudes: String,
    val longitude: String,
    val cupons : ArrayList<Cupons>
) : Parcelable