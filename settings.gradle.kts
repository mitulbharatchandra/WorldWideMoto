rootProject.name = "WorldWideMoto"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}

include(":composeApp")
include(":core:data")
include(":core:domain")
include(":core:designsystem")
include(":core:presentation")
include(":firebase-core")
include(":firebase-auth")
include(":kmp-auth-api")
include(":feature")
include(":feature:business")
include(":feature:business:presentation")
include(":feature:auth")
include(":feature:auth:presentation")
include(":feature:auth:domain")
include(":feature:auth:data")
include(":feature:home")
include(":feature:home:presentation")
include(":feature:profile:presentation")
include(":google-sign-in")
include(":apple-sign-in")
include(":maps")
include(":firestore")
