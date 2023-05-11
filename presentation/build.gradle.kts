@file:Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.plugin)
    alias(libs.plugins.hilt.plugin)
    id ("androidx.navigation.safeargs.kotlin")
    id ("kotlin-kapt")
}
kotlin{
    jvmToolchain(17)
}
android {
    namespace = "com.example.presentation"
    compileSdk = 33

    defaultConfig {
        minSdk = 24
        targetSdk = 33

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }

    dataBinding {
        enable = true
    }
}

dependencies {
    implementation(project(":domain"))
    implementation(libs.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.constraintlayout) // 디폴트에는 없었음

    testImplementation(libs.junit)
    androidTestImplementation(libs.test.junit)
    androidTestImplementation(libs.espresso.core)

    // hilt
    implementation(libs.hilt)
    kapt(libs.hilt.compiler)
    kapt(libs.androidx.hilt.compiler)

    // Coroutine
    implementation(libs.bundles.coroutine)

    // ViewModel
    implementation(libs.viewmodel)

    // Glide
    implementation(libs.glide)

    // for viewModel initialize
    implementation(libs.activity.ktx)
    implementation(libs.fragment.ktx)

    // Navigation
    implementation(libs.bundles.navigation)

    // splashscreen
    implementation(libs.splashscreen)

    // lottie
    implementation(libs.lottie)
}