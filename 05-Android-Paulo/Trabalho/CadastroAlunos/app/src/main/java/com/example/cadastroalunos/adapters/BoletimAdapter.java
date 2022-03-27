package com.example.cadastroalunos.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cadastroalunos.R;
import com.example.cadastroalunos.model.Boletim;
import com.example.cadastroalunos.model.Curso;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

public class BoletimAdapter extends RecyclerView.Adapter<BoletimAdapter.BoletimViewHolder> {

    private List<Boletim> boletimList;
    private Context context;

    public BoletimAdapter(List<Boletim> boletimList,  Context context) {
        this.boletimList = boletimList;
        this.context = context;
    }

    public static class BoletimViewHolder extends RecyclerView.ViewHolder {
        TextInputEditText edTurma;
        TextInputEditText edNomeDiciplina;
        TextInputEditText edAluno;
        TextInputEditText edMedia;
        TextInputEditText edFrequencia;
        CheckBox cbAprovado;

        public BoletimViewHolder(@NonNull View itemView) {
            super(itemView);

            edTurma = (TextInputEditText) itemView.findViewById(R.id.edTurma);
            edNomeDiciplina = (TextInputEditText) itemView.findViewById(R.id.edNomeDiciplina);
            edAluno = (TextInputEditText) itemView.findViewById(R.id.edAluno);
            edMedia = (TextInputEditText) itemView.findViewById(R.id.edMedia);
            edFrequencia = (TextInputEditText) itemView.findViewById(R.id.edFrequencia);
            cbAprovado = (CheckBox) itemView.findViewById(R.id.cbAprovado);
        }
    }

    @Override
    public BoletimViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_boletim, parent, false);
        BoletimViewHolder viewHolder = new BoletimViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BoletimViewHolder holder, int position) {
        Boletim boletim = boletimList.get(position);

        holder.edTurma.setText(boletim.getTurma().toString());
        holder.edNomeDiciplina.setText(boletim.getDiciplina().toString());
        holder.edAluno.setText(boletim.getAluno().toString());
        holder.edMedia.setText(boletim.getMedia());
        holder.edFrequencia.setText(boletim.getFrequencia());
        holder.cbAprovado.setChecked(boletim.isAprovado());
    }

    @Override
    public int getItemCount() {
        return boletimList.size();
    }

}
