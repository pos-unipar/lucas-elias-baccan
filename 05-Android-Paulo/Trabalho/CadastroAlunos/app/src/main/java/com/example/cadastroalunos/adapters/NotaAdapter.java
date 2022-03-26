package com.example.cadastroalunos.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cadastroalunos.R;
import com.example.cadastroalunos.enums.RegimeEnum;
import com.example.cadastroalunos.model.Nota;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

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

    public static class NotaViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextInputEditText edTurma;
        TextInputEditText edNomeDiciplina;
        TextInputEditText edAluno;
        TextInputEditText edNota1;
        TextInputEditText edNota2;
        TextInputEditText edNota3;
        TextInputLayout edNota3Input;
        TextInputEditText edNota4;
        TextInputLayout edNota4Input;

        OnListenner onListenner;

        public NotaViewHolder(@NonNull View itemView, OnListenner onListenner) {
            super(itemView);

            this.onListenner = onListenner;

            edTurma = (TextInputEditText) itemView.findViewById(R.id.edTurma);
            edNomeDiciplina = (TextInputEditText) itemView.findViewById(R.id.edNomeDiciplina);
            edAluno = (TextInputEditText) itemView.findViewById(R.id.edAluno);
            edNota1 = (TextInputEditText) itemView.findViewById(R.id.edNota1);
            edNota2 = (TextInputEditText) itemView.findViewById(R.id.edNota2);
            edNota3 = (TextInputEditText) itemView.findViewById(R.id.edNota3);
            edNota3Input = (TextInputLayout) itemView.findViewById(R.id.edNota3Input);
            edNota4 = (TextInputEditText) itemView.findViewById(R.id.edNota4);
            edNota4Input = (TextInputLayout) itemView.findViewById(R.id.edNota4Input);

            edTurma.setOnClickListener(this);
            edNomeDiciplina.setOnClickListener(this);
            edAluno.setOnClickListener(this);
            edNota1.setOnClickListener(this);
            edNota2.setOnClickListener(this);
            edNota3.setOnClickListener(this);
            edNota4.setOnClickListener(this);

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

        holder.edTurma.setText(nota.getTurma().toString());
        holder.edNomeDiciplina.setText(nota.getDiciplina().toString());
        holder.edAluno.setText(nota.getAluno().toString());
        holder.edNota1.setText(String.valueOf(nota.getNota1()));
        holder.edNota2.setText(String.valueOf(nota.getNota2()));
        if (nota.getTurma().getRegime() == RegimeEnum.SEMESTRAL) {
            holder.edNota3Input.setVisibility(View.GONE);
            holder.edNota4Input.setVisibility(View.GONE);
        } else {
            holder.edNota3.setText(String.valueOf(nota.getNota3()));
            holder.edNota4.setText(String.valueOf(nota.getNota4()));
        }
    }

    @Override
    public int getItemCount() {
        return notaList.size();
    }

    public interface OnListenner {
        void onListennerClick(int position);
    }

}
