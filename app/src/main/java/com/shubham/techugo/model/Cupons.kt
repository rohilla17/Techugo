package com.shubham.techugo.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Cupons(val title : String, val description : String, val price : String) : Parcelable
