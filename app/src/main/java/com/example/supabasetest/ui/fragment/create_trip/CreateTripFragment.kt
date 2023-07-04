package com.example.supabasetest.ui.fragment.create_trip

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.supabasetest.R
import com.example.supabasetest.databinding.FragmentCreateTripBinding
import com.google.android.material.transition.MaterialContainerTransform
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CreateTripFragment : Fragment() {
    private val args by navArgs<CreateTripFragmentArgs>()

    private val viewModel: CreateTripViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        MaterialContainerTransform().apply {
            drawingViewId = R.id.nav_host_fragment_activity_main

            scrimColor = Color.TRANSPARENT

            sharedElementEnterTransition = this
            sharedElementReturnTransition = this
        }

        postponeEnterTransition()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentCreateTripBinding.inflate(inflater, container, false)

        lifecycleScope.launch {
            viewModel.creationState
                .flowWithLifecycle(viewLifecycleOwner.lifecycle)
                .filterNotNull()
                .collect {
                    val navController = findNavController()
                    navController.navigateUp()

                    viewModel.handleCreationResult()
                }
        }

        val rider = args.rider
        ViewCompat.setTransitionName(binding.root, "rider_${rider.id}")

        binding.apply.setOnClickListener {
            val customerName = binding.customerName.text.toString()
            val customerSurname = binding.customerSurname.text.toString()

            val origin = binding.origin.text.toString()
            val destination = binding.origin.text.toString()

            viewModel.createTripByCustomerDataAndPoints(
                args.rider,
                customerName,
                customerSurname,
                origin,
                destination
            )
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.doOnPreDraw {
            startPostponedEnterTransition()
        }
    }
}