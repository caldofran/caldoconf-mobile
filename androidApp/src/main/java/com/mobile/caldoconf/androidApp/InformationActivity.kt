package com.mobile.caldoconf.androidApp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.mobile.caldoconf.shared.ApiClient
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class InformationActivity : AppCompatActivity() {
    private val mainScope = MainScope()
    private val apiClient = ApiClient()
    private lateinit var informationTitle: TextView
    private lateinit var informationContent: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_information)

        informationTitle = findViewById(R.id.informationTitle)
        informationContent = findViewById(R.id.informationContent)
    }

//    override fun onResume() {
//        super.onResume()
//        loadData()
//    }

    override fun onDestroy() {
        super.onDestroy()
        mainScope.cancel()
    }

    private fun loadData() {
        mainScope.launch {
            runCatching {
                apiClient.getEvents()
            }.onSuccess {
                informationTitle.text = it.toString()
            }.onFailure {
                informationTitle.text = it.localizedMessage
            }
        }
    }
}
