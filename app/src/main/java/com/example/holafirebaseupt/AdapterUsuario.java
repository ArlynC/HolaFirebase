package com.example.holafirebaseupt;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class AdapterUsuario extends RecyclerView.Adapter<AdapterUsuario.ViewHolder> {
    private LayoutInflater inflador; ArrayList<Usuario> datos; android.content.Context micontext;
    public AdapterUsuario(android.content.Context context, ArrayList<Usuario> datos) {
        this.datos= datos;
        micontext=context;
        inflador = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = inflador.inflate(R.layout.item_usuario, parent, false);
        return new ViewHolder(v);
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, final int i) {
        holder.miid.setText(datos.get(i).getId());
        holder.usu.setText(datos.get(i).getUsu());
        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(micontext, DetalleProducto.class);
                intent.putExtra("miid",datos.get(i).getId());
                intent.putExtra("usu",datos.get(i).getUsu());
            }
        });
    }
    @Override
    public int getItemCount() {
        return datos.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView miid, usu;
        ViewHolder(View itemView) {
            super(itemView);
            miid = (TextView)itemView.findViewById(R.id.id_text);
            usu = (TextView)itemView.findViewById(R.id.nombre);
        }
    }
}