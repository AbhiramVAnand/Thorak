//package com.abhiram.thorak.adapter
//
//import android.annotation.SuppressLint
//import android.content.Context
//import android.content.pm.PackageManager
//import android.graphics.Typeface
//import android.util.Log
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.*
//import androidx.recyclerview.widget.RecyclerView
//import com.abhiram.thorak.AppDatabase
//import com.abhiram.thorak.AppList
//import com.abhiram.thorak.R
//import com.abhiram.thorak.helpers.SharedPreferenceHelper
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.GlobalScope
//import kotlinx.coroutines.launch
//import kotlin.properties.Delegates
//
//private var appList : MutableList<AppList> = ArrayList()
//private var sharedPreferenceHelper = SharedPreferenceHelper()
//private var height by Delegates.notNull<Int>()
//private var width by Delegates.notNull<Int>()
//
//class FavAdapter(private var mList: List<AppList>,val context: Context,val pm: PackageManager, val appDb: AppDatabase) : RecyclerView.Adapter<FavAdapter.ViewHolder>() {
//    // create new views
//    private lateinit var ItemsViewModel : AppList
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        // inflates the card_view_design view
//        // that is used to hold list item
//        val view = LayoutInflater.from(parent.context)
//            .inflate(R.layout.favcard, parent, false)
//        sharedPreferenceHelper.SharedPreferenceHelperInit(context)
//        height = sharedPreferenceHelper.getIconHeight()
//        width = sharedPreferenceHelper.getIconWidth()
//        return ViewHolder(view)
//    }
//
//    // binds the list items to a view
//    @SuppressLint("ResourceAsColor")
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        ItemsViewModel = mList[position]
//        val font : Typeface = sharedPreferenceHelper.getFont(context)
//        holder.textView.typeface = font
//        holder.textView.setTextSize(sharedPreferenceHelper.getFontSize())
//        if (!ItemsViewModel.hide){
//            holder.imageView.setImageDrawable(pm.getApplicationIcon(ItemsViewModel.pkgName))
//            holder.textView.text = ItemsViewModel.appName
//            if(ItemsViewModel.isFav){
//                holder.check.setImageResource(R.drawable.heartfilled)
//            }else{
//                holder.check.setImageResource(R.drawable.ic_heartemptywhite)
//            }
//            holder.appF.setOnClickListener {
//                var i : Int = position
//                val check : ImageView = holder.check
//                GlobalScope.launch(Dispatchers.IO) {
//                    val appname :String = mList[i].appName
//                    val isfav : Int = appDb.appDao().isfav(appname)
//                    if (isfav==1){
//                        appDb.appDao().removeFav(appname)
//                        check.setImageResource(R.drawable.ic_heartemptywhite)
//                    }else{
//                        appDb.appDao().setFav(appname)
//                        check.setImageResource(R.drawable.heartfilled)
//                    }
//                    appList.clear()
//                    appList.addAll(appDb.appDao().getAll())
//                    mList = appList.toList()
//                }
//                this.notifyDataSetChanged()
//        }
//        }
//    }
//    override fun getItemCount(): Int {
//        return mList.size
//    }
//    // Holds the views for adding it to image and text
//    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView){
//        val imageView: ImageView = itemView.findViewById(R.id.icon)
//        val textView: TextView = itemView.findViewById(R.id.name)
//        val check : ImageView = itemView.findViewById(R.id.check)
//        val appF : LinearLayout = itemView.findViewById(R.id.appFav)
//
//        init {
//            imageView.layoutParams.height = height
//            imageView.layoutParams.width = width
//        }
//    }
//}