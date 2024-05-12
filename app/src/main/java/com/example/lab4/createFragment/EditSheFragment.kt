package com.example.lab4.createFragment

import android.app.AlertDialog
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
import androidx.navigation.fragment.navArgs
import com.example.lab4.MainActivity
import com.example.lab4.R
import com.example.lab4.databinding.FragmentEditSheduleBinding
import com.example.lab4.model.She
import com.example.lab4.viewModel.SheViewModel
class EditSheFragment : Fragment(R.layout.fragment_edit_shedule), MenuProvider {

    private var editSheBinding: FragmentEditSheduleBinding? = null
    private val binding get() = editSheBinding!!
    private lateinit var shesViewModel: SheViewModel
    private lateinit var currentShe: She
    private val args: EditSheFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        editSheBinding = FragmentEditSheduleBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)

        shesViewModel = (activity as MainActivity).sheViewModel
        currentShe = args.she!!

        binding.editSheTitle.setText(currentShe.sheTitle)
        binding.editSheDesc.setText(currentShe.sheDesc)

        binding.editSheFab.setOnClickListener{
            val sheTitle = binding.editSheTitle.text.toString().trim()
            val sheDesc = binding.editSheDesc.text.toString().trim()

            if(sheTitle.isNotEmpty()){
                val she = She(currentShe.id, sheTitle, sheDesc)
                shesViewModel.updateShe(she)
                view.findNavController().popBackStack(R.id.homeFragment, false)
            } else {
                Toast.makeText(context, " Please enter note title", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun deleteShe(){
        AlertDialog.Builder(activity).apply {
            setTitle("Delete Note")
            setMessage("Do you want to delete this note?")
            setPositiveButton("Delete"){_,_ ->
                shesViewModel.deleteShe(currentShe)
                Toast.makeText(context, " Note Deleted", Toast.LENGTH_SHORT).show()
                view?.findNavController()?.popBackStack(R.id.homeFragment, false)
            }
            setNegativeButton("Cancel", null)
        }.create().show()
    }
    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menu.clear()
        menuInflater.inflate(R.menu.editshedule, menu)
    }
    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return when(menuItem.itemId){
            R.id.deleteShe -> {
                deleteShe()
                true
            } else -> false
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        editSheBinding = null
    }
}

