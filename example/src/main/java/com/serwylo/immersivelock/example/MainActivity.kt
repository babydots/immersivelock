package com.serwylo.immersivelock.example

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
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
            .onStopImmersiveMode { supportActionBar?.show() }
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