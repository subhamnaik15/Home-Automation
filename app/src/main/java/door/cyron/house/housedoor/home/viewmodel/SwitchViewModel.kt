package door.cyron.house.housedoor.home.viewmodel

import door.cyron.house.housedoor.home.model.SwitchModel

class SwitchViewModel(private val offer: SwitchModel) {


    val name: String?
        get() = offer.name

    val title: String?
        get() = offer.title

    val type: String?
        get() = offer.type


}
