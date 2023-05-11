// Top-level build file where you can add configuration options common to all sub-projects/modules.
@file:Suppress("DSL_SCOPE_VIOLATION")
buildscript {
    repositories {
        google()
    }
    dependencies {
        classpath(libs.kotlin.plugin)
        classpath(libs.navigation.safeargs)
        classpath(libs.hilt.plugin)
    }
}
plugins {
    alias (libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.plugin) apply false
    alias(libs.plugins.hilt.plugin) apply false
    id("org.jetbrains.kotlin.jvm") version "1.8.0" apply false
}
