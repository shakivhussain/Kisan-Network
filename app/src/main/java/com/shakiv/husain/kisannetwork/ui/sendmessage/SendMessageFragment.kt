package com.shakiv.husain.kisannetwork.ui.sendmessage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.shakiv.husain.kisannetwork.KisanApplication
import com.shakiv.husain.kisannetwork.R
import com.shakiv.husain.kisannetwork.data.model.Contact
import com.shakiv.husain.kisannetwork.data.network.Resource
import com.shakiv.husain.kisannetwork.databinding.FragmentSendMessageBinding
import com.shakiv.husain.kisannetwork.ui.home.ContactAdapter
import com.shakiv.husain.kisannetwork.ui.home.ContactListFragment.Companion.KEY_CONTACT
import com.shakiv.husain.kisannetwork.ui.home.ContactViewModel
import com.shakiv.husain.kisannetwork.ui.viewmodel.AuthViewModel
import com.shakiv.husain.kisannetwork.ui.viewmodel.ViewModelFactory
import com.shakiv.husain.kisannetwork.utils.toast
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.util.Random
import javax.inject.Inject

class SendMessageFragment : Fragment() {

    private lateinit var binding: FragmentSendMessageBinding
    private var rootView: ConstraintLayout? = null

    @Inject lateinit var viewModelFactory: ViewModelFactory
    lateinit var authViewModel: AuthViewModel

    var contact: Contact? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
        contact = arguments?.getSerializable(KEY_CONTACT) as? Contact
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        if (rootView == null) {
            binding = FragmentSendMessageBinding.inflate(inflater, container, false)
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
    }
    private fun bindListener() {


        binding.sendMessageButton.setOnClickListener {
            sendMessage()
        }

        binding.apply {
            layoutToolbar.buttonBack.setOnClickListener {
                findNavController().navigateUp()
            }
        }
    }

    private fun sendMessage() {
        val otpText = binding.tvOtpMessage.text.toString()
        val contactNumber = contact?.number ?: ""
        lifecycleScope.launch {
            authViewModel.sendSms(contactNumber, otpText).collectLatest {

                val isLoading =  it is Resource.Loading
                binding.progressBar.isVisible = isLoading

                when (it) {
                    is Resource.Success -> {
                        toast(activity ?: return@collectLatest, "Otp successfully sent.")
                        binding.sendMessageButton.isEnabled = true

                    }
                    is Resource.Loading -> {
                        binding.sendMessageButton.isEnabled = false

                    }

                    is Resource.Failure -> {
                        binding.sendMessageButton.isEnabled = true
                        toast(activity ?: return@collectLatest, "Something went wrong.")
                    }
                }
            }
        }
    }

    private fun bindViews() {

        binding.apply {
            layoutToolbar.buttonBack.isVisible = true
            layoutToolbar.tvTitle.text = resources.getString(R.string.send_message)

            val random = Random(System.currentTimeMillis())
            val randomOtp = random.nextInt(1000000)
            val otpMessage = "Hi,Your OTP is : $randomOtp."
            tvOtpMessage.text = otpMessage

        }

    }

    private fun init() {
        (this.activity?.application as KisanApplication).appComponent.inject(this)
        authViewModel = ViewModelProvider(this, viewModelFactory)[AuthViewModel::class.java]
    }
}