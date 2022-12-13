package com.example.reposteria;

import java.util.HashMap;

public class global {
    private HashMap<String,String> parametros = new HashMap<String,String>();

    public void setParametro (String clave,String valor) {
        parametros.put(clave, valor);
    }

    public String getParametro(String clave) {

        return parametros.get(clave);
    }
}
