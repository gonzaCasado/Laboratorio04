package dam.isi.frsf.utn.edu.ar.laboratorio04;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;


public class SettingsActivity extends ActionBarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.add(android.R.id.content, new SettingsFragment());
        ft.commit();
    }
}
