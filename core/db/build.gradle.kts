plugins {
    alias(libs.plugins.practicediary.android.library)
    alias(libs.plugins.devToolsKsp)
    id ("kotlin-kapt")
    alias(libs.plugins.daggerHilt)
}

android {
    namespace = "com.core.db"
}

dependencies {
    implementation(projects.feature.home.domain)

    implementation(libs.androidx.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.material)

    // Room
    implementation(libs.androidx.room.runtime)
    annotationProcessor(libs.androidx.room.compiler)
    ksp(libs.androidx.room.compiler)
    implementation (libs.androidx.room.ktx)

    //Hilt
    implementation (libs.hilt.android)
    kapt (libs.hilt.compiler)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
}