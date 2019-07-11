package door.cyron.house.housedoor.home.viewmodel

import androidx.databinding.Bindable
import door.cyron.house.housedoor.callbacks.Request
import door.cyron.house.housedoor.configuration.ViewModel

class HomeViewModel(private val uri: String) : ViewModel() {
    private val request: Request? = null
    private var empty: Boolean = false

    @Bindable
    fun isEmpty(): Boolean {
        return empty
    }

    fun setEmpty(empty: Boolean) {
        this.empty = empty
//        notifyPropertyChanged(BR.empty)
    }

    fun download() {
        //        setLoading(true);
        //        setLoading(false);
        //        offers = new ArrayList<>();
        //        setOffers(response.getOfferList());
    }


    override fun retry() {
        //        super.retry();
        request!!.retry();
    }

    override fun clear() {
        request?.cancel()
    }


}