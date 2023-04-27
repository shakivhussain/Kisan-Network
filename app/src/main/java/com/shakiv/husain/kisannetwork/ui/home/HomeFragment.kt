package com.shakiv.husain.kisannetwork.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.tabs.TabLayoutMediator
import com.shakiv.husain.kisannetwork.R
import com.shakiv.husain.kisannetwork.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private var rootView: ConstraintLayout? = null

    private lateinit var contactPagerAdapter: ContactPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        contactPagerAdapter = ContactPagerAdapter(childFragmentManager, lifecycle, getFragmentList())

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        if (rootView == null) {
            binding = FragmentHomeBinding.inflate(inflater, container, false)
            rootView = binding.root
        }
        return rootView as ConstraintLayout
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindViews()
        bindListener()
    }

    private fun bindListener() {

        binding.apply {
            layoutToolbar.buttonBack.setOnClickListener {
                findNavController().navigateUp()
            }
        }
    }


    private fun getFragmentList(): List<Fragment> {
        return listOf<Fragment>(
            ContactListFragment.newInstance(true), ContactListFragment.newInstance(false)
        )
    }

    private fun bindViews() {
        binding.viewPager.adapter = contactPagerAdapter
        binding.viewPager.isSaveEnabled = false;
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = resources.getString(R.string.contacts)
                }
                1 -> {
                    tab.text = resources.getString(R.string.recent_contacts)
                }
            }
        }.attach()
    }


}