package com.rdevl.sportplug2.ui.live

import android.annotation.SuppressLint
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
import com.rdevl.sportplug2.databinding.ItemLiveMatchesBinding

class LiveMatchesAdapter(private val onClick: (Event) -> Unit):
    ListAdapter<Event, ListViewHolder>(ListViewHolder.CategoryDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ListViewHolder(
        ItemLiveMatchesBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        ), onClick
    )

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) =
        holder.bind(getItem(position))
}

class ListViewHolder(
    private val itemBinding: ItemLiveMatchesBinding,
    onClick: (Event) -> Unit
):
    RecyclerView.ViewHolder(itemBinding.root) {

    private lateinit var currentItem: Event

    fun bind(item: Event) {
        currentItem = item
        itemBinding.name.text = item.champName
        itemBinding.tvFirstTeam.text = item.name.toString().substringBefore("-").replaceAfter("(", "").replace("(", "").trim()
        itemBinding.tvSecondTeam.text = item.name.toString().substringAfter("-").replaceAfter("(", "").replace("(", "").trim()
        if (item.score.isNotEmpty()) {
            itemBinding.tvScoreFirstTeam.text = item.score.substringBefore(":").trim()
            itemBinding.tvScoreSecondTeam.text = item.score.substringAfter(":").trim()
        } else {
            itemBinding.tvScoreFirstTeam.text = "-"
            itemBinding.tvScoreSecondTeam.text = "-"
        }
    }

    object CategoryDiffCallback : DiffUtil.ItemCallback<Event>() {
        override fun areItemsTheSame(oldItem: Event, newItem: Event): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Event, newItem: Event): Boolean {
            return oldItem.name.toString() == newItem.name.toString()
        }
    }

}