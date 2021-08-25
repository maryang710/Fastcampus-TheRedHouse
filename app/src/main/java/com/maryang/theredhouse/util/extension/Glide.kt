package com.maryang.theredhouse.util.extension

import android.app.Activity
import android.content.Context
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager


val Context.glide: RequestManager get() = Glide.with(this)
val Fragment.glide: RequestManager get() = Glide.with(this)
val Activity.glide: RequestManager get() = Glide.with(this)

val RecyclerView.ViewHolder.context: Context get() = itemView.context
val RecyclerView.ViewHolder.glide: RequestManager get() = Glide.with(context)
