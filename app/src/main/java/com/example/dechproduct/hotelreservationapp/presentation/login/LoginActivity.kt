package com.example.dechproduct.hotelreservationapp.presentation.login

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.example.dechproduct.hotelreservationapp.R
import com.example.dechproduct.hotelreservationapp.databinding.ActivityLoginBinding
import com.example.dechproduct.hotelreservationapp.presentation.menu.MenuActivity
import com.example.dechproduct.hotelreservationapp.util.Constants
import com.example.dechproduct.hotelreservationapp.util.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val loginViewModel: LoginViewModel by viewModels()

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(
            this, R.layout.activity_login
        )

        binding.btSignIn.setOnClickListener {
            val userInput = findViewById<EditText>(R.id.editText)
            val pwdInput = findViewById<EditText>(R.id.editText2)

            var username: String = userInput.text.toString()
            var password: String = pwdInput.text.toString()

            if (username.isNotEmpty() and password.isNotEmpty()) {
                lifecycleScope.launch {
                    loginViewModel.loginUser(username, password)
                }
            } else {
                Toast.makeText(applicationContext, "Insufficient Information.", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        binding.tvSignIn.setOnClickListener {
            val userInput = findViewById<EditText>(R.id.editText)
            val pwdInput = findViewById<EditText>(R.id.editText2)

            var username: String = userInput.text.toString()
            var password: String = pwdInput.text.toString()

            if (username.isNotEmpty() and password.isNotEmpty()) {
                lifecycleScope.launch {
                    loginViewModel.loginUser(username, password)
                }
            } else {
                Toast.makeText(applicationContext, "Insufficient Information.", Toast.LENGTH_SHORT)
                    .show()
            }
            //val intent = Intent(this, MenuActivity::class.java)
            //startActivity(intent)
        }
        observeLogin()
    }

    override fun onBackPressed() {
        moveTaskToBack(true);
    }

    private fun observeLogin() {
        loginViewModel.activeUser.observe(this, {
            when (it) {
                is Resource.Success -> {
                    it.data?.let { user ->

                        sharedPreferences.edit()
                            .putString(
                                Constants.SHARED_PREF_SESSION,
                                user.employee.firstName + " " + user.employee.lastName
                            ).apply()

                        val intent = Intent(this@LoginActivity, MenuActivity::class.java)
//                        intent.putExtra(Constants.INTENT_EXP_USER, user.employee)

                        Toast.makeText(
                            applicationContext,
                            "Welcome! " + ": " + user.employee.firstName + " " + user.employee.lastName,
                            Toast.LENGTH_SHORT
                        ).show()

                        startActivity(intent)
                        //finish()
                    }
                }

                is Resource.Failure -> {
                    Toast.makeText(applicationContext, it.throwable.message, Toast.LENGTH_SHORT)
                        .show()
                }
            }
        })
    }
}