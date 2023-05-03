//package com.abhiram.thorak.adapter
//
//import android.content.Context
//import android.content.Intent
//import android.content.pm.PackageManager
//import android.graphics.Typeface
//import android.view.*
//import android.widget.ImageView
//import android.widget.TextView
//import androidx.fragment.app.FragmentTransaction
//import androidx.recyclerview.widget.RecyclerView
//import com.abhiram.thorak.AppList
//import com.abhiram.thorak.R
//import com.abhiram.thorak.helpers.SharedPreferenceHelper
//import kotlin.properties.Delegates
//
//private lateinit var pkgs : List<AppList>
//private lateinit var cntxt : Context
//private lateinit var fragT : FragmentTransaction
//private lateinit var pkm : PackageManager
//private var height by Delegates.notNull<Int>()
//private var width by Delegates.notNull<Int>()
//private var sharedPreferenceHelper = SharedPreferenceHelper()
//
//class CustomAdapter(private val mList: List<AppList>, val pm : PackageManager, val context : Context,val fragmentTransaction: FragmentTransaction) : RecyclerView.Adapter<CustomAdapter.ViewHolder>(){
//
//    private lateinit var view: View
//
//    // create new views
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        // inflates the card_view_design view
//        // that is used to hold list item
//        view = LayoutInflater.from(parent.context)
//            .inflate(R.layout.cardview, parent, false)
//        pkgs =mList
//        fragT = fragmentTransaction
//        cntxt = context
//        pkm = pm
//        sharedPreferenceHelper.SharedPreferenceHelperInit(context)
//        height = sharedPreferenceHelper.getIconHeight()
//        width = sharedPreferenceHelper.getIconWidth()
//        return ViewHolder(view)
//
//    }
//
//    // binds the list items to a view
//    override fun onBindViewHolder(holder: ViewHolder, position: Int){
//        val ItemsViewModel = mList[position]
//        val font : Typeface = sharedPreferenceHelper.getFont(context)
//        holder.textView.typeface = font
//        holder.textView.setTextSize(sharedPreferenceHelper.getFontSize())
//        if (!ItemsViewModel.hide){
//            holder.imageView.setImageDrawable(pm.getApplicationIcon(ItemsViewModel.pkgName))
//            holder.textView.text = ItemsViewModel.appName
//        }
//    }
//
//    // return the number of the items in the list
//    override fun getItemCount(): Int {
//        return mList.size
//    }
//
//    // Holds the views for adding it to image and text
//    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView), View.OnLongClickListener, View.OnClickListener{
//        val imageView: ImageView = itemView.findViewById(R.id.icon)
//        val textView: TextView = itemView.findViewById(R.id.name)
//        init {
//            imageView.layoutParams.height = height
//            imageView.layoutParams.width = width
//            itemView.setOnClickListener(this)
//            itemView.setOnLongClickListener(this)
//        }
//        override fun onLongClick(p0: View?): Boolean {
//            val i : Int = adapterPosition
//            val fragment = AppInfoFragment.newInstance(pkgs[i].pkgName)
//            fragT.replace(R.id.frag_view, fragment).addToBackStack("path").commit()
//            return false
//        }
//
//        override fun onClick(p0: View?) {
//            val i : Int = adapterPosition
//            val launchIntent : Intent = pkm.getLaunchIntentForPackage(pkgs[i].pkgName)!!
//            cntxt.startActivity(launchIntent)
//        }
//    }
//
//}