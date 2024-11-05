plugins {
    alias(libs.plugins.practicediary.android.library)
    alias(libs.plugins.devToolsKsp)
    id ("kotlin-kapt")
    alias(libs.plugins.daggerHilt)
}

android {
    namespace = "com.feature.diary_entry"
}

dependencies {
    implementation(projects.core.db)
    implementation(projects.feature.diaryEntry.data)
    implementation(projects.feature.diaryEntry.domain)

    // Room
    implementation(libs.androidx.room.runtime)
    annotationProcessor(libs.androidx.room.compiler)
    ksp(libs.androidx.room.compiler)
    implementation (libs.androidx.room.ktx)

    //Hilt
    implementation (libs.hilt.android)
    kapt (libs.hilt.compiler)

    implementation(libs.androidx.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
}