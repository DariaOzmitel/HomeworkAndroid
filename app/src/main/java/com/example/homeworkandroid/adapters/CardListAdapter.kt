package com.example.homeworkandroid.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.homeworkandroid.databinding.CardItemBinding
import io.reactivex.rxjava3.subjects.PublishSubject

class CardListAdapter(
    private val clickSubject: PublishSubject<Int>
) : ListAdapter<String, CardItemViewHolder>(CardItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardItemViewHolder {
        val binding = CardItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CardItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CardItemViewHolder, position: Int) {
        val cardItem = getItem(position)
        with(holder.binding) {
            tvId.text = cardItem
            root.setOnClickListener {
                clickSubject.onNext(position)
            }
        }
    }
}