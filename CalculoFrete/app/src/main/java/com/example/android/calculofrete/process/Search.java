package com.example.android.calculofrete.process;

import android.content.Context;
import android.widget.Toast;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * Created by joao on 10/02/2016.
 */
public class Search {
    OkHttpClient client = new OkHttpClient();

    // code request code here
    public String doGetRequest(String url) throws IOException {
        Request request = new Request.Builder().url(url).build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

}
