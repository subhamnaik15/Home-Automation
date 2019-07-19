package door.cyron.house.housedoor.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import door.cyron.house.housedoor.R
import door.cyron.house.housedoor.home.view.HomeFragment
import door.cyron.house.housedoor.utility.Constant.Companion.HOME_TITLE
import test.cyron.nmschool.nmschool.utility.FragmentHelper

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setHome()
    }

    fun setHome() {
        FragmentHelper.clearBackStack(this)
        val fragment = HomeFragment()
        FragmentHelper.replaceFragment(this@MainActivity, R.id.home_container, fragment, true, HOME_TITLE)

    }
}
