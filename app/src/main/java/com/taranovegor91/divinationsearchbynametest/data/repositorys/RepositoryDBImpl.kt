package com.taranovegor91.divinationsearchbynametest.data.repositorys

import android.content.Context
import androidx.lifecycle.asLiveData
import com.taranovegor91.divinationsearchbynametest.app.mainDb
import com.taranovegor91.divinationsearchbynametest.domain.interfaces.repository.RepositoryDB
import com.taranovegor91.divinationsearchbynametest.domain.models.Name
import kotlinx.coroutines.flow.Flow

class RepositoryDBImpl() : RepositoryDB {
    override fun insertName(name: Name) {
        mainDb.getDao().insertNameInSelectedNames(name)
    }

    override fun deleteName(name: Name) {
        mainDb.getDao().deleteItem(name)
    }

    override fun updateName(name: Name) {
        mainDb.getDao().updateItem(name)
    }

    override fun selectAllNames(): Flow<MutableList<Name>> {
        return mainDb.getDao().selectAllItems()
    }
}


