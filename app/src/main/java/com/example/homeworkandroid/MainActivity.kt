package com.example.homeworkandroid

import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.addTextChangedListener
import com.example.homeworkandroid.databinding.ActivityMainBinding
import com.example.homeworkandroid.viewModels.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val viewModel: MainViewModel by viewModel()

    private lateinit var textViewId: TextView
    private lateinit var textViewTimer: TextView
    private lateinit var editText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        textViewId = binding.textViewId
        textViewTimer = binding.textViewTimer
        editText = binding.editText
        observeId()
        observeTimer()
        addAfterTextChanged()
    }

    private fun observeId() {
        viewModel.idLiveData.observe(this) { id ->
            textViewId.text = id
        }
    }

    private fun observeTimer() {
        viewModel.timeLiveData.observe(this) { time ->
            textViewTimer.text = time
        }
    }

    private fun addAfterTextChanged() {
        editText.addTextChangedListener(afterTextChanged = { text ->
            viewModel.changeEditText(text.toString())
        })
    }
}