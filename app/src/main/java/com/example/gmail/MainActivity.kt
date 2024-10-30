package com.example.gmail

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gmail.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: EmailViewModel by viewModels()
    private val emailAdapter = EmailAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupToolbar()
        setupRecyclerView()
        setupFab()
        observeEmails()
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        // Handle menu click
        binding.menuIcon.setOnClickListener {
            Toast.makeText(this, "Menu clicked", Toast.LENGTH_SHORT).show()
            // TODO: Implement menu drawer
        }

        // Handle search click
        binding.searchIcon.setOnClickListener {
            Toast.makeText(this, "Search clicked", Toast.LENGTH_SHORT).show()
            // TODO: Implement search functionality
        }
    }

    private fun setupRecyclerView() {
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = emailAdapter
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }

        emailAdapter.onEmailClicked = { email ->
            // Handle email click
            Toast.makeText(this, "Email clicked: ${email.subject}", Toast.LENGTH_SHORT).show()
        }

        emailAdapter.onStarClicked = { email ->
            viewModel.toggleStar(email)
        }
    }

    private fun setupFab() {
        binding.fab.setOnClickListener {
            // Handle compose action
            Toast.makeText(this, "Compose new email", Toast.LENGTH_SHORT).show()
        }
    }

    private fun observeEmails() {
        viewModel.emails.observe(this) { emails ->
            emailAdapter.submitList(emails)
        }
    }
}