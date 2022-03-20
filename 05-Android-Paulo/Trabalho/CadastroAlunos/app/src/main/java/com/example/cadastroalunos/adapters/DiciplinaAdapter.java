package com.example.cadastroalunos.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cadastroalunos.R;
import com.example.cadastroalunos.model.Diciplina;
import com.google.android.material.textfield.TextInputEditText;

import org.w3c.dom.Text;

import java.util.List;

public class DiciplinaAdapter extends RecyclerView.Adapter<DiciplinaAdapter.DiciplinaViewHolder> {

    private List<Diciplina> listaDiciplinas;
    private Context context;
    private OnListenner onListenner;

    public DiciplinaAdapter(List<Diciplina> listaDiciplinas, OnListenner onListenner, Context context) {
        this.listaDiciplinas = listaDiciplinas;
        this.onListenner = onListenner;
        this.context = context;
    }

    public static class DiciplinaViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextInputEditText edIdDiciplina;
        TextInputEditText edNomeDiciplina;
        TextInputEditText atProfessor;

        OnListenner onListenner;

        public DiciplinaViewHolder(@NonNull View itemView, OnListenner onListenner) {
            super(itemView);

            this.onListenner = onListenner;

            edIdDiciplina = (TextInputEditText) itemView.findViewById(R.id.edIdDiciplina);
            edNomeDiciplina = (TextInputEditText) itemView.findViewById(R.id.edNomeDiciplina);
            atProfessor = (TextInputEditText) itemView.findViewById(R.id.atProfessor);

            edIdDiciplina.setOnClickListener(this);
            edNomeDiciplina.setOnClickListener(this);
            atProfessor.setOnClickListener(this);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onListenner.onListennerClick(getAdapterPosition());
        }
    }

    @Override
    public DiciplinaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_diciplina, parent, false);
        DiciplinaViewHolder viewHolder = new DiciplinaViewHolder(view, onListenner);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DiciplinaViewHolder holder, int position) {
        Diciplina diciplina = listaDiciplinas.get(position);

        holder.edIdDiciplina.setText(String.valueOf(diciplina.getId()));
        holder.edNomeDiciplina.setText(diciplina.getNome());
        holder.atProfessor.setText(diciplina.getProfessor().toString());
    }

    @Override
    public int getItemCount() {
        return listaDiciplinas.size();
    }

    public interface OnListenner {
        void onListennerClick(int position);
    }
}
