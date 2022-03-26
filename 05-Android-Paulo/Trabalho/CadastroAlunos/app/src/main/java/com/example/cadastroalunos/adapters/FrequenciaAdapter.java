package com.example.cadastroalunos.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cadastroalunos.R;
import com.example.cadastroalunos.model.Frequencia;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

public class FrequenciaAdapter extends RecyclerView.Adapter<FrequenciaAdapter.FrequenciaViewHolder> {

    private List<Frequencia> frequenciaList;
    private Context context;
    private OnListenner onListenner;

    public FrequenciaAdapter(List<Frequencia> frequenciaList, OnListenner onListenner, Context context) {
        this.frequenciaList = frequenciaList;
        this.onListenner = onListenner;
        this.context = context;
    }

    public static class FrequenciaViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextInputEditText edTurma;
        TextInputEditText edNomeDiciplina;
        TextInputEditText edAluno;
        CheckBox cbPresente;

        OnListenner onListenner;

        public FrequenciaViewHolder(@NonNull View itemView, OnListenner onListenner) {
            super(itemView);

            this.onListenner = onListenner;

            edTurma = (TextInputEditText) itemView.findViewById(R.id.edTurma);
            edNomeDiciplina = (TextInputEditText) itemView.findViewById(R.id.edNomeDiciplina);
            edAluno = (TextInputEditText) itemView.findViewById(R.id.edAluno);
            cbPresente = (CheckBox) itemView.findViewById(R.id.cbPresente);

            edTurma.setOnClickListener(this);
            edNomeDiciplina.setOnClickListener(this);
            edAluno.setOnClickListener(this);
            cbPresente.setOnClickListener(this);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onListenner.onListennerClick(getAdapterPosition());
        }
    }

    @Override
    public FrequenciaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_frequencia, parent, false);
        FrequenciaViewHolder viewHolder = new FrequenciaViewHolder(view, onListenner);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FrequenciaViewHolder holder, int position) {
        Frequencia frequencia = frequenciaList.get(position);

        holder.edTurma.setText(frequencia.getTurma().toString());
        holder.edNomeDiciplina.setText(frequencia.getDiciplina().toString());
        holder.edAluno.setText(frequencia.getAluno().toString());
        holder.cbPresente.setChecked(frequencia.isPresente());
    }

    @Override
    public int getItemCount() {
        return frequenciaList.size();
    }

    public interface OnListenner {
        void onListennerClick(int position);
    }


}
