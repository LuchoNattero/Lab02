package com.lrnl.lab02;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;

import com.lrnl.lab02.modelo.Tarjeta;
import com.lrnl.lab02.modelo.Pedido;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class pago_pedido extends AppCompatActivity implements View.OnClickListener {

    private EditText edit_text_nombre,edit_text_numero,edit_text_correo,edit_text_fecha,edit_text_tarjeta;
    private Intent intent;
    private Tarjeta tarjeta;
    private Pedido pedido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pago_pedido);

        edit_text_nombre = (EditText) findViewById(R.id.etNombre);
        edit_text_numero = (EditText) findViewById(R.id.etNumero);
        edit_text_correo = (EditText) findViewById(R.id.etCorreo);
        edit_text_fecha = (EditText) findViewById(R.id.etFecha);
        edit_text_tarjeta = (EditText) findViewById(R.id.etTarjeta);


        intent = getIntent();
        pedido = (Pedido) intent.getSerializableExtra("pedido");
        tarjeta = new Tarjeta();

        findViewById(R.id.boton_Confirmar).setOnClickListener(this);
        findViewById(R.id.boton_Cancelar).setOnClickListener(this);


    }
    @Override
    public void onClick(View v){


        boolean error = false;
        switch (v.getId()){

            case R.id.boton_Confirmar:

                if(edit_text_nombre.getText().toString().isEmpty()){

                    error = true;
                    edit_text_nombre.setError("Debe completar el campo");
                }
                if(edit_text_numero.getText().toString().isEmpty()){

                    error = true;
                    edit_text_numero.setError("Debe completar el campo");
                }
                if(edit_text_fecha.getText().toString().isEmpty()){

                    error = true;
                    edit_text_fecha.setError("Debe completar el campo");
                }
                if(edit_text_correo.getText().toString().isEmpty()){

                    error = true;
                    edit_text_correo.setError("Debe completar el campo");
                }
                if (edit_text_tarjeta.getText().toString().isEmpty()) {

                    error = true;
                    edit_text_tarjeta.setError("Debe completar el campo");
                }
                if(!error) {

                    tarjeta.setNombre(edit_text_nombre.getText().toString());
                    tarjeta.setCorreo(edit_text_correo.getText().toString());
                    try{
                        SimpleDateFormat simpleDate = new SimpleDateFormat("MM/yy");
                        Date date = simpleDate.parse(edit_text_fecha.getText().toString());
                        tarjeta.setFechaVencimiento(date);
                    }catch(ParseException ex){
                        edit_text_fecha.setError("El formato debe ser mm/yy");
                    }
                    tarjeta.setSeguridad(Integer.parseInt(edit_text_tarjeta.getText().toString()));
                    tarjeta.setNumero(Integer.parseInt(edit_text_numero.getText().toString()));
                    intent.putExtra("tarjeta",tarjeta);
                    intent.putExtra("pedido", pedido);
                    setResult(RESULT_OK, intent);
                    finish();
                }

                break;

            case R.id.boton_Cancelar:

                Toast.makeText(getApplicationContext(),"El pedido a sido cancelado",Toast.LENGTH_SHORT).show();
                setResult(RESULT_CANCELED,intent);
                finish();

                break;
        }

    }
}
