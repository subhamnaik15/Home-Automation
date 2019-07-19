package door.cyron.house.housedoor.retrofit;

import door.cyron.house.housedoor.callbacks.Request;
import door.cyron.house.housedoor.callbacks.ResponseListener;
import retrofit2.Call;
import retrofit2.Callback;

import java.net.UnknownHostException;


public final class RetrofitRequest<T> extends Request {

    private final ResponseListener responseListener;
    private Call<T> call;

    public RetrofitRequest(Call<T> call, ResponseListener<T> responseListener) {
        this.call = call;
        this.responseListener = responseListener;
    }

    @Override
    public void enqueue() {
        call.enqueue(new Callback<T>() {
            @Override
            public void onResponse(Call<T> call, retrofit2.Response<T> response) {
                if (response.isSuccessful())
                    responseListener.onResponse(response.body(), response.headers());
                else {
                    responseListener.onError(response.code(), response.message());
                }
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                if (call != null && call.isCanceled())// don't process if request is cancelled.
                    return;
                if (t instanceof UnknownHostException)
                    responseListener.onFailure(t);//network error
                else {
                    responseListener.onError(400, "Something went wrong");
                }
            }
        });
    }

    @Override
    public void cancel() {
        call.cancel();
    }

    @Override
    public void retry() {
        call = call.clone();
        enqueue();
    }

}
