package com.abhiram.thorak.fragments.startup

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.abhiram.thorak.R
import com.abhiram.thorak.adapter.FontAdapter
import com.abhiram.thorak.helpers.SharedPreferenceHelper

class ChangeFontFragment : Fragment() {
    private var sharedPreferenceHelper = SharedPreferenceHelper()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        val inflate = inflater.inflate(R.layout.fragment_change_font, container, false)
        val fontRv = inflate.findViewById<RecyclerView>(R.id.fontRV)

        sharedPreferenceHelper = SharedPreferenceHelper()
        sharedPreferenceHelper.SharedPreferenceHelperInit(requireContext())

        val fontArray = resources.getStringArray(R.array.fonts)
        val fontsList = ArrayList<String>()
        for(i in fontArray){
            fontsList.add(i.toString())
        }
        fontsList.sort()
        val adapter = FontAdapter(fontsList,requireContext(),parentFragmentManager)

        fontRv.layoutManager = LinearLayoutManager(requireContext())
        fontRv.adapter = adapter
        return inflate
    }
}