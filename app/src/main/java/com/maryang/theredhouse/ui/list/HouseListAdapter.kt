package com.maryang.theredhouse.ui.list

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import com.maryang.theredhouse.R
import com.maryang.theredhouse.databinding.ItemHouseListBinding
import com.maryang.theredhouse.databinding.ItemHouseListImageRightBinding
import com.maryang.theredhouse.domain.entity.HouseEntity
import com.maryang.theredhouse.featureflag.ConfigVariable
import com.maryang.theredhouse.featureflag.RemoteConfigManager
import com.maryang.theredhouse.ui.base.list.BaseDiffUtilItemCallback
import com.maryang.theredhouse.ui.base.list.BaseListAdapter
import com.maryang.theredhouse.ui.base.list.BaseViewHolder
import com.maryang.theredhouse.ui.detail.HouseDetailActivity
import com.maryang.theredhouse.util.PriceUtil
import com.maryang.theredhouse.util.extension.context
import com.maryang.theredhouse.util.extension.glide

class HouseListAdapter : BaseListAdapter<HouseEntity>(BaseDiffUtilItemCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<HouseEntity> =
        if (RemoteConfigManager.getBoolean(ConfigVariable.ShowHouseListItemImageOnRightSide)) {
            HouseImageRightViewHolder(
                ItemHouseListImageRightBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
        } else {
            HouseViewHolder(
                ItemHouseListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
        }


    class HouseViewHolder(
        private val binding: ItemHouseListBinding
    ) : BaseViewHolder<HouseEntity>(binding) {

        @SuppressLint("SetTextI18n")
        override fun bind(item: HouseEntity) {
            glide.load(item.imageResId)
                .placeholder(R.drawable.ic_house)
                .into(binding.image)
            binding.name.text = item.name
            binding.addressAndType.text = "${item.address} ${item.type.displayName}"
            binding.price.text = PriceUtil.convertKoreanPriceUnit(item.price)
            itemView.setOnClickListener {
                HouseDetailActivity.start(context, item.id)
            }
        }
    }

    class HouseImageRightViewHolder(
        private val binding: ItemHouseListImageRightBinding
    ) : BaseViewHolder<HouseEntity>(binding) {

        @SuppressLint("SetTextI18n")
        override fun bind(item: HouseEntity) {
            glide.load(item.imageResId)
                .placeholder(R.drawable.ic_house)
                .into(binding.image)
            binding.name.text = item.name
            binding.addressAndType.text = "${item.address} ${item.type.displayName}"
            binding.price.text = PriceUtil.convertKoreanPriceUnit(item.price)
            itemView.setOnClickListener {
                HouseDetailActivity.start(context, item.id)
            }
        }
    }
}
