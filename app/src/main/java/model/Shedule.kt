package model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "shedules")
@Parcelize
data class Shedule(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val sheTitle: String,
    val sheDesc: String
):Parcelable
