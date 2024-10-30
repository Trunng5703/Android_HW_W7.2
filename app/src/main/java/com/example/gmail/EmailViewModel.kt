package com.example.gmail

import android.graphics.Color
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class EmailViewModel : ViewModel() {
    private val _emails = MutableLiveData<List<Email>>()
    val emails: LiveData<List<Email>> = _emails

    init {
        loadEmails()
    }

    private fun loadEmails() {
        viewModelScope.launch {
            _emails.value = createDummyEmails()
        }
    }

    fun toggleStar(email: Email) {
        val currentList = _emails.value?.toMutableList() ?: return
        val index = currentList.indexOfFirst { it.id == email.id }
        if (index != -1) {
            currentList[index] = email.copy(isStarred = !email.isStarred)
            _emails.value = currentList
        }
    }

    private fun createDummyEmails(): List<Email> {
        val colors = listOf(
            Color.parseColor("#4285F4"), // Blue
            Color.parseColor("#DB4437"), // Red
            Color.parseColor("#F4B400"), // Yellow
            Color.parseColor("#0F9D58")  // Green
        )

        return listOf(
            Email(
                id = 1L,
                senderName = "Edurila.com",
                subject = "$19 Only (First 10 spots) - Bestselling",
                preview = "Are you looking to Learn Web Designing?",
                timestamp = System.currentTimeMillis() - 3600000,
                avatarColor = colors[0]
            ),
            Email(
                id = 2L,
                senderName = "Chris Abad",
                subject = "Help make Campaign Monitor better",
                preview = "Let us know your thoughts! No Images...",
                timestamp = System.currentTimeMillis() - 7200000,
                avatarColor = colors[1]
            ),
            // Add more dummy emails as needed
        )
    }
}