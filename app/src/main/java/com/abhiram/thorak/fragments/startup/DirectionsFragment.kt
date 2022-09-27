package com.abhiram.thorak.fragments.startup

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
import com.abhiram.thorak.R
import com.abhiram.thorak.fragments.HomeFragment


class DirectionsFragment : Fragment() {

    private lateinit var inflate : View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        inflate = inflater.inflate(R.layout.fragment_directions, container, false)
        requireActivity().window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        requireActivity().window.statusBarColor = resources.getColor(R.color.darkBl)
        requireActivity().window.navigationBarColor = resources.getColor(R.color.darkBl)
        val next : Button = inflate.findViewById(R.id.nextD)
        next.setOnClickListener {
            parentFragmentManager.beginTransaction().setCustomAnimations(R.anim.enter_from_right,R.anim.exit_to_left,R.anim.enter_from_left, R.anim.exit_to_right).replace(R.id.frag_view, FinishStartupFragment()).commit()
        }
        return inflate
    }
}