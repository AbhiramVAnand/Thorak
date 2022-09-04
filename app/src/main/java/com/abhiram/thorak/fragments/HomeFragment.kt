package com.abhiram.thorak.fragments

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.text.Layout
import android.util.Log
import android.view.*
import android.widget.TextClock
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintSet
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.abhiram.thorak.R
import java.lang.Math.abs
import java.lang.reflect.Type
import kotlin.concurrent.fixedRateTimer

class HomeFragment : Fragment() , View.OnTouchListener, GestureDetector.OnGestureListener{
    /*
    * TODO: Create Home fragment - Done
    * TODO: Make the view transparent - Done
    * TODO: Create Clock - Done
    * TODO: Long press
    * TODO: Right and Left swipe functions
    * TODO: Up and Down swipe functions
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
                        fragmentTransaction.replace(R.id.frag_view,AboutFragment()).addToBackStack("path").commit()
                    }else{
//                        Down to Up
                        fragmentTransaction.replace(R.id.frag_view,AboutFragment()).addToBackStack("path").commit()
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