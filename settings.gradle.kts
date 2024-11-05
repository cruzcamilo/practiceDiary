pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "PracticeDiary"
// https://issuetracker.google.com/issues/315023802
gradle.startParameter.excludedTaskNames.add(":build-logic:convention:testClasses")
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
include(":app")
include(":feature:home")
include(":feature:home:data")
include(":feature:home:domain")
include(":feature:home:ui")
include(":feature:diary_entry:data")
include(":feature:diary_entry:domain")
include(":feature:diary_entry:ui")
include(":core:db")
include(":core:common")
