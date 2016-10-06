package dam.isi.frsf.utn.edu.ar.laboratorio04;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Calendar;
import java.util.List;

import dam.isi.frsf.utn.edu.ar.laboratorio04.modelo.Departamento;
import dam.isi.frsf.utn.edu.ar.laboratorio04.modelo.Reserva;

public class AltaReservaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alta_reserva);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = getIntent();
        List<Departamento> listaDepartamentos = Departamento.getAlojamientosDisponibles();
        Departamento departamentoAReservar = new Departamento();
        int id = Integer.parseInt(intent.getExtras().getString("reserva"));

        for(int i = 0; i < listaDepartamentos.size(); i++){
            if(listaDepartamentos.get(i).getId() == id){
                departamentoAReservar = listaDepartamentos.get(i);
                break;
            }
        }
        Calendar inicio = Calendar.getInstance();
        Reserva reserva = new Reserva(1,inicio.getTime(),inicio.getTime(),departamentoAReservar);
    }

}
