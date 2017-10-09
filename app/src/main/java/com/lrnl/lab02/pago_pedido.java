package com.lrnl.lab02;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;

public class pago_pedido extends AppCompatActivity implements View.OnClickListener {

    EditText edit_text_nombre,edit_text_numero,edit_text_correo,edit_text_fecha,edit_text_tarjeta;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pago_pedido);

        edit_text_nombre = (EditText) findViewById(R.id.etNombre);
        edit_text_numero = (EditText) findViewById(R.id.etNumero);
        edit_text_correo = (EditText) findViewById(R.id.etCorreo);
        edit_text_fecha = (EditText) findViewById(R.id.etFecha);
        edit_text_tarjeta = (EditText) findViewById(R.id.etTarjeta);

        findViewById(R.id.boton_Confirmar).setOnClickListener(this);
        findViewById(R.id.boton_Cancelar).setOnClickListener(this);


    }
    @Override
    public void onClick(View v){

        switch (v.getId()){

            case R.id.boton_Confirmar:

                if(edit_text_nombre.getText().toString().isEmpty()){

                    Toast.makeText(getApplicationContext(), "Debe completar el campo", Toast.LENGTH_SHORT).show();
                }
                else if(edit_text_numero.getText().toString().isEmpty()){

                    Toast.makeText(getApplicationContext(), "Debe completar el campo", Toast.LENGTH_SHORT).show();

                }
                else if(edit_text_correo.getText().toString().isEmpty()){

                    Toast.makeText(getApplicationContext(), "Debe completar el campo", Toast.LENGTH_SHORT).show();
                }
                else if(edit_text_fecha.getText().toString().isEmpty()){

                    Toast.makeText(getApplicationContext(), "Debe completar el campo", Toast.LENGTH_SHORT).show();
                }
                else if (edit_text_tarjeta.getText().toString().isEmpty()) {

                        Toast.makeText(getApplicationContext(), "Debe completar el campo", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getApplicationContext(), "El pedido a sido confirmado", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(pago_pedido.this, MainActivity.class);
                    startActivity(intent);

                }

                break;

            case R.id.boton_Cancelar:

                Toast.makeText(getApplicationContext(),"El pedido a sido cancelado",Toast.LENGTH_SHORT).show();


                break;
        }

    }
}
