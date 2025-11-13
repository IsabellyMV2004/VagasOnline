package com.example.appandroid;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.appandroid.api.ApiClient;
import com.example.appandroid.api.VagasService;
import com.example.appandroid.model.Empresa;
import com.example.appandroid.model.Interesse;
import com.example.appandroid.model.Vaga;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InteresseActivity extends AppCompatActivity {

    private EditText etNome, etCpf, etEmail, etTelefone;
    private Button btnEnviar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interesse);

        etNome = findViewById(R.id.etNome);
        etCpf = findViewById(R.id.etCpf);
        etEmail = findViewById(R.id.etEmail);
        etTelefone = findViewById(R.id.etTelefone);
        btnEnviar = findViewById(R.id.btnEnviar);

        // Dados recebidos
        String vagaId = getIntent().getStringExtra("vaga_id");
        String cargo = getIntent().getStringExtra("cargo");
        String empresaId = getIntent().getStringExtra("empresa_id");
        String empresaNome = getIntent().getStringExtra("empresa_nome");
        String cidade = getIntent().getStringExtra("cidade");
        String estado = getIntent().getStringExtra("estado");

        Empresa empresa = new Empresa();
        empresa.setId(empresaId);
        empresa.setNome_fantasia(empresaNome);


        VagasService service = ApiClient.getClient().create(VagasService.class);

        btnEnviar.setOnClickListener(v -> {

            if (etNome.getText().toString().isEmpty() ||
                    etCpf.getText().toString().isEmpty() ||
                    etEmail.getText().toString().isEmpty() ||
                    etTelefone.getText().toString().isEmpty()) {

                Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
                return;
            }

            Vaga vaga = new Vaga();
            vaga.setId(vagaId); // APENAS O ID !!!

            Interesse interesse = new Interesse(
                    etNome.getText().toString(),
                    etCpf.getText().toString(),
                    etEmail.getText().toString(),
                    etTelefone.getText().toString(),
                    vaga
            );

            service.registrarInteresse(interesse).enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if (response.isSuccessful()) {
                        Toast.makeText(InteresseActivity.this, "Interesse registrado!", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(InteresseActivity.this, "Erro: " + response.code(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    Toast.makeText(InteresseActivity.this, "Falha ao enviar interesse", Toast.LENGTH_SHORT).show();
                }
            });
        });
    }
}