package com.example.cadastroalunos.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cadastroalunos.R;
import com.example.cadastroalunos.model.Turma;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

public class TurmaAdapter extends RecyclerView.Adapter<TurmaAdapter.TurmaViewHolder> {

    private List<Turma> turmaList;
    private Context context;
    private OnListenner onListenner;

    public TurmaAdapter(List<Turma> turmaList, OnListenner onListenner, Context context) {
        this.turmaList = turmaList;
        this.onListenner = onListenner;
        this.context = context;
    }

    public static class TurmaViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextInputEditText edIdTurma;
        TextInputEditText edNomeCurso;
        TextInputEditText edQtdAlunos;
        TextInputEditText edQtdDiciplinas;

        OnListenner onListenner;

        public TurmaViewHolder(@NonNull View itemView, OnListenner onListenner) {
            super(itemView);

            this.onListenner = onListenner;

            edIdTurma = (TextInputEditText) itemView.findViewById(R.id.edIdTurma);
            edNomeCurso = (TextInputEditText) itemView.findViewById(R.id.edNomeCurso);
            edQtdAlunos = (TextInputEditText) itemView.findViewById(R.id.edQtdAlunos);
            edQtdDiciplinas = (TextInputEditText) itemView.findViewById(R.id.edQtdDiciplinas);

            edIdTurma.setOnClickListener(this);
            edNomeCurso.setOnClickListener(this);
            edQtdAlunos.setOnClickListener(this);
            edQtdDiciplinas.setOnClickListener(this);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onListenner.onListennerClick(getAdapterPosition());
        }
    }

    @Override
    public TurmaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_turma, parent, false);
        TurmaViewHolder viewHolder = new TurmaViewHolder(view, onListenner);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TurmaViewHolder holder, int position) {
        Turma turma = turmaList.get(position);

        holder.edIdTurma.setText(turma.getId().toString());
        holder.edNomeCurso.setText(turma.getCurso().getNome());
        holder.edQtdAlunos.setText(String.valueOf(turma.getAlunos().size()));
        holder.edQtdDiciplinas.setText(String.valueOf(turma.getDiciplinas().size()));
    }

    @Override
    public int getItemCount() {
        return turmaList.size();
    }

    public interface OnListenner {
        void onListennerClick(int position);
    }
}
