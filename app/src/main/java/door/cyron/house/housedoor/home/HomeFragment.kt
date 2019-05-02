package door.cyron.house.housedoor.home


import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.graphics.Typeface
import android.os.Build
import android.os.Bundle
import android.support.annotation.StyleRes
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextSwitcher
import android.widget.TextView
import android.widget.ViewSwitcher
import door.cyron.house.housedoor.R
import door.cyron.house.housedoor.home.cardSlider.CardSliderLayoutManager
import door.cyron.house.housedoor.home.cardSlider.CardSnapHelper
import java.util.*


/**
 * A simple [Fragment] subclass.
 *
 */
class HomeFragment : Fragment() {

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

    private var houseModelArrayList: ArrayList<HouseModel>? = null
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


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        setValue()
        initRecyclerView(view)
        initCountryText(view)
        initSwitchers(view)


        return view
    }


    private fun setValue() {
        houseModelArrayList = ArrayList()
        val house1 = HouseModel()
        house1.pic = pics[0]
        house1.descriptions = getString(descriptions[0])
        house1.name = countries[0]
        house1.place = places[0]
        house1.temperature = temperatures[0]
        house1.time = times[0]
        houseModelArrayList!!.add(house1)

        val house2 = HouseModel()
        house2.pic = pics[1]
        house2.descriptions = getString(descriptions[1])
        house2.name = countries[1]
        house2.place = places[1]
        house2.temperature = temperatures[1]
        house2.time = times[1]
        houseModelArrayList!!.add(house2)


        val house3 = HouseModel()
        house3.pic = pics[2]
        house3.descriptions = getString(descriptions[2])
        house3.name = countries[2]
        house3.place = places[2]
        house3.temperature = temperatures[2]
        house3.time = times[2]
        houseModelArrayList!!.add(house3)

        val house4 = HouseModel()
        house4.pic = pics[3]
        house4.descriptions = getString(descriptions[3])
        house4.name = countries[3]
        house4.place = places[3]
        house4.temperature = temperatures[3]
        house4.time = times[3]
        houseModelArrayList!!.add(house4)


        val house5 = HouseModel()
        house5.pic = pics[4]
        house5.descriptions = getString(descriptions[4])
        house5.name = countries[4]
        house5.place = places[4]
        house5.temperature = temperatures[4]
        house5.time = times[4]
        houseModelArrayList!!.add(house5)


        sliderAdapter = SliderAdapter(houseModelArrayList!!, OnCardClickListener())
    }

    private fun initRecyclerView(view: View) {
        recyclerView = view.findViewById(R.id.recycler_view)
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

    private fun initSwitchers(view: View) {
        temperatureSwitcher = view.findViewById<View>(R.id.ts_temperature) as TextSwitcher
        temperatureSwitcher!!.setFactory(TextViewFactory(R.style.TemperatureTextView, true))
        temperatureSwitcher!!.setCurrentText(houseModelArrayList!![0].temperature)

        placeSwitcher = view.findViewById<View>(R.id.ts_place) as TextSwitcher
        placeSwitcher!!.setFactory(TextViewFactory(R.style.PlaceTextView, false))
        placeSwitcher!!.setCurrentText(houseModelArrayList!![0].place)

        clockSwitcher = view.findViewById<View>(R.id.ts_clock) as TextSwitcher
        clockSwitcher!!.setFactory(TextViewFactory(R.style.ClockTextView, false))
        clockSwitcher!!.setCurrentText(houseModelArrayList!![0].time)

        descriptionsSwitcher = view.findViewById<View>(R.id.ts_description) as TextSwitcher
        descriptionsSwitcher!!.setInAnimation(activity, android.R.anim.fade_in)
        descriptionsSwitcher!!.setOutAnimation(activity, android.R.anim.fade_out)
        descriptionsSwitcher!!.setFactory(TextViewFactory(R.style.DescriptionTextView, false))
        descriptionsSwitcher!!.setCurrentText(houseModelArrayList!![0].descriptions)


    }

    private fun initCountryText(view: View) {
        countryAnimDuration = resources.getInteger(R.integer.labels_animation_duration).toLong()
        countryOffset1 = resources.getDimensionPixelSize(R.dimen.left_offset)
        countryOffset2 = resources.getDimensionPixelSize(R.dimen.card_width)
        country1TextView = view.findViewById<View>(R.id.tv_country_1) as TextView
        country2TextView = view.findViewById<View>(R.id.tv_country_2) as TextView

        country1TextView!!.x = countryOffset1.toFloat()
        country2TextView!!.x = countryOffset2.toFloat()
        country1TextView!!.text = houseModelArrayList!![0].name
        country2TextView!!.alpha = 0f

        country1TextView!!.typeface = Typeface.createFromAsset(activity!!.assets, "open-sans-extrabold.ttf")
        country2TextView!!.typeface = Typeface.createFromAsset(activity!!.assets, "open-sans-extrabold.ttf")
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

        setCountryText(houseModelArrayList!!.get(pos % houseModelArrayList!!.size).name!!, left2right)

        temperatureSwitcher!!.setInAnimation(activity, animH[0])
        temperatureSwitcher!!.setOutAnimation(activity, animH[1])
        temperatureSwitcher!!.setText(houseModelArrayList!![pos % houseModelArrayList!!.size].temperature)

        placeSwitcher!!.setInAnimation(activity, animV[0])
        placeSwitcher!!.setOutAnimation(activity, animV[1])
        placeSwitcher!!.setText(houseModelArrayList!![pos % houseModelArrayList!!.size].place)

        clockSwitcher!!.setInAnimation(activity, animV[0])
        clockSwitcher!!.setOutAnimation(activity, animV[1])
        clockSwitcher!!.setText(houseModelArrayList!![pos % houseModelArrayList!!.size].time)

        descriptionsSwitcher!!.setText(houseModelArrayList!![pos % houseModelArrayList!!.size].descriptions)

        currentPosition = pos
    }

    private inner class TextViewFactory internal constructor(
        @param:StyleRes @field:StyleRes
        internal val styleId: Int, internal val center: Boolean
    ) : ViewSwitcher.ViewFactory {

        override fun makeView(): View {
            val textView = TextView(activity)

            if (center) {
                textView.gravity = Gravity.CENTER
            }

            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
                textView.setTextAppearance(activity, styleId)
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
            } else if (clickedPosition > activeCardPosition) {
                recyclerView!!.smoothScrollToPosition(clickedPosition)
                onActiveCardChange(clickedPosition)
            }
        }
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


    }


    override fun onStop() {
        super.onStop()

    }


}
