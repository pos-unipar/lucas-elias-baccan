package com.example.cadastroalunos.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cadastroalunos.R;
import com.example.cadastroalunos.model.Aluno;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

public class AlunoAdapter extends RecyclerView.Adapter<AlunoAdapter.AlunoViewHolder> {

    private List<Aluno> listaAlunos;
    private Context context;
    private OnListenner onListenner;

    public AlunoAdapter(List<Aluno> listaAlunos, OnListenner onListenner, Context context) {
        this.listaAlunos = listaAlunos;
        this.onListenner = onListenner;
        this.context = context;
    }

    public static class AlunoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextInputEditText edRaAluno;
        TextInputEditText edNomeAluno;
        TextInputEditText edCpfAluno;
        TextInputEditText edCurso;
        TextInputEditText edPeriodo;
        TextInputEditText edDtMatAluno;
        TextInputEditText edDtNasc;

        OnListenner onListenner;

        public AlunoViewHolder(@NonNull View itemView, OnListenner onListenner) {
            super(itemView);

            this.onListenner = onListenner;

            edRaAluno = (TextInputEditText) itemView.findViewById(R.id.edRaAluno);
            edNomeAluno = (TextInputEditText) itemView.findViewById(R.id.edNomeAluno);
            edCpfAluno = (TextInputEditText) itemView.findViewById(R.id.edCpfAluno);
            edCurso = (TextInputEditText) itemView.findViewById(R.id.edCursoAluno);
            edPeriodo = (TextInputEditText) itemView.findViewById(R.id.edPeriodoAluno);
            edDtMatAluno = (TextInputEditText) itemView.findViewById(R.id.edDtMatAluno);
            edDtNasc = (TextInputEditText) itemView.findViewById(R.id.edDtNascAluno);

            edRaAluno.setOnClickListener(this);
            edNomeAluno.setOnClickListener(this);
            edCpfAluno.setOnClickListener(this);
            edCurso.setOnClickListener(this);
            edPeriodo.setOnClickListener(this);
            edDtMatAluno.setOnClickListener(this);
            edDtNasc.setOnClickListener(this);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onListenner.onListennerClick(getAdapterPosition());
        }
    }

    @Override
    public AlunoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_aluno, parent, false);
        AlunoAdapter.AlunoViewHolder viewHolder = new AlunoViewHolder(view, onListenner);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AlunoViewHolder holder, int position) {
        Aluno aluno = listaAlunos.get(position);

        holder.edRaAluno.setText(String.valueOf(aluno.getRa()));
        holder.edCpfAluno.setText(aluno.getCpf());
        holder.edNomeAluno.setText(aluno.getNome());
        holder.edCurso.setText(aluno.getCurso().getNome());
        holder.edPeriodo.setText(aluno.getPeriodo().toString());
        holder.edDtMatAluno.setText(aluno.getDtMatricula());
        holder.edDtNasc.setText(aluno.getDtNasc());
    }

    @Override
    public int getItemCount() {
        return listaAlunos.size();
    }

    public interface OnListenner {
        void onListennerClick(int position);
    }
}
