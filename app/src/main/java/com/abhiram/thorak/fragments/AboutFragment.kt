package com.abhiram.thorak.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.abhiram.thorak.R


class AboutFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        val inflate = inflater.inflate(R.layout.fragment_about, container, false)
        val button : TextView = inflate.findViewById(R.id.textass)
        button.setOnClickListener {
            parentFragmentManager.beginTransaction().replace(R.id.frag_view,EditfavFragment()).addToBackStack("path").commit()
        }
        return inflate
    }
}