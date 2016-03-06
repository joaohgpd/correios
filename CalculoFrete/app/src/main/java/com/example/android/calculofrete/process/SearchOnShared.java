package com.example.android.calculofrete.process;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.android.calculofrete.domain.Busca;

import java.util.ArrayList;

/**
 * Created by joao on 06/03/2016.
 */
public class SearchOnShared {

    public ArrayList<Busca> acessaSharedPreferences(Context context) {
        // Acesso às Shared Preferences usando o nome definido.
        SharedPreferences prefs = context.getSharedPreferences("preferencias_1", Context.MODE_PRIVATE);
        ArrayList<Busca> buscaPrefArray = new ArrayList<>();
        int i = 0;
        Log.e("acessa_cep_origem_" + i, "valor " + prefs.getInt("cep_origem_" + i, 0));
        while (prefs.getInt("cep_origem_"+i, 0) != 0) {

            Log.e("cep_origem_"+i,"valor "+prefs.getInt("cep_origem_" + i, 0));
            Busca buscaPref = new Busca();
            // Acesso às informações de acordo com o tipo.
            buscaPref.setCep_origem(prefs.getInt("cep_origem_"+i, 0));
            buscaPref.setCep_dest(prefs.getInt("cep_dest_"+i, 0));
            buscaPref.setPeso((prefs.getFloat("peso_"+i, 0)));
            buscaPref.setValor((prefs.getFloat("valor_"+i, 0)));
            buscaPref.setPac((prefs.getFloat("pac_"+i, 0)));
            buscaPref.setSedex((prefs.getFloat("sedex_"+i, 0)));

            buscaPrefArray.add(buscaPref);
            i++;
        }
        return buscaPrefArray;
    }

    public void salvarSharedPref(ArrayList<Busca> buscaPrefArray, Busca busca,Context context) {
        // Cria ou abre.
        int tamanhoPref = buscaPrefArray.size();
        SharedPreferences prefs = context.getSharedPreferences("preferencias_1", Context.MODE_PRIVATE);
        // Precisamos utilizar um editor para alterar Shared Preferences.
        SharedPreferences.Editor ed = prefs.edit();
        //int tamanho = buscaArrayList.size();
        //for(int i=tamanhoPref;i<tamanho+tamanhoPref;i++) {
        int j=0;
        if(tamanhoPref!=0){
            Log.e("2cep_origem: ",""+tamanhoPref);
            j=1;
        }else{

            Log.e("1cep_origem: "," 2 "+tamanhoPref);
        }
        // salvando informações de acordo com o tipo
        ed.putInt("cep_origem_"+(tamanhoPref), busca.getCep_origem());
        ed.putInt("cep_dest_"+(tamanhoPref), busca.getCep_dest());
        ed.putFloat("peso_"+(tamanhoPref), busca.getPeso());
        ed.putFloat("valor_"+(tamanhoPref), busca.getValor());
        ed.putFloat("pac_"+(tamanhoPref), busca.getPac());
        ed.putFloat("sedex_"+(tamanhoPref), busca.getSedex());
        // }
        // Grava efetivamente as alterações.


        ed.commit();

    }
}
