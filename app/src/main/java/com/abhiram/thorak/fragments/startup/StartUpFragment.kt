package com.abhiram.thorak.fragments.startup

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.abhiram.thorak.R

class StartUpFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        val inflate = inflater.inflate(R.layout.fragment_start_up, container, false)
        val start : Button = inflate.findViewById(R.id.start)
        start.setOnClickListener {
            parentFragmentManager.beginTransaction().replace(R.id.frag_view, AddFavFragment()).commit()
        }
        return inflate
    }
}