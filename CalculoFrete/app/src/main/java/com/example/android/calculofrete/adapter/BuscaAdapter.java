package com.example.android.calculofrete.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.calculofrete.R;
import com.example.android.calculofrete.domain.Busca;

import java.text.NumberFormat;
import java.util.List;


public class BuscaAdapter extends RecyclerView.Adapter<BuscaAdapter.BuscasViewHolder> {

    private final List<Busca> buscas;
    private final Context context;
    private BuscaOnClickListener buscaOnClickListener;


    public BuscaAdapter(Context context, List<Busca> buscas, BuscaOnClickListener BuscaOnClickListener) {
        this.context = context;
        this.buscas = buscas;
        this.buscaOnClickListener = BuscaOnClickListener;
    }

    @Override
    public int getItemCount() {
        return this.buscas != null ? this.buscas.size() : 0;
    }

    @Override
    public BuscasViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Infla a view do layout
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_busca_adapter, viewGroup, false);

        CardView cardView = (CardView) view.findViewById(R.id.card_view);

        // Cria o ViewHolder
        BuscasViewHolder holder = new BuscasViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final BuscasViewHolder holder, final int position) {
        // Atualiza a view

        Busca busca = buscas.get(position);
        NumberFormat formatter = NumberFormat.getNumberInstance();
        formatter.setMinimumFractionDigits(3);
        formatter.setMaximumFractionDigits(3);

        String cep2 = ""+busca.getCep_origem();
        String cep1 = cep2.substring(0,5);
        cep2 = cep2.substring(5,8);
        holder.cep_o.setText("Cep Origem: " + cep1 + "-" + cep2);
        cep2 = ""+busca.getCep_dest();
        cep1 = cep2.substring(0,5);
        cep2 = cep2.substring(5,8);
        holder.cep_d.setText("Cep Destino: " + cep1 + "-" + cep2);
        holder.peso.setText("Peso: "+ formatter.format(busca.getPeso())+" Kg");
        formatter.setMinimumFractionDigits(2);
        formatter.setMaximumFractionDigits(2);
        holder.valor.setText("Valor: R$ "+formatter.format(busca.getValor()));
       // holder.image.setBackgroundResource(R.drawable.fita);
        holder.image_pac.setBackgroundResource(R.drawable.pac);
        holder.image_sedex.setBackgroundResource(R.drawable.sedex2);
        holder.pac.setText("R$: " + formatter.format(busca.getPac()));
        holder.sedex.setText("R$: " + formatter.format(busca.getSedex()));


            // Click
        if (buscaOnClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    buscaOnClickListener.onClickBusca(holder.itemView, position); // A variável position é final
                }
            });
        }
    }

    public interface BuscaOnClickListener {
        void onClickBusca(View view, int idx);
    }

    // ViewHolder com as views
    public static class BuscasViewHolder extends RecyclerView.ViewHolder {
        public TextView cep_d;
        public TextView cep_o;
        public TextView peso;
        public TextView valor;
      //  public ImageView image;
        public TextView pac;
        public TextView sedex;
        public ImageView image_pac;
        public ImageView image_sedex;

        public BuscasViewHolder(View view) {
            super(view);
            // Cria as views para salvar no ViewHolder
            cep_o = (TextView) view.findViewById(R.id.cep_origem);
            cep_d = (TextView) view.findViewById(R.id.cep_dest);
            peso = (TextView) view.findViewById(R.id.peso);
            valor = (TextView) view.findViewById(R.id.valor);
          //  image = (ImageView) view.findViewById(R.id.img);
            image_pac = (ImageView) view.findViewById(R.id.imgPac);
            image_sedex = (ImageView) view.findViewById(R.id.imgSedex);
            pac = (TextView) view.findViewById(R.id.pac);
            sedex = (TextView) view.findViewById(R.id.sedex);


        }
    }
}
