package door.cyron.house.housedoor.room

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import door.cyron.house.housedoor.R
import kotlinx.android.synthetic.main.activity_home.*


class RoomActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room)
//        swipeNoState.setOnActiveListener { Toast.makeText(this@RoomActivity, "Active!", Toast.LENGTH_SHORT).show() }

    }
}
