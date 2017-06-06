package com.example.jhongomez.cultivo;

/**
 * Created by JhonGomez on 5/06/2017.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Cultivo
{
    private  String grupo_de_cultivo;
    private  String cultivo;

    /**
     * get y set
     * @return
     */
    public String getGrupo_de_cultivo() {
        return grupo_de_cultivo;
    }

    public void setGrupo_de_cultivo(String grupo_de_cultivo) {
        this.grupo_de_cultivo = grupo_de_cultivo;
    }

    public String getCultivo() {
        return cultivo;
    }

    public void setCultivo(String cultivo) {
        this.cultivo = cultivo;
    }
}
