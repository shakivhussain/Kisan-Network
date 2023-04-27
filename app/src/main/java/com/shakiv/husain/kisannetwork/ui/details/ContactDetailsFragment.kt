package com.shakiv.husain.kisannetwork.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.shakiv.husain.kisannetwork.KisanApplication
import com.shakiv.husain.kisannetwork.R
import com.shakiv.husain.kisannetwork.data.model.Contact
import com.shakiv.husain.kisannetwork.databinding.FragmentContactDetailsBinding
import com.shakiv.husain.kisannetwork.ui.home.ContactListFragment.Companion.KEY_CONTACT
import com.shakiv.husain.kisannetwork.ui.home.ContactViewModel
import com.shakiv.husain.kisannetwork.ui.viewmodel.ViewModelFactory
import com.shakiv.husain.kisannetwork.utils.navigateToDestination
import com.shakiv.husain.kisannetwork.utils.toast
import javax.inject.Inject

class ContactDetailsFragment : Fragment() {

    private lateinit var binding: FragmentContactDetailsBinding
    private var rootView: ConstraintLayout? = null
    var contact: Contact? = null

    @Inject lateinit var viewModelFactory: ViewModelFactory
    lateinit var contactViewModel: ContactViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
        contact = arguments?.getSerializable(KEY_CONTACT) as? Contact
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        if (rootView == null) {
            binding = FragmentContactDetailsBinding.inflate(inflater, container, false)
            rootView = binding.root
        }
        return rootView as ConstraintLayout
    }


    override fun onDestroy() {
        super.onDestroy()
        arguments?.clear()
        contact = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindViews()
        bindListener()
        bindObserver()
    }

    private fun bindObserver() {
        val updatedContact = contact?.also { contact ->
            contact.openedAt = System.currentTimeMillis()
        }
        contactViewModel.addContact(contact = updatedContact ?: return)
    }

    private fun bindListener() {
        binding.apply {
            llTextContainer.setOnClickListener {
                val bundle = bundleOf(
                    KEY_CONTACT to contact
                )
                navigateToDestination(
                    bundle, R.id.action_contactDetailsFragment_to_sendMessageFragment
                )
            }
            tvPhoneNumber.setOnClickListener {
                toast(activity ?: return@setOnClickListener, "${contact?.number}")
            }
            layoutToolbar.buttonBack.setOnClickListener {
                findNavController().navigateUp()
            }
        }
    }

    private fun bindViews() {

        with(binding) {
            layoutToolbar.tvTitle.text = resources.getString(R.string.contact_detail)
            layoutToolbar.buttonBack.isVisible = true

            contact?.let { contact ->
                tvProfile.text = contact.firstName
                tvPhoneNumber.text = contact.number
                val fullName = "${contact.firstName} ${contact.lastName}"
                tvName.text = fullName
            }
        }
    }

    companion object {
        fun open(
            navController: NavController,
            contact: Contact,
        ) {
            navigate(navController, getArgs(contact))
        }

        private fun getArgs(contact: Contact) = Bundle().apply {
            putSerializable(KEY_CONTACT, contact)
        }

        private fun navigate(controller: NavController, bundle: Bundle) {
            controller.navigate(R.id.action_global_detail_contact, bundle)
        }
    }

    private fun init() {
        (this.activity?.application as KisanApplication).appComponent.inject(this)
        contactViewModel = ViewModelProvider(this, viewModelFactory)[ContactViewModel::class.java]
    }
}