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
include(":core:common")
include(":core:database")
include(":core:network")
include(":core:designsystem")
include(":navigation")
include(":features:camera")
include(":features:info")
include(":features:home")
include(":features:auth")
include(":features:explore")
include(":features:splash")
