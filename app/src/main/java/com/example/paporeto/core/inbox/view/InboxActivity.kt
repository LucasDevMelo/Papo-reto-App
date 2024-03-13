package com.example.paporeto.core.inbox.view

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import android.graphics.Rect
import android.graphics.drawable.BitmapDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import android.view.Menu
import android.view.MenuItem
import androidx.core.content.ContextCompat
import androidx.fragment.app.commit
import com.example.paporeto.R
import com.example.paporeto.core.model.User
import com.example.paporeto.core.newmessage.view.NewMessageFragment
import com.example.paporeto.core.profile.view.ProfileActivity
import com.example.paporeto.databinding.ActivityInboxBinding
import com.example.paporeto.databinding.FragmentSignUpBinding

interface ToolbarCallback {
    fun goToNewMessage()
    fun goToProfile()
}
class InboxActivity : AppCompatActivity(), ToolbarCallback {

    private lateinit var binding: ActivityInboxBinding
    private val user = User.mock
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInboxBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
    }

    override fun goToNewMessage() {
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

    override fun goToProfile() {
        val intent = Intent(this, ProfileActivity::class.java)
        intent.putExtra(ProfileActivity.KEY_USER, user)
        startActivity(intent)
    }
}