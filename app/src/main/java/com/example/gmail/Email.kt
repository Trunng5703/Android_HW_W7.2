package com.example.gmail

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

data class Email(
    val id: Long,
    val senderName: String,
    val subject: String,
    val preview: String,
    val timestamp: Long,
    val isStarred: Boolean = false,
    val avatarColor: Int
) {
    val initial: String
        get() = senderName.firstOrNull()?.uppercase() ?: ""

    val formattedTime: String
        get() {
            // Simple time formatting for demo
            val sdf = SimpleDateFormat("hh:mm a", Locale.getDefault())
            return sdf.format(Date(timestamp))
        }

}