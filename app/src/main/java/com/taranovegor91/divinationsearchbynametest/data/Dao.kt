package com.taranovegor91.divinationsearchbynametest.data

import androidx.room.*
import androidx.room.Dao
import com.taranovegor91.divinationsearchbynametest.domain.models.Name

import kotlinx.coroutines.flow.Flow

@Dao
interface Dao {
    @Insert
    fun insertNameInSelectedNames(name: Name)
    @Query("SELECT * FROM SelectedNames")
    fun selectAllItems():Flow<MutableList<Name>>
    @Query("SELECT * FROM SelectedNames WHERE id= :id LIMIT 1")
    fun getItemById(id:Int): Name
    @Update
    fun updateItem(name: Name)
    @Delete
    fun deleteItem(name: Name)
}