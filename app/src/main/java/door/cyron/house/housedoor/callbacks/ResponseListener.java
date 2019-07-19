package door.cyron.house.housedoor.callbacks;

import okhttp3.Headers;


public interface ResponseListener<T> {

    void onResponse(T response, Headers headers);

    void onError(int status, String error);

    void onFailure(Throwable throwable);
}
