package com.example.android.calculofrete.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.calculofrete.R;


public class FragmentBuscaAdapter extends android.app.Fragment{
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle saveInstaBundle){
        return inflater.inflate(R.layout.fragment_busca_adapter,container,false);
    }
}
