package com.example.homeworkandroid.rxJava

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.homeworkandroid.databinding.FragmentPositionBinding
import com.example.homeworkandroid.rxJava.viewModels.SharedViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable

class PositionFragment : Fragment() {
    private var _binding: FragmentPositionBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SharedViewModel by activityViewModels()
    private val disposables = CompositeDisposable()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPositionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.clickSubject
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { position ->
                    Toast.makeText(requireContext(), "Позиция: $position", Toast.LENGTH_SHORT)
                        .show()
                },
                { error ->
                    Log.e("PositionFragment", "Ошибка при подписке: ${error.message}")
                }
            ).let(disposables::add)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        disposables.clear()
        _binding = null
    }
}