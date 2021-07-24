package com.spidergod.nobrokerassignment.data.parcelables

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class NoBrokerParcelable(
    val image: String,
    val title: String,
    val subTitle: String,
) : Parcelable
