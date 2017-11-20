package com.cristhoper.munidenuncias.adapters;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cristhoper.munidenuncias.R;
import com.cristhoper.munidenuncias.activities.DetailActivity;
import com.cristhoper.munidenuncias.models.Denuncia;
import com.cristhoper.munidenuncias.services.ApiService;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cris on 15/11/2017.
 */

public class DenunciasAdapter extends RecyclerView.Adapter<DenunciasAdapter.ViewHolder>{

    private static final String TAG = DenunciasAdapter.class.getSimpleName();

    private List<Denuncia> denuncias;

    private Activity activity;

    public DenunciasAdapter(Activity activity){
        this.denuncias = new ArrayList<>();
        this.activity = activity;
    }

    public void setDenuncias(List<Denuncia> denuncias){
        this.denuncias = denuncias;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imagen;
        TextView titulo, ubicacion, usuario;

        public ViewHolder(View itemView) {
            super(itemView);

            imagen = itemView.findViewById(R.id.imgDenuncia);
            titulo = itemView.findViewById(R.id.tvTitulo);
            ubicacion = itemView.findViewById(R.id.tvUbicacion);
            usuario = itemView.findViewById(R.id.tv_item_Username);
        }
    }

    @Override
    public DenunciasAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemview = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_denuncia, parent, false);
        return new ViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(DenunciasAdapter.ViewHolder holder, int position) {

        final Denuncia denuncia = this.denuncias.get(position);

        holder.titulo.setText(denuncia.getTitulo());
        holder.ubicacion.setText(denuncia.getUbicacion());
        holder.usuario.setText(denuncia.getUsername());
        Log.d("username", "Usuario: " + denuncia.getUsername());

        String url = ApiService.API_BASE_URL + "/images/denuncias/" + denuncia.getImagen();
        Picasso.with(holder.itemView.getContext()).load(url).into(holder.imagen);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, DetailActivity.class);
                intent.putExtra("ID", denuncia.getId());
                activity.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return denuncias.size();
    }

}
