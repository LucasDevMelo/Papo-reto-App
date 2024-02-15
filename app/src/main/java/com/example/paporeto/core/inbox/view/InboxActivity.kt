package com.example.paporeto.core.inbox.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.paporeto.R
import com.example.paporeto.databinding.ActivityInboxBinding
import com.example.paporeto.databinding.FragmentSignUpBinding

class InboxActivity : AppCompatActivity() {

    private lateinit var binding: ActivityInboxBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInboxBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbar.title = "Conversas"
        setSupportActionBar(binding.toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.inbox_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.new_action -> {
                //TODO: MENU
            }
        }
        return super.onOptionsItemSelected(item)
    }
}