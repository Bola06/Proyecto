package com.example.registro;

import android.annotation.SuppressLint;
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

public class MainActivity3 extends AppCompatActivity {

    Button btnPrincipal;
    API api;

    EditText etNombre,etEmail,etContraseña,etConfContra;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main3);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        api = new API();//IMPORTANTE

        etNombre = findViewById(R.id.etNombre);
        etEmail = findViewById(R.id.etEmail);
        etContraseña = findViewById(R.id.etContraseña);
        etConfContra = findViewById(R.id.etConfCont);
        btnPrincipal = findViewById(R.id.button);

        btnPrincipal.setOnClickListener(new View.OnClickListener() { //ARREGLAR LOS ESPACIOS
            @Override
            public void onClick(View v) {
                if (etNombre.getText().length() == 0){
                    Toast toast=Toast.makeText(getApplicationContext(), "Debes rellenar el nombre de usuario",
                            Toast.LENGTH_LONG);
                    toast.show();
                }else if (etEmail.getText().length() == 0){
                    Toast toast=Toast.makeText(getApplicationContext(), "Debes rellenar el correo electrónico",
                            Toast.LENGTH_LONG);
                    toast.show();
                }else if (etContraseña.getText().length() == 0){
                    Toast toast=Toast.makeText(getApplicationContext(), "Debes seleccionar una contraseña",
                            Toast.LENGTH_LONG);
                    toast.show();
                }else if (etContraseña.getText() == etConfContra.getText()){
                    Toast toast=Toast.makeText(getApplicationContext(), "La contraseña no es la misma",
                            Toast.LENGTH_LONG);
                    toast.show();
                }else {


                    if (etContraseña.getText().toString().equals(etConfContra.getText().toString())) {

                        String nombre = etNombre.getText().toString();
                        String correo = etEmail.getText().toString();
                        String contraseña = etContraseña.getText().toString();

                        api.subirUsuario(nombre,correo,contraseña);

                        Intent intent = new Intent(MainActivity3.this, MainActivity2.class);
                        startActivity(intent);
                    }else{
                        Toast toast=Toast.makeText(getApplicationContext(), "Las contraseñas no coinciden",
                                Toast.LENGTH_LONG);
                        toast.show();
                    }
                }
            }
        });
    }
}