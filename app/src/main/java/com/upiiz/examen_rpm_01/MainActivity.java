package com.upiiz.examen_rpm_01;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnAgregar1, btnActualizar1, btnEliminar1, btnCreditos1, btnSalir ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnAgregar1 = findViewById(R.id.btnAgregar1);
        btnActualizar1 = findViewById(R.id.btnActualizar1);
        btnEliminar1 = findViewById(R.id.btnEliminar1);
        btnCreditos1 = findViewById(R.id.btnCreditos1);
        btnSalir = findViewById(R.id.btnSalir);

        btnAgregar1.setOnClickListener(this);
        btnActualizar1.setOnClickListener(this);
        btnEliminar1.setOnClickListener(this);
        btnCreditos1.setOnClickListener(this);
        btnSalir.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        btnAgregar1 = findViewById(R.id.btnAgregar1);
        btnActualizar1 = findViewById(R.id.btnActualizar1);
        btnEliminar1 = findViewById(R.id.btnEliminar1);
        btnCreditos1 = findViewById(R.id.btnCreditos1);
        btnSalir = findViewById(R.id.btnSalir);

        // Tu lógica original igual
        if (v.getId()==btnEliminar1.getId()){
            eliminar();
        } else if (v.getId()==btnActualizar1.getId()) {
            actualizar();
        } else if (v.getId()==btnAgregar1.getId()) {
            agregar();
        } else if (v.getId()==btnCreditos1.getId()) {
            creditos();
        } else {
            finishAffinity();;
        }



    }

    private void eliminar() {

        Intent intentDeleteActivity = new Intent(this, DeleteActivity.class);
        startActivity(intentDeleteActivity);
    }

    private void creditos() {

        Intent intentCreditsActivity = new Intent(this, CreditsActivity.class);
        startActivity(intentCreditsActivity);

    }

    private void agregar() {

        Intent intentAddActivity = new Intent(this, AddActivity.class);
        startActivity(intentAddActivity);
    }

    private void actualizar() {

        Intent intentSearchActivity = new Intent(this, SearchActivity.class);
        startActivity(intentSearchActivity);
    }
}