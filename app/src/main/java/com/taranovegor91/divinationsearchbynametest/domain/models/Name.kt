package com.taranovegor91.divinationsearchbynametest.domain.models
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "SelectedNames")
data class Name(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    @ColumnInfo(name = "name")
    var name:String,
    @ColumnInfo(name = "isCheckBoxVisible")
    var isCheckBoxVisible:Boolean,
    @ColumnInfo(name = "isChecked")
    var isChecked:Boolean

) : Parcelable