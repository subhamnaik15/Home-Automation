package door.cyron.house.housedoor.retrofit;

import door.cyron.house.housedoor.callbacks.Request;
import door.cyron.house.housedoor.callbacks.ResponseListener;
import door.cyron.house.housedoor.configuration.Page;
import door.cyron.house.housedoor.configuration.Pagination;
import retrofit2.Call;
import retrofit2.Callback;

import java.net.UnknownHostException;


public final class RetrofitListRequest<T extends Page> extends Request {

    private Call<T> call;
    private final ResponseListener responseListener;
    private Pagination pagination;

    public RetrofitListRequest(Call<T> call, ResponseListener<T> responseListener) {
        this.call = call;
        this.responseListener = responseListener;
    }

    @Override
    public void enqueue() {
        call.enqueue(new Callback<T>() {
            @Override
            public void onResponse(Call<T> call, retrofit2.Response<T> response) {
                if (response.isSuccessful()) {
                    pagination = response.body().getPagination();
                    responseListener.onResponse(response.body(), response.headers());
                } else {
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
                    responseListener.onError(400,"Something went wrong");
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


    @Override
    public boolean canLoadMore() {
        if (pagination == null)
            return false;
        return call.isExecuted() && pagination.hasNext();
    }

    @Override
    public String getNextUri() {
        return pagination.getNext();
    }

}