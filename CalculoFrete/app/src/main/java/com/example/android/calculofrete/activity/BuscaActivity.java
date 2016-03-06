package com.example.android.calculofrete.activity;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.android.calculofrete.R;
import com.example.android.calculofrete.fragment.FragmentBuscaDisplay;

public class BuscaActivity extends AppCompatActivity {
    private static final String URL = "https://api.github.com/search/users?q=";
       private FragmentBuscaDisplay buscaDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        this.buscaDisplay = new FragmentBuscaDisplay();
        android.app.FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.lista ,this.buscaDisplay);
        ft.commit();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Lista de fretes");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
//        int id = item.getItemId();
//        Log.e("menu apertado","qualquer");
//        if (id == R.id.home) {
//            Log.e("menu apertado","voltar");
//            onBackPressed();
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
        finish();
        return true;
    }

}
