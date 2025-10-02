package com.upiiz.examen_rpm_01;

import android.content.Intent;
import android.content.SharedPreferences;
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

    EditText etDestino, etFsalida, etFregreso, etPrecio;

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
        etPrecio = findViewById(R.id.etPrecio1);

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
        String fecha_salida = etFsalida.getText().toString();
        String fecha_regreso = etFregreso.getText().toString();
        String precioStr = etPrecio.getText().toString();

        if(destino.isEmpty() || precioStr.isEmpty()) {
            Toast.makeText(this, "Destino y precio son obligatorios", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            double precio = Double.parseDouble(precioStr);


            SharedPreferences sharedPref = getSharedPreferences("viajes_data", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();

            // Guardar los datos (puedes guardar múltiples viajes con claves únicas)
            String viajeKey = "viaje_" + System.currentTimeMillis(); // Clave única

            editor.putString(viajeKey + "_destino", destino);
            editor.putString(viajeKey + "_fecha_salida", fecha_salida);
            editor.putString(viajeKey + "_fecha_regreso", fecha_regreso);
            editor.putFloat(viajeKey + "_precio", (float) precio);

            editor.apply();

            Toast.makeText(this, "Viaje guardado ", Toast.LENGTH_SHORT).show();
            finish();

        } catch (NumberFormatException e) {
            Toast.makeText(this, "El precio debe ser un número válido", Toast.LENGTH_SHORT).show();
        }

    }

    private void regresar() {
        Intent intentMainActivity = new Intent(this, MainActivity.class);
        startActivity(intentMainActivity);
        finish();
    }
}