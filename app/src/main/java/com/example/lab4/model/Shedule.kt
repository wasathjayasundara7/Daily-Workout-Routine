package com.example.lab4.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "shes")
@Parcelize
data class She(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val sheTitle: String,
    val sheDesc: String
):Parcelable
