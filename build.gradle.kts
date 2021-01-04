buildscript {
    repositories {
        gradlePluginPortal()
        jcenter()
        google()
        mavenCentral()
    }
    val kotlin_version by extra("1.4.20")
    val kotlinVersion = "1.4.20"

    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
        classpath("com.android.tools.build:gradle:4.1.1")
        classpath("org.jetbrains.kotlin:kotlin-serialization:$kotlinVersion")
        classpath("com.google.gms:google-services:4.3.4")
    }
}
group = "com.mobile.caldoconf"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    google()
}
