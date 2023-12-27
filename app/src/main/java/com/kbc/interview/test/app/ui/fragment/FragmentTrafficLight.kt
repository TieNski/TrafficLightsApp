package com.kbc.interview.test.app.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.kbc.interview.test.app.databinding.FragmentTrafficLightBinding
import com.kbc.interview.test.app.viewmodel.CarViewModel

class FragmentTrafficLight : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val binding = FragmentTrafficLightBinding.inflate(inflater, container, false)

        val carViewModel = ViewModelProvider(requireActivity()).get(CarViewModel::class.java)
        binding.viewModel = carViewModel
        binding.lifecycleOwner = viewLifecycleOwner
        binding.trafficLight.startTrafficLights()

        return binding.root
    }
}