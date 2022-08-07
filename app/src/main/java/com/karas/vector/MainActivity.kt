package com.karas.vector

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.karas.vector.databinding.ActivityMainBinding
import com.karas.vector.player.presentation.PlayerFragment

class MainActivity : AppCompatActivity() {

    private lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        supportFragmentManager.beginTransaction()
            .add(activityMainBinding.flMainLayout.id, PlayerFragment::class.java, null)
            .commit()
    }
}