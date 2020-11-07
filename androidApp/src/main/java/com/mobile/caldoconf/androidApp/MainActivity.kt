package com.mobile.caldoconf.androidApp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mobile.caldoconf.shared.Greeting
import android.widget.TextView
import com.mobile.caldoconf.shared.ApiClient
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private val mainScope = MainScope()
    private val apiClient = ApiClient()
    private lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.text_view)
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
                textView.text = it.toString()
            }.onFailure {
                textView.text = it.localizedMessage
            }
        }
    }
}
