# Immersive Lock

[![](https://jitpack.io/v/babydots/immersivelock.svg)](https://jitpack.io/#babydots/immersivelock)

Android has various ways to full screen an application including locking the screen to prevent
accidentally leaving the app. This is particularly useful when making apps for little people that
you don't want to accidentally do online shopping while you are not watching.

Escaping this sticky mode can be tricky though if you didn't pay attention to the original prompts
from your Android OS, and each system is slightly different.

This library provides a simpler way to enter and leave this mode by tapping a specific `View` several
times in succession.

## Usage

See https://jitpack.io/#babydots/immersivelock/ for adding the Gradle dependency.

```kotlin
    // Create the lock, providing an Android View object that will be set to View.VISIBLE
    // when entering immersive mode. The user will need to tap this several times to leave
    // immersive mode.
    val immersiveLock = ImmersiveLock.Builder(viewUserNeedsToTapToUnlock)

        // Perform actions when leaving immersive load. Note there is no callback for when you
        // *enter* immersive mode, because you are responsible for calling the "startImmersiveMode()"
        // function, and at that point you can perform whatever actions you see fit.
        .onStopImmersiveMode { supportActionBar?.show() }

        // Defaults to ImmersiveLock.DEFAULT_TIME_BETWEEN_TOUCHES (750ms)
        .maxTimeBetweenTouches(750L)

        // Defaults to DEFAULT_TOUCHES_REQUIRED_TO_UNLOCK (5)
        .touchesRequiredToUnlock(5)

        // Defaults to the "screen_unlocked" resource provided with this library, and translated
        // by the wonderful community of translators to the BabyDots android app.
        .unlockedMessageStringRes(R.string.screen_unlocked)

        // Defaults to the "touch_lock_to_unlock" resource provided with this library, and translated
        // by the wonderful community of translators to the BabyDots android app.
        .touchLockToUnlockedMessagePluralRes(R.plurals.touch_lock_to_unlock)

        .build()

    // At some point, enter immersive mode (e.g. in response to tapping a menu item from the ActionBar):
    immersiveLock.startImmersiveMode(this)
    supportActionBar?.hide()
```

## Contributing

### Reporting Issues

Please report any issues or suggest features on the [issue tracker](https://github.com/babydots/babyphone/issues).

### Submitting changes

Pull requests will be warmly received at [https://github.com/babydots/babyphone](https://github.com/babydots/babyphone).

## Compiling

This app uses a typical `gradle` folder structure and is written in Kotlin.

 * To build (a debug version): `gradle assembleDebug`

Alternatively, you can import the project into Android Studio and build from there.

## License

This program is Free Software: You can use, study share and improve it at your will. Specifically you can redistribute and/or modify it under the terms of the [GNU General Public License](https://www.gnu.org/licenses/gpl.html) as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
