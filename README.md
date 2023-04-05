
# The Movie App

This project is developed using the latest KMM framework. 
Using the REST API of the TMDB, the app displays a collection of movies.


## Features

- Kotlin MultiPlatform Mobile
- Light/dark mode support
- **Popular Screen:** Displays a list of popular movies. User can navigate to the movie details screen by tapping on a movie card. User can also favorite/unfavorite a movie by tapping the star icon on the movie card.
- **Detail Screen:** Shows an overview of a movie and also displays a list of recommended movies.
- **Favorite Screen:** Displays a list of movies that a user has marked as favorite.

## Tech Stack

**Framework:** Kotlin MultiPlatform Mobile (KMM)

**UI Framework:** Jetpack Compose (Android), SwiftUI (IOS)

**Dependency Injection:** Koin

**Network Client:** Ktor

**Local Storage:** SQLDelight

**Concurrency:** Coroutine, Flow, KMP-NativeCoroutines

**Test:** MockK
