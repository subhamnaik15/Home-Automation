package door.cyron.house.housedoor.acount;


import door.cyron.house.housedoor.callbacks.Request;
import door.cyron.house.housedoor.callbacks.ResponseListener;
import door.cyron.house.housedoor.configuration.ViewModel;
import door.cyron.house.housedoor.retrofit.RetrofitClient;
import door.cyron.house.housedoor.retrofit.RetrofitRequest;
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

    public void signin(String email, String type, String pass) {
        setLoading(true);
        SigninModel signinModel = new SigninModel();
        signinModel.setEmail(email);
        signinModel.setPassword(pass);
        signinModel.setType(type);
        Call<SignInResponseModel> call = RetrofitClient.getAPIInterface().signIn(signinModel);
        request = new RetrofitRequest<>(call, new ResponseListener<SignInResponseModel>() {
            @Override
            public void onResponse(SignInResponseModel response, Headers headers) {
                setLoading(false);
                signinListener.onSucess();
            }

            @Override
            public void onError(int status, String errors) {
                setLoading(false);
                signinListener.onError(errors);
            }

            @Override
            public void onFailure(Throwable throwable) {
                setLoading(false);
                setRetry(true);
                signinListener.onError("Failure");
            }
        });
        request.enqueue();
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

