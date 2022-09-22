package com.abhiram.thorak.fragments

import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.abhiram.thorak.R
import com.abhiram.thorak.adapter.AppListView
import com.abhiram.thorak.adapter.CustomAdapter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class FavouritesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        val inflate = inflater.inflate(R.layout.fragment_favourites, container, false)
        val pm : PackageManager = requireContext().packageManager
        var favlist : ArrayList<AppListView> = ArrayList()
        val gson : Gson = Gson()
        val pref = context?.getSharedPreferences("lists",0)
        var json : String = pref?.getString("favList"," ").toString()
        val type: Type = object : TypeToken<ArrayList<AppListView?>?>() {}.type
        favlist = gson.fromJson(json, type)
        for(i in favlist){
            Log.e("Favlist", i.toString())
        }
        val recyclerview: RecyclerView = inflate.findViewById(R.id.recyclerviewfav)
        recyclerview.layoutManager = LinearLayoutManager(context)
        val adapter = context?.let { CustomAdapter(favlist, pm, it) }
        recyclerview.adapter = adapter
        return inflate
    }


}