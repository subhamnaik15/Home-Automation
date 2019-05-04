package door.cyron.house.housedoor.configuration;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.view.View;
import door.cyron.house.housedoor.BR;
import door.cyron.house.housedoor.utility.Network;


public abstract class ViewModel extends BaseObservable {

    private boolean loading = false;

    private boolean retry = false;

    /**
     * Retry on going request one more time
     */
    public void retry() {
        setRetry(false);
        setLoading(true);
    }

    public abstract void clear();


    @Bindable
    public boolean isLoading() {
        return loading;
    }

    public void setLoading(boolean loading) {
        this.loading = loading;
        notifyPropertyChanged(BR.loading);
    }

    public void setRetry(boolean retry) {
        this.retry = retry;
        notifyPropertyChanged(BR.retry);
    }

    public View.OnClickListener onRetryClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Network.isConnected(v.getContext()))
                    retry();
            }
        };
    }


    @Bindable
    public boolean isRetry() {
        return retry;
    }
}
