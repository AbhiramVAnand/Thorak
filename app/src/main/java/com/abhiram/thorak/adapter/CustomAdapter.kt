package com.abhiram.thorak.adapter

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.Settings
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.abhiram.thorak.AppList
import com.abhiram.thorak.R

lateinit var pkgs : List<AppList>
lateinit var cntxt : Context
class CustomAdapter(private val mList: List<AppList>, val pm : PackageManager, val context : Context) : RecyclerView.Adapter<CustomAdapter.ViewHolder>(){

    private lateinit var view: View



    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        view = LayoutInflater.from(parent.context)
            .inflate(R.layout.cardview, parent, false)
        pkgs=mList
        cntxt = context
        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int){
        val ItemsViewModel = mList[position]
        holder.imageView.setImageDrawable(pm.getApplicationIcon(ItemsViewModel.pkgName))
        holder.textView.text = ItemsViewModel.appName
        holder.holdre.setOnClickListener{
            val launchIntent : Intent = pm.getLaunchIntentForPackage(ItemsViewModel.pkgName)!!
            context.startActivity(launchIntent)
        }
    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView), View.OnLongClickListener{
        val imageView: ImageView = itemView.findViewById(R.id.icon)
        val textView: TextView = itemView.findViewById(R.id.name)
        val holdre : ConstraintLayout = itemView.findViewById(R.id.holdre)
        init {
            itemView.setOnLongClickListener(this)
        }
        override fun onLongClick(p0: View?): Boolean {
            val i : Int = adapterPosition
            val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
            val uri = Uri.fromParts("package", pkgs[i].pkgName, null)
            intent.data = uri
            cntxt.startActivity(intent)
            return false
        }
    }

}