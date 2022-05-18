package com.serwylo.immersivelock.example

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.serwylo.immersivelock.ImmersiveLock
import com.serwylo.immersivelock.example.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var immersiveLock: ImmersiveLock

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        immersiveLock = ImmersiveLock.Builder(binding.unlock)

            // Perform actions when leaving immersive load. Note there is no callback for when you
            // *enter* immersive mode, because you are responsible for calling the "lock()" function,
            // and at that point you can perform whatever actions you see fit.
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
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.lock -> {
                immersiveLock.startImmersiveMode(this)
                supportActionBar?.hide()
            }
        }

        return super.onOptionsItemSelected(item)
    }

}