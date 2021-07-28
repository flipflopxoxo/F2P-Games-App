package com.clydelizardo.f2pgames.list.view.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.clydelizardo.f2pgames.databinding.ListItemGameInfoBinding
import com.clydelizardo.f2pgames.list.viewmodel.view.GameInfo

class GameEntryViewHolder(private val binding: ListItemGameInfoBinding, private val onSelect: (GameInfo) -> Unit) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(gameInfo: GameInfo) {
        binding.title.text = gameInfo.name
        binding.description.text = gameInfo.description
        Glide.with(binding.root)
            .load(gameInfo.thumbnail)
            .into(binding.thumbnail)
        binding.root.setOnClickListener {
            onSelect(gameInfo)
        }
    }
}