package com.maryang.theredhouse.ui.upload

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.widget.addTextChangedListener
import com.maryang.theredhouse.R
import com.maryang.theredhouse.databinding.ActivityUploadContactBinding
import com.maryang.theredhouse.domain.entity.HouseEntity
import com.maryang.theredhouse.event.EnterEventDefinitions
import com.maryang.theredhouse.event.EventDefinition
import com.maryang.theredhouse.ui.base.BaseViewModelActivity
import com.maryang.theredhouse.ui.list.HouseListActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UploadContactActivity : BaseViewModelActivity() {

    private lateinit var binding: ActivityUploadContactBinding
    override val viewModel by viewModels<UploadContactViewModel>()

    companion object {
        const val KEY_HOUSE = "KEY_HOUSE"

        fun start(context: Context, house: HouseEntity.Upload) {
            context.startActivity(
                Intent(context, UploadContactActivity::class.java)
                    .putExtra(KEY_HOUSE, house)
            )
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUploadContactBinding.inflate(layoutInflater)
        setContentView(binding.root)
        overridePendingTransition(R.anim.slide_in_from_right, R.anim.none)

        setToolbar()
        observeCompleteButtonState()
        setComplete()
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.none, R.anim.slide_out_to_right)
    }

    private fun setToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "연락처 입력"
    }

    private fun observeCompleteButtonState() {
        binding.contact.addTextChangedListener { contact ->
            val enable = !contact.isNullOrBlank()
            binding.completeButton.isEnabled = enable
            binding.completeButton.setBackgroundColor(
                if (enable) getColor(R.color.red_900)
                else getColor(R.color.gray500)
            )
        }
    }

    private fun setComplete() {
        binding.completeButton.setOnClickListener {
            val house = intent.getParcelableExtra<HouseEntity.Upload>(KEY_HOUSE)!!
            viewModel.upload(
                house.copy(
                    contact = binding.contact.text.toString()
                )
            )
        }
        viewModel.uploadLiveData.observe(this) { upload ->
            if (upload) {
                startActivity(
                    Intent(this, HouseListActivity::class.java)
                        .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                )
                finish()
            }
        }
    }
}
