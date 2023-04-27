package com.shakiv.husain.kisannetwork.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shakiv.husain.kisannetwork.data.model.Contact
import com.shakiv.husain.kisannetwork.data.network.Resource
import com.shakiv.husain.kisannetwork.data.repository.ContactRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

class ContactViewModel @Inject constructor(
    private val contactRepository: ContactRepository
) : ViewModel() {
    fun getContacts(): Flow<Resource<List<Contact>>> {
        return contactRepository.getContacts()
    }
    fun getAllLocalContacts() = contactRepository.getAllContactLocalContacts()
    fun addContact(contact: Contact) {
        viewModelScope.launch {
            contactRepository.addContact(contact = contact)
        }
    }

}

