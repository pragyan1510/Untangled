package com.example.newthought;

import android.content.Context;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class requestmanager {
    Context context;
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://type.fit/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();


    public requestmanager(Context context) {
        this.context = context;
    }

    public void getquotes(QouteresponseListener listener){
        CallQuotes callQuotes = retrofit.create(CallQuotes.class);
        Call<List<QouteResponse>> call = callQuotes.CallQoutes();
        call.enqueue(new Callback<List<QouteResponse>>() {
            @Override
            public void onResponse(Call<List<QouteResponse>> call, Response<List<QouteResponse>> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(context, "request not successful", Toast.LENGTH_SHORT).show();
                    return;
                }
                listener.didFetch(response.body(),response.message());
            }

            @Override
            public void onFailure(Call<List<QouteResponse>> call, Throwable throwable) {
                listener.diderror(throwable.getMessage());

            }
        });
    }

    private interface CallQuotes{
        @GET("api/quotes")
        Call<List<QouteResponse>> CallQoutes();
    }



}
