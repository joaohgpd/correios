package com.example.android.calculofrete.activity;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.AsyncTask;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.calculofrete.R;
import com.example.android.calculofrete.domain.Busca;
import com.example.android.calculofrete.domain.BuscaService;
import com.example.android.calculofrete.process.Search;
import com.example.android.calculofrete.process.SearchOnShared;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String URL = "http://developers.agenciaideias.com.br/correios/frete/json/";

    private Busca busca;
    private TextView cep_origem;
    private TextView cep_dest;
    private TextView peso;
    private TextView valor;
    private EditText cep_origem_Edit;
    private EditText cep_dest_Edit;
    private EditText peso_Edit;
    private EditText valor_Edit;
    private RelativeLayout calcula_frete;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpToolbar();
        float scale = getResources().getDisplayMetrics().density;
        final RelativeLayout linear2 = (RelativeLayout) findViewById(R.id.linear2);
        final LinearLayout linear = (LinearLayout) findViewById(R.id.linear);
        ImageView image = (ImageView) findViewById(R.id.imageView);
        Display display = ((WindowManager) getSystemService(this.WINDOW_SERVICE))  .getDefaultDisplay();
        Point point = new Point();
        display.getSize(point);
        int posx = point.toString().indexOf(")");
        int posy = point.toString().indexOf(",");
        int displaysize = Integer.parseInt(point.toString().substring(6, posy));


        cep_origem = (TextView) findViewById(R.id.cep_origem);
        cep_origem.setAlpha(0f);
        cep_dest = (TextView) findViewById(R.id.cep_destino);
        cep_dest.setAlpha(0f);
        peso = (TextView) findViewById(R.id.peso);
        peso.setAlpha(0f);
        valor = (TextView) findViewById(R.id.valor_encomenda);
        valor.setAlpha(0f);
        calcula_frete = (RelativeLayout) findViewById(R.id.calcula_frete);
        calcula_frete.setAlpha(0f);
        cep_origem_Edit = (EditText) findViewById(R.id.cep_origem_Edit);
        cep_origem_Edit.setAlpha(0f);
        cep_dest_Edit = (EditText) findViewById(R.id.cep_destino_Edit);
        cep_dest_Edit.setAlpha(0f);
        peso_Edit = (EditText) findViewById(R.id.peso_Edit);
        peso_Edit.setAlpha(0f);
        valor_Edit = (EditText) findViewById(R.id.valor_encomenda_Edit);
        valor_Edit.setAlpha(0f);


        ObjectAnimator sobe = ObjectAnimator.ofFloat(image, "y", displaysize/2, 0f);
        ObjectAnimator desce = ObjectAnimator.ofFloat(image, "y", 0f, displaysize/2f);
        ObjectAnimator aparece = ObjectAnimator.ofFloat(image,"alpha",0f,1f);

        image.setAlpha(0f);
        Animator.AnimatorListener listener = new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        };
        AnimatorSet animSet = new AnimatorSet();




        animSet.play(desce).before(aparece);
        animSet.play(sobe).after(aparece);
        animSet.setInterpolator(new AccelerateDecelerateInterpolator());
        animSet.addListener(listener);
        sobe.setDuration(1000);
        desce.setDuration(20);
        aparece.setDuration(1500);
        animSet.start();



        //image.setAlpha(1f);
        cep_origem.animate().x(16f*scale).alpha(1).setDuration(300).setStartDelay(3100);
        cep_origem_Edit.animate().x(136* scale).alpha(1).setDuration(300).setStartDelay(3200);
        cep_dest.animate().x(16f*scale).alpha(1).setDuration(300).setStartDelay(3300);
        cep_dest_Edit.animate().x(136f*scale).alpha(1).setDuration(300).setStartDelay(3400);
        peso.animate().x(16f*scale).alpha(1).setDuration(300).setStartDelay(3500);
        peso_Edit.animate().x(136f*scale).alpha(1).setDuration(300).setStartDelay(3600);
        valor.animate().x(16f*scale).alpha(1).setDuration(300).setStartDelay(3700);
        valor_Edit.animate().x(136f * scale).alpha(1).setDuration(300).setStartDelay(3800);
        calcula_frete.animate().alpha(1f).setDuration(500).setStartDelay(3900).setInterpolator(new AccelerateDecelerateInterpolator());


    }

    protected void setUpToolbar() {
        // fazer import android.support.v7.widget.Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {

            setSupportActionBar(toolbar);



        }
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_about) {
            Toast.makeText(getApplication(), "Version 1.0", Toast.LENGTH_SHORT).show();
            return true;
        }
        if(id == R.id.action_lista){
            Intent intent = new Intent(this,BuscaActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    public void calcular_frete(View view) {
        Busca busca = new Busca();
        String newUrl ="";
        String error= "";

        //Verifica entrada Origem
        if(cep_origem_Edit.getText().length()==8){
            newUrl = URL+cep_origem_Edit.getText().toString()+"/";
        }else{
           error="Origem precisa conter 8 digitos";
        }
        //verifica entrada Destino
        if(cep_dest_Edit.getText().length()==8){
            newUrl = newUrl+cep_dest_Edit.getText().toString()+"/";
        }else{
            error=error+"\nDestino precisa conter 8 digitos";
        }
        //verifica entrada Peso
        int pos = peso_Edit.getText().toString().indexOf(".");
        if(pos==-1){
           error=error+"\nPeso incorreto (1 -> 1.000)";
        }else{
            String newPeso = peso_Edit.getText().toString().substring(pos+1);
            if(newPeso.length()!=3){
                error=error+"\nPeso incorreto (1.5 -> 1.500)";
            }else{
                newUrl=newUrl+peso_Edit.getText().toString()+"/";
            }
        }
        //verifica entrada Valor encomenda
        int pos2 = valor_Edit.getText().toString().indexOf(".");
        if(pos==-1){
            error=error+"\nValor da encomenda Incorreto";
        }else{
            String novoValor = valor_Edit.getText().toString().substring(pos+1);
            if(novoValor.length()!=2){
                error=error+"\nValor da encomenda Incorreto \n(Ex: 100 reais -> 100.00)";
            }else{
                newUrl=newUrl+valor_Edit.getText().toString();
            }
        }
        //Imprimi erros, se ouver
        if(!error.isEmpty()){
            Toast.makeText(getApplication(),error,Toast.LENGTH_LONG).show();
        }else{
            try {
                Log.e("response",newUrl);
                taskBusca(newUrl);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private void taskBusca(String url) throws IOException {
        // faz a busca na internet
        new SearchTask().execute(url);
    }


    private class SearchTask extends AsyncTask<String,Void,String> {
        ProgressDialog dialog;
        @Override protected void onPreExecute() {
            super.onPreExecute();
            dialog = ProgressDialog.show(MainActivity.this, "Espere", "Calculando Frete...");
        }

        @Override
        protected String doInBackground(String... params){
            String getResponse=null;
            try{
                Search search = new Search();
                getResponse = search.doGetRequest(params[0]);
                Log.e("response",getResponse);
            }catch (Exception e){
                Log.e("teste", e.getMessage(), e);
            }

            return getResponse;
        }
        protected void onPostExecute(String json){

            if(json!=null){
                BuscaService service = new BuscaService();
                try {
                    busca =  service.parserJSON(getApplication(),json);
                    if(busca!= null){
                        NumberFormat formatter = NumberFormat.getNumberInstance();
                        formatter.setMinimumFractionDigits(2);
                        formatter.setMaximumFractionDigits(2);
                        busca.setCep_origem(Integer.parseInt(cep_origem_Edit.getText().toString()));
                        busca.setCep_dest(Integer.parseInt(cep_dest_Edit.getText().toString()));
                        busca.setPeso(Float.parseFloat(peso_Edit.getText().toString()));
                        busca.setValor(Float.parseFloat(valor_Edit.getText().toString()));
                        ArrayList<Busca> buscaPrefArray;
                        SearchOnShared search = new SearchOnShared();
                        buscaPrefArray = search.acessaSharedPreferences(getApplication());
                        search.salvarSharedPref(buscaPrefArray, busca, getApplication());
                        TextView pac = (TextView) findViewById(R.id.textView2);
                        TextView sedex = (TextView) findViewById(R.id.textView3);
                        pac.setText("R$: " + formatter.format(busca.getPac()));
                        sedex.setText("R$: " + formatter.format(busca.getSedex()));


                    }else
                        Toast.makeText(getApplication(), "No user was found", Toast.LENGTH_SHORT).show();

                } catch (IOException e) {
                    e.printStackTrace();
                }

                dialog.dismiss();

            }
        }





    }
}
