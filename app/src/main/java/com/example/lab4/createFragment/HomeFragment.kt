package com.example.lab4.createFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import androidx.navigation.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.lab4.MainActivity
import com.example.lab4.R
import com.example.lab4.adapter.SheAdapter
import com.example.lab4.databinding.FragmentHomeBinding
import com.example.lab4.model.She
import com.example.lab4.viewModel.SheViewModel


class HomeFragment : Fragment(R.layout.fragment_home),SearchView.OnQueryTextListener,MenuProvider {

    private var homeBinding: FragmentHomeBinding? = null
    private val binding get() = homeBinding!!

    private lateinit var shesViewModel: SheViewModel
    private lateinit var sheAdapter: SheAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        homeBinding = FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)
        shesViewModel = (activity as MainActivity).sheViewModel
        setupHomeRecyclerView()

        binding.addSheduleFab.setOnClickListener{
            it.findNavController().navigate(R.id.action_homeFragment_to_addSheFragment)
        }
    }
    private fun updateUI(she: List<She>?){
        if(she != null){
            if(she.isNotEmpty()){
                binding.emptySheImage
                binding.homeRecyclerView.visibility = View.VISIBLE
            }
            else{
                binding.emptySheImage.visibility = View.VISIBLE
                binding.homeRecyclerView.visibility = View.GONE
            }
        }
    }

    private fun setupHomeRecyclerView() {
        sheAdapter = SheAdapter()
        binding.homeRecyclerView.apply {
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            setHasFixedSize(true)
            adapter = sheAdapter
        }

        activity?.let {
            shesViewModel.getAllShes().observe(viewLifecycleOwner) { she ->
                sheAdapter.differ.submitList(she)
                updateUI(she)
            }
        }
    }

    private fun searchShe(query: String?){
        val searchQuery = if (query.isNullOrEmpty()) {
            "%" // Return all results if query is empty or null
        } else {
            "%$query%" // Include wildcard character for partial matches
        }

        shesViewModel.searchShe(searchQuery).observe(this) {list ->
            sheAdapter.differ.submitList(list)
        }
    }

    override fun onQueryTextSubmit(p0: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        if (newText != null){
            searchShe(newText)
        }
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        homeBinding = null
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menu.clear()
        menuInflater.inflate(R.menu.day, menu)

        val menuSearch = menu.findItem(R.id.searchMenu).actionView as SearchView
        menuSearch.isSubmitButtonEnabled = false
        menuSearch.setOnQueryTextListener(this)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return false
    }

}


