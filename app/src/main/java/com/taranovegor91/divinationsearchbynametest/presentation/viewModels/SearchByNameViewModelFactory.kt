package com.taranovegor91.divinationsearchbynametest.presentation.viewModels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.taranovegor91.divinationsearchbynametest.data.repositorys.RepositoryDBImpl
import com.taranovegor91.divinationsearchbynametest.data.repositorys.RepositorySearchImpl
import com.taranovegor91.divinationsearchbynametest.domain.useCases.GetDivinationUseCase
import com.taranovegor91.divinationsearchbynametest.domain.useCases.InsertNameUseCase


class SearchByNameViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    var repositoryDB = RepositoryDBImpl()
    var repositorySearch = RepositorySearchImpl()
    var insertNameUseCase = InsertNameUseCase(repositoryDB)
    var getDivinationUseCase = GetDivinationUseCase(repositorySearch)

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SearchByNameViewModel(insertNameUseCase,getDivinationUseCase) as T
    }
}