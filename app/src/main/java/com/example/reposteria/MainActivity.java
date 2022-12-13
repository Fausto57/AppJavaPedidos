package com.example.reposteria;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity{
    private Adaptador adaptador;
    private String URL = "https://royalback.herokuapp.com/api/obtenerterminados/";

    Button btnTermina;
    Button btnEntrega;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnTermina = findViewById(R.id.btnAbreTermina);
        btnEntrega = findViewById(R.id.btnAbreEntrega);

        btnTermina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ActTermina.class);
                startActivity(intent);
            }
        });

        btnEntrega.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ActEntrega.class);
                startActivity(intent);
            }
        });

    }

}