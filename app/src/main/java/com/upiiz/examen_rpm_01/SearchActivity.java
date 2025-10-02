package com.upiiz.examen_rpm_01;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SearchActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnRegresar2, btnActualizar2, btnBuscar ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_search);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnBuscar = findViewById(R.id.btnBuscar);
        btnActualizar2 = findViewById(R.id.btnActualizar2);
        btnRegresar2 = findViewById(R.id.btnRegresar2);
        
        btnRegresar2.setOnClickListener(this);
        btnBuscar.setOnClickListener(this);
        btnActualizar2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId()==btnRegresar2.getId()){
            regresar();
        } else if (v.getId()==btnActualizar2.getId()) {
            actualizar();
        } else {
            buscar();
        }
    }

    private void buscar() {

    }

    private void actualizar() {
    }

    private void regresar() {
        Intent intentMainActivity = new Intent(this, MainActivity.class);
        startActivity(intentMainActivity);
        finish();
    }
}