package com.example.paporeto.core.authentication.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.commit
import com.example.paporeto.R

interface LoginCallback {
    fun onCreateAcountClicked()
    fun onLoginClicked()
}
class LoginActivity : AppCompatActivity(), LoginCallback {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    override fun onCreateAcountClicked() {
        val fragment = SignUpFragment()
        supportFragmentManager.commit {
            replace(R.id.fragment_container_view, fragment)
            addToBackStack(null)
        }

        // aqui responde o evento de touch
    }

    override fun onLoginClicked() {
        supportFragmentManager.popBackStack()
    }
}