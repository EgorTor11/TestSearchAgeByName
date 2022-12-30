package com.taranovegor91.divinationsearchbynametest.presentation.viewModels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.taranovegor91.divinationsearchbynametest.data.repositorys.RepositoryDBImpl
import com.taranovegor91.divinationsearchbynametest.domain.useCases.DeleteNameUseCase
import com.taranovegor91.divinationsearchbynametest.domain.useCases.InsertNameUseCase
import com.taranovegor91.divinationsearchbynametest.domain.useCases.SelectAllNamesUseCase
import com.taranovegor91.divinationsearchbynametest.domain.useCases.UpdateNameUseCase


class SelectedNameViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    var repository = RepositoryDBImpl()
    var deleteNameUseCase = DeleteNameUseCase(repository)
    var updateNameUseCase = UpdateNameUseCase(repository)
    var selectAllNamesUseCase = SelectAllNamesUseCase(repository)
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SelectedNamesViewModel(deleteNameUseCase, updateNameUseCase,selectAllNamesUseCase) as T
    }
}