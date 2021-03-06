# NewsApplication

This News app is built with data consumed from a public API.
It is designed following Google's recommended Jetpack MVVM architecture.

The network calls are made with Retrofit, and JSON data is parsed using the Moshi converter library.
API requests are handled on the background thread with Kotlin coroutines.

Navigation is based on the latest Navigation library with a single activity and multiple fragments. 
Various News fragments are accessed from the Navigation Drawer.

Other Jetpack components features in this app include:
-Data binding.
-LiveData observation.
-ViewModel persistence.
-RecyclerView.
