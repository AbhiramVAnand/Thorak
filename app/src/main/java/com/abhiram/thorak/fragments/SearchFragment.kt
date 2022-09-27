package com.abhiram.thorak.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.AutoCompleteTextView
import android.widget.EditText
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.abhiram.thorak.AppDatabase
import com.abhiram.thorak.AppList
import com.abhiram.thorak.R
import com.abhiram.thorak.adapter.SearchAdapter
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class SearchFragment : Fragment() {
    private lateinit var appdb : AppDatabase
    private var appList : MutableList<AppList> = ArrayList()
    private lateinit var searchList : ArrayList<AppList>

    @RequiresApi(Build.VERSION_CODES.O)
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
        val fragT : FragmentTransaction = parentFragmentManager.beginTransaction()
        appdb = AppDatabase.getDatabse(requireContext())
        searchList = getApps() as ArrayList<AppList>
        for(i in searchList){
            Log.e("Search",i.toString())
        }
        autoCompleteTextView.showKeyboard()
        val arrayAdapter : SearchAdapter = SearchAdapter(requireContext(),R.layout.searchitem,searchList,fragT)
        autoCompleteTextView.threshold = 1
        autoCompleteTextView.setAdapter(arrayAdapter)
        return inflate
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appdb = AppDatabase.getDatabse(requireContext())
        getApps()
    }

fun AutoCompleteTextView.showKeyboard() {
    post {
        requestFocus()
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
    }
}
private fun getApps(): List<AppList> {
    GlobalScope.launch{
        appList.clear()
        appList.addAll(appdb.appDao().getAll())
    }
    return appList.toList()
}
}