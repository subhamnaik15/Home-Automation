package door.cyron.house.housedoor.home.view.adapter

import android.content.Context
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v4.app.FragmentActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import door.cyron.house.housedoor.BR
import door.cyron.house.housedoor.R
import door.cyron.house.housedoor.databinding.ListItemNotificationBinding
import door.cyron.house.housedoor.home.model.SwitchModel
import door.cyron.house.housedoor.home.view.HomeFragment
import door.cyron.house.housedoor.home.viewmodel.SwitchViewModel
import door.cyron.house.housedoor.utility.RecyclerViewHolder

class SwitchAdapter(var activity: FragmentActivity?, val listener: HomeFragment) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var list = arrayListOf<SwitchModel>()
    private var layoutInflater: LayoutInflater? = null

    init {
        layoutInflater = activity?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater?

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is RecyclerViewHolder<*>) {
            val offer = list.get(position)
            val viewHolder = holder
            viewHolder.binding.setVariable(BR.vm, SwitchViewModel(offer))
            viewHolder.binding.executePendingBindings()
            viewHolder.binding.root.setOnClickListener { v ->
                val position = viewHolder.adapterPosition
                listener.onItemClick(list.get(position), v, position)
            }

            val binding = viewHolder.binding as ListItemNotificationBinding

            if (binding.switchImage.tag == null) {
                binding.switchImage.setAnimation("raw/light2_inactive.json")
                binding.switchImage.tag = "INACTIVE"
                binding.switchImage.repeatCount = 1
                binding.switchImage.playAnimation()
                binding.switchImage.tag = "INACTIVE"
            }

            binding.switchImage.setOnClickListener {
                if (binding.switchImage.tag.equals("INACTIVE")) {
                    binding.switchImage.setAnimation("raw/light2_active.json")
                    binding.switchImage.tag = "ACTIVE"
//                    binding.switchImage.progress = 0.5f
                } else {
                    binding.switchImage.setAnimation("raw/light2_inactive.json")
                    binding.switchImage.tag = "INACTIVE"
                }
                binding.switchImage.repeatCount = 2

                binding.switchImage.playAnimation()
            }
            binding.switchImage.resumeAnimation()
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder<ViewDataBinding?> {

        val binding =
            layoutInflater?.let {
                DataBindingUtil.inflate<ViewDataBinding>(
                    it,
                    R.layout.list_item_notification,
                    parent,
                    false
                )
            }
        return RecyclerViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun setList(list: List<SwitchModel>) {
        if (this.list.size > 0)
            this.list.clear()
        this.list.addAll(list as ArrayList<SwitchModel>)
        notifyDataSetChanged()
    }

}
