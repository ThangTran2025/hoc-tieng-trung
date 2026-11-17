plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.android.kmp.library)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.compose.multiplatform)
}

kotlin {
    androidLibrary {
        namespace = "com.chineselearn.shared"
        compileSdk = 35
        minSdk = 24
        androidResources { enable = true }
    }

    listOf(iosArm64(), iosSimulatorArm64()).forEach {
        it.binaries.framework {
            baseName = "Shared"
            isStatic = true
        }
    }

    jvm("desktop")

    sourceSets {
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(libs.compose.material.icons.extended)
            implementation(libs.kotlinx.coroutines.core)
            implementation(libs.androidx.sqlite)
            implementation(libs.androidx.sqlite.bundled)
        }
    }
}

compose.resources {
    publicResClass = true
    packageOfResClass = "com.chineselearn.app.resources"
    generateResClass = always
}
