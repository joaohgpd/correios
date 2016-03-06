package com.example.android.calculofrete.fragment;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.android.calculofrete.R;
import com.example.android.calculofrete.adapter.BuscaAdapter;
import com.example.android.calculofrete.domain.Busca;
import com.example.android.calculofrete.domain.BuscaService;
import com.example.android.calculofrete.process.Search;
import com.example.android.calculofrete.process.SearchOnShared;


import java.io.IOException;
import java.util.List;


public class FragmentBuscaDisplay extends android.app.Fragment {
    protected RecyclerView recyclerView;
    private List<Busca> buscas;
    private LinearLayoutManager mLayoutManager;
    private String url = null;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_busca_display, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        SearchOnShared search = new SearchOnShared();
        buscas = search.acessaSharedPreferences(getActivity());
        recyclerView.setAdapter(new BuscaAdapter(getActivity(), buscas, onClickBusca()));
    }


    private BuscaAdapter.BuscaOnClickListener onClickBusca() {
        return new BuscaAdapter.BuscaOnClickListener() {
            @Override
            public void onClickBusca(View view, int idx) {

                Busca busca = buscas.get(idx);
                Toast.makeText(getActivity(), "Usuario: " + busca.getCep_dest(), Toast.LENGTH_SHORT).show();
                // Intent intent = new Intent(getActivity(), BuscaDescActivity.class);
                // intent.putExtra("URL", busca.getCep_dest());
                //startActivity(intent);
            }
        };
    }
}