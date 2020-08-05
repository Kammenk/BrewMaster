package com.example.brewmaster.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.brewmaster.R
import java.util.*
import kotlin.concurrent.schedule

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()

        Timer().schedule(2000){
            val intent = Intent(this@MainActivity,
                WrapperActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}