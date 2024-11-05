plugins {
    alias(libs.plugins.practicediary.android.library)
    id ("kotlin-kapt")
    alias(libs.plugins.daggerHilt)
}

android {
    namespace = "com.feature.diary.entry.ui"

    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.8"
    }
}

dependencies {
    implementation(projects.core.common)
    implementation(projects.feature.diaryEntry.domain)

    implementation(libs.androidx.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.material)

    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.boom))
    implementation(libs.ui)
    implementation(libs.ui.graphics)
    implementation(libs.ui.tooling.preview)
    implementation(libs.material3)
    implementation(libs.navigation.compose)
    debugImplementation(libs.androidx.ui.tooling)

    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.runtime.livedata)

    //Hilt
    implementation (libs.hilt.android)
    kapt (libs.hilt.compiler)
    implementation(libs.androidx.hilt.navigation.compose)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
}