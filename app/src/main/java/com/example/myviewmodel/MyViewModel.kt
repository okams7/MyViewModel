package com.example.myviewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MyViewModel: ViewModel() {
    private val _titleInputFlow = MutableStateFlow<String>("")
    val titleInputFlow: StateFlow<String> get() = _titleInputFlow

    fun setTitle(title: String) {
        _titleInputFlow.value = title
    }

    fun resetTitle() {
        _titleInputFlow.value = ""
    }
}