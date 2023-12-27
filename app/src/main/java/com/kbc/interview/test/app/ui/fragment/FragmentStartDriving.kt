package com.kbc.interview.test.app.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.kbc.interview.test.app.R
import com.kbc.interview.test.app.databinding.FragmentStartDrivingBinding
import com.kbc.interview.test.app.viewmodel.CarViewModel

class FragmentStartDriving : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val binding = FragmentStartDrivingBinding.inflate(inflater, container, false)

        val carViewModel = ViewModelProvider(requireActivity()).get(CarViewModel::class.java)
        binding.viewModel = carViewModel
        binding.lifecycleOwner = viewLifecycleOwner
        binding.buttonDrive.setOnClickListener {
            findNavController().navigate(R.id.action_nav_start_driving_to_nav_traffic_light)
        }

        return binding.root
    }
}