package com.cristhoper.munidenuncias.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.cristhoper.munidenuncias.R;
import com.cristhoper.munidenuncias.models.Usuario;
import com.cristhoper.munidenuncias.services.ApiService;
import com.cristhoper.munidenuncias.services.ApiServiceGenerator;
import com.cristhoper.munidenuncias.services.ResponseMessage;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    EditText etUsername, etPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etUsername = findViewById(R.id.etUser);
        etPassword = findViewById(R.id.etPass);
    }

    public void iniciarSesion(View view){

        String usuario = etUsername.getText().toString();
        String contraseña = etPassword.getText().toString();

        if (usuario.isEmpty() || contraseña.isEmpty()) {
            Toast.makeText(this, "Complete los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        ApiService service = ApiServiceGenerator.createService(ApiService.class);

//        Call<ResponseMessage> call = null
        Call<Usuario> call = null;

        // Si no se incluye imagen hacemos un envío POST simple
        call = service.iniciarSesion(usuario, contraseña);

        call.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                try {

                    int statusCode = response.code();
                    Log.d(TAG, "HTTP status code: " + statusCode);

                    if (response.isSuccessful()) {

                        Usuario usuario = response.body();
                        Log.d(TAG, "usuario: " + usuario);

                        int usuario_id = usuario.getId();

                        Intent dashAct = new Intent(MainActivity.this, DashboardActivity.class);
                        dashAct.putExtra("usuario_id", usuario_id);

                        //Si la respuesta es satisfactoria pasamos al dashboard activity
                        startActivity(dashAct);
                        finish();
                    } else {
                        Log.e(TAG, "onError: " + response.errorBody().string());
                        throw new Exception("Error en el servicio");
                    }

                } catch (Throwable t) {
                    try {
                        Log.e(TAG, "onThrowable: " + t.toString(), t);
                        Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                    }catch (Throwable x){}
                }
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {

            }
        });
    }

    public void crearCuenta(View view){
        startActivity(new Intent(MainActivity.this, RegisterActivity.class));
    }
}
