package com.maf.custom.views.customgradientbutton

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.maf.custom.views.gradient_button.CustomGradientButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<CustomGradientButton>(R.id.custom_button)
        button.onClick = {
            Toast.makeText(this, "Test", Toast.LENGTH_SHORT).show()
        }
    }
}