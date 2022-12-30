package com.taranovegor91.divinationsearchbynametest.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.taranovegor91.divinationsearchbynametest.R
import com.taranovegor91.divinationsearchbynametest.databinding.ItemNameBinding
import com.taranovegor91.divinationsearchbynametest.domain.models.Name

class SelectedNameAdapter(
    private val listener: Listener,
) : ListAdapter<Name, SelectedNameAdapter.NoteHolder>(ItemCallback), View.OnClickListener,
    View.OnLongClickListener {
    override fun onClick(v: View) {
        val name = v.tag as Name
        when (v.id) {
            R.id.checkBoxDelete -> {
                v as CheckBox
                name.isChecked = v.isChecked
                listener.onCheckBoxClick(name.copy())
            }
        }
    }

    override fun onLongClick(v: View?): Boolean {
        listener.onRootLongClick()
        return true
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemNameBinding.inflate(inflater, parent, false)
        binding.root.setOnLongClickListener(this)
        binding.checkBoxDelete.setOnClickListener(this)      //назначаю OnClickListener а не OnCheckedChangeListener
        return NoteHolder(binding)
    }

    override fun onBindViewHolder(holder: NoteHolder, position: Int) {
        val name = getItem(position)
        with(holder.binding) {
            tvName.tag = name
            checkBoxDelete.tag = name
            tvName.text = name.name
            if (name.isCheckBoxVisible) {
                checkBoxDelete.visibility = View.VISIBLE
            } else {
                checkBoxDelete.visibility = View.GONE
            }
            checkBoxDelete.isChecked = name.isChecked
        }
    }

    interface Listener {
        fun onCheckBoxClick(name: Name)
        fun onRootLongClick()
    }

    class NoteHolder(
        val binding: ItemNameBinding,
    ) : RecyclerView.ViewHolder(binding.root)

    object ItemCallback : DiffUtil.ItemCallback<Name>() {
        override fun areItemsTheSame(oldItem: Name, newItem: Name): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Name, newItem: Name): Boolean {
            return oldItem == newItem
        }
    }
}
