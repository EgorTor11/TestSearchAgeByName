package com.taranovegor91.divinationsearchbynametest.domain.useCases


import com.taranovegor91.divinationsearchbynametest.domain.interfaces.repository.RepositoryDB
import com.taranovegor91.divinationsearchbynametest.domain.models.Name

class InsertNameUseCase(private val repositoryDB: RepositoryDB) {
    fun execute(name: Name){
        repositoryDB.insertName(name)
    }
}