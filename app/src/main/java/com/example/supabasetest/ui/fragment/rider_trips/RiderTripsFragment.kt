package com.example.supabasetest.ui.fragment.rider_trips

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.supabasetest.data.model.Resource
import com.example.supabasetest.databinding.FragmentRiderTripsBinding
import com.example.supabasetest.ui.fragment.rider_trips.adapter.TripAdapter
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RiderTripsFragment : Fragment() {
    private val args by navArgs<RiderTripsFragmentArgs>()

    private var _binding: FragmentRiderTripsBinding? = null

    private val binding get() = _binding!!

    private val viewModel: RiderTripsViewModel by viewModels()

    private lateinit var tripAdapter: TripAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        tripAdapter = TripAdapter(requireContext()) { trip, position, view ->
            val rider = args.rider

            if (trip.status?.title == "not_accepted") {
                MaterialAlertDialogBuilder(requireContext())
                    .setTitle("Accept order?")
                    .setPositiveButton("OK") { _, _ ->
                        viewModel.acceptTrip(trip)
                    }
                    .create()
                    .show()
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRiderTripsBinding.inflate(inflater, container, false)

        val rider = args.rider
        viewModel.loadTrips(rider)

        lifecycleScope.launch {
            viewModel.trips
                .flowWithLifecycle(viewLifecycleOwner.lifecycle)
                .map { if (it is Resource.Success) it.data else null }
                .filterNotNull()
                .collect {
                    tripAdapter.submitList(it)
                }
        }

        binding.trips.let {
            it.adapter = tripAdapter
            it.layoutManager = LinearLayoutManager(requireContext())

            it.addItemDecoration(DividerItemDecoration(requireContext(), RecyclerView.VERTICAL))
        }

        return binding.root
    }
}