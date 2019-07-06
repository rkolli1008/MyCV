package com.exercise.cvapp.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.exercise.cvapp.databinding.FragmentProfileBinding
import com.exercise.cvapp.viewmodel.ProfileViewModel

class ProfileFragment : Fragment() {

    /**
     * Lazily initialize our [ProfileFragment].
     */
    private val viewModel: ProfileViewModel by lazy {
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }
        ViewModelProviders.of(this, ProfileViewModel.Factory(activity.application))
            .get(ProfileViewModel::class.java)
    }

    /**
     * RecyclerView Adapter for converting a list of Video to cards.
     */
    private var viewModelAdapter: MultiViewTypeAdapter? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.profile.observe(viewLifecycleOwner, Observer {
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
