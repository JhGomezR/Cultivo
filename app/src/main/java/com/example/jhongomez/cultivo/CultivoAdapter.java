package com.example.jhongomez.cultivo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class CultivoAdapter extends RecyclerView.Adapter<CultivoAdapter.ViewHolder>
{
    private ArrayList<Cultivo> dataset;
    private Context context;

    public CultivoAdapter(Context context)
    {
        this.context = context;
        dataset = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cultivo, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position)
    {
        Cultivo c = dataset.get(position);
        holder.grupo_de_cultivo.setText(c.getGrupo_de_cultivo().toString());
        holder.cultivo.setText(c.getCultivo().toString());
    }

    @Override
    public int getItemCount()
    {
        return dataset.size();
    }

    public void ListaCultivo(ArrayList<Cultivo> listaCultivo)
    {
        dataset.addAll(listaCultivo);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        private TextView grupo_de_cultivo;
        private TextView cultivo;


        public ViewHolder(View itemView)
        {
            super(itemView);

            cultivo = (TextView) itemView.findViewById(R.id.cultivo);
            grupo_de_cultivo = (TextView) itemView.findViewById(R.id.grupo_de_cultivo);
        }
    }


}
