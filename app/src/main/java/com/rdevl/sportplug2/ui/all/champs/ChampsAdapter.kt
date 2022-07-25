package com.rdevl.sportplug2.ui.all.champs

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rdevl.sportplug2.data.entries.Data
import com.rdevl.sportplug2.databinding.ItemChampsBinding

class ChampsAdapter(private val onClick: (Data) -> Unit):
    ListAdapter<Data, ListViewHolder>(ListViewHolder.CategoryDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ListViewHolder(
        ItemChampsBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        ), onClick
    )

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) =
        holder.bind(getItem(position))
}

class ListViewHolder(
    private val itemBinding: ItemChampsBinding,
    onClick: (Data) -> Unit
):
    RecyclerView.ViewHolder(itemBinding.root) {

    private lateinit var currentItem: Data

    init {
        itemView.setOnClickListener {
            currentItem.let(onClick)
        }
    }

    fun bind(item: Data) {
        currentItem = item
        itemBinding.name.text = item.n
    }

    object CategoryDiffCallback : DiffUtil.ItemCallback<Data>() {
        override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem.sort == newItem.sort
        }
    }

}