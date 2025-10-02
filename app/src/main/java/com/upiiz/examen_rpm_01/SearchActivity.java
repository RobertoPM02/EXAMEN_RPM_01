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

import java.util.Date;
import java.util.Map;

public class SearchActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnRegresar2, btnActualizar2, btnBuscar ;

    EditText etDestino, etFsalida, etFregreso, etPrecio;

    String destino, fecha_salida, fecha_regreso;

    float precio;

    private String viajeKey = "";

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

        etDestino = findViewById(R.id.etDestino2);
        etFsalida = findViewById(R.id.etFsalida2);
        etFregreso = findViewById(R.id.etFregreso2);
        etPrecio = findViewById(R.id.etPrecio2);


        
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

        String destinoBuscado = etDestino.getText().toString();

        if (destinoBuscado.isEmpty()) {
            Toast.makeText(this, "Ingresa un destino para buscar", Toast.LENGTH_SHORT).show();
            return;
        }
        SharedPreferences sharedPref = getSharedPreferences("viajes_data", MODE_PRIVATE);
        Map<String, ?> allEntries = sharedPref.getAll();

        boolean encontrado = false;

        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            if (entry.getKey().endsWith("_destino") && entry.getValue().equals(destinoBuscado)) {
                // Encontramos el destino, ahora obtener los demás datos
                String keyBase = entry.getKey().replace("_destino", "");

                String fechaSalida = sharedPref.getString(keyBase + "_fecha_salida", "");
                String fechaRegreso = sharedPref.getString(keyBase + "_fecha_regreso", "");
                float precio = sharedPref.getFloat(keyBase + "_precio", 0);

                // Llenar los campos
                etFsalida.setText(fechaSalida);
                etFregreso.setText(fechaRegreso);
                etPrecio.setText(String.valueOf(precio));


                viajeKey = keyBase;
                encontrado = true;

                Toast.makeText(this, "Viaje encontrado", Toast.LENGTH_SHORT).show();
                break;
            }
        }

        if (!encontrado) {
            Toast.makeText(this, "No se encontró el viaje", Toast.LENGTH_SHORT).show();
        }

    }

    private void actualizar() {

        if (viajeKey.isEmpty()) {
            Toast.makeText(this, "Primero busca un viaje para actualizar", Toast.LENGTH_SHORT).show();
            return;
        }

        String nuevoDestino = etDestino.getText().toString();
        String nuevaFechaSalida = etFsalida.getText().toString();
        String nuevaFechaRegreso = etFregreso.getText().toString();
        String nuevoPrecioStr = etPrecio.getText().toString();

        if (nuevoDestino.isEmpty() || nuevoPrecioStr.isEmpty()) {
            Toast.makeText(this, "Destino y precio son obligatorios", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            float nuevoPrecio = Float.parseFloat(nuevoPrecioStr);

            SharedPreferences sharedPref = getSharedPreferences("viajes_data", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();

            editor.putString(viajeKey + "_destino", nuevoDestino);
            editor.putString(viajeKey + "_fecha_salida", nuevaFechaSalida);
            editor.putString(viajeKey + "_fecha_regreso", nuevaFechaRegreso);
            editor.putFloat(viajeKey + "_precio", nuevoPrecio);

            editor.apply();

            Toast.makeText(this, "Viaje actualizado exitosamente", Toast.LENGTH_SHORT).show();
            viajeKey = "";

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