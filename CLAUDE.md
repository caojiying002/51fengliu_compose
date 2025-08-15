# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

This is an Android application built with Jetpack Compose, named "51fengliu_compose". It uses a modern Android development stack with Kotlin and Material Design 3.

## Build and Development Commands

### Building the Application
```bash
./gradlew build
```

### Running Tests
```bash
# Unit tests
./gradlew test

# Instrumented tests (requires Android emulator or device)
./gradlew connectedAndroidTest
```

### Building Debug APK
```bash
./gradlew assembleDebug
```

### Building Release APK
```bash
./gradlew assembleRelease
```

### Clean Build
```bash
./gradlew clean
```

## Code Architecture

### Package Structure
- **Main package**: `com.jiyingcao.a51fengliu.compose`
- **UI Theme**: `com.jiyingcao.a51fengliu.compose.ui.theme`

### Key Components
- **MainActivity**: Entry point using Jetpack Compose with edge-to-edge display
- **Theme System**: Material 3 theme with dynamic color support (Android 12+)
  - Light/dark theme variants
  - Custom color palette (Purple, PurpleGrey, Pink variants)
  - Typography and color schemes in `/ui/theme/`

### Build Configuration
- **Target SDK**: 36
- **Min SDK**: 24
- **Kotlin**: 2.0.21
- **AGP**: 8.12.0
- **Compose BOM**: 2024.09.00
- **Java Version**: 11

### Key Dependencies
- Jetpack Compose (UI toolkit)
- Material 3 (design system)
- AndroidX libraries (core, lifecycle, activity)
- Testing: JUnit, Espresso, Compose UI testing

### Development Notes
- Uses Kotlin official code style
- AndroidX libraries enabled
- Compose compiler integrated via Kotlin plugin
- ProGuard configuration for release builds (currently disabled)