plugins {
    alias(libs.plugins.practicediary.android.library)
}

android {
    namespace = "com.feature.diary.entry.domain"
}

dependencies {
    implementation(projects.core.common)

    implementation(libs.androidx.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
}