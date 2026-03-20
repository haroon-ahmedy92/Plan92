# Plan92

Plan92 is an Android planner app prototype built with Kotlin and Jetpack Compose.  
It recreates a broad set of planner, journal, productivity, calendar, and specialty templates as interactive editable screens instead of static image previews.

## Highlights

- Jetpack Compose + Material 3 UI
- Palette-driven theme based on the Plan92 brand colors
- Reusable planner template engine and schema system
- Editable planner pages with local interactive state
- Template browser, search, create-new flow, home shell, calendar/reminder UI, and settings
- Preview-friendly component structure for future expansion

## Tech Stack

- Kotlin
- Jetpack Compose
- Material 3
- Navigation Compose
- AndroidX Startup
- Gradle Kotlin DSL

## Current Product Scope

The app currently includes:

- Splash and main shell
- Your Planners home
- Create New flow
- Template browser
- Search
- Calendar / reminder screens
- Settings
- Favorites empty state
- Widget promo screen
- Planner detail/editor route

Implemented planner coverage includes:

- Daily planners
- Weekly / monthly / yearly planners
- Journals and reflection pages
- Notes / bullet journal pages
- Shopping / grocery / checklist templates
- Work / project / meeting templates
- Budget and finance templates
- Specialty planners such as study, teacher, medical, travel, family, and event boards

## Project Structure

```text
app/src/main/java/com/example/plan92/
  data/mock/              Mock template data and owned planner state models
  navigation/             App routes and tab definitions
  planner/engine/         Template schema and planner definition engine
  ui/components/          Reusable planner primitives and family-specific sections
  ui/screens/             App screens and flows
  ui/theme/               Color, typography, shapes, and brand palette
  Plan92App.kt            Main app shell and navigation host
  MainActivity.kt         Android entry point
```

## Getting Started

### Requirements

- Android Studio
- Android SDK with platform tools
- JDK 11+
- An emulator or USB-debug-enabled Android device

### Build

```bash
./gradlew assembleDebug
```

### Install on a connected device

```bash
/home/haroon/Android/Sdk/platform-tools/adb install -r app/build/outputs/apk/debug/app-debug.apk
/home/haroon/Android/Sdk/platform-tools/adb shell am start -n com.example.plan92/.MainActivity
```

## Design Notes

- The UI is implemented as Compose layouts, not screenshot embedding.
- Planner pages are intentionally interactive and editable using local state for now.
- Backend, auth, sync, persistence, payments, real reminders, and PDF workflows are still deferred.

## Repository Notes

The repository intentionally excludes local design-reference assets and working docs such as:

- `pictures/`
- `TEMPLATE_IMPLEMENTATION_TRACKER.md`
- local IDE metadata
- machine-specific Android configuration files

Those files are useful during development, but they are not required to build or run the app.
