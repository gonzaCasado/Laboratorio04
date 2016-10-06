package dam.isi.frsf.utn.edu.ar.laboratorio04.utils;

import android.os.AsyncTask;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import dam.isi.frsf.utn.edu.ar.laboratorio04.modelo.Ciudad;
import dam.isi.frsf.utn.edu.ar.laboratorio04.modelo.Departamento;
import dam.isi.frsf.utn.edu.ar.laboratorio04.utils.BusquedaFinalizadaListener;
import dam.isi.frsf.utn.edu.ar.laboratorio04.utils.FormBusqueda;

/**
 * Created by martdominguez on 22/09/2016.
 */
public class BuscarDepartamentosTask extends AsyncTask<FormBusqueda,Integer,List<Departamento>> {

    private BusquedaFinalizadaListener<Departamento> listener;

    public BuscarDepartamentosTask(BusquedaFinalizadaListener<Departamento> dListener){
        this.listener = dListener;
    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(List<Departamento> departamentos) {
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        listener.busquedaActualizada("departamento "+values[0]);
    }

    @Override
    protected List<Departamento> doInBackground(FormBusqueda... busqueda) {

        List<Departamento> todos = Departamento.getAlojamientosDisponibles();
        List<Departamento> resultado = new ArrayList<Departamento>();
        int contador = 0;
        int cantidadHuespedes = busqueda[0].getHuespedes();
        double precioMinimo = 0;
        double precioMaximo = 2500;
        Ciudad ciudadBuscada = busqueda[0].getCiudad();
        Boolean permiteFumar = busqueda[0].getPermiteFumar();
        if(busqueda[0].getPrecioMinimo() != null)
            precioMinimo = busqueda[0].getPrecioMinimo();
            if(busqueda[0].getPrecioMaximo() != null)
            precioMaximo = busqueda[0].getPrecioMaximo();

        for(int i = 0; i<todos.size(); i++){
            Departamento departamento = todos.get(i);
            if(departamento.getCiudad().equals(ciudadBuscada)){
                if(permiteFumar && departamento.getNoFumador() || !permiteFumar && !departamento.getNoFumador())
                    if(departamento.getCapacidadMaxima() >= cantidadHuespedes)
                        if(departamento.getPrecio() >= precioMinimo && departamento.getPrecio() <= precioMaximo)
                            resultado.add(departamento);
            }
        }
        listener.busquedaFinalizada(resultado);
        return resultado;
    }
}
