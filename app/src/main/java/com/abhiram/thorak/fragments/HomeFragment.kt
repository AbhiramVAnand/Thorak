package com.abhiram.thorak.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.abhiram.thorak.R

class HomeFragment : Fragment() {
    /*
    * TODO: Create Home fragment - Done
    * TODO: Make the view transparent - Done
    * TODO: Create Clock
    * TODO: Long press
    * TODO: Right and Left swipe functions
    * TODO: Up and Down swipe functions
    */

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

}