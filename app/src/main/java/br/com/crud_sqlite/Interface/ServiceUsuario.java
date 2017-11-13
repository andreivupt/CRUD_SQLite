package br.com.crud_sqlite.Interface;

import java.util.List;

import br.com.crud_sqlite.Model.UsuarioGitHub;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

/**
 * Created by andrei.vupt on 12/11/2017.
 */

public interface ServiceUsuario {

    public static final String URL = "https://api.github.com/";
    public static final Retrofit retrofit =  new Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    @GET("users")
    Call<List<UsuarioGitHub>> getUsers();

}
