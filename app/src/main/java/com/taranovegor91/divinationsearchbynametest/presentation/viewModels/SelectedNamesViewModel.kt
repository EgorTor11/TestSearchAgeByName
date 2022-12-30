package com.taranovegor91.divinationsearchbynametest.presentation.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.taranovegor91.divinationsearchbynametest.domain.models.Name
import com.taranovegor91.divinationsearchbynametest.domain.useCases.DeleteNameUseCase
import com.taranovegor91.divinationsearchbynametest.domain.useCases.InsertNameUseCase
import com.taranovegor91.divinationsearchbynametest.domain.useCases.SelectAllNamesUseCase
import com.taranovegor91.divinationsearchbynametest.domain.useCases.UpdateNameUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SelectedNamesViewModel(
    private val deleteNameUseCase: DeleteNameUseCase,
    private val updateNameUseCase: UpdateNameUseCase,
    private val selectAllNamesUseCase: SelectAllNamesUseCase,
) : ViewModel() {
    val selectAllNamesLiveData = selectAllNamesUseCase.execute().asLiveData()
    var listForDeleteLiveData = MutableLiveData<MutableList<Name>>()
    fun showCheckBoxes(nameList: MutableList<Name>) {
        val listForDeleteName = mutableListOf<Name>()
        nameList.forEach {
            it.isCheckBoxVisible = true
            listForDeleteName.add(it.copy())
        }
        listForDeleteLiveData.postValue(listForDeleteName)
    }

    fun updateListName(name: Name, nameList: MutableList<Name>) {
        val updatedList = mutableListOf<Name>()
        nameList.forEach {
            updatedList.add(it.copy())
        }
        nameList.forEachIndexed { i: Int, n: Name ->
            if (n.id == name.id) {
                updatedList.remove(n)
                updatedList.add(i, name)
            }
        }
        listForDeleteLiveData.postValue(updatedList)
    }

    fun deleteNames(nameList: MutableList<Name>) {
        viewModelScope.launch(Dispatchers.IO) {
            nameList.filter { it.isChecked }.forEach {
                updateNameUseCase.execute(it)
            }
            nameList.filter { it.isChecked }.forEach {
                deleteNameUseCase.execute(it)
            }
        }
    }
}

