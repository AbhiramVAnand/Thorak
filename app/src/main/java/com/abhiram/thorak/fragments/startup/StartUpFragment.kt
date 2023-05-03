package com.abhiram.thorak.fragments.startup

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.navigation.Navigation
import com.abhiram.thorak.R

class StartUpFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        requireActivity().window.navigationBarColor = resources.getColor(R.color.darkDim)
        requireActivity().window.statusBarColor = resources.getColor(R.color.darkDim)
        return inflater.inflate(R.layout.fragment_start_up, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val start : ImageButton = view.findViewById(R.id.start)

        start.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_startUpFragment_to_customFragment)
        }
    }
}