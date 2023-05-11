@file:Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias (libs.plugins.android.application)
    alias(libs.plugins.kotlin.plugin)
    alias(libs.plugins.hilt.plugin)
    // 아래 플러그인들은 프로젝트 build.gradle plugin에 없음
    id ("androidx.navigation.safeargs.kotlin")
    id ("kotlin-kapt")
}
kotlin{
    jvmToolchain(17)
}
android {
    namespace = "com.example.toyproject1"
    compileSdk = libs.versions.targetSDK.get().toInt()

    defaultConfig {
        applicationId = "com.example.toyproject1"
        minSdk = libs.versions.minSDK.get().toInt()
        targetSdk = libs.versions.targetSDK.get().toInt()
        versionCode = libs.versions.versionCode.get().toInt()
        versionName = libs.versions.versionName.get()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
//        sourceCompatibility = JavaVersion.VERSION_1_8
//        targetCompatibility = JavaVersion.VERSION_1_8
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
//        jvmTarget = "1.8"
        jvmTarget = JavaVersion.VERSION_17.toString()
    }

    dataBinding {
        enable = true
    }
}

dependencies {
    implementation(project(":presentation"))
    implementation(project(":domain"))
    implementation(project(":data"))

    implementation(libs.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.constraintlayout)

    testImplementation(libs.junit)
    androidTestImplementation(libs.test.junit)
    androidTestImplementation(libs.espresso.core)

    // for viewModel initialize
    implementation(libs.activity.ktx)
    implementation(libs.fragment.ktx)

    // Navigation
    implementation(libs.bundles.navigation)

    // Retrofit
    implementation(libs.retrofit)

    // Moshi
    implementation(libs.bundles.moshi)

    // ViewModel
    implementation(libs.viewmodel)

    // Glide
    implementation(libs.glide)
//    annotationProcessor(libs.glidecompiler)

    // Coroutine
    implementation(libs.bundles.coroutine)

    // Hilt
    implementation(libs.hilt)
    kapt(libs.hilt.compiler)
    kapt(libs.androidx.hilt.compiler)

    // Room
    implementation(libs.room.ktx)
}
