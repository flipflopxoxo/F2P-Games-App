/*
 * Copyright (c) 2022 Clyde Lizardo
 */

package com.clydelizardo.f2pgames.list.view

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bumptech.glide.Glide
import com.clydelizardo.f2pgames.model.GameInfo

object ViewBinding {
    @JvmStatic
    @BindingAdapter("isRefreshing")
    fun isRefreshing(refreshLayout: SwipeRefreshLayout, isRefreshing: Boolean) {
        refreshLayout.isRefreshing = isRefreshing
    }

    @JvmStatic
    @BindingAdapter("gameInfoList")
    fun gameInfoList(recyclerView: RecyclerView, list: List<GameInfo>?) {
        val adapter = recyclerView.adapter
        when (adapter) {
            is GameListAdapter -> {
                adapter.submitList(list)
            }
        }
    }

    @JvmStatic
    @BindingAdapter("onRefresh")
    fun onRefresh(refreshLayout: SwipeRefreshLayout, onRefresh: () -> Unit) {
        refreshLayout.setOnRefreshListener(onRefresh)
    }

    @JvmStatic
    @BindingAdapter("imageUrl")
    fun imageUrl(imageView: ImageView, url: String?) {
        Glide.with(imageView)
            .load(url)
            .into(imageView)
    }
}