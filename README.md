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

### Add dependency to Gradle

See https://jitpack.io/#babydots/immersivelock/ for adding the Gradle dependency.

### Add a UI element for unlocking

The UI element added will need to be tapped `touchesRequiredToUnlock` times in quick succession
to unlock the screen. It could be a simple `TextView`, or something more complex like this:

```xml
<LinearLayout
    android:id="@+id/unlock_wrapper"
    android:visibility="gone"
    tools:visibility="visible"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    app:layout_constraintEnd_toEndOf="parent"
    app:layout_constraintBottom_toBottomOf="parent"
    android:background="@color/transparent_back"
    android:orientation="horizontal"
    android:padding="8dp"
    android:layout_marginEnd="16dp"
    android:gravity="center"
    android:layout_marginBottom="16dp">

    <ImageView
        android:layout_width="24dp"
        android:layout_height="24dp"
        android:src="@drawable/ic_lock"
        tools:ignore="ContentDescription" />

    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="@string/touch_to_unlock"
        android:textSize="16sp"
        android:layout_marginStart="8dp"
        android:textColor="@android:color/white" />

</LinearLayout>
```

You may find it helpful to use the `@string/touch_to_unlock` string from this library in your
`TextView` to make use of the translations here.

### Configure the lock

Create the lock, providing the Android `View` object from the previous step that will be set
to `View.VISIBLE` when entering immersive mode. The user will need to tap this several times
to leave immersive mode.

```kotlin
val immersiveLock = ImmersiveLock.Builder(viewUserNeedsToTapToUnlock)

    // Perform actions when leaving immersive load. Note there is no callback for when you
    // *enter* immersive mode, because you are responsible for calling the "startImmersiveMode()"
    // function, and at that point you can perform whatever actions you see fit.
    .onStopImmersiveMode {
        // Without this delay, the showing of the action bar trips over the showing of
        // the status bar, and results in it showing behind the status bar. I discovered
        // this while reading through the show() code for the support action bar which
        // had a hard coded delay of 250ms before starting an animation for showing the
        // action bar, specifically due to the fact it wants to wait for the status bar
        // to do its thing first.
        delay(300)
        supportActionBar?.show()
    }

    // Optional: Defaults to ImmersiveLock.DEFAULT_TIME_BETWEEN_TOUCHES (750ms)
    .maxTimeBetweenTouches(750L)

    // Optional: Defaults to ImmersiveLock.DEFAULT_TOUCHES_REQUIRED_TO_UNLOCK (5)
    .touchesRequiredToUnlock(5)

    // Optional: Defaults to the "screen_unlocked" resource provided with this library, and
    // translated by the wonderful community of translators to the BabyDots android app.
    .unlockedMessageStringRes(R.string.screen_unlocked)

    // Optional: Defaults to the "touch_lock_to_unlock" resource provided with this library, and
    // translated by the wonderful community of translators to the BabyDots android app.
    .touchLockToUnlockedMessagePluralRes(R.plurals.touch_lock_to_unlock)

    .build()
```

### Initiate the lock

Likely you will want this to happen from a `Toolbar` which contains a lock icon with
the `@string/lock` title, for example:

```kotlin
override fun onOptionsItemSelected(item: MenuItem): Boolean {
    when (item.itemId) {
        R.id.menu_lock -> {
            immersiveLock.startImmersiveMode(this)
            speedDial.visibility = View.GONE
            toolbar.visibility = View.GONE
        }
    }
    return false
}
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
