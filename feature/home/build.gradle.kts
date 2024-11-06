plugins {
    alias(libs.plugins.practicediary.android.library)
    alias(libs.plugins.practicediary.android.hilt)
}

android {
    namespace = "com.feature.home"
}

dependencies {
    implementation(projects.core.db)
    implementation(projects.feature.home.data)
    implementation(projects.feature.home.domain)

    // Room
    implementation(libs.androidx.room.runtime)
    annotationProcessor(libs.androidx.room.compiler)
    ksp(libs.androidx.room.compiler)
    implementation (libs.androidx.room.ktx)

    implementation(libs.androidx.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
}