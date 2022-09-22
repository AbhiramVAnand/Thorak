package com.abhiram.thorak.fragments


import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.TextClock
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.abhiram.thorak.R
import java.lang.Math.abs

class HomeFragment : Fragment() , View.OnTouchListener, GestureDetector.OnGestureListener{
    /*
    * TODO: Create Home fragment - Done
    * TODO: Make the view transparent - Done
    * TODO: Create Clock - Done
    * TODO: Long press - Done
    * TODO: Right and Left swipe functions - Done
    * TODO: Up and Down swipe functions - Done
    */

    private lateinit var mgesturedetector : GestureDetector
    private lateinit var fragmentTransaction : FragmentTransaction

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        val inflate = inflater.inflate(R.layout.fragment_home, container, false)
        val font : Typeface = Typeface.createFromAsset(context?.assets, "Fonts/NotoSans-Regular.ttf")
        val time : TextClock = inflate.findViewById(R.id.time)
        val date : TextClock = inflate.findViewById(R.id.date)
        val homeScreen : View? = inflate.findViewById(R.id.homeScreen)
        fragmentTransaction = parentFragmentManager.beginTransaction()
        if (homeScreen != null) {
            homeScreen.setOnTouchListener(this)
        }
        mgesturedetector = GestureDetector(this)
        time.setTypeface(font)
        date.setTypeface(font)
        return inflate
    }


    override fun onTouch(p0: View?, p1: MotionEvent?): Boolean {
        mgesturedetector.onTouchEvent(p1)
        return true
    }

    override fun onDown(p0: MotionEvent?): Boolean {
        return false
    }

    override fun onShowPress(p0: MotionEvent?) {
    }

    override fun onSingleTapUp(p0: MotionEvent?): Boolean {
        return false
    }

    override fun onScroll(p0: MotionEvent?, p1: MotionEvent?, p2: Float, p3: Float): Boolean {
        return false
    }

    override fun onLongPress(p0: MotionEvent?) {
        Log.e("onLongpress","true")
        fragmentTransaction.replace(R.id.frag_view,AboutFragment()).addToBackStack("path").commit()
    }

    override fun onFling(e1: MotionEvent?, e2: MotionEvent?, p2: Float, p3: Float): Boolean {
        try {
            val xdiff = e2?.x!! - e1?.x!!
            val ydiff = e2?.y!! - e1?.y!!
            val com : Int = compareValues(abs(xdiff),abs(ydiff))
            var swipeThreshold = 250
            var swipeVelocityThreshold = 250
            if(com==1){
                if (abs(xdiff) > swipeThreshold && abs(p2) > swipeVelocityThreshold){
                    if(xdiff>0){
//                       Left To Right
                        fragmentTransaction.replace(R.id.frag_view,FavouritesFragment()).addToBackStack("path").commit()
//                        fragmentTransaction.replace(R.id.frag_view,EditfavFragment()).addToBackStack("path").commit()
                    }else{
//                       Right to left
                        fragmentTransaction.replace(R.id.frag_view,TrayFragment()).addToBackStack("path").commit()
                    }
                }
            }
            if(com==-1){
                if (abs(ydiff) > swipeThreshold && abs(p3) > swipeVelocityThreshold){
                    if(ydiff>0){
//                        Up to down
//                        val intent : Intent = Intent(android.provider.Settings.ACTION_QUICK_ACCESS_WALLET_SETTINGS)
//                        requireContext().startActivity(intent)
//                        fragmentTransaction.replace(R.id.frag_view,AboutFragment()).addToBackStack("path").commit()
                    }else{
//                        Down to Up
                        fragmentTransaction.replace(R.id.frag_view,SearchFragment()).addToBackStack("path").commit()
                    }
                }
            }
        }
        catch (exception: Exception) {
            exception.printStackTrace()
        }
        return false
    }

}