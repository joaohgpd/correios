package com.example.android.calculofrete.domain;

/**
 * Created by joao on 10/02/2016.
 */

import android.content.Context;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;


public class BuscaService {

    public Busca parserJSON(Context context, String json) throws IOException {
        Busca busca = new Busca();
        try {
            JSONObject root = new JSONObject(json);
           // boolean incompleteResults = Boolean.parseBoolean(root.getString("incomplete_results"));
            //if(incompleteResults) {
            //    Toast.makeText(context,"Erro do servidor... \nTente mais tarde",Toast.LENGTH_SHORT).show();
            //    return null;
           // }

            float sedex = Float.parseFloat(root.getString("sedex"));
            float pac = Float.parseFloat(root.getString("pac"));
            busca.setSedex(sedex);
            busca.setPac(pac);

        } catch (JSONException e) {
            throw new IOException(e.getMessage(), e);
        }
        return busca;
    }
}
