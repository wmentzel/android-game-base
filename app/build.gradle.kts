import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    namespace = "com.randomlychosenbytes.androidgamebase"

    compileSdk = 33

    defaultConfig {
        applicationId = "com.randomlychosenbytes.androidgamebase"
        minSdk = 21
        targetSdk = 33

        versionCode = 1
        versionName = "1.0"

        multiDexEnabled = true
    }

    buildTypes {

        release {
            signingConfig = signingConfigs.getByName("debug") // just for testing
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation("androidx.multidex:multidex:2.0.1")
}
