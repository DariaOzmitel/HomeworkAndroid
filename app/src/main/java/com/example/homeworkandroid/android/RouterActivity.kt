package com.example.homeworkandroid.android

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.homeworkandroid.R
import com.example.homeworkandroid.databinding.ActivityRouterBinding

class RouterActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityRouterBinding.inflate(layoutInflater)
    }
    lateinit var router: FragmentRouter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        router = FragmentRouter(this, binding.fragmentContainer.id)

    }
}