package dam.isi.frsf.utn.edu.ar.laboratorio04.utils;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import dam.isi.frsf.utn.edu.ar.laboratorio04.modelo.Departamento;

/**
 * Created by Marce on 13/10/2016.
 */
public class ListaReservas{
    static ArrayList<Departamento> lista;


    public ListaReservas(){
        lista = new ArrayList<Departamento>();
    }
    public static ArrayList<Departamento> getLista(){
        return lista;
    }

    public static void agregar(Departamento departamento){
        lista.add(departamento);
    }
}
