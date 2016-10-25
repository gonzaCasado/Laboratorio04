package dam.isi.frsf.utn.edu.ar.laboratorio04.utils;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.RingtoneManager;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import dam.isi.frsf.utn.edu.ar.laboratorio04.AltaReservaActivity;
import dam.isi.frsf.utn.edu.ar.laboratorio04.MainActivity;
import dam.isi.frsf.utn.edu.ar.laboratorio04.R;
import dam.isi.frsf.utn.edu.ar.laboratorio04.modelo.Reserva;

/**
 * Created by Administrador on 19/10/2016.
 */
public class TestReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context ctx, Intent intent) {
        String message = "La reserva fue confirmada";

        if (System.currentTimeMillis()% 3 == 0) {

            AlarmService.cancelarAlarma();
            Reserva reserva = (Reserva) intent.getSerializableExtra("reserva");
            reserva.setConfirmada(true);
            //MainActivity.actualizarReserva(reserva);
            AlarmService.cancelarAlarma();

            String ns= Context.NOTIFICATION_SERVICE;
            NotificationManager nm= (NotificationManager) ctx.getSystemService(ns);

            Intent i= new Intent(ctx, AltaReservaActivity.class);
            PendingIntent pi = PendingIntent.getActivity(ctx, 0, i, 0);
            NotificationCompat.Builder mBuilder= new NotificationCompat.Builder(ctx.getApplicationContext()).setSmallIcon(R.drawable.icono).setContentIntent(pi).setContentTitle("RESERVA CONFIRMADA").setContentText(message);
            SharedPreferences getAlarms = PreferenceManager.getDefaultSharedPreferences(ctx);
            String alarms = getAlarms.getString("ringtone", null);
            Uri uri = Uri.parse(alarms);
            mBuilder.setSound(uri);
            nm.notify(1, mBuilder.build());
        }
        //else{Toast.makeText(context,"NO ERA MULTIPLO",Toast.LENGTH_SHORT).show();}
    }

}