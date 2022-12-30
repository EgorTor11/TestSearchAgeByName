package com.taranovegor91.divinationsearchbynametest.domain.useCases
import com.taranovegor91.divinationsearchbynametest.domain.interfaces.repository.RepositoryDB
import com.taranovegor91.divinationsearchbynametest.domain.models.Name
import kotlinx.coroutines.flow.Flow

class SelectAllNamesUseCase(private val repositoryDB: RepositoryDB) {
    fun execute() : Flow<MutableList<Name>> {
        return repositoryDB.selectAllNames()
    }
}