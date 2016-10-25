package dam.isi.frsf.utn.edu.ar.laboratorio04;

import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;


public class SettingsFragment extends PreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener{

    public SettingsFragment() {
        // Constructor Por Defecto
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
    }

    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {

        if(key.equals("ringtone")){
            Preference preference = findPreference(key);
            preference.setSummary(sharedPreferences.getString(key,""));
        }
    }

    public void onResume() {
        super.onResume();
        // Registrar escucha
        getPreferenceScreen().getSharedPreferences()
                .registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        // Eliminar registro de la escucha
        getPreferenceScreen().getSharedPreferences()
                .unregisterOnSharedPreferenceChangeListener(this);
    }


}
