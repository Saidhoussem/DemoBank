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
rootProject.name = "DemoBank"
include(":app")
include(":core")
include(":core:data")
include(":core:model")
include(":feature")
include(":feature:account")
include(":feature:converter")
include(":feature:simulator")
include(":core:datasource")
include(":core:domain")
