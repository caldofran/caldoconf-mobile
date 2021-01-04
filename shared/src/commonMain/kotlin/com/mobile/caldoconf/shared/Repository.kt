package com.mobile.caldoconf.shared

import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.firestore.firestore
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class Repository {
    private val store = Firebase.firestore
    val eventsFlow = store.collection("events").snapshots.map {
        it.documents.map { documentSnapshot ->
            documentSnapshot.data(Event.serializer())
        }
    }

    fun events() {
        GlobalScope.launch {
            runCatching {
                store.collection("events").get()
            }.onSuccess {
                it.documents.forEach {  document ->
                    println("Document: $document.id")
                    val event = document.data(Event.serializer())
                    println("Event: ${event.information}")
                }
            }.onFailure {
                println(it)
            }

        }
    }
}