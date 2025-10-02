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

public class DeleteActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnRegresar5, btnEliminar5, btnBuscar5 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_delete);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnBuscar5 = findViewById(R.id.btnBuscar5);
        btnRegresar5 = findViewById(R.id.btnRegresar5);
        btnEliminar5 = findViewById(R.id.btnEliminar5);

        btnBuscar5.setOnClickListener(this);
        btnEliminar5.setOnClickListener(this);
        btnRegresar5.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v.getId()==btnRegresar5.getId()){
            regresar();
        } else if (v.getId()==btnBuscar5.getId()) {
            buscar();

        }else {
            eliminar();
        }

    }

    private void eliminar() {
    }

    private void buscar() {
    }

    private void regresar() {
        Intent intentMainActivity = new Intent(this, MainActivity.class);
        startActivity(intentMainActivity);
        finish();
    }
}