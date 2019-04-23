package door.cyron.house.housedoor.home


import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.app.Activity
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import door.cyron.house.housedoor.R
import door.cyron.house.housedoor.utility.CircleImageView
import java.util.*

class NavigationDrawerAdapter(private val activity: HomeActivity) :
    RecyclerView.Adapter<NavigationDrawerAdapter.ViewHolder>() {
    private val listItems = ArrayList<NavigationDrawerModel>()
    private val drawerList: ArrayList<NavigationDrawerModel>
    private val profileDetails: ArrayList<NavigationDrawerModel>
    private var pos = 99

    private var viewoneHeight: Int = 0
    private var viewoneWidth: Int = 0
    private var viewoneX: Float = 0.toFloat()
    private var viewTwoX: Float = 0.toFloat()

    init {
        drawerList = ArrayList()
        profileDetails = ArrayList()
    }


    class ViewHolder(itemView: View, ViewType: Int) : RecyclerView.ViewHolder(itemView) {

        internal lateinit var img_profile: CircleImageView
        internal lateinit var imageViewTwoDemo: CircleImageView
        internal lateinit var imageViewTwo: CircleImageView
        internal lateinit var imageViewThree: CircleImageView
        internal lateinit var imageViewThreeDemo: CircleImageView
        internal lateinit var txtItemName: TextView
        internal lateinit var txtName: TextView
        internal lateinit var txtClass: TextView
        internal lateinit var imgNavIcon: ImageView
        internal lateinit var nav_list_row: RelativeLayout

        init {
            if (ViewType == VIEW_TYPE_FIRST) {
                img_profile = itemView.findViewById(R.id.img_profile)
                imageViewTwo = itemView.findViewById(R.id.imageViewTwo)
                imageViewTwoDemo = itemView.findViewById(R.id.imageViewTwoDemo)
                imageViewThree = itemView.findViewById(R.id.imageViewThree)
                imageViewThreeDemo = itemView.findViewById(R.id.imageViewThreeDemo)
                txtName = itemView.findViewById(R.id.txtName)
                txtClass = itemView.findViewById(R.id.txtClass)
            } else {
                txtItemName = itemView.findViewById<View>(R.id.listTitle) as TextView
                imgNavIcon = itemView.findViewById<View>(R.id.imgNavIcon) as ImageView
                nav_list_row = itemView.findViewById<View>(R.id.nav_list_row) as RelativeLayout
            }
        }

    }

    override fun onBindViewHolder(holder: ViewHolder, posItem: Int) {
        if (posItem == VIEW_TYPE_FIRST) {
            val prof1 = BitmapFactory.decodeResource(activity.resources, profileDetails[0].image)
            val prof2 = BitmapFactory.decodeResource(activity.resources, profileDetails[1].image)
            val prof3 = BitmapFactory.decodeResource(activity.resources, profileDetails[2].image)

            holder.img_profile.setImageBitmap(prof1)
            holder.imageViewTwo.setImageBitmap(prof2)
            holder.imageViewTwoDemo.setImageBitmap(prof1)

            holder.imageViewThree.setImageBitmap(prof3)
            holder.imageViewThreeDemo.setImageBitmap(prof1)

            holder.imageViewTwoDemo.visibility = View.INVISIBLE
            holder.imageViewThreeDemo.visibility = View.INVISIBLE
            //set default profile name
            holder.txtName.text = profileDetails[0].name

            holder.imageViewTwo.setOnClickListener {
                viewoneHeight = holder.img_profile.height
                viewoneWidth = holder.img_profile.width

                viewoneX = holder.img_profile.x
                viewTwoX = holder.imageViewTwo.x

                holder.txtName.text = profileDetails[1].name
                holder.txtName.visibility = View.VISIBLE
                val profileInSet = AnimatorSet()

                profileInSet.playTogether(
                    ObjectAnimator.ofFloat(holder.txtName, "alpha", 0f, 1f).setDuration(150),
                    ObjectAnimator.ofFloat(holder.txtName, "scaleX", 0.5f, 1f).setDuration(150),
                    ObjectAnimator.ofFloat(holder.txtName, "scaleY", 0.5f, 1f).setDuration(150)
                )
                profileInSet.start()
                startAnimationAccTwo(holder.imageViewTwoDemo, holder.img_profile, holder.imageViewTwo, 1)
            }
            holder.imageViewThree.setOnClickListener {
                viewoneHeight = holder.img_profile.measuredHeight
                viewoneWidth = holder.img_profile.measuredWidth

                viewoneX = holder.img_profile.x
                viewTwoX = holder.imageViewTwo.x
                holder.txtName.text = profileDetails[2].name
                holder.txtName.visibility = View.VISIBLE
                val profileInSet = AnimatorSet()

                profileInSet.playTogether(
                    ObjectAnimator.ofFloat(holder.txtName, "alpha", 0f, 1f).setDuration(150),
                    ObjectAnimator.ofFloat(holder.txtName, "scaleX", 0.5f, 1f).setDuration(150),
                    ObjectAnimator.ofFloat(holder.txtName, "scaleY", 0.5f, 1f).setDuration(150)
                )
                profileInSet.start()
                startAnimationAcc(holder.imageViewThreeDemo, holder.img_profile, holder.imageViewThree, 2)
            }

        } else {

            if (posItem == pos) {
                holder.nav_list_row.setBackgroundResource(R.color.black_transparent)
            } else {
                holder.nav_list_row.setBackgroundColor(Color.parseColor("#ffffff"))
            }
            holder.imgNavIcon.setImageResource(listItems[posItem].image)

            holder.nav_list_row.tag = listItems[posItem].name

            holder.txtItemName.text = listItems[posItem].name
            holder.nav_list_row.setOnClickListener {
                pos = posItem
                notifyDataSetChanged()
            }
        }
    }

    fun refreshList() {
        pos = 99
        notifyDataSetChanged()
    }


    private fun startAnimationAccTwo(viewTwoDemo: View, viewone: CircleImageView, viewTwo: CircleImageView, pos: Int) {

        exitAnimationTwo(viewone, viewTwoX - viewoneX * 2, pos, viewTwoDemo)
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

    private fun startAnimationAcc(viewThreeDemo: View, viewone: CircleImageView, viewTwo: CircleImageView, pos: Int) {

        exitAnimation(viewone, viewTwo.x - viewone.x * 2, pos, viewThreeDemo)
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


    private fun getItem(str: String) {

        var i = 0
        while (i < listItems.size) {

            if (str.equals(listItems[i].name!!, ignoreCase = true)) {
                pos = i
                i = listItems.size
            }
            i++

        }
        notifyItemChanged(pos)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        if (viewType == VIEW_TYPE_FIRST) {
            val v = LayoutInflater.from(parent.context)
                .inflate(R.layout.nav_header_main, parent, false) //Inflating the layout
            return ViewHolder(v, viewType)
        } else {
            val v =
                LayoutInflater.from(parent.context).inflate(R.layout.drawer_items, parent, false) //Inflating the layout
            return ViewHolder(v, viewType)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (isPositionHeader(position)) VIEW_TYPE_FIRST else 1
    }

    private fun isPositionHeader(position: Int): Boolean {
        return position == 0
    }


    override fun getItemId(position: Int): Long {
        return position.toLong()
    }


    override fun getItemCount(): Int {
        return listItems.size
    }

    fun setDrawerList(drawerList: ArrayList<NavigationDrawerModel>) {
        if (this.drawerList.size > 0)
            this.drawerList.clear()

        this.drawerList.addAll(drawerList)

        if (listItems.size > 0)
            listItems.clear()
        listItems.addAll(drawerList)
        notifyDataSetChanged()
    }

    fun setProfileDetails(profileDetails: ArrayList<NavigationDrawerModel>) {

        if (this.profileDetails.size > 0)
            this.profileDetails.clear()

        this.profileDetails.addAll(profileDetails)

    }


    private fun startanimation(viewDemo: View, pos: Int) {
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

                (activity as HomeActivity).drawerClose(pos)
                //                updateFriendList(pos);
            }

            override fun onAnimationCancel(animator: Animator) {
                //                updateFriendList(pos);
                (activity as HomeActivity).drawerClose(pos)
            }

            override fun onAnimationRepeat(animator: Animator) {

            }
        })
    }

    private fun exitAnimation(one: View, x: Float, pos: Int, viewDemo: View) {
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
                startanimation(viewDemo, pos)

            }

            override fun onAnimationCancel(animator: Animator) {
                viewDemo.visibility = View.VISIBLE
                startanimation(viewDemo, pos)
            }

            override fun onAnimationRepeat(animator: Animator) {

            }
        })

    }

    private fun exitAnimationTwo(one: View, x: Float, pos: Int, viewTwoDemo: View) {
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
                startanimation(viewTwoDemo, pos)

            }

            override fun onAnimationCancel(animator: Animator) {
                viewTwoDemo.visibility = View.VISIBLE
                startanimation(viewTwoDemo, pos)
            }

            override fun onAnimationRepeat(animator: Animator) {

            }
        })

    }

    companion object {
        private val VIEW_TYPE_FIRST = 0
    }
}