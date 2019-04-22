package door.cyron.house.housedoor

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AppCompatActivity
import door.cyron.house.housedoor.cardSlider.CardSliderActivity


class HomeActivity : AppCompatActivity() {


    lateinit var floatingActionButton: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        floatingActionButton = findViewById(R.id.btnClick)
        floatingActionButton.setOnClickListener { startActivity(Intent(this@HomeActivity,CardSliderActivity::class.java)) }


    }


    override fun onBackPressed() {
        super.onBackPressed()
        finish();
    }
}

