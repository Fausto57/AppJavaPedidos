package com.example.reposteria;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ActEntrega extends AppCompatActivity {
    private String URL = "https://royalback.herokuapp.com/api/obtenerentrega/";
    private RecyclerView recyclerView;
    List<dataGetSet> Listadata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_entrega);

        recyclerView = findViewById(R.id.reciclerview2);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Listadata = new ArrayList<>();
        ObtenerLista();
    }

    public void ObtenerLista() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, response ->  {
            JSONArray array = null;
            dataGetSet data = new dataGetSet();
            try {
                array = new JSONArray(response);
                for (int i = 0; i < array.length(); i++) {
                    JSONObject object = array.getJSONObject(i);
                    String id = object.get("IDLineaPedido").toString();
                    String nom = object.get("Nombre").toString();
                    String dir = object.get("Direccion").toString();
                    String ped = object.get("Toping").toString();

                    data.setId(id);
                    data.setNombre(nom);
                    data.setDireccion(dir);
                    data.setPedido(ped);
                    Listadata.add(new dataGetSet(id+"",nom+"", dir+"", ped+""));
                }
                recyclerView.setAdapter(new Adaptador2(Listadata,this));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, error -> {
            Toast.makeText(this, "Error, no hay datos", Toast.LENGTH_SHORT).show();
        });
        Volley.newRequestQueue(this).add(stringRequest);
    }
}