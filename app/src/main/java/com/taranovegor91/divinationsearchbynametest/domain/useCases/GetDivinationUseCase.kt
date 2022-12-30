package com.taranovegor91.divinationsearchbynametest.domain.useCases

import com.taranovegor91.divinationsearchbynametest.domain.interfaces.repository.RepositorySearch
import com.taranovegor91.divinationsearchbynametest.domain.models.Name

class GetDivinationUseCase(private val repositorySearch: RepositorySearch) {
    suspend fun execute(name: String):String {
        return repositorySearch.getDivination(name)
    }
}