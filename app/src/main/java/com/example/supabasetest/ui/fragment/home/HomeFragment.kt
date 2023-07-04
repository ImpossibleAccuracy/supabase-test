package com.example.supabasetest.ui.fragment.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.supabasetest.data.model.Rider
import com.example.supabasetest.databinding.FragmentHomeBinding
import com.example.supabasetest.ui.adapter.RidersAdapter
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!

    private val homeViewModel: HomeViewModel by viewModels()

    private lateinit var ridersAdapter: RidersAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ridersAdapter = RidersAdapter(requireContext()) { rider, positon, view ->
            val navController = findNavController()

            val direction = HomeFragmentDirections
                .actionNavigationHomeToCreateTripFragment(rider)

            val navExtras = FragmentNavigatorExtras(
                view to view.transitionName
            )

            navController.navigate(direction, navExtras)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        val findNavController = findNavController()
        binding.toolbar.title = findNavController.currentDestination?.label

        lifecycleScope.launch {
            homeViewModel.uiState
                .flowWithLifecycle(viewLifecycleOwner.lifecycle)
                .collect { state ->
                    displayRiders(state.riders)

                    if (state.hasNetworkError) {
                        Snackbar.make(
                            binding.root,
                            "Error while loading riders",
                            Snackbar.LENGTH_INDEFINITE
                        ).show()
                    }
                }
        }

        binding.riders.let {
            it.adapter = ridersAdapter
            it.layoutManager = LinearLayoutManager(requireContext())

            it.addItemDecoration(DividerItemDecoration(requireContext(), RecyclerView.VERTICAL))
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun displayRiders(riders: List<Rider>) {
        ridersAdapter.submitList(riders)
    }
}