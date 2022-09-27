package com.abhiram.thorak.fragments.startup

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.provider.Settings
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
import android.widget.Toast
import com.abhiram.thorak.R
import com.abhiram.thorak.fragments.HomeFragment

class FinishStartupFragment : Fragment() {

    @SuppressLint("ResourceAsColor")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        val inflate = inflater.inflate(R.layout.fragment_finish_startup, container, false)
        requireActivity().window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        requireActivity().window.statusBarColor = resources.getColor(R.color.darkBl)
        requireActivity().window.navigationBarColor = resources.getColor(R.color.darkBl)
        val finish : Button = inflate.findViewById(R.id.finish)
        val pref = context?.getSharedPreferences("lists",Context.MODE_PRIVATE)
        finish.setOnClickListener {
            pref!!.edit().putBoolean("isFirstRun",false).commit()
            val callHomeSettingIntent = Intent(Settings.ACTION_HOME_SETTINGS)
            showToast("Set Thorak as your default Launcher.")
            startActivity(callHomeSettingIntent)
            parentFragmentManager.beginTransaction().setCustomAnimations(R.anim.enter_from_right,R.anim.exit_to_left,R.anim.enter_from_left, R.anim.exit_to_right).replace(R.id.frag_view,HomeFragment()).commit()
        }
        return inflate
    }
    private fun showToast(message:String){
        Toast.makeText(context,message, Toast.LENGTH_SHORT).show()
    }
}