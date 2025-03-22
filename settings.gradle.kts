pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
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

rootProject.name = "jantar"
include(":app")
include(":core:network")
include(":core:common")
include(":core:di")
include(":features:camera")
include(":core:designsystem")
include(":features:info")
include(":core:database")
include(":features:home")
include(":features:auth")
