package com.example.simplef1app.constructors

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
import com.example.simplef1app.data.database.entities.ConstructorEntity
import com.example.simplef1app.databinding.ConstructorsListItemBinding

class ConstructorsAdapter(
    private val navController: NavController
) : ListAdapter<ConstructorEntity, ConstructorsAdapter.ConstructorsViewHolder>(ConstructorDiffCallBack()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ConstructorsViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.constructors_list_item,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ConstructorsViewHolder, position: Int) {
        val constructor = getItem(position)
        holder.recyclerviewConstructorBinding.constructor = constructor
        holder.recyclerviewConstructorBinding.root.setOnClickListener {
            onRecyclerViewItemClick(
                holder.recyclerviewConstructorBinding.root, constructor
            )
        }
    }

    private fun onRecyclerViewItemClick(view: View, constructor: ConstructorEntity) {
        val args = Bundle()

        val constructorJson = JsonTools.gsonParser?.toJson(constructor)
        args.putString(ConstructorDetailsFragment.DETAIL_JSON_STRING, constructorJson)

        navController.navigate( R.id.action_ConstructorsFragment_to_ConstructorDetailsFragment, args)
    }

    inner class ConstructorsViewHolder(
        val recyclerviewConstructorBinding: ConstructorsListItemBinding
    ) : RecyclerView.ViewHolder(recyclerviewConstructorBinding.root)

    private class ConstructorDiffCallBack : DiffUtil.ItemCallback<ConstructorEntity>() {
        override fun areItemsTheSame(oldItem: ConstructorEntity, newItem: ConstructorEntity): Boolean =
            oldItem.constructorId == newItem.constructorId

        override fun areContentsTheSame(oldItem: ConstructorEntity, newItem: ConstructorEntity): Boolean =
            oldItem == newItem
    }
}