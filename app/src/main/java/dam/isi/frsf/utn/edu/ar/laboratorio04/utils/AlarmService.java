package dam.isi.frsf.utn.edu.ar.laboratorio04.utils;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import dam.isi.frsf.utn.edu.ar.laboratorio04.modelo.Reserva;

/**
 * Created by Marce on 24/10/2016.
 */
public class AlarmService {

    static Context contexto;
    static Reserva reserva;

    public AlarmService (Context context, Reserva res){

        contexto=context;
        reserva=res;

        Intent intent = new Intent(context, TestReceiver.class);
        intent.putExtra("reserva", reserva);

        PendingIntent pi = PendingIntent.getBroadcast(context,reserva.getId(),intent,0);

        AlarmManager am =(AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        am.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), 15*1000, pi);
    }

    public static void cancelarAlarma(){
        Intent intent = new Intent(contexto, TestReceiver.class);

        PendingIntent pi = PendingIntent.getBroadcast(contexto, reserva.getId(), intent, 0);
        AlarmManager am = (AlarmManager) contexto.getSystemService(Context.ALARM_SERVICE);
        am.cancel(pi);

    }
}
