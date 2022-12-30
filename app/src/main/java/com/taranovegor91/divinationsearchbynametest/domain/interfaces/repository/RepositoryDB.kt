package com.taranovegor91.divinationsearchbynametest.domain.interfaces.repository

import com.taranovegor91.divinationsearchbynametest.domain.models.Name
import kotlinx.coroutines.flow.Flow

interface RepositoryDB {
    fun insertName(name: Name)
    fun deleteName(name: Name)
    fun updateName(name: Name)
    fun selectAllNames(): Flow<MutableList<Name>>
}