package com.rdevl.sportplug2.ui.all.matches

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rdevl.sportplug2.data.entries.Data
import com.rdevl.sportplug2.data.entries.Event
import com.rdevl.sportplug2.databinding.ItemChampsBinding

class MatchesAdapter(private val onClick: (Event) -> Unit):
    ListAdapter<Event, ListViewHolder>(ListViewHolder.CategoryDiffCallback) {

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
    onClick: (Event) -> Unit
):
    RecyclerView.ViewHolder(itemBinding.root) {

    private lateinit var currentItem: Event

    init {
        itemView.setOnClickListener {
            currentItem.let(onClick)
        }
    }

    fun bind(item: Event) {
        currentItem = item
        itemBinding.name.text = item.name.toString()
    }

    object CategoryDiffCallback : DiffUtil.ItemCallback<Event>() {
        override fun areItemsTheSame(oldItem: Event, newItem: Event): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Event, newItem: Event): Boolean {
            return oldItem.brId == newItem.brId
        }
    }

}