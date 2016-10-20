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
import dam.isi.frsf.utn.edu.ar.laboratorio04.utils.BuscarDepartamentosTask;
import dam.isi.frsf.utn.edu.ar.laboratorio04.utils.FormBusqueda;
import dam.isi.frsf.utn.edu.ar.laboratorio04.utils.ListaReservas;
import dam.isi.frsf.utn.edu.ar.laboratorio04.utils.TestReceiver;

public class AltaReservaActivity extends AppCompatActivity {
    private TextView tvEstadoBusqueda;
    private ListView listaReservas;
    private DepartamentoAdapter reservasAdapter;
    private List<Departamento> lista;

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
        Bundle extras = getIntent().getExtras();
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
            new Reserva(1, inicio.getTime(), inicio.getTime(), departamentoAReservar);
            ListaReservas.agregar(departamentoAReservar);

            //Acá tendria que disparar la alarma!
            sendRepeatingAlarm(id);

            lista = ListaReservas.getLista();
        } else {
            tvEstadoBusqueda.setVisibility(View.GONE);
            lista = ListaReservas.getLista();
        }
        reservasAdapter = new DepartamentoAdapter(AltaReservaActivity.this, lista);
        listaReservas.setAdapter(reservasAdapter);
    }

    public void sendRepeatingAlarm(int idDepto) {
        //long tiempoActual = System.currentTimeMillis();

        Intent intent = new Intent(this, TestReceiver.class);
        intent.putExtra("Depto",idDepto);
        PendingIntent pIntent = this.getDistinctPendingIntent(intent, idDepto);
        AlarmManager manager = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
        manager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), 15 * 1000, pIntent);


    }

    private PendingIntent getDistinctPendingIntent(Intent intent, int i) {
        PendingIntent pi = PendingIntent.getBroadcast(this, i, intent, 0);
        return pi;
    }

/*    public void cancelRepeatingAlarm() {
        Intent intent = new Intent(this, TestReceiver.class);
        PendingIntent pi = this.getDistinctPendingIntent(intent, 1);
        AlarmManager am = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
        am.cancel(pi);
    }*/

}


