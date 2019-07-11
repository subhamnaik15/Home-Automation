package door.cyron.house.housedoor.utility

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import door.cyron.house.housedoor.home.HomeActivity

class Util {

    companion object {
        fun rotateImageDropdown(v: Float, v1: Float, view: View) {
            val animation = ObjectAnimator.ofFloat(view, "rotationX", v, v1)
            animation.duration = 150
            animation.repeatCount = 0
            animation.interpolator = AccelerateDecelerateInterpolator()
            animation.start()
        }

        fun startAnimationAccTwo(
            activity: HomeActivity,
            viewTwoDemo: View,
            viewone: CircleImageView,
            viewTwo: CircleImageView,
            pos: Int,
            viewoneHeight: Int,
            viewoneWidth: Int,
            viewTwoX: Float,
            viewoneX: Float
        ) {

            exitAnimationTwo(activity, viewone, viewTwoX - viewoneX * 2, pos, viewTwoDemo)
            val animation = ObjectAnimator.ofFloat(
                viewTwo,
                "translationX", -(viewTwoX - (viewoneX + viewoneX))
            )
            animation.duration = 300

            animation.start()
            animation.addListener(object : Animator.AnimatorListener {
                override fun onAnimationStart(animator: Animator) {

                }

                override fun onAnimationEnd(animator: Animator) {


                    viewTwo.layoutParams.height = viewoneHeight
                    viewTwo.layoutParams.width = viewoneWidth
                    viewTwo.requestLayout()
                }

                override fun onAnimationCancel(animator: Animator) {

                }

                override fun onAnimationRepeat(animator: Animator) {

                }
            })
        }


        fun exitAnimationTwo(activity: HomeActivity, one: View, x: Float, pos: Int, viewTwoDemo: View) {
            val profileInSet = AnimatorSet()
            profileInSet.playTogether(
                ObjectAnimator.ofFloat(one, "alpha", 1f, 0f).setDuration(150),
                ObjectAnimator.ofFloat(one, "scaleX", 1f, 0.5f).setDuration(150),
                ObjectAnimator.ofFloat(one, "scaleY", 1f, 0.5f).setDuration(150)
            )
            profileInSet.start()
            profileInSet.addListener(object : Animator.AnimatorListener {
                override fun onAnimationStart(animator: Animator) {

                }

                override fun onAnimationEnd(animator: Animator) {

                    viewTwoDemo.visibility = View.VISIBLE
                    startanimation(activity, viewTwoDemo, pos)

                }

                override fun onAnimationCancel(animator: Animator) {
                    viewTwoDemo.visibility = View.VISIBLE
                    startanimation(activity, viewTwoDemo, pos)
                }

                override fun onAnimationRepeat(animator: Animator) {

                }
            })

        }

        fun startanimation(activity: HomeActivity, viewDemo: View, pos: Int) {
            viewDemo.visibility = View.VISIBLE
            val profileInSet = AnimatorSet()

            profileInSet.playTogether(
                ObjectAnimator.ofFloat(viewDemo, "alpha", 0f, 1f).setDuration(100),
                ObjectAnimator.ofFloat(viewDemo, "scaleX", 0.5f, 1f).setDuration(100),
                ObjectAnimator.ofFloat(viewDemo, "scaleY", 0.5f, 1f).setDuration(100)
            )
            profileInSet.start()
            profileInSet.addListener(object : Animator.AnimatorListener {
                override fun onAnimationStart(animator: Animator) {

                }

                override fun onAnimationEnd(animator: Animator) {

                    (activity).drawerClose(pos)
                    //                updateFriendList(pos);
                }

                override fun onAnimationCancel(animator: Animator) {
                    //                updateFriendList(pos);
                    (activity).drawerClose(pos)
                }

                override fun onAnimationRepeat(animator: Animator) {

                }
            })
        }


        fun startAnimationAcc(
            activity: HomeActivity,
            viewThreeDemo: View, viewone: CircleImageView, viewTwo: CircleImageView, pos: Int, viewoneHeight: Int
            , viewoneWidth: Int
        ) {

            exitAnimation(activity, viewone, viewTwo.x - viewone.x * 2, pos, viewThreeDemo)
            val animation = ObjectAnimator.ofFloat(
                viewTwo,
                "translationX", -(viewTwo.x - (viewone.x + viewone.x)).toInt() + 0.0f
            )
            animation.setDuration(300)

            animation.start()
            animation.addListener(object : Animator.AnimatorListener {
                override fun onAnimationStart(animator: Animator) {

                }

                override fun onAnimationEnd(animator: Animator) {


                    viewTwo.layoutParams.height = viewoneHeight
                    viewTwo.layoutParams.width = viewoneWidth
                    viewTwo.requestLayout()
                }

                override fun onAnimationCancel(animator: Animator) {

                }

                override fun onAnimationRepeat(animator: Animator) {

                }
            })
        }

        fun exitAnimation(activity: HomeActivity, one: View, x: Float, pos: Int, viewDemo: View) {
            val profileInSet = AnimatorSet()
            profileInSet.playTogether(
                ObjectAnimator.ofFloat(one, "alpha", 1f, 0f).setDuration(200),
                ObjectAnimator.ofFloat(one, "scaleX", 1f, 0.5f).setDuration(200),
                ObjectAnimator.ofFloat(one, "scaleY", 1f, 0.5f).setDuration(200)
            )
            profileInSet.start()
            profileInSet.addListener(object : Animator.AnimatorListener {
                override fun onAnimationStart(animator: Animator) {

                }

                override fun onAnimationEnd(animator: Animator) {

                    viewDemo.visibility = View.VISIBLE
                    startanimation(activity, viewDemo, pos)

                }

                override fun onAnimationCancel(animator: Animator) {
                    viewDemo.visibility = View.VISIBLE
                    startanimation(activity, viewDemo, pos)
                }

                override fun onAnimationRepeat(animator: Animator) {

                }
            })

        }


    }


}
