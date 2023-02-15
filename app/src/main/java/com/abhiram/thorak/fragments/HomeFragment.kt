package com.abhiram.thorak.fragments


import android.annotation.SuppressLint
import android.app.Notification
import android.app.Service
import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.provider.AlarmClock
import android.provider.Settings
import android.util.Log
import android.view.*
import android.widget.TextClock
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.abhiram.thorak.R
import java.lang.Math.abs


class HomeFragment : Fragment() , View.OnTouchListener, GestureDetector.OnGestureListener{

    private lateinit var mgesturedetector : GestureDetector
    private lateinit var fragmentTransaction : FragmentTransaction
    private lateinit var inflate : View

    @SuppressLint("ResourceAsColor")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        inflate = inflater.inflate(R.layout.fragment_home, container, false)
        requireActivity().window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        requireActivity().window.statusBarColor = resources.getColor(R.color.transparent)
        requireActivity().window.navigationBarColor = resources.getColor(R.color.transparent)
        val font : Typeface = Typeface.createFromAsset(context?.assets, "Fonts/NotoSans-Regular.ttf")
        val time : TextClock = inflate.findViewById(R.id.time)
        val date : TextClock = inflate.findViewById(R.id.date)
        val homeScreen : View? = inflate.findViewById(R.id.homeScreen)
        fragmentTransaction = parentFragmentManager.beginTransaction()
        parentFragmentManager.popBackStack("path",FragmentManager.POP_BACK_STACK_INCLUSIVE)
        if (homeScreen != null) {
            homeScreen.setOnTouchListener(this)
        }
        mgesturedetector = GestureDetector(this)
        time.setTypeface(font)
        date.setTypeface(font)
        time.setOnClickListener {
            val openClockIntent = Intent(AlarmClock.ACTION_SHOW_ALARMS)
            openClockIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            requireContext().startActivity(openClockIntent)
        }
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
            var swipeThreshold = 200
            var swipeVelocityThreshold = 200
            if(com==1){
                if (abs(xdiff) > swipeThreshold && abs(p2) > swipeVelocityThreshold){
                    if(xdiff>0){
//                       Left To Right
                        fragmentTransaction.setCustomAnimations(R.anim.enter_from_left,R.anim.exit_to_right,R.anim.enter_from_right,R.anim.exit_to_left).replace(R.id.frag_view,FavouritesFragment()).addToBackStack("path").commit()
//                        fragmentTransaction.replace(R.id.frag_view,EditfavFragment()).addToBackStack("path").commit()
                    }else{
//                       Right to left
                        fragmentTransaction.setCustomAnimations(R.anim.enter_from_right,R.anim.exit_to_left,R.anim.enter_from_left, R.anim.exit_to_right).replace(R.id.frag_view,TrayFragment()).addToBackStack("path").commit()
                    }
                }
            }
            if(com==-1){
                if (abs(ydiff) > swipeThreshold && abs(p3) > swipeVelocityThreshold){
                    if(ydiff>0){
//                        Up to down
//                        val intent : Intent = Intent(Settings.ACTION_SETTINGS)
//                        requireContext().startActivity(intent)
//                        fragmentTransaction.replace(R.id.frag_view,AboutFragment()).addToBackStack("path").commit()
                    }else{
//                        Down to Up
                        fragmentTransaction.setCustomAnimations(R.anim.to_up,R.anim.go_down,R.anim.to_down,R.anim.go_up).replace(R.id.frag_view,SearchFragment()).addToBackStack("path").commit()
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
