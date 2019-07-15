package door.cyron.house.housedoor.home.view


import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.graphics.Color
import android.graphics.Typeface
import android.os.Build
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.ViewSwitcher
import androidx.annotation.StyleRes
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.listener.OnChartValueSelectedListener
import door.cyron.house.housedoor.R
import door.cyron.house.housedoor.callbacks.OnItemClickListener
import door.cyron.house.housedoor.databinding.FragmentHomeBinding
import door.cyron.house.housedoor.home.model.HouseModel
import door.cyron.house.housedoor.home.model.SwitchModel
import door.cyron.house.housedoor.home.view.adapter.SliderAdapter
import door.cyron.house.housedoor.home.view.adapter.SwitchAdapter
import door.cyron.house.housedoor.home.view.cardSlider.CardSliderLayoutManager
import door.cyron.house.housedoor.home.view.cardSlider.CardSnapHelper
import door.cyron.house.housedoor.utility.CustomMarkerView
import door.cyron.house.housedoor.utility.MyValueFormatter
import kotlinx.android.synthetic.main.fragment_home.*


/**
 * A simple [Fragment] subclass.
 *
 */
class HomeFragment : Fragment(), OnItemClickListener<SwitchModel> {


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
    private var countryOffset1: Int = 0
    private var countryOffset2: Int = 0
    private var countryAnimDuration: Long = 0
    private var currentPosition: Int = 0


    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        val view = inflater.inflate(R.layout.fragment_home, container, false)
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        setValue()
        initRecyclerView()
        initCountryText()
        initSwitchers()

        openChart()
        openCircleChart()
        return binding.root
    }


    override fun onItemClick(t: SwitchModel, view: View, position: Int) {

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


        sliderAdapter =
            SliderAdapter(houseModelArrayList!!, OnCardClickListener())
    }

    private fun initRecyclerView() {
        binding.recyclerView.adapter = sliderAdapter
        binding.recyclerView.setHasFixedSize(true)

        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    onActiveCardChange()
                }
            }
        })

        layoutManger = binding.recyclerView.layoutManager as CardSliderLayoutManager?

        CardSnapHelper().attachToRecyclerView(binding.recyclerView)
    }

    private fun initSwitchers() {
        binding.tsTemperature.setFactory(TextViewFactory(R.style.TemperatureTextView, true))
        binding.tsTemperature.setCurrentText(houseModelArrayList!![0].temperature)

        binding.tsPlace.setFactory(TextViewFactory(R.style.PlaceTextView, false))
        binding.tsPlace.setCurrentText(houseModelArrayList!![0].place)

        binding.tsClock.setFactory(TextViewFactory(R.style.ClockTextView, false))
        binding.tsClock.setCurrentText(houseModelArrayList!![0].time)

        binding.tsDescription.setInAnimation(activity, android.R.anim.fade_in)
        binding.tsDescription.setOutAnimation(activity, android.R.anim.fade_out)
        binding.tsDescription.setFactory(TextViewFactory(R.style.DescriptionTextView, false))
        binding.tsDescription.setCurrentText(houseModelArrayList!![0].descriptions)


    }

    private fun initCountryText() {
        countryAnimDuration = resources.getInteger(R.integer.labels_animation_duration).toLong()
        countryOffset1 = resources.getDimensionPixelSize(R.dimen.left_offset)
        countryOffset2 = resources.getDimensionPixelSize(R.dimen.card_width)

        binding.tvCountry1.x = countryOffset1.toFloat()
        binding.tvCountry2.x = countryOffset2.toFloat()
        binding.tvCountry1.text = houseModelArrayList!![0].name
        binding.tvCountry2.alpha = 0f

        binding.tvCountry1.typeface = Typeface.createFromAsset(activity!!.assets, "open-sans-extrabold.ttf")
        binding.tvCountry2.typeface = Typeface.createFromAsset(activity!!.assets, "open-sans-extrabold.ttf")
    }

    private fun setCountryText(text: String, left2right: Boolean) {
        val invisibleText: TextView
        val visibleText: TextView
        if (binding.tvCountry1.alpha > binding.tvCountry2.alpha) {
            visibleText = binding.tvCountry1
            invisibleText = binding.tvCountry2
        } else {
            visibleText = binding.tvCountry2
            invisibleText = binding.tvCountry1
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

        binding.tsTemperature.setInAnimation(activity, animH[0])
        binding.tsTemperature.setOutAnimation(activity, animH[1])
        binding.tsTemperature.setText(houseModelArrayList!![pos % houseModelArrayList!!.size].temperature)

        binding.tsPlace.setInAnimation(activity, animV[0])
        binding.tsPlace.setOutAnimation(activity, animV[1])
        binding.tsPlace.setText(houseModelArrayList!![pos % houseModelArrayList!!.size].place)

        binding.tsClock.setInAnimation(activity, animV[0])
        binding.tsClock.setOutAnimation(activity, animV[1])
        binding.tsClock.setText(houseModelArrayList!![pos % houseModelArrayList!!.size].time)

        binding.tsDescription.setText(houseModelArrayList!![pos % houseModelArrayList!!.size].descriptions)

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
            val lm = binding.recyclerView.layoutManager as CardSliderLayoutManager?

            if (lm!!.isSmoothScrolling) {
                return
            }

            val activeCardPosition = lm.activeCardPosition
            if (activeCardPosition == RecyclerView.NO_POSITION) {
                return
            }

            val clickedPosition = binding.recyclerView.getChildAdapterPosition(view)
            if (clickedPosition == activeCardPosition) {
            } else if (clickedPosition > activeCardPosition) {
                binding.recyclerView.smoothScrollToPosition(clickedPosition)
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

    private fun openChart() {
        val entries = java.util.ArrayList<Entry>()
        val ent = java.util.ArrayList<Int>()
        val colors = java.util.ArrayList<Int>()
        ent.add(20)
        ent.add(32)
        ent.add(65)
        ent.add(70)
        ent.add(75)
        for (i in ent.indices) {
            entries.add(Entry(ent[i].toFloat(), i))
        }
        /*entries.add(new Entry(20f, 0));
        entries.add(new Entry(55f, 1));
        entries.add(new Entry(65f, 2));
        entries.add(new Entry(70f, 3));
        entries.add(new Entry(75f, 4));*/


        val dataset = LineDataSet(entries, "")
        val labels = java.util.ArrayList<String>()
        labels.add("a")
        labels.add("b")
        labels.add("c")
        labels.add("d")
        labels.add("e")
        labels.add("")

        for (i in ent.indices) {
            if (ent[i] < 33) {
                colors.add(Color.parseColor("#ff008577"))
                //dataset.setCircleColor(((Color.parseColor("#ff0000"))));
            } else
                colors.add(Color.parseColor("#24a6a4"))

        }

        val data = LineData(labels, dataset)
        //dataset.setColors(ColorTemplate.createColors(new int[]{(Color.parseColor("#24a6a4"))})); //
        // dataset.setDrawCubic(true);
        dataset.setDrawFilled(false)
        dataset.setDrawValues(true)
        dataset.setDrawCircles(true)
        dataset.setDrawCircleHole(false)
        dataset.circleColors = colors
        dataset.valueFormatter = MyValueFormatter() //for hiding decimal value in graph plotting
        dataset.valueTextColor = Color.parseColor("#212121")

        val xAxis = binding.progressReportChart.getXAxis()
        xAxis.setAxisLineWidth(1f)
        xAxis.setAxisLineColor(Color.parseColor("#24a6a4"))
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM)
        xAxis.setAvoidFirstLastClipping(true)


        val leftAxis = binding.progressReportChart.getAxisLeft()
        leftAxis.setAxisLineWidth(1f)
        leftAxis.setAxisLineColor(Color.parseColor("#24a6a4"))
        leftAxis.setStartAtZero(true)
        leftAxis.setAxisMaxValue(100f)

        //        Integer max_val = Collections.max(totalList);

        //        if(max_val<=10)
        //        {
        //            leftAxis.setAxisMaxValue(10);
        //            progressReportChart.getViewPortHandler().setMaximumScaleY(1f);
        //        }
        //
        //        else
        //        {
        //            leftAxis.setAxisMaxValue(max_val+5);
        //            progressReportChart.getViewPortHandler().setMaximumScaleY(2f);
        //        }

        binding.progressReportChart.setDescription("")
        binding.progressReportChart.setDrawGridBackground(false)
        binding.progressReportChart.getAxisRight().setEnabled(false)
        binding.progressReportChart.getXAxis().setDrawGridLines(false)
        binding.progressReportChart.getAxisLeft().setDrawGridLines(false)

        // chartContainer.removeAllViews();
        //lineChart.getViewPortHandler().setMaximumScaleY(0f);
        binding.progressReportChart.setData(data)
        binding.progressReportChart.animateY(1000)
        binding.progressReportChart.getLegend().setEnabled(false)

        binding.progressReportChart.setOnChartValueSelectedListener(object : OnChartValueSelectedListener {
            override fun onValueSelected(e: Entry, dataSetIndex: Int, h: Highlight) {
                val mv = CustomMarkerView(activity, R.layout.marker_layout)
                binding.progressReportChart.setMarkerView(mv)

            }

            override fun onNothingSelected() {

            }
        })
    }

    private fun openCircleChart() {
        binding.staffAttChart.setTouchEnabled(false)
        binding.staffAttChart.setUsePercentValues(true)
        val yvalues = java.util.ArrayList<Entry>()
        yvalues.add(Entry(50f, 0))
        yvalues.add(Entry(33f, 1))
        yvalues.add(Entry(17f, 2))


        val dataSet = PieDataSet(yvalues, "staff Results")
        val colorlist = java.util.ArrayList<Int>()
        colorlist.add(Color.parseColor("#26aa8a"))
        colorlist.add(Color.parseColor("#f19b1f"))
        colorlist.add(Color.parseColor("#cd3147"))

        dataSet.colors = colorlist
        val xVals = java.util.ArrayList<String>()

        xVals.add("")
        xVals.add("")
        xVals.add("")


        val data = PieData(xVals, dataSet)
        //        data.setValueFormatter(new PercentFormatter());
        binding.staffAttChart.data = data
        binding.staffAttChart.isDrawHoleEnabled = true
        binding.staffAttChart.transparentCircleRadius = 30f

        binding.staffAttChart.holeRadius = 45f
        data.setValueTextSize(7f)
        data.setValueTextColor(Color.WHITE)
        binding.staffAttChart.animateY(1000)
        binding.staffAttChart.legend.isEnabled = false
        binding.staffAttChart.setDescription("")
        binding.staffAttChart.setCenterText("%")
        binding.staffAttChart.setCenterTextColor(Color.parseColor("#045187"))
        binding.staffAttChart.invalidate()

    }
}
