package com.example.jhongomez.cultivo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.jhongomez.cultivo.DatosApiService.*;

import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity
{
    private Retrofit retrofit;
    private static final String TAG="AUTO";
    private RecyclerView recyclerView;
    private boolean aptoParaCargar;
    private CultivoAdapter CultivoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        CultivoAdapter = new CultivoAdapter(this);
        recyclerView.setAdapter(CultivoAdapter);

        recyclerView.setHasFixedSize(true);
        final GridLayoutManager layoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener()
        {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy)
            {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0)
                {
                    int visibleItemCount = layoutManager.getChildCount();
                    int totalItemCount = layoutManager.getItemCount();
                    int pastVisibleItems = layoutManager.findFirstVisibleItemPosition();

                    if (aptoParaCargar)
                    {
                        if ((visibleItemCount + pastVisibleItems) >= totalItemCount)
                        {
                            Log.i(TAG, " Llegamos al final.");
                            aptoParaCargar = false;
                            obtenerListaCultivo();
                        }
                    }
                }
            }
        });
        retrofit = new Retrofit.Builder()
                .baseUrl("https://www.datos.gov.co/resource/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        aptoParaCargar = true;
        obtenerListaCultivo();

    }

    private void obtenerListaCultivo()
    {
        DatosOpen service = retrofit.create(DatosOpen.class);
        Call<ArrayList<Cultivo>> cultivoRespuestaCall = service.obtenerListaCultivo();
        cultivoRespuestaCall.enqueue(new Callback<ArrayList<Cultivo>>()
        {
            @Override
            public void onResponse(Call<ArrayList<Cultivo>> call, Response<ArrayList<Cultivo>> response)
            {
                if(response.isSuccessful())
                {
                    ArrayList lista = response.body();
                    CultivoAdapter.ListaCultivo(lista);
                }
                else
                {
                    Log.e(TAG, "onResponse: "+response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Cultivo>> call, Throwable t)
            {
                Log.e(TAG," onFailure: "+t.getMessage());
            }
        });
    }

    public void pormi(View view)
    {
        Intent i = new Intent(this, Pormi.class);
        startActivity(i);
    }



}
