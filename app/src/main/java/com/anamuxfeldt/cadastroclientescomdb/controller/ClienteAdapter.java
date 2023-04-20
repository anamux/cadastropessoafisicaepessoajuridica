package com.anamuxfeldt.cadastroclientescomdb.controller;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.anamuxfeldt.cadastroclientescomdb.model.Cliente;
import com.anamuxfeldt.cadastropessoafisicaepessoajuridica.databinding.LinhaDetalheClienteBinding;

import java.util.List;

public class ClienteAdapter extends RecyclerView.Adapter<ClienteAdapter.ViewHolder> {

    private List<Cliente> aClientes;
    private Context aContext;

    public ClienteAdapter(List<Cliente> aClientes, Context aContext) {
        this.aClientes = aClientes;
        this.aContext = aContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        LinhaDetalheClienteBinding binding = LinhaDetalheClienteBinding.inflate(inflater, parent, false);
        ViewHolder viewHolder = new ViewHolder(binding);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ClienteAdapter.ViewHolder holder, int position) {

        Cliente objLinha= aClientes.get(position);

        TextView txtPrimeiroNome = holder.binding.rvPrimeiroNome;
        txtPrimeiroNome.setText(objLinha.getPrimeiroNome());

        Button rvIsPessoaFisica  = holder.binding.rvIsPessoaFisica;
        rvIsPessoaFisica.setText(objLinha.isPessoaFisica()?"CPF" : "CNPJ");
    }

    @Override
    public int getItemCount() {
        return aClientes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private LinhaDetalheClienteBinding binding;

        public ViewHolder(LinhaDetalheClienteBinding itemBinding) {
            super(itemBinding.getRoot());
            binding = itemBinding;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            Cliente clienteSelecionado = aClientes.get(position);
            if(position != RecyclerView.NO_POSITION){

                Log.i(LOG_ADAPTER, "onClick:Cliente ID: "+position+ "Primeiro Nome: "
                        +clienteSelecionado.getPrimeiroNome());
                Toast.makeText(aContext, "Cliente ID: "+position+ "Primeiro Nome: "
                                                +clienteSelecionado.getPrimeiroNome(), Toast.LENGTH_SHORT).show();
            }

        }
    }

    public static final String LOG_ADAPTER = "LOG_ADAPTER";
}