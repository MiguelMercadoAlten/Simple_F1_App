package com.example.simplef1app.circuits

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.simplef1app.R
import com.example.simplef1app.api.JsonTools
import com.example.simplef1app.data.database.entities.CircuitEntity
import com.example.simplef1app.databinding.CircuitsListItemBinding

class CircuitsAdapter(
    private val navController: NavController
) : ListAdapter<CircuitEntity, CircuitsAdapter.CircuitsViewHolder>(CircuitDiffCallBack()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        CircuitsViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.circuits_list_item,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: CircuitsViewHolder, position: Int) {
        val circuit = getItem(position)
        holder.recyclerviewCircuitBinding.circuit = circuit
        holder.recyclerviewCircuitBinding.root.setOnClickListener {
            onRecyclerViewItemClick(
                holder.recyclerviewCircuitBinding.root, circuit
            )
        }
    }

    private fun onRecyclerViewItemClick(view: View, circuit: CircuitEntity) {
        val args = Bundle()

        val circuitJson = JsonTools.gsonParser?.toJson(circuit)
        args.putString(CircuitDetailsFragment.DETAIL_JSON_STRING, circuitJson)

        navController.navigate( R.id.action_CircuitsFragment_to_CircuitDetailsFragment, args)
    }

    inner class CircuitsViewHolder(
        val recyclerviewCircuitBinding: CircuitsListItemBinding
    ) : RecyclerView.ViewHolder(recyclerviewCircuitBinding.root)

    private class CircuitDiffCallBack : DiffUtil.ItemCallback<CircuitEntity>() {
        override fun areItemsTheSame(oldItem: CircuitEntity, newItem: CircuitEntity): Boolean =
            oldItem.circuitId == newItem.circuitId

        override fun areContentsTheSame(oldItem: CircuitEntity, newItem: CircuitEntity): Boolean =
            oldItem == newItem
    }
}