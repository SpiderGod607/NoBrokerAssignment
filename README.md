<h1 align="center">NoBroker Assignment</h1>

<p align="center">  
This is an assignment given by NoBroker.
</p>
</br>
<p align="center">
<img src="/screenshots/app_screenshots.png"/>
</p>

## Download
Go to the [Releases](https://github.com/SpiderGod607/NoBrokerAssignment/releases/tag/1.0) to download APK.

<img src="/screenshots/appvideo.gif" align="right" width="32%"/>

## Tech stack & Open-source libraries
- Minimum SDK level 23
- [Hilt](https://dagger.dev/hilt/) for dependency injection.
- [Flow](https://developer.android.com/kotlin/flow) for asynchronous data handeling.
- JetPack
  - Lifecycle - dispose of observing data when lifecycle state changes.
  - ViewModel - UI related data holder, lifecycle aware.
  - Compose - Modern Android UI toolkit.
  - Room - For storing data locally and caching.
- Architecture
  - MVVM Architecture (View - ViewModel - Model)
  - Repository pattern
- [Coil](https://coil-kt.github.io/coil/compose/) - loading images.
- [Swipe Refresh](https://google.github.io/accompanist/swiperefresh/) - for pull to refresh.
- [Retrofit](https://square.github.io/retrofit/) - Http client.
- [Gson](https://github.com/google/gson) - To use JSON in koltin.
</br>
</br>
</br>
</br>
</br>
</br>
</br>
</br>
</br>
</br>

## Architecture
Book Parking is based on MVVM architecture and repository pattern.
![architecture](/screenshots/architecture.png)
