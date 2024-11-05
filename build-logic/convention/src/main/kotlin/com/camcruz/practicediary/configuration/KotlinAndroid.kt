package com.camcruz.practicediary.configuration

import com.android.build.api.dsl.CommonExtension
import com.camcruz.practicediary.libs
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

internal fun Project.configureKotlinAndroid(
    commonExtension: CommonExtension<*, *, *, *, *>,
) {
    commonExtension.apply {
        compileSdk = Versions.compileSdk

        defaultConfig {
            minSdk = Versions.minSdk
            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        }

        compileOptions {
            sourceCompatibility = Versions.jvmVersion
            targetCompatibility = Versions.jvmVersion
            isCoreLibraryDesugaringEnabled = true
        }

        buildTypes {
            getByName("release") {
                isMinifyEnabled = false
            }

            getByName("debug") {
                isJniDebuggable = true
            }
        }

        buildFeatures {
            buildConfig = true
        }
    }

    configureKotlin()
    dependencies {
        add("coreLibraryDesugaring", libs.findLibrary("android.desugarJdkLibs").get())
    }
}

private fun Project.configureKotlin() {
    tasks.withType<KotlinCompile>().configureEach {
        kotlinOptions {
            jvmTarget = Versions.jvmVersion.toString()
        }
    }
}
