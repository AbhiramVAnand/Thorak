
package com.abhiram.thorak


import android.content.Context
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.abhiram.thorak.fragments.HomeFragment
import com.abhiram.thorak.fragments.startup.StartUpFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        supportActionBar?.hide()
        if (isFirstRun()){
            supportFragmentManager.beginTransaction().replace(R.id.frag_view, StartUpFragment()).commit()
        }else{
            supportFragmentManager.beginTransaction().replace(R.id.frag_view,HomeFragment()).commit()
        }

    }
    fun isFirstRun(): Boolean{
        val pref = getSharedPreferences("lists",Context.MODE_PRIVATE)
        val isfirstrun : Boolean = pref.getBoolean("isFirstRun",true)
        return isfirstrun
    }

}