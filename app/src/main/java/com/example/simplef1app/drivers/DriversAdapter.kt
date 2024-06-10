package com.example.simplef1app.drivers

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
import com.example.simplef1app.data.database.entities.DriverEntity
import com.example.simplef1app.databinding.DriversListItemBinding

class DriversAdapter(
    private val navController: NavController
) : ListAdapter<DriverEntity, DriversAdapter.DriversViewHolder>(DriverDiffCallBack()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DriversViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.drivers_list_item,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: DriversViewHolder, position: Int) {
        val driver = getItem(position)
        holder.recyclerviewDriverBinding.driver = driver
        holder.recyclerviewDriverBinding.root.setOnClickListener {
            onRecyclerViewItemClick(
                holder.recyclerviewDriverBinding.root, driver
            )
        }
    }

    private fun onRecyclerViewItemClick(view: View, driver: DriverEntity) {
        val args = Bundle()

        val driverJson = JsonTools.gsonParser?.toJson(driver)
        args.putString(DriverDetailsFragment.DETAIL_JSON_STRING, driverJson)

        navController.navigate( R.id.action_DriversFragment_to_DriverDetailsFragment, args)
    }

    inner class DriversViewHolder(
        val recyclerviewDriverBinding: DriversListItemBinding
    ) : RecyclerView.ViewHolder(recyclerviewDriverBinding.root)

    private class DriverDiffCallBack : DiffUtil.ItemCallback<DriverEntity>() {
        override fun areItemsTheSame(oldItem: DriverEntity, newItem: DriverEntity): Boolean =
            oldItem.driverId == newItem.driverId

        override fun areContentsTheSame(oldItem: DriverEntity, newItem: DriverEntity): Boolean =
            oldItem == newItem
    }

}