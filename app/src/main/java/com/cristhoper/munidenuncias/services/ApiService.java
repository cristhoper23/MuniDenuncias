package com.cristhoper.munidenuncias.services;

import com.cristhoper.munidenuncias.models.Denuncia;
import com.cristhoper.munidenuncias.models.Usuario;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

/**
 * Created by Cris on 13/11/2017.
 */

public interface ApiService {

    String API_BASE_URL = "https://muni-denuncias-cristhoper23.c9users.io";

    @GET("api/v1/denuncias")
    Call<List<Denuncia>> getAllDenuncias();

    @GET("api/v1/denuncias_usuario/{usuario_id}")
    Call<List<Denuncia>> getDenuncias(@Path("usuario_id") int usuario_id);

    /*@Path("usuario_id") int usuario_id*/
//    @GET("api/v1/productos/{id}")
//    Call<Usuario> obtenerUsuario(@Path("id") Integer id);

    @FormUrlEncoded
    @POST("/api/v1/login")
    Call<Usuario> iniciarSesion(@Field("username") String username,
                                @Field("password") String password);

    @FormUrlEncoded
    @POST("/api/v1/register")
    Call<ResponseMessage> registrarUsuario(@Field("username") String nombre,
                                         @Field("password") String precio,
                                         @Field("email") String detalles,
                                         @Field("fullname") String fullname);

    @GET("api/v1/denuncias/{id}")
    Call<Denuncia> showDenuncia(@Path("id") Integer id);

    //Registro de denuncia --------->
    @FormUrlEncoded
    @POST("/api/v1/denuncias")
    Call<ResponseMessage> registrarDenuncia(@Field("usuario_id") String usuario_id,
                                            @Field("titulo") String titulo,
                                            @Field("descripcion") String descripcion,
                                            @Field("ubicacion") String ubicacion);
    @Multipart
    @POST("/api/v1/denuncias")
    Call<ResponseMessage> registrarDenunciaConImagen(
            @Part("usuario_id") RequestBody usuario_id,
            @Part("titulo") RequestBody titulo,
            @Part("descripcion") RequestBody descripcion,
            @Part("ubicacion") RequestBody ubicacion,
            @Part MultipartBody.Part imagen
    );
    //------------------------------->
}
