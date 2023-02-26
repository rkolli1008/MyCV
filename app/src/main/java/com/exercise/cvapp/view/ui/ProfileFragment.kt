package com.exercise.cvapp.view.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.exercise.cvapp.databinding.FragmentProfileBinding
import com.exercise.cvapp.view.adapter.MultiViewTypeAdapter
import com.exercise.cvapp.view.viewmodel.ProfileViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    private val viewModel: ProfileViewModel by viewModels()
    /**
     * RecyclerView Adapter for converting a list of Video to cards.
     */
    private var viewModelAdapter: MultiViewTypeAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.profileData.observe(viewLifecycleOwner, Observer {
            if (null != it) {
                viewModelAdapter?.profile = it
            }
        })
    }

    /**
     * Inflates the layout with Data Binding, sets its lifecycle owner to the OverviewFragment
     * to enable Data Binding to observe LiveData, and sets up the RecyclerView with an adapter.
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = FragmentProfileBinding.inflate(inflater)

        // Giving the binding access to the OverviewViewModel
        binding.viewModel = viewModel

        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.lifecycleOwner = this

        viewModelAdapter = MultiViewTypeAdapter()

        binding.profileRecyclerView.adapter = viewModelAdapter

        return binding.root
    }
}
