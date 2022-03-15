package com.example.cadastroalunos.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cadastroalunos.R;
import com.example.cadastroalunos.model.Curso;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

public class CursoAdapter extends RecyclerView.Adapter<CursoAdapter.CursoViewHolder> {

    private List<Curso> cursoList;
    private Context context;

    public CursoAdapter(List<Curso> cursoList, Context context) {
        this.cursoList = cursoList;
        this.context = context;
    }

    public static class CursoViewHolder extends RecyclerView.ViewHolder {
        TextInputEditText edIdCurso;
        TextInputEditText edNomeCurso;

        public CursoViewHolder(@NonNull View itemView) {
            super(itemView);

            edIdCurso = (TextInputEditText) itemView.findViewById(R.id.edIdCurso);
            edNomeCurso = (TextInputEditText) itemView.findViewById(R.id.edNomeCurso);
        }
    }

    @Override
    public CursoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_curso, parent, false);
        CursoViewHolder viewHolder = new CursoViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CursoViewHolder holder, int position) {
        Curso curso = cursoList.get(position);

        holder.edIdCurso.setText(curso.getId().toString());
        holder.edNomeCurso.setText(curso.getNome());
    }

    @Override
    public int getItemCount() {
        return cursoList.size();
    }
}
