package com.abhiram.thorak.fragments

import android.graphics.Typeface
import android.os.Bundle
import android.view.*
import android.widget.TextClock
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.abhiram.thorak.R
import java.lang.reflect.Type

class HomeFragment : Fragment() {
    /*
    * TODO: Create Home fragment - Done
    * TODO: Make the view transparent - Done
    * TODO: Create Clock
    * TODO: Long press
    * TODO: Right and Left swipe functions
    * TODO: Up and Down swipe functions
    */

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        val inflate = inflater.inflate(R.layout.fragment_home, container, false)
        val font : Typeface = Typeface.createFromAsset(context?.assets, "Fonts/NotoSans-Regular.ttf")
        val time : TextClock = inflate.findViewById(R.id.time)
        val date : TextClock = inflate.findViewById(R.id.date)
        time.setTypeface(font)
        date.setTypeface(font)
        return inflate
    }

}