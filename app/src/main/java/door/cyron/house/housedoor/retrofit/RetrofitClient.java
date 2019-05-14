package door.cyron.house.housedoor.retrofit;

import door.cyron.house.housedoor.BuildConfig;
import okhttp3.*;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.File;
import java.io.IOException;


public class RetrofitClient {

    private static final int CACHE_SIZE = 5 * 1024 * 1024;//5 MB
    private static RetrofitClient sInstance;
    private Retrofit retrofit = null;

    public static void create(File cacheDir) {
        if (sInstance == null) {
            synchronized (RetrofitClient.class) {
                if (sInstance == null) {
                    sInstance = new RetrofitClient(cacheDir);
                }
            }
        } else
            throw new IllegalStateException("RetrofitClient instance is already been created.");
    }

    private RetrofitClient() {
    }

    private RetrofitClient(File cacheDir) {
        this();
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.HEADERS);
            httpClient.addInterceptor(logging);
        }
        httpClient.cache(new Cache(cacheDir, CACHE_SIZE));
        httpClient.addInterceptor(new ApplicationInterceptor());
        retrofit = new Retrofit.Builder()
                .baseUrl("https://home-automation-iot.herokuapp.com/web/iot/")
                .client(httpClient.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    /**
     * Returns the instance of {@link Retrofit}.
     * This method must be called after {@link #create}.
     */
    public static Retrofit retrofit() {
        synchronized (RetrofitClient.class) {
            if (sInstance == null)
                throw new IllegalStateException("RetrofitClient instance is not created yet. Call RetrofitClient.create() before calling getInstance()");

        }
        return sInstance.retrofit;
    }

    /**
     * Returns the instance of {@link APIInterface}.
     * This method must be called after {@link #create}.
     */
    public static APIInterface getAPIInterface() {
        return retrofit().create(APIInterface.class);
    }

    /**
     * An interceptor is used to modify each request before it is performed and alters the request header.
     * The advantage is, that you don’t have to add @Header("Authorization") to each API method definition.
     */
    private class ApplicationInterceptor implements Interceptor {


        public ApplicationInterceptor() {
        }

      @Override
        public Response intercept(Chain chain) throws IOException {
            Request original = chain.request();
            Request.Builder requestBuilder = original.newBuilder();
//            TimeZone timeZone = TimeZone.getDefault();
//            requestBuilder.addHeader(APIInterface.Header.TIMEZONE, timeZone.getID());
//            String authToken = session.getAuthToken();
//            requestBuilder.header(APIInterface.Header.AUTHORIZATION, authToken).build();
            Request request = requestBuilder.build();
            Response response = chain.proceed(request);
            return response;
        }
    }
}
