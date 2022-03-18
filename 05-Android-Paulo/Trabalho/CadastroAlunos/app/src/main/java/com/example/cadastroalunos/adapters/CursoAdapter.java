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
    private OnListenner onListenner;

    public CursoAdapter(List<Curso> cursoList, OnListenner onListenner, Context context) {
        this.cursoList = cursoList;
        this.onListenner = onListenner;
        this.context = context;
    }

    public static class CursoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextInputEditText edIdCurso;
        TextInputEditText edNomeCurso;

        OnListenner onListenner;

        public CursoViewHolder(@NonNull View itemView, OnListenner onListenner) {
            super(itemView);

            this.onListenner = onListenner;

            edIdCurso = (TextInputEditText) itemView.findViewById(R.id.edIdCurso);
            edNomeCurso = (TextInputEditText) itemView.findViewById(R.id.edNomeCurso);

            edIdCurso.setOnClickListener(this);
            edNomeCurso.setOnClickListener(this);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onListenner.onListennerClick(getAdapterPosition());
        }
    }

    @Override
    public CursoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_curso, parent, false);
        CursoViewHolder viewHolder = new CursoViewHolder(view, onListenner);

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

    public interface OnListenner {
        void onListennerClick(int position);
    }


}
