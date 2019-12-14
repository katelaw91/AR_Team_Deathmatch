package ai.fritz.camera


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import ai.fritz.poseestimationdemo.R
import android.graphics.Color
import android.support.v4.content.ContextCompat
import android.support.v4.graphics.drawable.DrawableCompat
import android.util.Log
import android.view.MotionEvent
import android.widget.ImageView
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_crosshair.*
import kotlinx.android.synthetic.main.fragment_crosshair.view.*
import kotlinx.android.synthetic.main.fragment_crosshair.view.imageView_Crosshair

/**
 * A simple [Fragment] subclass.
 */
class CrosshairFragment : Fragment() {

    lateinit var mCrosshair : ImageView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        val view: View = inflater!!.inflate(R.layout.fragment_crosshair, container, false)

        mCrosshair = view.findViewById(R.id.imageView_Crosshair)
        mCrosshair.setOnTouchListener {v, m ->
            val pointerCount = m.pointerCount
            val pointerId = m.getPointerId(0)
            mCrosshair.setColorFilter(Color.argb(255, 255, 255, 255))
            Log.d("crosshair imagview", "clicked")
            true
        }
        /*view.imageView_Crosshair.setOnClickListener { view ->
            //Toast.makeText(this, "Hit", Toast.LENGTH_SHORT).show()


        }*/
        return inflater.inflate(R.layout.fragment_crosshair, container, false)
    }


}
