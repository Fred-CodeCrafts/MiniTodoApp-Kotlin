plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.google.ksp) // Tambahkan ini}
    }

android {
    namespace = "com.fredcodecrafts.minitodoapp"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.fredcodecrafts.minitodoapp"
        minSdk = 28
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildFeatures {
        viewBinding = true
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {
    implementation(libs.androidx.core.ktx) // Versi mungkin berbeda, sesuaikan
    implementation(libs.androidx.appcompat) // Versi mungkin berbeda, sesuaikan
    implementation(libs.material.v1130) // Versi mungkin berbeda, sesuaikan
    implementation(libs.androidx.constraintlayout) // Versi mungkin berbeda, sesuaikan
    implementation(libs.androidx.activity.ktx) // Versi mungkin berbeda, sesuaikan
    implementation(libs.androidx.fragment.ktx) // Untuk Fragment KTX

    // Navigation Component
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)

    // ViewModel and LiveData
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.lifecycle.livedata.ktx)

    // Room (Opsional, tapi akan saya sertakan)
    implementation(libs.androidx.room.runtime)
    ksp(libs.androidx.room.compiler)
    implementation(libs.androidx.room.ktx) // For Kotlin Coroutines support with Room


    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // Navigation
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)

    // Lifecycle
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.9.3")

    // RecyclerView
    implementation("androidx.recyclerview:recyclerview:1.3.2")
}