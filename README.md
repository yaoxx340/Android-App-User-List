# Android-App-User-List

This Android application is developed as part of the Fetch Rewards coding exercise. It demonstrates modern Android development with Jetpack Compose, following best practices and utilizing popular libraries such as Retrofit for networking, Hilt for dependency injection, and Coroutines for asynchronous operations.

## Screenshots
![image]https://my.pcloud.com/#page=filemanager&folder=15523857619)

## Assignment Overview
The application fetches and displays a list of items from a provided JSON endpoint. It showcases the ability to:

Fetch data from a network resource.
Process and display data in a user-friendly manner.
Implement a clean architecture with MVVM pattern.

## Features
- Modern UI: Utilizes Jetpack Compose for building native UIs.
- Architecture: Implements MVVM pattern for clear separation of concerns.
- Efficient Data Handling: Filters out items with empty or null names and groups by listId.
- Sorting: Sorts items first by listId then by name
- Dependency Injection with Hilt
- Networking with Retrofit and Coroutines
- Comprehensive Unit and UI Test

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.


### Installing

A step-by-step series of examples that tell you how to get a development environment running:

1. **Clone the repository:**

```bash
git clone git@github.com:yaoxx340/Android-App-User-List.git
```

2. **Open Android Studio and import the project**

Navigate to `File -> Open` and select the project directory

3. **Run the application**

Select a device or emulator and run the application.

## Running the tests

Explain how to run the automated tests for this system:

### Unit Tests

```bash
./gradlew test
```

### UI Tests

```bash
./gradlew connectedAndroidTest
```

## Built With

- [Jetpack Compose](https://developer.android.com/jetpack/compose) - Modern toolkit for building native UI.
- [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - Manage UI-related data in a lifecycle-conscious way.
- [Hilt](https://dagger.dev/hilt/) - Dependency injection library for Android.
- [Retrofit](https://square.github.io/retrofit/) - Type-safe HTTP client for Android and Java.
- [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html) - Manage background threads with simplified code and reduce needs for callbacks.

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details
