package com.abhiram.thorak.fragments.startup

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.abhiram.thorak.R
import com.abhiram.thorak.fragments.HomeFragment

class FinishStartupFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        val inflate = inflater.inflate(R.layout.fragment_finish_startup, container, false)
        val finish : Button = inflate.findViewById(R.id.finish)
        val pref = context?.getSharedPreferences("lists",Context.MODE_PRIVATE)
        finish.setOnClickListener {
            pref!!.edit().putBoolean("isFirstRun",false).commit()
            parentFragmentManager.beginTransaction().replace(R.id.frag_view,HomeFragment()).commit()
        }
        return inflate
    }
}