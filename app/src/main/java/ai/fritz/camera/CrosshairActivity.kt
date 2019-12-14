package ai.fritz.camera

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.view.MotionEvent
import android.widget.ImageView

import android.view.View

import ai.fritz.poseestimationdemo.R
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class CustomView(context: Context) : View(context) {
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawColor(Color.GREEN)
    }

    var collision = false

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        event?.apply {
            when(action){
                MotionEvent.ACTION_DOWN -> {
                    Log.d("touch","down at $x  $y")
                    //collision = circle.contains(x,y)
                    if(collision)
                        Log.d("touch","collision at $x $y")
                }
                MotionEvent.ACTION_MOVE -> {
                    Log.d("touch", "move at $x $y")
                }
            }
        }
        return true
    }
}


class CrosshairActivity : AppCompatActivity() {


    lateinit var mCrosshair : ImageView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crosshair)
    }

   // mCrosshair = findViewByID(R.id.crosshair)



}
