package door.cyron.house.housedoor.home

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.graphics.Typeface
import android.os.Build
import android.os.Bundle
import android.support.annotation.StyleRes
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.View
import android.widget.TextSwitcher
import android.widget.TextView
import android.widget.ViewSwitcher
import door.cyron.house.housedoor.R
import door.cyron.house.housedoor.home.cardSlider.CardSliderLayoutManager
import door.cyron.house.housedoor.home.cardSlider.CardSnapHelper
import java.util.*

class HomeActivity : AppCompatActivity() {


    private val pics = intArrayOf(
        R.drawable.p1,
        R.drawable.p2,
        R.drawable.p3,
        R.drawable.p4,
        R.drawable.p5
    )
    private val descriptions =
        intArrayOf(
            R.string.text1,
            R.string.text2,
            R.string.text3,
            R.string.text4,
            R.string.text5
        )
    private val countries = arrayOf("KITCHEN", "HALL", "BATHROOM", "ROOM1", "ROOM2")
    private val places = arrayOf("About", "About", "About", "About", "About")
    private val temperatures = arrayOf("21°C", "19°C", "17°C", "23°C", "20°C")
    private val times = arrayOf(
        "Aug 1 - Dec 15    7:00-18:00",
        "Sep 5 - Nov 10    8:00-16:00",
        "Mar 8 - May 21    7:00-18:00",
        "Aug 1 - Dec 15    7:00-18:00",
        "Sep 5 - Nov 10    8:00-16:00"
    )

    private var houseArrayList: ArrayList<House>? = null
    private var sliderAdapter: SliderAdapter? = null

    private var layoutManger: CardSliderLayoutManager? = null
    private var recyclerView: RecyclerView? = null
    private var temperatureSwitcher: TextSwitcher? = null
    private var placeSwitcher: TextSwitcher? = null
    private var clockSwitcher: TextSwitcher? = null
    private var descriptionsSwitcher: TextSwitcher? = null

    private var country1TextView: TextView? = null
    private var country2TextView: TextView? = null
    private var countryOffset1: Int = 0
    private var countryOffset2: Int = 0
    private var countryAnimDuration: Long = 0
    private var currentPosition: Int = 0
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

        setValue()
        initRecyclerView()
        initCountryText()
        initSwitchers()


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


    }

    private fun updateNavigationDrawer() {

        drawerList?.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(this)
        drawerList?.setLayoutManager(layoutManager)
        adapter = NavigationDrawerAdapter(this@HomeActivity)
        drawerList?.setAdapter(adapter)
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

    private fun setValue() {
        houseArrayList = ArrayList()
        val house1 = House()
        house1.pic = pics[0]
        house1.descriptions = getString(descriptions[0])
        house1.name = countries[0]
        house1.place = places[0]
        house1.temperature = temperatures[0]
        house1.time = times[0]
        houseArrayList!!.add(house1)

        val house2 = House()
        house2.pic = pics[1]
        house2.descriptions = getString(descriptions[1])
        house2.name = countries[1]
        house2.place = places[1]
        house2.temperature = temperatures[1]
        house2.time = times[1]
        houseArrayList!!.add(house2)


        val house3 = House()
        house3.pic = pics[2]
        house3.descriptions = getString(descriptions[2])
        house3.name = countries[2]
        house3.place = places[2]
        house3.temperature = temperatures[2]
        house3.time = times[2]
        houseArrayList!!.add(house3)

        val house4 = House()
        house4.pic = pics[3]
        house4.descriptions = getString(descriptions[3])
        house4.name = countries[3]
        house4.place = places[3]
        house4.temperature = temperatures[3]
        house4.time = times[3]
        houseArrayList!!.add(house4)


        val house5 = House()
        house5.pic = pics[4]
        house5.descriptions = getString(descriptions[4])
        house5.name = countries[4]
        house5.place = places[4]
        house5.temperature = temperatures[4]
        house5.time = times[4]
        houseArrayList!!.add(house5)


        sliderAdapter = SliderAdapter(houseArrayList!!, OnCardClickListener())
    }

    private fun initRecyclerView() {
        recyclerView = findViewById(R.id.recycler_view)
        recyclerView!!.adapter = sliderAdapter
        recyclerView!!.setHasFixedSize(true)

        recyclerView!!.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    onActiveCardChange()
                }
            }
        })

        layoutManger = recyclerView!!.layoutManager as CardSliderLayoutManager?

        CardSnapHelper().attachToRecyclerView(recyclerView)
    }

    private fun initSwitchers() {
        temperatureSwitcher = findViewById<View>(R.id.ts_temperature) as TextSwitcher
        temperatureSwitcher!!.setFactory(TextViewFactory(R.style.TemperatureTextView, true))
        temperatureSwitcher!!.setCurrentText(houseArrayList!![0].temperature)

        placeSwitcher = findViewById<View>(R.id.ts_place) as TextSwitcher
        placeSwitcher!!.setFactory(TextViewFactory(R.style.PlaceTextView, false))
        placeSwitcher!!.setCurrentText(houseArrayList!![0].place)

        clockSwitcher = findViewById<View>(R.id.ts_clock) as TextSwitcher
        clockSwitcher!!.setFactory(TextViewFactory(R.style.ClockTextView, false))
        clockSwitcher!!.setCurrentText(houseArrayList!![0].time)

        descriptionsSwitcher = findViewById<View>(R.id.ts_description) as TextSwitcher
        descriptionsSwitcher!!.setInAnimation(this, android.R.anim.fade_in)
        descriptionsSwitcher!!.setOutAnimation(this, android.R.anim.fade_out)
        descriptionsSwitcher!!.setFactory(TextViewFactory(R.style.DescriptionTextView, false))
        descriptionsSwitcher!!.setCurrentText(houseArrayList!![0].descriptions)


    }

    private fun initCountryText() {
        countryAnimDuration = resources.getInteger(R.integer.labels_animation_duration).toLong()
        countryOffset1 = resources.getDimensionPixelSize(R.dimen.left_offset)
        countryOffset2 = resources.getDimensionPixelSize(R.dimen.card_width)
        country1TextView = findViewById<View>(R.id.tv_country_1) as TextView
        country2TextView = findViewById<View>(R.id.tv_country_2) as TextView

        country1TextView!!.x = countryOffset1.toFloat()
        country2TextView!!.x = countryOffset2.toFloat()
        country1TextView!!.text = houseArrayList!![0].name
        country2TextView!!.alpha = 0f

        country1TextView!!.typeface = Typeface.createFromAsset(assets, "open-sans-extrabold.ttf")
        country2TextView!!.typeface = Typeface.createFromAsset(assets, "open-sans-extrabold.ttf")
    }

    private fun setCountryText(text: String, left2right: Boolean) {
        val invisibleText: TextView
        val visibleText: TextView
        if (country1TextView!!.alpha > country2TextView!!.alpha) {
            visibleText = country1TextView as TextView
            invisibleText = country2TextView as TextView
        } else {
            visibleText = country2TextView as TextView
            invisibleText = country1TextView as TextView
        }

        val vOffset: Int
        if (left2right) {
            invisibleText.x = 0f
            vOffset = countryOffset2
        } else {
            invisibleText.x = countryOffset2.toFloat()
            vOffset = 0
        }

        invisibleText.text = text

        val iAlpha = ObjectAnimator.ofFloat(invisibleText, "alpha", 1f)
        val vAlpha = ObjectAnimator.ofFloat(visibleText, "alpha", 0f)
        val iX = ObjectAnimator.ofFloat(invisibleText, "x", countryOffset1 + 0f)
        val vX = ObjectAnimator.ofFloat(visibleText, "x", vOffset + 0f)

        val animSet = AnimatorSet()
        animSet.playTogether(iAlpha, vAlpha, iX, vX)
        animSet.duration = countryAnimDuration
        animSet.start()
    }

    private fun onActiveCardChange() {
        val pos = layoutManger!!.activeCardPosition
        if (pos == RecyclerView.NO_POSITION || pos == currentPosition) {
            return
        }

        onActiveCardChange(pos)
    }

    private fun onActiveCardChange(pos: Int) {
        val animH = intArrayOf(
            R.anim.slide_in_right,
            R.anim.slide_out_left
        )
        val animV = intArrayOf(
            R.anim.slide_in_top,
            R.anim.slide_out_bottom
        )

        val left2right = pos < currentPosition
        if (left2right) {
            animH[0] = R.anim.slide_in_left
            animH[1] = R.anim.slide_out_right

            animV[0] = R.anim.slide_in_bottom
            animV[1] = R.anim.slide_out_top
        }

        setCountryText(houseArrayList!!.get(pos % houseArrayList!!.size).name!!, left2right)

        temperatureSwitcher!!.setInAnimation(this@HomeActivity, animH[0])
        temperatureSwitcher!!.setOutAnimation(this@HomeActivity, animH[1])
        temperatureSwitcher!!.setText(houseArrayList!![pos % houseArrayList!!.size].temperature)

        placeSwitcher!!.setInAnimation(this@HomeActivity, animV[0])
        placeSwitcher!!.setOutAnimation(this@HomeActivity, animV[1])
        placeSwitcher!!.setText(houseArrayList!![pos % houseArrayList!!.size].place)

        clockSwitcher!!.setInAnimation(this@HomeActivity, animV[0])
        clockSwitcher!!.setOutAnimation(this@HomeActivity, animV[1])
        clockSwitcher!!.setText(houseArrayList!![pos % houseArrayList!!.size].time)

        descriptionsSwitcher!!.setText(houseArrayList!![pos % houseArrayList!!.size].descriptions)

        currentPosition = pos
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

    private inner class OnCardClickListener : View.OnClickListener {
        override fun onClick(view: View) {
            val lm = recyclerView!!.layoutManager as CardSliderLayoutManager?

            if (lm!!.isSmoothScrolling) {
                return
            }

            val activeCardPosition = lm.activeCardPosition
            if (activeCardPosition == RecyclerView.NO_POSITION) {
                return
            }

            val clickedPosition = recyclerView!!.getChildAdapterPosition(view)
            if (clickedPosition == activeCardPosition) {
                //                final Intent intent = new Intent(HomeActivity.this, DetailsActivity.class);
                //                intent.putExtra(DetailsActivity.BUNDLE_IMAGE_ID, pics[activeCardPosition % pics.length]);
                //
                //                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                //                    startActivity(intent);
                //                } else {
                //                    final CardView cardView = (CardView) view;
                //                    final View sharedView = cardView.getChildAt(cardView.getChildCount() - 1);
                //                    final ActivityOptions options = ActivityOptions
                //                            .makeSceneTransitionAnimation(HomeActivity.this, sharedView, "shared");
                //                    startActivity(intent, options.toBundle());
                //                }
            } else if (clickedPosition > activeCardPosition) {
                recyclerView!!.smoothScrollToPosition(clickedPosition)
                onActiveCardChange(clickedPosition)
            }
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

