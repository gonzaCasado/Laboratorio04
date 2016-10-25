package dam.isi.frsf.utn.edu.ar.laboratorio04;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
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
import dam.isi.frsf.utn.edu.ar.laboratorio04.utils.AlarmService;
import dam.isi.frsf.utn.edu.ar.laboratorio04.utils.BuscarDepartamentosTask;
import dam.isi.frsf.utn.edu.ar.laboratorio04.utils.FormBusqueda;
import dam.isi.frsf.utn.edu.ar.laboratorio04.utils.ListaReservas;
import dam.isi.frsf.utn.edu.ar.laboratorio04.utils.TestReceiver;

public class AltaReservaActivity extends AppCompatActivity {
    private TextView tvEstadoBusqueda;
    private ListView listaReservas;
    private DepartamentoAdapter reservasAdapter;
    private List<Departamento> lista;
    Bundle extras;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alta_reserva);
        lista = new ArrayList<>();
        listaReservas = (ListView) findViewById(R.id.listaReservas);
        tvEstadoBusqueda = (TextView) findViewById(R.id.estadoBusqueda);
        registerForContextMenu(listaReservas);

    }

    @Override

    protected void onStart() {
        super.onStart();
        if (getIntent().getExtras() != null) {
            extras = getIntent().getExtras();
            Boolean esReserva = extras.getBoolean("esReserva");
            if (esReserva) {
                int id = extras.getInt("reserva");
                List<Departamento> listaDepartamentos = Departamento.getAlojamientosDisponibles();
                Departamento departamentoAReservar = new Departamento();
                for (int i = 0; i < listaDepartamentos.size(); i++) {
                    if (listaDepartamentos.get(i).getId() == id) {
                        departamentoAReservar = listaDepartamentos.get(i);
                        break;
                    }
                }

                Calendar inicio = Calendar.getInstance();
                Reserva r = new Reserva(1, inicio.getTime(), inicio.getTime(), departamentoAReservar);
                r.setConfirmada(false);

                ListaReservas.agregar(departamentoAReservar);

                new AlarmService(getApplicationContext(), r);

                lista = ListaReservas.getLista();
            } else {
                tvEstadoBusqueda.setVisibility(View.GONE);
                lista = ListaReservas.getLista();
            }
            reservasAdapter = new DepartamentoAdapter(AltaReservaActivity.this, lista);
            listaReservas.setAdapter(reservasAdapter);
        } else {
            tvEstadoBusqueda.setVisibility(View.GONE);
            lista = ListaReservas.getLista();
            reservasAdapter = new DepartamentoAdapter(AltaReservaActivity.this, lista);
            listaReservas.setAdapter(reservasAdapter);
        }
    }
}


