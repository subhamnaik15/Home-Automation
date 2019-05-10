package door.cyron.house.housedoor.acount

import android.animation.Animator
import android.animation.Animator.AnimatorListener
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.ActivityCompat
import android.support.v4.app.ActivityOptionsCompat
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import com.airbnb.lottie.LottieAnimationView
import door.cyron.house.housedoor.home.HomeActivity
import door.cyron.house.housedoor.R


class SigninActivity : AppCompatActivity() {

    private lateinit var idTittle: TextView
    private lateinit var etEmail: EditText
    private lateinit var etRePassword: EditText
    private lateinit var tvForgetpass: TextView
    private lateinit var linearLayout: LinearLayout
    private lateinit var linView: LinearLayout
    private lateinit var floatingActionButton: FloatingActionButton

    private val animDuration: Long = 500
    private val animRepeat: Int = 0
    private val transValue: Float = 1000f
    private val transValueStart: Float = 1f
    private val transValueEnd: Float = 0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)
        idTittle = findViewById(R.id.idTittle)
        tvForgetpass = findViewById(R.id.tvForgetpass)
        etEmail = findViewById(R.id.etEmail)
        etRePassword = findViewById(R.id.etRePassword)
        linearLayout = findViewById(R.id.linearLayout)
        linView = findViewById(R.id.linView)
        floatingActionButton = findViewById(R.id.floatingActionButton)

        idTittle.text = "Login"
        tvForgetpass.text = "Forget Password ?"

        tvForgetpass.setOnClickListener() {
            if (idTittle.text.equals("Login"))
                signinGoing() else
                ForgetpassGoing()

        }
        signinComing()

        floatingActionButton.setOnClickListener() {

            val intent = (Intent(this@SigninActivity, HomeActivity::class.java))
//            overridePendingTransition(R.anim.slide_from_right,R.anim.slide_to_left);
            val options =
                ActivityOptionsCompat.makeSceneTransitionAnimation(
                    this,
                    floatingActionButton, // Starting view
                    "demo"    // The String
                )
            //Start the Intent
            ActivityCompat.startActivity(this, intent, options.toBundle())
//            finish()

        }


    }

    private fun signinGoing() {

        etRePassword.visibility = View.GONE
        linView.visibility = View.GONE

        val linearLayoutAnimOut = ObjectAnimator.ofFloat(linearLayout, "translationX", -transValue)
        linearLayoutAnimOut.repeatCount = animRepeat
        linearLayoutAnimOut.duration = animDuration
        val tvRegAnimOut = ObjectAnimator.ofFloat(tvForgetpass, "translationX", transValue)
        tvRegAnimOut.repeatCount = animRepeat
        tvRegAnimOut.duration = animDuration
//        val tvForgetAnimOut = ObjectAnimator.ofFloat(tvForget, "translationX", transValue)
//        tvForgetAnimOut.repeatCount = animRepeat
//        tvForgetAnimOut.duration = animDuration
        val idTitleAnimHide = ObjectAnimator.ofFloat(idTittle, "alpha", transValueStart, transValueEnd)
        idTitleAnimHide.repeatCount = animRepeat
        idTitleAnimHide.duration = animDuration
        floatingActionButton.hide()
        val set = AnimatorSet()
        set.play(tvRegAnimOut).with(idTitleAnimHide).with(linearLayoutAnimOut)
        set.start()

        set.addListener(object : AnimatorListener {

            override fun onAnimationStart(animation: Animator) {
                // ...
            }

            override fun onAnimationRepeat(animation: Animator) {
                // ...
            }

            override fun onAnimationEnd(animation: Animator) {
                ForgetpassComing()
            }

            override fun onAnimationCancel(animation: Animator) {
                // ...
            }
        })

    }

    private fun ForgetpassComing() {

        idTittle.text = "Forget Password"
        tvForgetpass.text = "Login"
        floatingActionButton.show()

        etRePassword.visibility = View.VISIBLE
        linView.visibility = View.VISIBLE

//        val etEmailExpand = ObjectAnimator.ofFloat(etEmail, "translationY", 0f)
//        etEmailExpand.repeatCount = 0
//        etEmailExpand.duration = 1000

        val linearLayoutAnimIn = ObjectAnimator.ofFloat(linearLayout, "translationX", transValueEnd)
        linearLayoutAnimIn.repeatCount = animRepeat
        linearLayoutAnimIn.duration = animDuration
        val tvRegAnimIn = ObjectAnimator.ofFloat(tvForgetpass, "translationX", transValueEnd)
        tvRegAnimIn.repeatCount = animRepeat
        tvRegAnimIn.duration = animDuration
        val idTitleAnimshow = ObjectAnimator.ofFloat(idTittle, "alpha", transValueEnd, transValueStart)
        idTitleAnimshow.repeatCount = animRepeat
        idTitleAnimshow.duration = animDuration
        val set = AnimatorSet()
        set.play(tvRegAnimIn).with(idTitleAnimshow).with(linearLayoutAnimIn)
        set.start()
    }

    private fun ForgetpassGoing() {

        etRePassword.visibility = View.VISIBLE
        linView.visibility = View.VISIBLE

        val linearLayoutAnimOut = ObjectAnimator.ofFloat(linearLayout, "translationX", -transValue)
        linearLayoutAnimOut.repeatCount = animRepeat
        linearLayoutAnimOut.duration = animDuration

        val tvRegAnimOut = ObjectAnimator.ofFloat(tvForgetpass, "translationX", transValue)
        tvRegAnimOut.repeatCount = animRepeat
        tvRegAnimOut.duration = animDuration
//        val tvForgetAnimOut = ObjectAnimator.ofFloat(tvForget, "translationX", transValue)
//        tvForgetAnimOut.repeatCount = animRepeat
//        tvForgetAnimOut.duration = animDuration
        val idTitleAnimHide = ObjectAnimator.ofFloat(idTittle, "alpha", transValueStart, transValueEnd)
        idTitleAnimHide.repeatCount = animRepeat
        idTitleAnimHide.duration = animDuration
        floatingActionButton.hide()
        val set = AnimatorSet()
        set.play(tvRegAnimOut).with(idTitleAnimHide).with(linearLayoutAnimOut)
        set.start()

        set.addListener(object : AnimatorListener {

            override fun onAnimationStart(animation: Animator) {
                // ...
            }

            override fun onAnimationRepeat(animation: Animator) {
                // ...
            }

            override fun onAnimationEnd(animation: Animator) {
                signinComing()

            }

            override fun onAnimationCancel(animation: Animator) {
                // ...
            }
        });
    }

    private fun signinComing() {
        idTittle.text = "Login"
        tvForgetpass.text = "Forget Password ?"
        floatingActionButton.show()

        etRePassword.visibility = View.GONE
        linView.visibility = View.GONE

        val linearLayoutAnimIn = ObjectAnimator.ofFloat(linearLayout, "translationX", transValueEnd)
        linearLayoutAnimIn.repeatCount = animRepeat
        linearLayoutAnimIn.duration = animDuration

        val tvRegAnimIn = ObjectAnimator.ofFloat(tvForgetpass, "translationX", transValueEnd)
        tvRegAnimIn.repeatCount = animRepeat
        tvRegAnimIn.duration = animDuration
//        val tvForgetAnimIn = ObjectAnimator.ofFloat(tvForget, "translationX", transValueEnd)
//        tvForgetAnimIn.repeatCount = animRepeat
//        tvForgetAnimIn.duration = animDuration
        val idTitleAnimshow = ObjectAnimator.ofFloat(idTittle, "alpha", transValueEnd, transValueStart)
        idTitleAnimshow.repeatCount = animRepeat
        idTitleAnimshow.duration = animDuration
        val set = AnimatorSet()
        set.play(tvRegAnimIn).with(idTitleAnimshow).with(linearLayoutAnimIn)
        set.start()
    }

}

