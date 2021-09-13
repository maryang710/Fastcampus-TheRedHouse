package com.maryang.theredhouse.ui.detail

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.maryang.theredhouse.R
import com.maryang.theredhouse.databinding.ActivityHouseDetailBinding
import com.maryang.theredhouse.event.EnterEventDefinitions
import com.maryang.theredhouse.event.EventDefinition
import com.maryang.theredhouse.event.EventDefinitions
import com.maryang.theredhouse.featureflag.ConfigVariable
import com.maryang.theredhouse.featureflag.RemoteConfigManager
import com.maryang.theredhouse.ui.base.BaseViewModelActivity
import com.maryang.theredhouse.util.PriceUtil
import com.maryang.theredhouse.util.extension.glide
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HouseDetailActivity : BaseViewModelActivity() {

    private lateinit var binding: ActivityHouseDetailBinding
    override val viewModel by viewModels<HouseDetailViewModel>()

    companion object {
        const val KEY_ID = "KEY_ID"

        fun start(context: Context, id: Int) {
            context.startActivity(
                Intent(context, HouseDetailActivity::class.java)
                    .putExtra(KEY_ID, id)
            )
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHouseDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setToolbar()
        setContactButton()
        observeHouse()
        viewModel.onCreate(intent.getIntExtra(KEY_ID, 0))
    }

    private fun setToolbar() {
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun setContactButton() {
        binding.contactButton.visibility = View.VISIBLE
        binding.contactButtonFab.visibility = View.GONE

        binding.contactButton.text = "연락하기"
        binding.contactButton.setOnClickListener { view ->
            startActivity(
                Intent(Intent.ACTION_DIAL, Uri.parse("tel:${viewModel.houseLiveData.value?.contact}"))
            )
        }

        binding.contactButtonFab.setColorFilter(Color.WHITE)
        binding.contactButtonFab.setOnClickListener { view ->
            startActivity(
                Intent(Intent.ACTION_DIAL, Uri.parse("tel:${viewModel.houseLiveData.value?.contact}"))
            )
        }
    }

    @SuppressLint("SetTextI18n")
    private fun observeHouse() {
        viewModel.houseLiveData.observe(this) {
            binding.toolbarLayout.title = it.name
            binding.content.price.text = PriceUtil.convertKoreanPriceUnit(it.price)
            binding.content.addressAndType.text = "${it.address} ${it.type.displayName}"
            glide.load(it.imageResId)
                .placeholder(R.drawable.background_house)
                .into(binding.image)
        }
    }
}
