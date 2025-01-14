package com.beon.androidchallenge.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.beon.androidchallenge.databinding.SearchHistoryItemViewBinding

class SearchHistoryAdapter(
    private val onItemClick: (String) -> Unit
) : RecyclerView.Adapter<SearchHistoryAdapter.SearchHistoryViewHolder>() {

    private val data = mutableListOf<String>()

    fun update(newItem: String?) {
        if (newItem == null) return

        if (data.size >= 5) {
            data.removeFirst()
        }

        data.add(newItem)
        notifyDataSetChanged()
    }

    class SearchHistoryViewHolder(
        private val binding: SearchHistoryItemViewBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: String, onClick: (String) -> Unit): Unit = with(binding.tvItem) {
            text = item
            setOnClickListener { onClick(item) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchHistoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = SearchHistoryItemViewBinding.inflate(inflater, parent, false)
        return SearchHistoryViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: SearchHistoryViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item, onItemClick)
    }
}