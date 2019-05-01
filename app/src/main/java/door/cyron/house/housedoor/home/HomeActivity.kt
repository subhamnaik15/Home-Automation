package door.cyron.house.housedoor.home

import android.os.Build
import android.os.Bundle
import android.support.annotation.StyleRes
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.TextView
import android.widget.ViewSwitcher
import door.cyron.house.housedoor.R
import door.cyron.house.housedoor.drawer.NavigationDrawerAdapter
import door.cyron.house.housedoor.drawer.NavigationDrawerModel
import door.cyron.house.housedoor.utility.Constant.Companion.HOME_TITLE
import test.cyron.nmschool.nmschool.utility.FragmentHelper
import java.util.*


class HomeActivity : AppCompatActivity() {


    private var adapter: NavigationDrawerAdapter? = null

    private val listItems = ArrayList<NavigationDrawerModel>()
    private val listProfile = ArrayList<NavigationDrawerModel>()
    private var drawer: DrawerLayout? = null
    private var drawerList: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        drawer = findViewById<View>(R.id.drawer_layout) as DrawerLayout
        drawerList = findViewById<View>(R.id.left_drawer) as RecyclerView

        addNavValue()
        setNav(0)

        drawer?.addDrawerListener(object : DrawerLayout.DrawerListener {

            override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
                //Called when a drawer's position changes.
                Log.e("TAG", "-----> $slideOffset")
            }

            override fun onDrawerOpened(drawerView: View) {
                //Called when a drawer has settled in a completely open state.
                //The drawer is interactive at this point.
                // If you have 2 drawers (left and right) you can distinguish
                // them by using id of the drawerView. int id = drawerView.getId();
                // id will be your layout's id: for example R.id.left_drawer
            }

            override fun onDrawerClosed(drawerView: View) {
                // Called when a drawer has settled in a completely closed state.
            }

            override fun onDrawerStateChanged(newState: Int) {
                // Called when the drawer motion state changes. The new state will be one of STATE_IDLE, STATE_DRAGGING or STATE_SETTLING.
            }
        })
        setHome()
    }

    fun setHome() {
        FragmentHelper.clearBackStack(this)
        val fragment = HomeFragment()
        FragmentHelper.replaceFragment(this@HomeActivity, R.id.home_container, fragment, true, HOME_TITLE)

    }

    private fun addNavValue() {

        listItems.clear()
        listProfile.clear()

        val one = NavigationDrawerModel()
        one.name = ""
        one.image = 0
        listItems.add(one)

        val two = NavigationDrawerModel()
        two.name = "drawer 1"
        two.image = R.drawable.green_dot_small
        listItems.add(two)

        val thr = NavigationDrawerModel()
        thr.name = "drawr 2"
        thr.image = R.drawable.green_dot_small
        listItems.add(thr)


        val six = NavigationDrawerModel()
        six.name = "drawer 3"
        six.image = R.drawable.green_dot_small
        listItems.add(six)

        val twoo = NavigationDrawerModel()
        twoo.name = "one"
        twoo.image = R.drawable.p3
        listProfile.add(twoo)

        val thrr = NavigationDrawerModel()
        thrr.name = "two"
        thrr.image = R.drawable.p1
        listProfile.add(thrr)

        val thrrr = NavigationDrawerModel()
        thrrr.name = "three"
        thrrr.image = R.drawable.p2
        listProfile.add(thrrr)


        val thrrr2 = NavigationDrawerModel()
        thrrr2.name = "four"
        thrrr2.image = R.drawable.p2
        listProfile.add(thrrr2)


        val thrrr3 = NavigationDrawerModel()
        thrrr3.name = "five"
        thrrr3.image = R.drawable.p2
        listProfile.add(thrrr3)


    }

    private fun updateNavigationDrawer() {

        drawerList?.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(this)
        drawerList?.layoutManager = layoutManager
        adapter = NavigationDrawerAdapter(this@HomeActivity)
        drawerList?.adapter = adapter
    }

    private fun setNav(pos: Int) {

        var clickedItem = listProfile[pos]
        var zeroPosItem = listProfile[0]

        listProfile.removeAt(0)
        listProfile.add(0, clickedItem)
        listProfile.removeAt(pos)
        listProfile.add(pos, zeroPosItem)//swap clicked item

        updateNavigationDrawer()

        adapter?.setProfileDetails(listProfile)
        adapter?.setDrawerList(listItems)
    }


    private inner class TextViewFactory internal constructor(
        @param:StyleRes @field:StyleRes
        internal val styleId: Int, internal val center: Boolean
    ) : ViewSwitcher.ViewFactory {

        override fun makeView(): View {
            val textView = TextView(this@HomeActivity)

            if (center) {
                textView.gravity = Gravity.CENTER
            }

            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
                textView.setTextAppearance(this@HomeActivity, styleId)
            } else {
                textView.setTextAppearance(styleId)
            }

            return textView
        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish();
    }

    fun drawerClose(pos: Int) {
//        drawer?.closeDrawer(Gravity.LEFT)
        setNav(pos)
    }
}

