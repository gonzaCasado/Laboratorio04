package dam.isi.frsf.utn.edu.ar.laboratorio04.utils;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import dam.isi.frsf.utn.edu.ar.laboratorio04.R;

/**
 * Created by Administrador on 19/10/2016.
 */
public class TestReceiver extends BroadcastReceiver {
    private static final String tag = "TestReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("TestReceiver", "intent=" + intent);
        String message = intent.getStringExtra("message");
        this.sendNotification(context, "HOLA HOLA HOLA");
        Log.d(tag, message);
    }

    private void sendNotification(Context ctx, String message) {
        String ns= Context.NOTIFICATION_SERVICE;
        NotificationManager nm= (NotificationManager) ctx.getSystemService(ns);
        int icon= R.drawable.icono;
        Intent intent= new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("http://www.google.com"));
        PendingIntent pi = PendingIntent.getActivity(ctx, 0, intent, 0);
        NotificationCompat.Builder mBuilder= new NotificationCompat.Builder(ctx.getApplicationContext()).setSmallIcon(R.drawable.icono).setContentIntent(pi).setContentTitle("Mynotification").setContentText(message);
        nm.notify(1, mBuilder.build());
    }





}