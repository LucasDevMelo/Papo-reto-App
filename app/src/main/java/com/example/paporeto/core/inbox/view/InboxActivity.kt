package com.example.paporeto.core.inbox.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.commit
import com.example.paporeto.R
import com.example.paporeto.core.newmessage.view.NewMessageFragment
import com.example.paporeto.databinding.ActivityInboxBinding
import com.example.paporeto.databinding.FragmentSignUpBinding

class InboxActivity : AppCompatActivity() {

    private lateinit var binding: ActivityInboxBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInboxBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.inbox_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.new_action -> {
                val fragment = NewMessageFragment()
                supportFragmentManager.commit{
                    setCustomAnimations(
                        R.anim.slide_in,
                        R.anim.fade_out,
                        R.anim.fade_in,
                        R.anim.slide_out
                    )
                    replace(R.id.fragment_container_view, fragment)
                    addToBackStack(null)
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }
}