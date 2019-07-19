package door.cyron.house.housedoor.callbacks

import android.view.View

interface OnItemClickListener<T> {

    fun onItemClick(t: T, view: View, position: Int)
}