package com.example.homeworkandroid

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homeworkandroid.adapters.CardListAdapter
import com.example.homeworkandroid.databinding.ActivityMainBinding
import com.example.homeworkandroid.viewModels.MainViewModel
import com.example.homeworkandroid.viewModels.SharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val viewModel: MainViewModel by viewModel()
    private val sharedViewModel: SharedViewModel by viewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        observeId()
        observeTimer()
        addAfterTextChanged()
        observeCardItems()
        addFragment()
    }

    private fun observeId() {
        viewModel.idLiveData.observe(this) { id ->
            binding.textViewId.text = id
        }
    }

    private fun observeTimer() {
        viewModel.timeLiveData.observe(this) { time ->
            binding.textViewTimer.text = time
        }
    }

    private fun addFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView, PositionFragment())
            .commit()
    }

    private fun addAfterTextChanged() {
        binding.editText.addTextChangedListener(afterTextChanged = { text ->
            viewModel.changeEditText(text.toString())
        })
    }

    private fun observeCardItems() {
        val recyclerView = binding.recycler
        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = CardListAdapter(sharedViewModel.clickSubject)
        recyclerView.adapter = adapter
        viewModel.cardListLiveData.observe(this) { cardList ->
            adapter.submitList(cardList)
        }
    }


}