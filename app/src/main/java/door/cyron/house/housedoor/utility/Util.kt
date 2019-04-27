package door.cyron.house.housedoor.utility

import android.animation.Animator
import android.animation.ObjectAnimator
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator

class Util{

    companion object{
         fun rotateImageDropdown(v: Float, v1: Float, view: View) {
            val animation = ObjectAnimator.ofFloat(view, "rotationX", v, v1)
            animation.duration = 150
            animation.repeatCount = 0
            animation.interpolator = AccelerateDecelerateInterpolator()
            animation.start()
        }


    }


}
