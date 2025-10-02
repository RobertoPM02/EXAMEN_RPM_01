package com.upiiz.examen_rpm_01;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.sql.Date;

public class AddActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnAgregar2, btnRegresar1;

    EditText etDestino, etFsalida, etFregreso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnAgregar2 = findViewById(R.id.btnAgregar2);
        btnRegresar1 = findViewById(R.id.btnRegresar1);
        etDestino = findViewById(R.id.etDestino);
        etFsalida = findViewById(R.id.etFsalida);
        etFregreso = findViewById(R.id.etFregreso);

        btnAgregar2.setOnClickListener(this);
        btnRegresar1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId()==btnRegresar1.getId()){
            regresar();
        }else{
            agregar();
        }
    }

    private void agregar() {
        String destino = etDestino.getText().toString();
        // Date fecha_salida = etFsalida.getText().insert();
        // Date fecha_regreso = etFregreso.getText().insert();





        Toast.makeText(this, "Viaje guardado", Toast.LENGTH_SHORT).show();

    }

    private void regresar() {
        Intent intentMainActivity = new Intent(this, MainActivity.class);
        startActivity(intentMainActivity);
        finish();
    }
}