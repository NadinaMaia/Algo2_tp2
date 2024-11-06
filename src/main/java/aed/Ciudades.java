package aed;

import java.util.ArrayList;

public class Ciudades {
    private ArrayList<Ciudad> ciudades;
    private Heap<Ciudad> mayorGanancia;
    private Heap<Ciudad> mayorPerdida;
    private Heap<Ciudad> mayorSuperavit; 

    public Ciudades(ArrayList<Ciudad> arr) {
        mayorGanancia = new Heap<>(arr, new ComparadorGanancia());
        mayorPerdida = new Heap<>(arr, new ComparadorPerdida());
        mayorSuperavit = new Heap<>(arr, new ComparadorSuperavit());
        ciudades = arr;
    }
    public Heap<Ciudad> obtenerMayorGanancia(){ //O(1)
        return mayorGanancia;
    }

    public Heap<Ciudad> obtenerMayorPerdida(){ //O(1)
        return mayorPerdida;
    }

    public Heap<Ciudad> obtenerMayorSuperavit(){ //O(1)
        return mayorSuperavit;
    }

    public ArrayList<Ciudad> obtenerCiudades(){ //O(1)
        return ciudades;
    }
}
