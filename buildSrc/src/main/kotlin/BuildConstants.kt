import org.gradle.api.JavaVersion

object BuildConstants {
    private const val majorVersion = 1
    private const val minorVersion = 0
    private const val patchVersion = 0

    const val applicationId = "com.example.practicediary"
    const val MIN_SDK = 30
    const val TARGET_SDK = 34
    const val COMPILE_SDK = 34
    const val VERSION_CODE = 1
    const val versionName = "$majorVersion.$minorVersion.$patchVersion"
    const val jvmTarget = "1.8"

    val javaVersion = JavaVersion.VERSION_1_8
}