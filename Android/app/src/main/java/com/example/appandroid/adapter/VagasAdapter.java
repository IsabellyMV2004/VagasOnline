package com.example.appandroid.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appandroid.R;
import com.example.appandroid.model.Vaga;

import java.util.List;

public class VagasAdapter extends RecyclerView.Adapter<VagasAdapter.VagaViewHolder> {

    public interface OnInteresseClick {
        void onInteresse(Vaga vaga);
    }

    private final List<Vaga> lista;
    private final OnInteresseClick listener;

    public VagasAdapter(List<Vaga> lista, OnInteresseClick listener) {
        this.lista = lista;
        this.listener = listener;
    }

    @NonNull
    @Override
    public VagaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_vaga, parent, false);
        return new VagaViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull VagaViewHolder holder, int position) {
        Vaga v = lista.get(position);

        holder.tvCargo.setText(v.getCargo() != null ? v.getCargo() : "Cargo não informado");
        holder.tvEmpresa.setText(
                v.getEmpresa() != null ? v.getEmpresa().getNome_fantasia() : "Empresa desconhecida"
        );
        holder.tvLocal.setText(v.getCidade() + " - " + v.getEstado());

        holder.btnInteresse.setOnClickListener(view -> {
            if (listener != null) listener.onInteresse(v);
        });
    }

    @Override
    public int getItemCount() {
        return lista != null ? lista.size() : 0;
    }

    static class VagaViewHolder extends RecyclerView.ViewHolder {
        TextView tvCargo, tvEmpresa, tvLocal;
        Button btnInteresse;

        public VagaViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCargo = itemView.findViewById(R.id.tvCargo);
            tvEmpresa = itemView.findViewById(R.id.tvEmpresa);
            tvLocal = itemView.findViewById(R.id.tvLocal);
            btnInteresse = itemView.findViewById(R.id.btnInteresse);
        }
    }
}