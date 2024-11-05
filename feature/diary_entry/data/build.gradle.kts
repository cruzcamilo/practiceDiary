plugins {
    alias(libs.plugins.practicediary.android.library)
    id ("kotlin-kapt")
    alias(libs.plugins.daggerHilt)
}

android {
    namespace = "com.feature.diary_entry.data"
}

dependencies {
    implementation(projects.core.common)
    implementation(projects.core.db)
    implementation(projects.feature.diaryEntry.domain)

    implementation (libs.hilt.android)
    kapt (libs.hilt.compiler)

    implementation(libs.androidx.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
}