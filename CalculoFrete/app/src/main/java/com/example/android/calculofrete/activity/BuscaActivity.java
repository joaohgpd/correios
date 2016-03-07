package com.example.android.calculofrete.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.android.calculofrete.R;
import com.example.android.calculofrete.fragment.FragmentBuscaDisplay;
import com.example.android.calculofrete.process.Search;
import com.example.android.calculofrete.process.SearchOnShared;

public class BuscaActivity extends AppCompatActivity {
    private static final String URL = "https://api.github.com/search/users?q=";
       private FragmentBuscaDisplay buscaDisplay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_frete);
        overridePendingTransition(R.anim.filho_entrando, R.anim.pai_saindo);
        this.buscaDisplay = new FragmentBuscaDisplay();
        android.app.FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.lista ,this.buscaDisplay);
        ft.commit();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getResources().getString(R.string.titulo_lista));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        Log.e("menu apertado", "qualquer"+item.getItemId()+" home: "+ R.id.home);
        if (id == android.R.id.home) {
            finish();
            overridePendingTransition(R.anim.pai_entrando, R.anim.filho_saindo);
            return true;
        }if(id == R.id.action_remover){
            excluir();
            return true;
        }
        onBackPressed();
        return super.onOptionsItemSelected(item);

     }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.pai_entrando, R.anim.filho_saindo);
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_lista, menu);
        return true;
    }
    private void excluir() {
            //Cria o gerador do AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
            //define o titulo
        builder.setTitle("Excluir Histórico"); //define a mensagem
        builder.setMessage("Deseja excluir todo o histórico?"); //define um botão como positivo
        builder.setPositiveButton("Excluir", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                SearchOnShared search = new SearchOnShared();
                search.deletarSharedPref(getApplicationContext());
                Toast.makeText(getApplicationContext(),"Historico excluido com sucesso",Toast.LENGTH_SHORT).show();
                onBackPressed();
            }
        }); //define um botão como negativo.
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {

            }
        }); //cria o AlertDialog
        AlertDialog alerta = builder.create();
        alerta.show();
    }



    }
