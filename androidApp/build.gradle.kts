plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.chineselearn.app"
    compileSdk = 35

    defaultConfig {
        // Khac applicationId cua ban chinh de cai song song duoc
        applicationId = "com.chineselearn.app.trial"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "5.0.0-trial"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    implementation(project(":shared"))
    implementation(libs.androidx.activity.compose)
}
