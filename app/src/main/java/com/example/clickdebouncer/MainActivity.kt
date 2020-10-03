package com.example.clickdebouncer

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.example.clickdebouncer.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var counter = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(
            this, R.layout.activity_main
        )

        binding.activity = this
        binding.coroutineScope = lifecycleScope
        textViewCounter.text = counter.toString()
    }


    @SuppressLint("SetTextI18n")
    fun incrementCounter() {
        textViewCounter.text = (counter++).toString()
    }

}
