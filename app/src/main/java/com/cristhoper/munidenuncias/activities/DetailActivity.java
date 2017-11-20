package com.cristhoper.munidenuncias.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cristhoper.munidenuncias.R;
import com.cristhoper.munidenuncias.models.Denuncia;
import com.cristhoper.munidenuncias.services.ApiService;
import com.cristhoper.munidenuncias.services.ApiServiceGenerator;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity {

    private static final String TAG = DetailActivity.class.getSimpleName();
    Integer id;

    ImageView imagen;
    TextView titulo, descripcion, ubicacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        imagen = findViewById(R.id.img_detail_denuncia);
        titulo = findViewById(R.id.tv_titulo);
        descripcion = findViewById(R.id.tv_descripcion);
        ubicacion = findViewById(R.id.tv_ubicacion);

        id = getIntent().getExtras().getInt("ID");

        initialize();
    }

    private void initialize() {

        ApiService service = ApiServiceGenerator.createService(ApiService.class);

        Call<Denuncia> call = service.showDenuncia(id);

        call.enqueue(new Callback<Denuncia>() {
            @Override
            public void onResponse(Call<Denuncia> call, Response<Denuncia> response) {
                try {

                    int statusCode = response.code();
                    Log.d(TAG, "HTTP status code: " + statusCode);

                    if (response.isSuccessful()) {

                        Denuncia denuncia = response.body();
                        Log.d(TAG, "denuncia: " + denuncia);

                        String url = ApiService.API_BASE_URL + "/images/denuncias/" + denuncia.getImagen();
                        Picasso.with(DetailActivity.this).load(url).into(imagen);

                        titulo.setText(denuncia.getTitulo());
                        descripcion.setText(denuncia.getDescripcion());
                        ubicacion.setText(denuncia.getUbicacion());

                    } else {
                        Log.e(TAG, "onError: " + response.errorBody().string());
                        throw new Exception("Error en el servicio");
                    }

                } catch (Throwable t) {
                    try {
                        Log.e(TAG, "onThrowable: " + t.toString(), t);
                        Toast.makeText(DetailActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                    }catch (Throwable x){}
                }
            }

            @Override
            public void onFailure(Call<Denuncia> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.toString());
                Toast.makeText(DetailActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }

        });
    }
}
