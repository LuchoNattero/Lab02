package com.lrnl.lab02;
import com.lrnl.lab02.modelo.TipoBebida;
import com.lrnl.lab02.modelo.Utils;
import com.lrnl.lab02.modelo.Tarjeta;
import com.lrnl.lab02.modelo.Pedido;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;



public  class MainActivity extends AppCompatActivity implements  View.OnClickListener , AdapterView.OnItemClickListener{

    private Spinner horarios;
    private ListView listaElementos;
    private ArrayAdapter<Utils.ElementoMenu> adaptador;
    private Utils uti;
    private Utils.ElementoMenu [] listado_elementos;
    private Utils.ElementoMenu elemento_selecionado = null;
    private RadioGroup radioGroup;
    private TextView texto_pedidos;
    private String clave_texto_pedido = "clave_texto_pedido";
    private String clave_pedido_confirmado = "cave_pedido_confirmado";
    private String clave_bebida = "cave_bebida";
    private String clave_postre = "cave_postre";
    private String clave_principal = "clave_principal";
    private String clave_total = "clave_total";



    boolean pedido_confirmado = false;
    double total = 0;
    //DecimalFormat df = new DecimalFormat("##.00");
    boolean bebida = false;
    boolean principal = false;
    boolean postre = false;
    public Pedido pedido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.boton_agregar).setOnClickListener(this);
        findViewById(R.id.boton_confirmar_pedido).setOnClickListener(this);
        findViewById(R.id.boton_reiniciar).setOnClickListener(this);

        //------------------------------------------ADAPTADOR HORA----------------------------------


        horarios = (Spinner) findViewById(R.id.sp01);

        ArrayAdapter<CharSequence> adaptador_horario = ArrayAdapter.createFromResource(this, R.array.horarios, android.R.layout.simple_spinner_item);
        horarios.setAdapter(adaptador_horario);


        //----------------------------------------- ADAPTADOR PEDIDOS---------------------------------------------

        texto_pedidos =(TextView)findViewById(R.id.datos_pedidos);

        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        //radioGroup.setOnCheckedChangeListener(this);

        uti = new Utils();
        uti.iniciarListas();
        pedido = new Pedido();

        listaElementos = (ListView) findViewById(R.id.lvScroll);
        listaElementos.setOnItemClickListener(this);
        listaElementos.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

    }

    public void onRadioButtonClicked(View v){

        final int id = v.getId();
        switch (id){
            case -1:
                break;
            case  R.id.radio_plato:
                //cargar lista platos
                listado_elementos =uti.getListaPlatos();
                adaptador=new ArrayAdapter<>(this,android.R.layout.simple_list_item_single_choice, listado_elementos);
                elemento_selecionado=null;
                break;
            case R.id.radio_bebida:
                //cargar lista Bebidas
                listado_elementos =uti.getListaBebidas();
                adaptador=new ArrayAdapter<>(this,android.R.layout.simple_list_item_single_choice, listado_elementos);
                elemento_selecionado=null;
                break;
            case R.id.radio_postre:
                //cargar lista
                listado_elementos =uti.getListaPostre();
                adaptador=new ArrayAdapter<>(this,android.R.layout.simple_list_item_single_choice, listado_elementos);
                elemento_selecionado=null;
                break;
        }
        listaElementos.setAdapter(adaptador);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId())
        {
            case R.id.boton_agregar:

                if (elemento_selecionado != null) {

                    if(!pedido_confirmado) {

                        if (TipoBebida.PRINCIPAL == elemento_selecionado.getTipo() && !principal) {
                            texto_pedidos.append(elemento_selecionado.toString() + '\n');
                            Toast.makeText(getApplicationContext(), "El plato fue agregado", Toast.LENGTH_SHORT).show();
                            total += elemento_selecionado.getPrecio();
                            pedido.setPlato(elemento_selecionado);
                            principal = true;
                        } else if (TipoBebida.BEBIDA == elemento_selecionado.getTipo() && !bebida) {

                            texto_pedidos.append(elemento_selecionado.toString() + '\n');
                            Toast.makeText(getApplicationContext(), "La bebida fue agregada", Toast.LENGTH_SHORT).show();
                            total += elemento_selecionado.getPrecio();
                            pedido.setBebida(elemento_selecionado);
                            bebida = true;

                        } else if (TipoBebida.POSTRE == elemento_selecionado.getTipo() && !postre) {


                            texto_pedidos.append(elemento_selecionado.toString() + '\n');
                            Toast.makeText(getApplicationContext(), "El postre fue agregado", Toast.LENGTH_SHORT).show();
                            total += elemento_selecionado.getPrecio();
                            pedido.setPostre(elemento_selecionado);
                            postre = true;
                        }
                        else if(principal || bebida || postre)Toast.makeText(getApplicationContext(), "Solo puede agregar un elemento de cada tipo", Toast.LENGTH_SHORT).show();
                    }
                    else
                        Toast.makeText(getApplicationContext(), "El pedido ya fue confirmado, no puede volver a agregar", Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(getApplicationContext(), "Debe seleccionar un pedido", Toast.LENGTH_SHORT).show();


                break;

            case  R.id.boton_confirmar_pedido:
                if(elemento_selecionado != null) {
                    pedido_confirmado = true;
                    //texto_pedidos.append("---------------------------" + '\n' + "TOTAL = $" + Math.round(total*100d)/100d);
                    pedido.setCosto(total);
                    //Toast.makeText(getApplicationContext(), "El pedido ha sido realizado", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, pago_pedido.class);
                    startActivity(intent);
                }
                else
                    Toast.makeText(getApplicationContext(), "Debe agregar un pedido", Toast.LENGTH_SHORT).show();
                break;

            case R.id.boton_reiniciar:


                Toast.makeText(getApplicationContext(),"Se ha reiniciado el pedido",Toast.LENGTH_SHORT).show();
                texto_pedidos.setText("");
                elemento_selecionado = null;
                //radioGroup.clearCheck();
                adaptador=new ArrayAdapter<>(this,android.R.layout.simple_list_item_single_choice, listado_elementos);
                listaElementos.setAdapter(adaptador);
                total = 0;
                pedido_confirmado = false;
                principal = false;
                bebida = false;
                postre = false;

                pedido.setBebida(null);
                pedido.setPostre(null);
                pedido.setPlato(null);
                pedido.setCosto(null);

                break;
        }


    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        elemento_selecionado = (Utils.ElementoMenu) listaElementos.getItemAtPosition(i);

    }


    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString(clave_texto_pedido, texto_pedidos.getText().toString());
        savedInstanceState.putBoolean(clave_pedido_confirmado,pedido_confirmado);
        savedInstanceState.putBoolean(clave_bebida,bebida);
        savedInstanceState.putBoolean(clave_postre,postre);
        savedInstanceState.putBoolean(clave_principal,principal);

        savedInstanceState.putDouble(clave_total,total);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        texto_pedidos.setText(savedInstanceState.getString(clave_texto_pedido));
       // pedido_confirmado = savedInstanceState.getBoolean(clave_pedido_confirmado);
        bebida = savedInstanceState.getBoolean(clave_bebida);
        postre = savedInstanceState.getBoolean(clave_postre);
        principal = savedInstanceState.getBoolean(clave_principal);
        total = savedInstanceState.getDouble(clave_total);


    }

    public ArrayAdapter<Utils.ElementoMenu> getAdaptador(){

        return adaptador;
    }


}
