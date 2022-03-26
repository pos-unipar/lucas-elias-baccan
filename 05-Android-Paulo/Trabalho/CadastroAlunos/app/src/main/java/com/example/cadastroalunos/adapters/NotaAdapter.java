package com.example.cadastroalunos.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cadastroalunos.R;
import com.example.cadastroalunos.model.Nota;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

public class NotaAdapter extends RecyclerView.Adapter<NotaAdapter.NotaViewHolder> {

    private List<Nota> notaList;
    private Context context;
    private OnListenner onListenner;

    public NotaAdapter(List<Nota> notaList, OnListenner onListenner, Context context) {
        this.notaList = notaList;
        this.onListenner = onListenner;
        this.context = context;
    }

    public static class NotaViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextInputEditText edIdCurso;
        TextInputEditText edNomeCurso;

        OnListenner onListenner;

        public NotaViewHolder(@NonNull View itemView, OnListenner onListenner) {
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
    public NotaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_nota, parent, false);
        NotaViewHolder viewHolder = new NotaViewHolder(view, onListenner);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NotaViewHolder holder, int position) {
        Nota nota = notaList.get(position);

        holder.edIdCurso.setText(nota.getId().toString());
        holder.edNomeCurso.setText(String.valueOf(nota.getId()));
    }

    @Override
    public int getItemCount() {
        return notaList.size();
    }

    public interface OnListenner {
        void onListennerClick(int position);
    }

}
