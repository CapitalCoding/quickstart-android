package br.com.dbccompany.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.Date;

import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class Network {
    public Retrofit.Builder createNetworkClient(String baseUrl, boolean debug){
        return retrofitClient(baseUrl, httpClient(debug), createGson());
    }

    private OkHttpClient httpClient(boolean debug)  {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT);
        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
        if (debug) {
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            clientBuilder.addInterceptor(httpLoggingInterceptor);
        }
        return clientBuilder.build();
    }

    /**
     * Date from long
     * @return
     */
    private Gson createGson(){
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Date.class,
                (JsonDeserializer<Date>) (json, typeOfT, context) ->
                        new Date(json.getAsJsonPrimitive().getAsLong()));
        return builder.create();
    }
    private Retrofit.Builder retrofitClient(String baseUrl, OkHttpClient httpClient, Gson gson) {

        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()));
    }

}
