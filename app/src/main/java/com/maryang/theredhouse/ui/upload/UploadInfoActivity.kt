package com.maryang.theredhouse.ui.upload

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.core.widget.addTextChangedListener
import com.maryang.theredhouse.R
import com.maryang.theredhouse.databinding.ActivityUploadInfoBinding
import com.maryang.theredhouse.domain.entity.HouseEntity
import com.maryang.theredhouse.domain.entity.HouseType
import com.maryang.theredhouse.event.EnterEventDefinitions
import com.maryang.theredhouse.event.EventDefinition
import com.maryang.theredhouse.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.Observable
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.subjects.BehaviorSubject

@AndroidEntryPoint
class UploadInfoActivity : BaseActivity() {

    private lateinit var binding: ActivityUploadInfoBinding
    override var enterEvent: EventDefinition? = EnterEventDefinitions.uploadInfo()

    private val priceState = BehaviorSubject.createDefault(false)
    private val nameState = BehaviorSubject.createDefault(false)
    private val addressState = BehaviorSubject.createDefault(false)
    private val buttonState = BehaviorSubject.createDefault(false)

    companion object {
        const val KEY_TYPE = "KEY_TYPE"

        fun start(context: Context, houseType: HouseType) {
            context.startActivity(
                Intent(context, UploadInfoActivity::class.java)
                    .putExtra(KEY_TYPE, houseType)
            )
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUploadInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        overridePendingTransition(R.anim.slide_in_from_right, R.anim.none)

        setToolbar()
        observeNextButtonState()
        setNextButton()
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.none, R.anim.slide_out_to_right)
    }

    private fun setToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "매물 정보 입력"
    }

    private fun observeNextButtonState() {
        binding.price.addTextChangedListener { price ->
            priceState.onNext(!price.isNullOrBlank())
        }
        binding.name.addTextChangedListener { name ->
            nameState.onNext(!name.isNullOrBlank())
        }
        binding.address.addTextChangedListener { address ->
            addressState.onNext(!address.isNullOrBlank())
        }
        compositeDisposable += Observable.combineLatest(
            priceState,
            nameState,
            addressState,
            { priceState, nameState, addressState ->
                priceState && nameState && addressState
            }
        ).subscribe(buttonState::onNext)
        compositeDisposable += buttonState.subscribe {
            binding.nextButton.isEnabled = it
            binding.nextButton.setBackgroundColor(
                if (it) getColor(R.color.red_900)
                else getColor(R.color.gray500)
            )
        }
    }

    private fun setNextButton() {
        binding.nextButton.setOnClickListener {
            val house = HouseEntity.Upload(
                name = binding.name.text.toString(),
                price = binding.price.text.toString().toLong(),
                address = binding.address.text.toString(),
                contact = "",
                type = intent.getSerializableExtra(KEY_TYPE) as HouseType
            )
            UploadContactActivity.start(this, house)
        }
    }
}
