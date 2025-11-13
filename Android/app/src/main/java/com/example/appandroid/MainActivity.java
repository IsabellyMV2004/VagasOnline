package com.example.appandroid;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appandroid.adapter.VagasAdapter;
import com.example.appandroid.api.ApiClient;
import com.example.appandroid.api.VagasService;
import com.example.appandroid.model.Vaga;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerVagas;
    private VagasService vagasService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerVagas = findViewById(R.id.recyclerVagas);
        recyclerVagas.setLayoutManager(new LinearLayoutManager(this));

        vagasService = ApiClient.getClient().create(VagasService.class);

        vagasService.getVagas().enqueue(new Callback<List<Vaga>>() {
            @Override
            public void onResponse(Call<List<Vaga>> call, Response<List<Vaga>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Vaga> lista = response.body();
                    recyclerVagas.setAdapter(new VagasAdapter(lista, vaga -> {
                        Intent i = new Intent(MainActivity.this, InteresseActivity.class);
                        i.putExtra("vaga_id", vaga.getId());
                        i.putExtra("cargo", vaga.getCargo());
                        i.putExtra("empresa_id", vaga.getEmpresa() != null ? vaga.getEmpresa().getId() : "");
                        i.putExtra("empresa_nome", vaga.getEmpresa() != null ? vaga.getEmpresa().getNome_fantasia() : "");
                        i.putExtra("cidade", vaga.getCidade());
                        i.putExtra("estado", vaga.getEstado());
                        startActivity(i);
                    }));
                } else {
                    Toast.makeText(MainActivity.this, "Erro ao carregar vagas", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Vaga>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Falha de conexão", Toast.LENGTH_SHORT).show();
            }
        });
    }
}