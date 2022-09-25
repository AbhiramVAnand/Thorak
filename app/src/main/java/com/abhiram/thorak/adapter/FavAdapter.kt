package com.abhiram.thorak.adapter

import android.content.Context
import android.content.pm.PackageManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.abhiram.thorak.AppDatabase
import com.abhiram.thorak.AppList
import com.abhiram.thorak.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


private lateinit var appDB: AppDatabase
private lateinit var pkgs : List<AppList>

class FavAdapter(private val mList: List<AppList>, val pm: PackageManager, val context: Context, val appDb: AppDatabase) : RecyclerView.Adapter<FavAdapter.ViewHolder>() {
    // create new views
    private lateinit var ItemsViewModel : AppList
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.favcard, parent, false)
        appDB = appDb
        pkgs = mList
        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        ItemsViewModel = mList[position]
        holder.imageView.setImageDrawable(pm.getApplicationIcon(ItemsViewModel.pkgName))
        holder.textView.text = ItemsViewModel.appName
        if(ItemsViewModel.isFav){
            holder.check.setImageResource(R.drawable.ic_heartfilledwhite)
        }else{
            holder.check.setImageResource(R.drawable.ic_heartemptywhite)
        }

    }
    override fun getItemCount(): Int {
        return mList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView), View.OnClickListener {
        val imageView: ImageView = itemView.findViewById(R.id.icon)
        val textView: TextView = itemView.findViewById(R.id.name)
        val check : ImageView = itemView.findViewById(R.id.check)
        init {
            itemView.findViewById<ImageView>(R.id.check).setOnClickListener(this)
        }
        override fun onClick(p0: View?) {
            val i : Int = adapterPosition
            val check : ImageView = itemView.findViewById(R.id.check)
            GlobalScope.launch(Dispatchers.IO) {
                val appname :String = pkgs[i].appName
                val isfav : Int = appDB.appDao().isfav(appname)
                if (isfav==1){
                    appDB.appDao().removeFav(appname)
                    check.setImageResource(R.drawable.ic_heartemptywhite)
                }else{
                    appDB.appDao().setFav(appname)
                    check.setImageResource(R.drawable.ic_heartfilledwhite)
                }
            }
        }
    }
}