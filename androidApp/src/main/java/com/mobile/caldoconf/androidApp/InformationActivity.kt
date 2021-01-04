package com.mobile.caldoconf.androidApp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mobile.caldoconf.androidApp.databinding.ActivityInformationBinding
import com.mobile.caldoconf.shared.ApiClient
import com.mobile.caldoconf.shared.Repository
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class InformationActivity : AppCompatActivity() {
    private val mainScope = MainScope()
    private val apiClient = ApiClient()
    private val repository = Repository()
    private val viewBinding by lazy {
        ActivityInformationBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(viewBinding.root)
    }

    override fun onResume() {
        super.onResume()
        loadData()
    }

    override fun onDestroy() {
        super.onDestroy()
        mainScope.cancel()
    }

    private fun loadData() {
        mainScope.launch {
            runCatching {
                apiClient.getEvents()
            }.onSuccess {
                viewBinding.informationTitle.text = it.toString()
            }.onFailure {
                viewBinding.informationTitle.text = it.localizedMessage
            }
        }

        repository.events()
    }
}