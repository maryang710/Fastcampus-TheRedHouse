package com.maryang.theredhouse.ui.base.list

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil
import com.maryang.theredhouse.domain.entity.Identifier

class BaseDiffUtilItemCallback<I : Identifier> : DiffUtil.ItemCallback<I>() {
    override fun areItemsTheSame(oldItem: I, newItem: I): Boolean =
        oldItem.id == newItem.id

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: I, newItem: I): Boolean =
        oldItem == newItem
}
