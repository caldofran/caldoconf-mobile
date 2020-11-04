package com.mobile.caldoconf.shared


class Greeting {
    fun greeting(): String {
        return "Guess what it is! > ${Platform().platform.reversed()}"
    }
}
