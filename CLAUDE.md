# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

This is an Android application built with Jetpack Compose, named "51fengliu_compose". It implements a modern tab-based navigation system with five main sections (首页, 信息, 暗巷, 商家, 我的) using a clean architecture pattern with Hilt dependency injection and custom Material 3 theming.

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

## Architecture Overview

### Navigation Architecture
- **Custom Tab Navigation**: Uses custom bottom navigation instead of AndroidX Navigation Component
- **State Management**: Centralized in `MainNavigationViewModel` with `SavedStateHandle` for state restoration
- **Navigation Models**: Well-defined with `MainTab` enum and `MainNavigationUiState` data class
- **Intent Handling**: Custom re-login logic via intent extras

### Dependency Injection (Hilt)
- **Application Class**: `App.kt` with `@HiltAndroidApp` annotation
- **Entry Points**: Activities use `@AndroidEntryPoint`, ViewModels use `@HiltViewModel`
- **ViewModel Integration**: Uses `hiltViewModel()` in Compose screens for automatic injection
- **KSP Processing**: Uses Kotlin Symbol Processing for Hilt code generation

### MVVM + Unidirectional Data Flow Pattern
```kotlin
@HiltViewModel
class FeatureViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow(FeatureUiState())
    val uiState: StateFlow<FeatureUiState> = _uiState.asStateFlow()
    
    fun handleAction(action: FeatureAction) { /* Handle user actions */ }
}

@Composable
fun FeatureScreen(viewModel: FeatureViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    FeatureContent(uiState = uiState, onAction = viewModel::handleAction)
}
```

### Key Architectural Components

#### **Main Navigation System** (`/ui/navigation/`)
- `MainNavigation.kt`: Main navigation Composable with Scaffold pattern
- `MainNavigationViewModel.kt`: Navigation state management with tab selection logic
- `NavigationModels.kt`: Data models for navigation state
- `CustomBottomNavigation.kt`: Custom Material 3 bottom navigation with gradient effects

#### **Screen Architecture** (`/ui/tab/`)
Each screen follows the same pattern:
- Screen Composable with `hiltViewModel()` injection
- Separate Content Composable for UI logic
- ViewModel with StateFlow-based state management
- UiState and Action data classes for type safety

#### **Theme System** (`/ui/theme/`)
- **Material 3 Implementation**: Complete Material Design 3 theming
- **Dynamic Colors**: Light/dark theme with system detection
- **Custom Components**: Extensive customization for Chinese app design patterns
- **Edge-to-Edge Support**: Proper WindowInsets handling

## Build Configuration

### Version Catalog (`gradle/libs.versions.toml`)
- **Target SDK**: 36, **Min SDK**: 24
- **Kotlin**: 2.0.21 with Compose plugin integration
- **AGP**: 8.12.0
- **Compose BOM**: 2024.09.00
- **Hilt**: 2.56.2 with Navigation Compose: 1.2.0
- **Java Version**: 11

### Key Dependencies
- **UI Framework**: Full Jetpack Compose stack with Material 3
- **Dependency Injection**: Dagger Hilt with Compose integration (`androidx.hilt:hilt-navigation-compose`)
- **Architecture**: AndroidX Lifecycle with ViewModels
- **State Management**: StateFlow with `collectAsStateWithLifecycle()`
- **Testing**: JUnit, AndroidX Test, Espresso, Compose UI testing

### Development Notes
- Uses Gradle Version Catalogs for centralized dependency management
- KSP (Kotlin Symbol Processing) for Hilt annotation processing
- ProGuard configured but disabled for release builds
- Compose compiler integrated via Kotlin plugin (no separate version needed)
- Chinese localization with custom design patterns

## Project Structure

```
app/src/main/java/com/jiyingcao/a51fengliu/compose/
├── App.kt                          # Application class with @HiltAndroidApp
├── MainActivity.kt                 # Single activity with edge-to-edge Compose
└── ui/
    ├── navigation/                 # Custom navigation system
    │   ├── MainNavigation.kt       # Main navigation Scaffold
    │   ├── MainNavigationViewModel.kt # Navigation state management
    │   ├── NavigationModels.kt     # Navigation data models
    │   └── components/
    │       └── CustomBottomNavigation.kt # Custom bottom nav
    ├── tab/                        # Feature screens (home, record, street, merchant, profile)
    │   └── [feature]/
    │       ├── [Feature]Screen.kt  # Screen Composable
    │       └── [Feature]ViewModel.kt # Business logic
    └── theme/                      # Material 3 theme system
        ├── Color.kt               # Color definitions
        ├── Theme.kt               # Main theme Composable
        └── Type.kt                # Typography system
```

## Special Considerations

### Chinese Market App Patterns
- Custom Material 3 color schemes adapted for Chinese preferences
- Tab-based navigation common in Chinese mobile apps
- Custom bottom navigation with gradient effects and Chinese text labels

### Modern Android Development
- **Edge-to-Edge Display**: Full implementation with proper insets handling
- **State Preservation**: Handles configuration changes and process death
- **Activity Lifecycle**: Custom intent handling for re-login scenarios
- **Material 3**: Complete adoption of latest Material Design system