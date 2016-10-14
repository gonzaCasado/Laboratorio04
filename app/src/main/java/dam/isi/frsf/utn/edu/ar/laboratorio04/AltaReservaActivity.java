package dam.isi.frsf.utn.edu.ar.laboratorio04;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import dam.isi.frsf.utn.edu.ar.laboratorio04.modelo.Departamento;
import dam.isi.frsf.utn.edu.ar.laboratorio04.modelo.Reserva;
import dam.isi.frsf.utn.edu.ar.laboratorio04.utils.BuscarDepartamentosTask;
import dam.isi.frsf.utn.edu.ar.laboratorio04.utils.FormBusqueda;
import dam.isi.frsf.utn.edu.ar.laboratorio04.utils.ListaReservas;

public class AltaReservaActivity extends AppCompatActivity {
    private TextView tvEstadoBusqueda;
    private ListView listaReservas;
    private DepartamentoAdapter reservasAdapter;
    private List<Departamento> lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alta_reserva);
        lista= new ArrayList<>();
        listaReservas= (ListView ) findViewById(R.id.listaReservas);
        tvEstadoBusqueda = (TextView) findViewById(R.id.estadoBusqueda);
        registerForContextMenu(listaReservas);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Bundle extras = getIntent().getExtras();
        Boolean esReserva = extras.getBoolean("esReserva");
        if(esReserva){
            int id = extras.getInt("reserva");
            List<Departamento> listaDepartamentos = Departamento.getAlojamientosDisponibles();
            Departamento departamentoAReservar = new Departamento();
            for(int i = 0; i < listaDepartamentos.size(); i++){
                if(listaDepartamentos.get(i).getId() == id){
                    departamentoAReservar = listaDepartamentos.get(i);
                    break;
                }
            }
            Calendar inicio = Calendar.getInstance();
            new Reserva(1,inicio.getTime(),inicio.getTime(),departamentoAReservar);
            ListaReservas.agregar(departamentoAReservar);
            lista = ListaReservas.getLista();
        }else{
            tvEstadoBusqueda.setVisibility(View.GONE);
            lista = ListaReservas.getLista();
        }
        reservasAdapter = new DepartamentoAdapter(AltaReservaActivity.this,lista);
        listaReservas.setAdapter(reservasAdapter);
    }

}
