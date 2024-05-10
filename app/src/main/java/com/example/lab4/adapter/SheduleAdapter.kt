package com.example.lab4.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.lab4.createFragment.HomeFragmentDirections
import com.example.lab4.databinding.SheduleLayoutBinding
import com.example.lab4.model.She

class SheAdapter : RecyclerView.Adapter<SheAdapter.SheViewHolder>() {
    class SheViewHolder(val itemBinding: SheduleLayoutBinding): RecyclerView.ViewHolder(itemBinding.root)

    private val differCallback = object  : DiffUtil.ItemCallback<She>(){
        override fun areItemsTheSame(oldItem: She, newItem: She): Boolean {
            return oldItem.id == newItem.id &&
                    oldItem.sheDesc == newItem.sheDesc &&
                    oldItem.sheTitle == newItem.sheTitle
        }

        override fun areContentsTheSame(oldItem: She, newItem: She): Boolean {
            return oldItem == newItem
        }
    }
    val differ = AsyncListDiffer(this, differCallback)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SheViewHolder {
        return SheViewHolder(
            SheduleLayoutBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        )
    }

    override fun getItemCount(): Int {
       return differ.currentList.size
    }

    override fun onBindViewHolder(holder: SheViewHolder, position: Int) {
        val currentShe = differ.currentList[position]

        holder.itemBinding.sheTitle.text = currentShe.sheTitle
        holder.itemBinding.sheDesc.text = currentShe.sheDesc

        holder.itemView.setOnClickListener{
            val direction = HomeFragmentDirections.actionHomeFragmentToEditSheFragment(currentShe)
            it.findNavController().navigate(direction)
        }
    }
}