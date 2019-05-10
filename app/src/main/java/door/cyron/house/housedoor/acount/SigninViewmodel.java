package door.cyron.house.housedoor.acount;


import door.cyron.house.housedoor.callbacks.Request;
import door.cyron.house.housedoor.configuration.ViewModel;
import okhttp3.Headers;
import retrofit2.Call;


public class SigninViewmodel extends ViewModel {

    private SigninListener signinListener;
    private Request request;

    public SigninViewmodel(SigninListener signinListener) {
        this.signinListener = signinListener;
    }

    public interface SigninListener {
        void onSucess();

        void onError(String error);
    }

    public void signin(String detailUrl) {
        setLoading(true);
       /*Call<SigninModel> call = RetrofitClient.getAPIInterface().getFeverDetails(detailUrl);
        request = new RetrofitRequest<>(call, new ResponseListener<SigninModel>() {
            @Override
            public void onResponse(SigninModel response, Headers headers) {
                setLoading(false);
            }

            @Override
            public void onError(int status, Errors errors) {
                setLoading(false);
            }

            @Override
            public void onFailure(Throwable throwable) {
                setLoading(false);
                setRetry(true);
            }
        });*/
//        request.enqueue();
    }

    @Override
    public void retry() {
        super.retry();
        request.retry();
    }

    @Override
    public void clear() {
        if (request != null)
            request.cancel();
    }

}

