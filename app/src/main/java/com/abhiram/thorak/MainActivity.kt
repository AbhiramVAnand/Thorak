
package com.abhiram.thorak

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.abhiram.thorak.fragments.HomeFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        supportFragmentManager.beginTransaction().replace(R.id.frag_view,HomeFragment()).commit()
    }
}