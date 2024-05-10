package adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.lab4.createFragment.HomeFragmentDirections
import com.example.lab4.databinding.SheduleLayoutBinding
import model.Shedule

class SheduleAdapter : RecyclerView.Adapter<SheduleAdapter.SheduleViewHolder>() {
    class SheduleViewHolder(val itemBinding: SheduleLayoutBinding): RecyclerView.ViewHolder(itemBinding.root)

    private val differCallback = object  : DiffUtil.ItemCallback<Shedule>(){
        override fun areItemsTheSame(oldItem: Shedule, newItem: Shedule): Boolean {
            return oldItem.id == newItem.id &&
                    oldItem.sheduleDesc == newItem.sheduleDesc &&
                    oldItem.sheduleTitle == newItem.sheduleTitle
        }

        override fun areContentsTheSame(oldItem: Shedule, newItem: Shedule): Boolean {
            return oldItem == newItem
        }
    }
    val differ = AsyncListDiffer(this, differCallback)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SheduleViewHolder {
        return SheduleViewHolder(
            SheduleLayoutBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        )
    }

    override fun getItemCount(): Int {
       return differ.currentList.size
    }

    override fun onBindViewHolder(holder: SheduleViewHolder, position: Int) {
        val currentShedule = differ.currentList[position]

        holder.itemBinding.sheduleTitle.text = currentShedule.sheduleTitle
        holder.itemBinding.sheduleDesc.text = currentShedule.sheduleDesc

        holder.itemView.setOnClickListener{
            val direction = HomeFragmentDirections.actionHomeFragmentToEditSheduleFragment(currentShedule)
            it.findNavController().navigate(direction)
        }
    }
}