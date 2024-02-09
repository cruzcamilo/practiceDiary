pluginManagement {
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
include(":app")
include(":feature:home:data")
include(":feature:home:domain")
include(":feature:home:ui")
include(":feature:diary_entry:data")
include(":feature:diary_entry:domain")
include(":feature:diary_entry:ui")
include(":core:db")
include(":core:common")
include(":core:feature_api")
