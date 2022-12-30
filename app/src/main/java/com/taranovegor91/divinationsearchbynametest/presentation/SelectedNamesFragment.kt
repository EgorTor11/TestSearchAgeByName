package com.taranovegor91.divinationsearchbynametest.presentation

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.taranovegor91.divinationsearchbynametest.databinding.AlertDialogItemBinding
import com.taranovegor91.divinationsearchbynametest.databinding.FragmentSelectedNamesBinding
import com.taranovegor91.divinationsearchbynametest.domain.models.Name
import com.taranovegor91.divinationsearchbynametest.presentation.viewModels.SelectedNameViewModelFactory
import com.taranovegor91.divinationsearchbynametest.presentation.viewModels.SelectedNamesViewModel
import com.taranovegor91.divinationsearchbynametest.utils.NotificationDialog


class SelectedNamesFragment :
    Fragment(com.taranovegor91.divinationsearchbynametest.R.layout.fragment_selected_names) {
    private lateinit var binding: FragmentSelectedNamesBinding
    lateinit var vm: SelectedNamesViewModel

    companion object {
        val KEY = "note"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSelectedNamesBinding.bind(view)
        vm = ViewModelProvider(this, SelectedNameViewModelFactory(requireActivity())).get(
            SelectedNamesViewModel::class.java)
        var currentNameList = mutableListOf<Name>()
        val adapter = SelectedNameAdapter(object : SelectedNameAdapter.Listener {
            override fun onCheckBoxClick(name: Name) {
                val nl = mutableListOf<Name>()
                currentNameList.forEach {
                    nl.add(it.copy())
                }
                vm.updateListName(name.copy(), nl)
            }

            override fun onRootLongClick() {
                val nl = mutableListOf<Name>()
                currentNameList.forEach {
                    nl.add(it.copy())
                }
                vm.showCheckBoxes(nl)
            }
        })
        (binding.rcNote.itemAnimator as? DefaultItemAnimator)?.supportsChangeAnimations = false
        binding.rcNote.layoutManager = LinearLayoutManager(requireContext())
        binding.rcNote.adapter = adapter

        vm.selectAllNamesLiveData.observe(requireActivity()) { list ->
            currentNameList = list
            adapter.submitList(list)
        }

        vm.listForDeleteLiveData.observe(requireActivity()) {
            currentNameList = it
            adapter.submitList(it)
            if (currentNameList.filter { n -> n.isChecked }.isNotEmpty()) {
                binding.llDelete.visibility = View.VISIBLE
            } else {
                binding.llDelete.visibility = View.GONE
            }
        }
        with(binding) {
            imDelete.setOnClickListener {
                val notificationDialog = NotificationDialog(requireContext())
                notificationDialog.showDialog(NotificationDialog.DialogCallback {
                    vm.deleteNames(currentNameList)
                    binding.llDelete.visibility = View.GONE
                })
            }
            llHomeNavigate.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }
}
