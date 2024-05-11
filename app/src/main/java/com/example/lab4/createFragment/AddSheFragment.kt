package com.example.lab4.createFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import androidx.navigation.findNavController
import com.example.lab4.MainActivity
import com.example.lab4.R
import com.example.lab4.databinding.FragmentAddSheduleBinding
import com.example.lab4.model.She
import com.example.lab4.viewModel.SheViewModel

class AddSheFragment : Fragment(R.layout.fragment_add_shedule), MenuProvider {

    private var addSheBinding: FragmentAddSheduleBinding? = null
    private val binding get()= addSheBinding!!

    private lateinit var shesViewModel: SheViewModel
    private lateinit var addSheView: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        addSheBinding = FragmentAddSheduleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)

        shesViewModel = (activity as MainActivity).sheViewModel
        addSheView = view
    }

    private fun saveShe(view: View){
        val sheTitle = binding.addSheTitle.text.toString().trim()
        val sheDesc = binding.addSheDesc.text.toString().trim()

        if (sheTitle.isNotEmpty()){
            val she = She(0, sheTitle, sheDesc)
            shesViewModel.add(she)

            Toast.makeText(addSheView.context, "Note Saved", Toast.LENGTH_SHORT).show()
            view.findNavController().popBackStack(R.id.homeFragment, false)
        } else {
            Toast.makeText(addSheView.context, "Please enter note title", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menu.clear()
        menuInflater.inflate(R.menu.addshedule, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return when(menuItem.itemId){
            R.id.saveShe -> {
                saveShe(addSheView)
                true
            }
            else -> false
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        addSheBinding = null
    }
}