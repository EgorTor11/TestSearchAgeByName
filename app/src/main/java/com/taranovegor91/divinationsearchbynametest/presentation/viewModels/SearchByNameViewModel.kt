package com.taranovegor91.divinationsearchbynametest.presentation.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.taranovegor91.divinationsearchbynametest.domain.models.Name
import com.taranovegor91.divinationsearchbynametest.domain.useCases.GetDivinationUseCase
import com.taranovegor91.divinationsearchbynametest.domain.useCases.InsertNameUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class SearchByNameViewModel(
    private val insertNameUseCase: InsertNameUseCase,
    private val getDivinationUseCase: GetDivinationUseCase,
) : ViewModel() {
    val divinationLiveData = MutableLiveData<String?>()

    fun getDivination(name: String) {
        viewModelScope.launch {
        divinationLiveData.postValue(getDivinationUseCase.execute(name))
        }
    }
    fun insertName(name:String){
        viewModelScope.launch(Dispatchers.IO) {
            insertNameUseCase.execute(Name(null,name,false,false))
        }
    }
}
