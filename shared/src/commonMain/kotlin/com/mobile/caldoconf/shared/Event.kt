package com.mobile.caldoconf.shared

import kotlinx.serialization.Serializable

@Serializable
data class Event(
    val identifier: String,
    val motto: String?
)
