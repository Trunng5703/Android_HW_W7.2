package com.example.gmail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.gmail.Email
import com.example.gmail.databinding.ItemEmailBinding

class EmailAdapter : ListAdapter<Email, EmailAdapter.EmailViewHolder>(EmailDiffCallback()) {

    var onEmailClicked: ((Email) -> Unit)? = null
    var onStarClicked: ((Email) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmailViewHolder {
        val binding = ItemEmailBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return EmailViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EmailViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class EmailViewHolder(
        private val binding: ItemEmailBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onEmailClicked?.invoke(getItem(position))
                }
            }

            binding.starButton.setOnClickListener {
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onStarClicked?.invoke(getItem(position))
                }
            }
        }

        fun bind(email: Email) {
            binding.apply {
                avatarText.text = email.initial
                avatarBackground.setBackgroundColor(email.avatarColor)
                senderName.text = email.senderName
                subject.text = email.subject
                preview.text = email.preview
                timestamp.text = email.formattedTime
                starButton.isSelected = email.isStarred
            }
        }
    }

    private class EmailDiffCallback : DiffUtil.ItemCallback<Email>() {
        override fun areItemsTheSame(oldItem: Email, newItem: Email): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Email, newItem: Email): Boolean {
            return oldItem == newItem
        }
    }
}