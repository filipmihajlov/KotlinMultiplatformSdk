# Kotlin Multiplatform SDK Demo

This is a Kotlin Multiplatform project targeting **Android** and **iOS**.  
It contains a shared SDK module that will be implemented in host applications for both mobile platforms.

The main points of this repo are: **generate local SDK artefacts** that you can consume from the native Showcase apps.
Point 2 is to showcase how things are done in KMP

- **Android:** publish AARs to **Maven Local**
- **iOS:** build an **XCFramework** for Xcode

---

## What the SDK Demonstrates

### Local SDK delivery model (project-specific)

This project is set up so you can treat the shared KMP code as an SDK during development:

- Android Showcase consumes:
    - `com.example.esimsdkkmp:shared-android:<version>`
    - `com.example.esimsdkkmp:cardkit-android:<version>` (Android wrapper)
- iOS Showcase consumes:
    - a generated `*.xcframework` built from the KMP module


### Android & iOS integration (in this repo)

#### Android

- SDK is published locally as Maven artefacts.
- Host apps consume it via `mavenLocal()` and normal Gradle dependencies.

#### iOS

- SDK is exported as an XCFramework.
- Host apps consume it by adding the `.xcframework` in Xcode (“drop-in SDK”).

---

## Project Structure

This is a Kotlin Multiplatform project targeting **Android** and **iOS**.

### `/composeApp`

[`/composeApp`](./composeApp/src) contains the **Android app** and any shared Compose Multiplatform UI.

This module mostly exists to show how the SDK can be consumed from an Android app. Presentation wise it's empty because that part
has been moved to the implementation or Showcase Android SDK.


### `/iosApp`

[`/iosApp`](./iosApp/iosApp) contains the **iOS sample application**.

This module shows the reference integration style on iOS (Xcode + SwiftUI consuming the shared code).Presentation wise it's empty because that part
has been moved to the implementation or Showcase iOS SDK.

### `/shared`

[`/shared`](./shared/src) is the **core SDK module**, shared between all targets.

This is the module you publish/build as an SDK artefact:

- **Android:** `shared-android` (published to Maven Local)
- **iOS:** XCFramework (built from `shared`)

### `/cardkit-android`

`/cardkit-android` is an **Android-only wrapper artefact** that depends on `shared-android`.

It’s published as:

- **Android:** `cardkit-android` (published to Maven Local)

---

## Generating SDK artefacts (local)

Android artefacts:
./gradlew clean --no-configuration-cache
./gradlew :shared:publishToMavenLocal --no-configuration-cache
./gradlew :cardkit-android:publishToMavenLocal --no-configuration-cache

The artefact is named: cardkit-android version 1.0.1 as of writing of this file.
In the Showcase Android SDK it's used as a dependency in this approach:
implementation("com.example.esimsdkkmp:cardkit-android:1.0.1")

iOS XCFramework:

./gradlew :shared:assembleXCFramework --no-configuration-cache

The generated ios framework currently must be manually located and added in the Showcase iOS app as a dependency.

Later on both SDK's will be delivered to the 'customers' now 'showcase' apps either locally (android) or manually (ios).
This will not be the final approach when we go to Production.