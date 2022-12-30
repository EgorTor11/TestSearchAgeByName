package com.taranovegor91.divinationsearchbynametest.presentation

import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.TextView.OnEditorActionListener
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.taranovegor91.divinationsearchbynametest.R
import com.taranovegor91.divinationsearchbynametest.databinding.FragmentSearchByNameBinding
import com.taranovegor91.divinationsearchbynametest.presentation.viewModels.SearchByNameViewModel
import com.taranovegor91.divinationsearchbynametest.presentation.viewModels.SearchByNameViewModelFactory
import com.taranovegor91.notesdemoapplication.utils.shareText

class SearсhByNameFragment : Fragment(R.layout.fragment_search_by_name) {
    lateinit var vm: SearchByNameViewModel
    lateinit var binding: FragmentSearchByNameBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSearchByNameBinding.bind(view)
        vm = ViewModelProvider(this, SearchByNameViewModelFactory(requireActivity())).get(
            SearchByNameViewModel::class.java)
        vm.divinationLiveData.observe(viewLifecycleOwner) {
            when (it) {
                "null" -> {
                    Toast.makeText(requireContext(),
                        getString(R.string.not_name_toast),
                        Toast.LENGTH_LONG).show()
                    binding.llHint.visibility = View.VISIBLE
                    binding.llDivination.visibility = View.GONE
                    binding.llAddShareName.visibility = View.GONE
                }
                "" -> {
                    Toast.makeText(requireContext(),
                        getString(R.string.error_toast),
                        Toast.LENGTH_LONG).show()
                    binding.llHint.visibility = View.VISIBLE
                    binding.llDivination.visibility = View.GONE
                    binding.llAddShareName.visibility = View.GONE
                }
                else -> {
                    binding.tvDivination.text = it
                    binding.llHint.visibility = View.GONE
                    binding.llDivination.visibility = View.VISIBLE
                    binding.llAddShareName.visibility = View.VISIBLE
                }
            }
        }
        with(binding) {
            edSearch.setOnEditorActionListener(OnEditorActionListener { v, actionId, event ->
                if (event != null && event.keyCode == KeyEvent.KEYCODE_ENTER
                    || actionId == EditorInfo.IME_ACTION_SEARCH
                ) {
                    vm.getDivination(binding.edSearch.text.toString())
                    closeKeyboard()
                }
                false
            })

            imAddName.setOnClickListener {
                vm.insertName(edSearch.text.toString())
            }
            llSelectedNavigate.setOnClickListener {
                findNavController().navigate(R.id.action_detailseNoteFragment_to_rootFragment)
            }
            imShareName.setOnClickListener {
                context?.shareText(edSearch.text.toString())
            }
        }
    }

    fun closeKeyboard() {
        val imm =
            requireActivity().applicationContext.getSystemService(AppCompatActivity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.edSearch.getWindowToken(), 0)
    }
}
