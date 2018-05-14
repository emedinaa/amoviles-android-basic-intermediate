package com.academiamoviles.javaforandroid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class JavaForAndroidActivity extends AppCompatActivity {

    Random generador;
    int n1,n2,resultado;
    TextView tv_number1,tv_number2;
    EditText edt_respuesta;
    Button btnVerificar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_java_for_android);

        inicializar();
        generarNumeros();
        iniciarEventos();
    }

    private void iniciarEventos() {

        btnVerificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verificar();
            }
        });
    }

    public void inicializar(){

        tv_number1 = findViewById(R.id.tv_numero1);
        tv_number2 = findViewById(R.id.tv_numero2);
        edt_respuesta = findViewById(R.id.edt_respuesta);
        btnVerificar =  findViewById(R.id.btnVerificar);
    }

    public void generarNumeros(){

        generador = new Random();
        n1 = Math.abs(generador.nextInt()%6);
        n2 = Math.abs(generador.nextInt()%6);

        tv_number1.setText(""+n1);
        tv_number2.setText("" + n2);
    }

    public void verificar(){

        String respuesta = "";
        resultado = n1 + n2;
        int resultadoUsuario = Integer.parseInt ( edt_respuesta.getText().toString() );
        if (resultado == resultadoUsuario) {
            respuesta = ":-)";
            mostrarMensaje(respuesta);

        }else{
            respuesta = ":-(";
            mostrarMensaje(respuesta);
        }

    }

    public void limpiarDatos(){
        edt_respuesta.setText("");
    }

    public void mostrarMensaje(String respuesta){
        Toast.makeText(this,respuesta,Toast.LENGTH_LONG).show();
    }
}
