package com.maryang.theredhouse.ui.base

import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
abstract class BaseViewModelActivity : BaseActivity() {

    abstract val viewModel: BaseViewModel

    override fun onDestroy() {
        super.onDestroy()
        viewModel.onDestroy()
    }
}
