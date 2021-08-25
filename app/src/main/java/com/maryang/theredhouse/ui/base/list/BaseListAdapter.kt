package com.maryang.theredhouse.ui.base.list

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.maryang.theredhouse.domain.entity.Entity

abstract class BaseListAdapter<E : Entity>(
    diffUtilItemCallback: DiffUtil.ItemCallback<E>
) : ListAdapter<E, BaseViewHolder<E>>(diffUtilItemCallback) {

    override fun onBindViewHolder(holder: BaseViewHolder<E>, position: Int) {
        holder.bind(getItem(position))
    }
}
