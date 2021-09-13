package com.maryang.theredhouse.ui.list

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.maryang.theredhouse.BuildConfig
import com.maryang.theredhouse.R
import com.maryang.theredhouse.databinding.ActivityHouseListBinding
import com.maryang.theredhouse.event.EnterEventDefinitions
import com.maryang.theredhouse.event.EventDefinition
import com.maryang.theredhouse.featureflag.ConfigVariable
import com.maryang.theredhouse.featureflag.RemoteConfigManager
import com.maryang.theredhouse.ui.base.BaseViewModelActivity
import com.maryang.theredhouse.ui.splash.SplashActivity
import com.maryang.theredhouse.ui.upload.UploadTypeActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HouseListActivity : BaseViewModelActivity() {

    private lateinit var binding: ActivityHouseListBinding
    private val adapter by lazy {
        HouseListAdapter()
    }
    override val viewModel by viewModels<HouseListViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHouseListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setToolbar()
        setList()
        setLogout()
        viewModel.onCreate()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_house_list, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_upload -> {
                startActivity(Intent(this, UploadTypeActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = "The Red House"
    }

    private fun setList() {
        binding.list.adapter = adapter
        binding.list.layoutManager = LinearLayoutManager(this)
        binding.refreshLayout.setOnRefreshListener {
            viewModel.getHouseList()
            binding.refreshLayout.isRefreshing = false
        }
        viewModel.houseListLiveData.observe(this) {
            adapter.submitList(it)
        }
    }

    private fun setLogout() {
        binding.logout.visibility = if (BuildConfig.DEBUG) View.VISIBLE else View.GONE
        binding.logout.setOnClickListener {
            viewModel.logout()
        }
        viewModel.logoutLiveData.observe(this) { logout ->
            if (logout) {
                startActivity(
                    Intent(this, SplashActivity::class.java)
                        .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                )
                finish()
            }
        }
    }
}
