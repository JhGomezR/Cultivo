package com.example.jhongomez.cultivo.DatosApiService;

import com.example.jhongomez.cultivo.Cultivo;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface DatosOpen
{
    @GET("7vra-xqsw.json")
    Call<ArrayList<Cultivo>> obtenerListaCultivo();

}
