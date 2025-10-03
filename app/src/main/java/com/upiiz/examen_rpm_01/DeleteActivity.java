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

import java.util.Map;

public class DeleteActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnRegresar5, btnEliminar5, btnBuscar5;
    EditText etDestino, etFsalida, etFregreso, etPrecio;
    private String viajeKey = "";

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

        etDestino = findViewById(R.id.etDestino5);
        etFsalida = findViewById(R.id.etFsalida5);
        etFregreso = findViewById(R.id.etFregreso5);
        etPrecio = findViewById(R.id.etPrecio5);

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

        SharedPreferences sharedPref = getSharedPreferences("viajes_data", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        editor.remove(viajeKey + "_destino");
        editor.remove(viajeKey + "_fecha_salida");
        editor.remove(viajeKey + "_fecha_regreso");
        editor.remove(viajeKey + "_precio");

        editor.apply();

        etDestino.setText("");
        etFsalida.setText("");
        etFregreso.setText("");
        etPrecio.setText("");

        viajeKey = "";
        Toast.makeText(this, "Viaje eliminado", Toast.LENGTH_SHORT).show();
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
                String keyBase = entry.getKey().replace("_destino", "");

                String fechaSalida = sharedPref.getString(keyBase + "_fecha_salida", "");
                String fechaRegreso = sharedPref.getString(keyBase + "_fecha_regreso", "");
                float precio = sharedPref.getFloat(keyBase + "_precio", 0);


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

    private void regresar() {
        Intent intentMainActivity = new Intent(this, MainActivity.class);
        startActivity(intentMainActivity);
        finish();
    }
}