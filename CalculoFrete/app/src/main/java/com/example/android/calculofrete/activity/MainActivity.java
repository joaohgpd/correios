package com.example.android.calculofrete.activity;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Point;
import android.graphics.drawable.AnimationDrawable;
import android.os.AsyncTask;
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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.calculofrete.R;
import com.example.android.calculofrete.domain.Busca;
import com.example.android.calculofrete.domain.BuscaService;
import com.example.android.calculofrete.process.Search;
import com.example.android.calculofrete.process.SearchOnShared;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final String URL = "http://developers.agenciaideias.com.br/correios/frete/json/";

    private Busca busca;
    private TextView cep_origem;
    private TextView cep_dest;
    private TextView peso;
    private TextView valor;
    private TextView info;
    private TextView calcularFrete;
    private EditText cep_origem_Edit;
    private EditText cep_dest_Edit;
    private EditText peso_Edit;
    private EditText valor_Edit;
    private RelativeLayout calcula_frete_Layout;
    private AnimationDrawable animation;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        overridePendingTransition(R.anim.pai_entrando, R.anim.filho_saindo);
        setUpToolbar();
        float scale = getResources().getDisplayMetrics().density;
        final RelativeLayout relative = (RelativeLayout) findViewById(R.id.relative);

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
        calcula_frete_Layout = (RelativeLayout) findViewById(R.id.calcula_frete_layout);
        calcula_frete_Layout.setAlpha(0f);
        cep_origem_Edit = (EditText) findViewById(R.id.cep_origem_Edit);
        cep_origem_Edit.setAlpha(0f);
        cep_dest_Edit = (EditText) findViewById(R.id.cep_destino_Edit);
        cep_dest_Edit.setAlpha(0f);
        peso_Edit = (EditText) findViewById(R.id.peso_Edit);
        peso_Edit.setAlpha(0f);
        valor_Edit = (EditText) findViewById(R.id.valor_encomenda_Edit);
        valor_Edit.setAlpha(0f);
        calcularFrete = (TextView) findViewById(R.id.calcular_frete);
        calcularFrete.setAlpha(0f);
        info = (TextView) findViewById(R.id.info);
        info.setAlpha(0f);
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
                info.animate().alpha(1f).setDuration(10).setInterpolator(new AccelerateDecelerateInterpolator());
                relative.setBackgroundColor(getResources().getColor(R.color.gray));

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        };
        AnimatorSet animSet = new AnimatorSet();



        animation = new AnimationDrawable();
        animation.addFrame(getResources().getDrawable(R.drawable.mundo_1), 70);
        animation.addFrame(getResources().getDrawable(R.drawable.mundo_2), 70);
        animation.addFrame(getResources().getDrawable(R.drawable.mundo_3), 70);
        animation.addFrame(getResources().getDrawable(R.drawable.mundo_4), 70);
        animation.addFrame(getResources().getDrawable(R.drawable.mundo_5), 70);
        animation.addFrame(getResources().getDrawable(R.drawable.mundo_6), 70);
        animation.addFrame(getResources().getDrawable(R.drawable.mundo_7), 70);
        animation.addFrame(getResources().getDrawable(R.drawable.mundo_8), 70);
        animation.addFrame(getResources().getDrawable(R.drawable.mundo_9), 70);
        animation.addFrame(getResources().getDrawable(R.drawable.mundo_10),70);
        animation.addFrame(getResources().getDrawable(R.drawable.mundo_11),70);



        animation.setOneShot(true);
        final ScrollView scrollView = (ScrollView) findViewById(R.id.scroll);

        //
        scrollView.setBackgroundDrawable(animation);

        scrollView.postDelayed(new Runnable() {
            @Override
            public void run() {
                animation.start();
            }
        }, 10);
        animSet.play(desce).before(aparece);
        animSet.play(sobe).after(aparece);
        animSet.setInterpolator(new AccelerateDecelerateInterpolator());
        animSet.addListener(listener);
        sobe.setDuration(1000);
        desce.setDuration(20);
        aparece.setDuration(500);
        aparece.setStartDelay(500);
        animSet.start();



        //image.setAlpha(1f);
        cep_origem.animate().x(16f*scale).alpha(1).setDuration(300).setStartDelay(2300);
        cep_origem_Edit.animate().x(136* scale).alpha(1).setDuration(300).setStartDelay(2400);
        cep_dest.animate().x(16f*scale).alpha(1).setDuration(300).setStartDelay(2500);
        cep_dest_Edit.animate().x(136f*scale).alpha(1).setDuration(300).setStartDelay(2600);
        peso.animate().x(16f*scale).alpha(1).setDuration(300).setStartDelay(2700);
        peso_Edit.animate().x(136f*scale).alpha(1).setDuration(300).setStartDelay(2800);
        valor.animate().x(16f*scale).alpha(1).setDuration(300).setStartDelay(2900);
        valor_Edit.animate().x(136f * scale).alpha(1).setDuration(300).setStartDelay(3000);
        calcula_frete_Layout.animate().alpha(1f).setDuration(500).setStartDelay(3100).setInterpolator(new AccelerateDecelerateInterpolator());
        calcularFrete.animate().alpha(1f).setDuration(500).setStartDelay(3250).setInterpolator(new AccelerateDecelerateInterpolator());

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
        if (id == R.id.action_about_pac) {
            Toast.makeText(getApplication(), "Pac\nA encomenda econonica dos correios\nPrazo de entrega de 3 a 7 dias", Toast.LENGTH_LONG).show();
            return true;
        }
        if (id == R.id.action_about_sedex) {
            Toast.makeText(getApplication(), "Sedex\nMandou, chegou\nPrazo de entrega de 1 a 3 dias", Toast.LENGTH_LONG).show();
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
                if((Float.parseFloat(peso_Edit.getText().toString())>30)){
                    error=error+"Peso limite ultrapassado. (Peso Max: 30kg)";

                }else
                    newUrl=newUrl+peso_Edit.getText().toString()+"/";
            }
        }
        //verifica entrada Valor encomenda
        int pos2 = valor_Edit.getText().toString().indexOf(".");
        if(pos2==-1){
            error=error+"\nValor da encomenda Incorreto";
        }else{
            String novoValor = valor_Edit.getText().toString().substring(pos2+1);
            if(novoValor.length()!=2){
                error=error+"\nValor da encomenda Incorreto \n(Ex: 100 reais -> 100.00)\n"+novoValor;
            }else{
                if(Float.parseFloat(valor_Edit.getText().toString())>10000f){
                    valor_Edit.setText("10000.00");
                    newUrl=newUrl+""+10000.00;
                    Toast.makeText(getApplicationContext(),"O valor da encomenda foi modificado para R$: 10.000,00 (Valor Maximo)",Toast.LENGTH_SHORT).show();
                }else
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
            dialog = ProgressDialog.show(MainActivity.this, "Aguarde", "Calculando Frete...");
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
                        TextView pac = (TextView) findViewById(R.id.valorPac);
                        TextView sedex = (TextView) findViewById(R.id.valorSedex);
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
