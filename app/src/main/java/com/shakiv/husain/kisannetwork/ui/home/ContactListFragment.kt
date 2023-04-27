package com.shakiv.husain.kisannetwork.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.shakiv.husain.kisannetwork.KisanApplication
import com.shakiv.husain.kisannetwork.R
import com.shakiv.husain.kisannetwork.data.model.Contact
import com.shakiv.husain.kisannetwork.data.network.Resource
import com.shakiv.husain.kisannetwork.databinding.FragmentContactListBinding
import com.shakiv.husain.kisannetwork.ui.viewmodel.AuthViewModel
import com.shakiv.husain.kisannetwork.ui.viewmodel.ViewModelFactory
import com.shakiv.husain.kisannetwork.utils.navigateToDestination
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class ContactListFragment : Fragment() {

    private lateinit var binding: FragmentContactListBinding
    private var rootView: ConstraintLayout? = null

    @Inject lateinit var viewModelFactory: ViewModelFactory
    lateinit var authViewModel: AuthViewModel
    lateinit var contactViewModel: ContactViewModel
    lateinit var contactAdapter: ContactAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        if (rootView == null) {
            binding = FragmentContactListBinding.inflate(inflater, container, false)
            rootView = binding.root
        }
        return rootView as ConstraintLayout
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindObserver()
        bindViews()

    }

    private fun bindViews() {
        binding.contactRecyclerView.apply {
            adapter = contactAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            setHasFixedSize(true)
        }
    }

    private fun bindObserver() {


        lifecycleScope.launch {
            contactViewModel.getContacts().collectLatest {
                when (it) {
                    is Resource.Success -> {
                        contactAdapter.submitList(it.data)
                    }

                    is Resource.Loading -> {
                    }

                    is Resource.Failure -> {
                    }
                }
            }
        }
    }

    private fun onItemClick(contact: Contact) {

        val bundle = bundleOf(
            KEY_CONTACT to contact
        )
        navigateToDestination(bundle, R.id.action_contactListFragment_to_contactDetailsFragment)
    }


    companion object {
        const val KEY_CONTACT = "key_contact"
    }

    private fun init() {
        (this.activity?.application as KisanApplication).appComponent.inject(this)
        authViewModel = ViewModelProvider(this, viewModelFactory)[AuthViewModel::class.java]
        contactViewModel = ViewModelProvider(this, viewModelFactory)[ContactViewModel::class.java]
        contactAdapter = ContactAdapter(::onItemClick)
    }

}