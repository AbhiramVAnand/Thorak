package com.abhiram.thorak.adapter

import android.content.Context
import android.content.pm.PackageManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.abhiram.thorak.R
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class StartUpAdapter(private val mList: List<AppListView>, val pm : PackageManager, val context : Context) : RecyclerView.Adapter<StartUpAdapter.ViewHolder>() {
    var favlist : ArrayList<AppListView> = ArrayList()
    val gson : Gson = Gson()
    val pref = context.getSharedPreferences("lists",0)
    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.favcard, parent, false)
        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val ItemsViewModel = mList[position]
        holder.imageView.setImageDrawable(pm.getApplicationIcon(ItemsViewModel.pkg))
        holder.textView.text = ItemsViewModel.appName
        if(ItemsViewModel.isFav){
            holder.check.text = "Remove"
        }else{
            holder.check.text = "Add"
        }
        holder.check.setOnClickListener {
            ItemsViewModel.isFav = !ItemsViewModel.isFav
            if(ItemsViewModel.isFav){
                holder.check.text = "Remove"
                favlist.add(AppListView(ItemsViewModel.appName,ItemsViewModel.pkg,true))
                var json : String = gson.toJson(favlist)
                pref.edit().putString("favList",json).commit()
            }else{
                holder.check.text = "Add"
                favlist.remove(AppListView(ItemsViewModel.appName,ItemsViewModel.pkg,true))
                var json : String = gson.toJson(favlist)
                pref.edit().putString("favList",json).commit()
            }
        }
    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val imageView: ImageView = itemView.findViewById(R.id.icon)
        val textView: TextView = itemView.findViewById(R.id.name)
        val check : Button = itemView.findViewById(R.id.check)
    }
}