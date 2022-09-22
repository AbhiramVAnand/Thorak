package com.abhiram.thorak.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.abhiram.thorak.R

class SearchFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        val inflate = inflater.inflate(R.layout.fragment_search, container, false)
        val pref = context?.getSharedPreferences("lists",Context.MODE_PRIVATE)
        val exam : String = pref!!.getString("favList"," ").toString()
        Log.e("favList","$exam")
        return inflate
    }
}