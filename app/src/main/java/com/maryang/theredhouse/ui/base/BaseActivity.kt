package com.maryang.theredhouse.ui.base

import androidx.appcompat.app.AppCompatActivity
import com.maryang.theredhouse.event.AnalyticsManager
import com.maryang.theredhouse.event.EventDefinition
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

@AndroidEntryPoint
abstract class BaseActivity : AppCompatActivity() {

    protected val compositeDisposable = CompositeDisposable()

    @Inject
    lateinit var analyticsManager: AnalyticsManager

    override fun onSupportNavigateUp(): Boolean {
        if (!super.onSupportNavigateUp())
            onBackPressed()
        return true
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }
}
