package com.clydelizardo.f2pgames.list.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.clydelizardo.f2pgames.databinding.ListItemGameInfoBinding
import com.clydelizardo.f2pgames.list.view.viewholder.GameEntryViewHolder
import com.clydelizardo.f2pgames.list.viewmodel.view.GameInfo

class GameListAdapter : ListAdapter<GameInfo, GameEntryViewHolder>(object :
    DiffUtil.ItemCallback<GameInfo>() {
    override fun areItemsTheSame(oldItem: GameInfo, newItem: GameInfo) = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: GameInfo, newItem: GameInfo) =
        oldItem == newItem.copy(id = oldItem.id)

}) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameEntryViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding =
            ListItemGameInfoBinding.inflate(layoutInflater, parent, false)
        return GameEntryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GameEntryViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}