package com.shakiv.husain.kisannetwork.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.shakiv.husain.kisannetwork.data.model.Contact
import com.shakiv.husain.kisannetwork.databinding.LayoutContactItemBinding
import com.shakiv.husain.kisannetwork.utils.toFormattedDateTime

class ContactAdapter(
    private val onItemClick: (Contact) -> Unit,
    private val isNeedToShowTime: Boolean
) :
    ListAdapter<Contact, ContactAdapter.ContactViewHolder>(COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val binding =
            LayoutContactItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ContactViewHolder(binding, onItemClick)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        holder.bind(position)
    }

    inner class ContactViewHolder(
        private val binding: LayoutContactItemBinding,
        private val onItemClick: (Contact) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        private var _contact: Contact? = null

        init {
            with(binding) {
                root.setOnClickListener {
                    _contact?.let {
                        onItemClick.invoke(it)
                    }
                }
            }
        }

        fun bind(position: Int) {
            val contact = getItem(position)
            _contact = contact
            binding.apply {
                val fullName = "${contact.firstName} ${contact.lastName}"
                tvTitle.text = fullName
                tvProfile.text = contact.firstName
                tvTime.isVisible=isNeedToShowTime
                if (isNeedToShowTime){
                    tvTime.text = contact.openedAt.toFormattedDateTime()
                }
            }
        }
    }

    companion object COMPARATOR : DiffUtil.ItemCallback<Contact>() {
        override fun areContentsTheSame(oldItem: Contact, newItem: Contact): Boolean {
            return oldItem == newItem
        }

        override fun areItemsTheSame(oldItem: Contact, newItem: Contact): Boolean {
            return oldItem.id == newItem.id || oldItem.number == newItem.number
        }
    }


}