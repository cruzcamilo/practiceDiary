plugins {
    alias(libs.plugins.practicediary.android.library)
    alias(libs.plugins.practicediary.android.hilt)
}

android {
    namespace = "com.feature.home.data"
}

dependencies {
    implementation(projects.core.common)
    implementation(projects.feature.home.domain)
    implementation(projects.core.db)

    implementation(libs.androidx.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
}