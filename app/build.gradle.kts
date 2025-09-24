plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.kotlin.ksp)
}

android {
    namespace = "com.putragandad.pagingretrofit"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.putragandad.pagingretrofit"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(project(":common"))
    implementation(project(":data"))
    implementation(project(":domain"))
    implementation(project(":presentation"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    implementation(libs.androidx.navigation.compose)
    implementation(libs.kotlin.serialization.json)

    // ViewModel
    implementation(libs.lifecycle.viewmodel.ktx)
    // ViewModel utilities for Compose
    implementation(libs.lifecycle.viewmodel.compose)
    // LiveData
    implementation(libs.lifecycle.livedata.ktx)
    // Lifecycles only (without ViewModel or LiveData)
    implementation(libs.lifecycle.runtime.ktx)
    // Lifecycle utilities for Compose
    implementation(libs.lifecycle.runtime.compose)
    // Saved state module for ViewModel
    implementation(libs.lifecycle.viewmodel.savedstate)
    // Annotation processor
    implementation(libs.lifecycle.compiler)

    // Retrofit
    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.gson)
    implementation(libs.okhttp.logging.interceptor)
    implementation(libs.serialization.json)

    // Koin (Dependency Injection)
    // Declare koin-bom version
    implementation(platform(libs.koin.bom))
    implementation(libs.koin.core)
    // Declare the koin dependencies that you need
    implementation(libs.koin.android)
    implementation(libs.koin.core.coroutines)
    implementation(libs.koin.androidx.workmanager)

    implementation(libs.koin.androidx.compose)
    implementation(libs.koin.androidx.compose.navigation)

    // Chucker
    debugImplementation(libs.chucker.debug)
    releaseImplementation(libs.chucker.release)
}