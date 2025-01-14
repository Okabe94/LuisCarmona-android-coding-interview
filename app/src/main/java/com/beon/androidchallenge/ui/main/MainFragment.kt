package com.beon.androidchallenge.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.beon.androidchallenge.R
import com.beon.androidchallenge.databinding.MainFragmentBinding

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel
    private lateinit var binding: MainFragmentBinding

    private val searchHistoryAdapter = SearchHistoryAdapter {
        binding.numberEditText.setText(it)
        viewModel.searchNumberFact(it)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MainFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        initViews()
        initObservers()
    }

    private fun initViews() {
        binding.run {

            numberEditText.addTextChangedListener {
                rvSearchHistory.isVisible = it?.isEmpty() == true
            }

            rvSearchHistory.apply {
                adapter = searchHistoryAdapter
                layoutManager = LinearLayoutManager(context)
            }

            numberEditText.setOnEditorActionListener { textView, actionId, keyEvent ->
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    val newNumber = textView.text.toString()
                    searchHistoryAdapter.update(newNumber)
                    viewModel.searchNumberFact(newNumber)
                    return@setOnEditorActionListener true
                } else {
                    return@setOnEditorActionListener false
                }
            }

            retryButton.setOnClickListener {
                val newNumber = numberEditText.text.toString()
                searchHistoryAdapter.update(newNumber)
                viewModel.searchNumberFact(numberEditText.text.toString())
            }

            numberEditText.requestFocus()

            btnClear.setOnClickListener {
                binding.numberEditText.text.clear()
            }
        }
    }

    private fun initObservers() {
        viewModel.currentFact.observeForever {
            if (binding.numberEditText.text.toString().isEmpty()) {
                binding.factTextView.setText(R.string.instructions)
            } else {
                binding.factTextView.text = it?.fact?.text
                binding.tvErrorText.visibility =
                    if (it?.isError == true) View.VISIBLE else View.GONE
            }
        }
    }

}