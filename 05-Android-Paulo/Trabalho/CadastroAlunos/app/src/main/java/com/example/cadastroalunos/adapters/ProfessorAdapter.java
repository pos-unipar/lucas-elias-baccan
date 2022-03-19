package com.example.cadastroalunos.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cadastroalunos.R;
import com.example.cadastroalunos.model.Professor;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

public class ProfessorAdapter extends RecyclerView.Adapter<ProfessorAdapter.ProfessorViewHolder> {

    private List<Professor> listaProfessores;
    private Context context;
    private OnListenner onListenner;

    public ProfessorAdapter(List<Professor> listaProfessores, OnListenner onListenner, Context context) {
        this.listaProfessores = listaProfessores;
        this.onListenner = onListenner;
        this.context = context;
    }

    public static class ProfessorViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextInputEditText edRaProfessor;
        TextInputEditText edNomeProfessor;
        TextInputEditText edCpfProfessor;
        TextInputEditText edDtMatProfessor;
        TextInputEditText edDtNasc;

        OnListenner onListenner;

        public ProfessorViewHolder(@NonNull View itemView, OnListenner onListenner) {
            super(itemView);

            this.onListenner = onListenner;

            edRaProfessor = (TextInputEditText) itemView.findViewById(R.id.edRaProfessor);
            edNomeProfessor = (TextInputEditText) itemView.findViewById(R.id.edNomeProfessor);
            edCpfProfessor = (TextInputEditText) itemView.findViewById(R.id.edCpfProfessor);
            edDtMatProfessor = (TextInputEditText) itemView.findViewById(R.id.edDtMatProfessor);
            edDtNasc = (TextInputEditText) itemView.findViewById(R.id.edDtNascProfessor);

            edRaProfessor.setOnClickListener(this);
            edNomeProfessor.setOnClickListener(this);
            edCpfProfessor.setOnClickListener(this);
            edDtMatProfessor.setOnClickListener(this);
            edDtNasc.setOnClickListener(this);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onListenner.onListennerClick(getAdapterPosition());
        }
    }

    @Override
    public ProfessorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_professor, parent, false);
        ProfessorViewHolder viewHolder = new ProfessorViewHolder(view, onListenner);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProfessorViewHolder holder, int position) {
        Professor professor = listaProfessores.get(position);

        holder.edRaProfessor.setText(String.valueOf(professor.getRa()));
        holder.edCpfProfessor.setText(professor.getCpf());
        holder.edNomeProfessor.setText(professor.getNome());
        holder.edDtMatProfessor.setText(professor.getDtMatricula());
        holder.edDtNasc.setText(professor.getDtNasc());
    }

    @Override
    public int getItemCount() {
        return listaProfessores.size();
    }

    public interface OnListenner {
        void onListennerClick(int position);
    }
}
