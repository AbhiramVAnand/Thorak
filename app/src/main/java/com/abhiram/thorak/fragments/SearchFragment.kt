package com.abhiram.thorak.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import com.abhiram.thorak.AppDao
import com.abhiram.thorak.AppDatabase
import com.abhiram.thorak.AppList
import com.abhiram.thorak.R
import com.abhiram.thorak.R.color
import com.abhiram.thorak.adapter.SearchAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SearchFragment : Fragment() {
    private lateinit var appdb : AppDatabase
    private var appList : MutableList<AppList> = ArrayList()
    private lateinit var searchList : ArrayList<AppList>

    @SuppressLint("ResourceAsColor")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        val inflate = inflater.inflate(R.layout.fragment_search, container, false)
        requireActivity().window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        requireActivity().window.statusBarColor = resources.getColor(R.color.darkDim)
        requireActivity().window.navigationBarColor = resources.getColor(R.color.darkDim)
        val autoCompleteTextView : AutoCompleteTextView = inflate.findViewById(R.id.auto)
        appdb = AppDatabase.getDatabse(requireContext())
        searchList = getApps() as ArrayList<AppList>
        for(i in searchList){
            Log.e("Search",i.toString())
        }
        val arrayAdapter : SearchAdapter = SearchAdapter(requireContext(),R.layout.searchitem,searchList)
        autoCompleteTextView.threshold = 1
        autoCompleteTextView.setAdapter(arrayAdapter)
        return inflate
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appdb = AppDatabase.getDatabse(requireContext())
        getApps()
    }

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        appdb = AppDatabase.getDatabse(requireContext())
//        getNames()
//    }
private fun getApps(): List<AppList> {
    GlobalScope.launch{
        appList.clear()
        appList.addAll(appdb.appDao().getAll())
    }
    return appList.toList()
}
}