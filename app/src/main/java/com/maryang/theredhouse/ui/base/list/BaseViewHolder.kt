package com.maryang.theredhouse.ui.base.list

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.maryang.theredhouse.domain.entity.Entity

abstract class BaseViewHolder<E : Entity>(
    binding: ViewBinding
) : RecyclerView.ViewHolder(binding.root) {
    abstract fun bind(item: E)
}
