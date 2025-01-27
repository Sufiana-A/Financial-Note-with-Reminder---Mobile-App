plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.financialnotesnotifapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.financialnotesnotifapp"
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
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

    // Room Database
    implementation(libs.room.runtime)
    annotationProcessor(libs.room.compiler)
    implementation(libs.lifecycle.viewmodel)

    // WorkManager
    implementation(libs.work.runtime.ktx)

    // Notifications
    implementation(libs.core.ktx)
}