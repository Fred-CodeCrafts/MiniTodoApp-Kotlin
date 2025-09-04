package com.fredcodecrafts.minitodoapp


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.fredcodecrafts.minitodoapp.databinding.ActivityMainBinding

/**
 * Single-activity host for Navigation component.
 */
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
