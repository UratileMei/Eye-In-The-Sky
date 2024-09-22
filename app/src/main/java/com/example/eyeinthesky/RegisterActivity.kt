package com.example.eyeinthesky
import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val usernameInput = findViewById<EditText>(R.id.usernameInput)
        val passwordInput = findViewById<EditText>(R.id.passwordInput)
        val registerButton = findViewById<Button>(R.id.registerButton)

        registerButton.setOnClickListener {
            val username = usernameInput.text.toString()
            val password = passwordInput.text.toString()

            val sharedPref = getSharedPreferences("UserCredentials", Context.MODE_PRIVATE)
            with(sharedPref.edit()) {
                putString("username", username)
                putString("password", password)
                apply()
            }

            Toast.makeText(this, "Registered Successfully!", Toast.LENGTH_SHORT).show()
            finish()  // Go back to Login screen
        }
    }
}
