package com.anamuxfeldt.cadastroclientescomdb.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.anamuxfeldt.cadastroclientescomdb.controller.ClienteAdapter;
import com.anamuxfeldt.cadastroclientescomdb.controller.ClienteController;
import com.anamuxfeldt.cadastroclientescomdb.databinding.ActivityConsultarClientesBinding;
import com.anamuxfeldt.cadastroclientescomdb.model.Cliente;

import java.util.ArrayList;
import java.util.List;

public class ConsultarClientes extends AppCompatActivity {
    private ActivityConsultarClientesBinding binding;
    List<Cliente> clientes;
    ClienteAdapter adapter;
    ClienteController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityConsultarClientesBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        controller = new ClienteController(getApplicationContext());

        clientes = controller.listar(getApplicationContext());

        adapter = new ClienteAdapter(clientes, getApplicationContext());

        binding.rvClientes.setAdapter(adapter);
        binding.rvClientes.setLayoutManager(new LinearLayoutManager(this));
    }
}