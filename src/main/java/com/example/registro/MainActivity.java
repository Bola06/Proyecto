package com.example.registro;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Button boton1;

    EditText editText1;
    EditText editText2;
    Intent intent;
    TextView tv5;
    API api;
    boolean suceso;
    User u;

    @SuppressLint("MissingInflatedId")
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

        boton1 = findViewById(R.id.button1);
        editText1 = findViewById(R.id.et1);
        editText2 = findViewById(R.id.et2);
        tv5 = findViewById(R.id.tv5);
        suceso = true;
        api = new API();//IMPORTANTE


        boton1.setOnClickListener(new View.OnClickListener() { //ARREGLAR LOS ESPACIOS
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                if (editText1.getText().length() == 0){
                    Toast toast=Toast.makeText(getApplicationContext(), "Debes rellenar el nombre de usuario",
                            Toast.LENGTH_LONG);
                    toast.show();

                }else if (editText2.getText().length() == 0){
                    Toast toast=Toast.makeText(getApplicationContext(), "Debes de seleccionar una contraseña",
                            Toast.LENGTH_LONG);

                    toast.show();
                }else{
                    api.obtenerDatosUsuario(
                            editText1.getText().toString(),
                            editText2.getText().toString(),
                            (success, user) -> runOnUiThread(() -> {
                                if(success && user != null && user.getNombre() != null && !user.getNombre().isEmpty()) {

                                    Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                                    startActivity(intent);
                                } else {
                                    System.out.println(user);
                                    Toast.makeText(getApplicationContext(),
                                            "Usuario no encontrado",
                                            Toast.LENGTH_LONG).show();
                                }
                            })
                    );
                }
            }
        });

        tv5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MainActivity.this, MainActivity3.class);
                startActivity(intent);
            }
        });
    }
}