package com.maryang.theredhouse.ui.upload

import android.os.Bundle
import com.maryang.theredhouse.databinding.ActivityUploadTypeBinding
import com.maryang.theredhouse.domain.entity.HouseType
import com.maryang.theredhouse.event.EnterEventDefinitions
import com.maryang.theredhouse.event.EventDefinition
import com.maryang.theredhouse.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UploadTypeActivity : BaseActivity() {

    private lateinit var binding: ActivityUploadTypeBinding
    override var enterEvent: EventDefinition? = EnterEventDefinitions.uploadType()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUploadTypeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setToolbar()
        setTypeButton()
    }

    private fun setToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "매물 종류를 선택해주세요"
    }

    private fun setTypeButton() {
        binding.apart.setOnClickListener {
            UploadInfoActivity.start(this, HouseType.APART)
        }
        binding.villa.setOnClickListener {
            UploadInfoActivity.start(this, HouseType.VILLA)
        }
    }
}
