package aed;

import java.util.ArrayList;

public class Ciudades {
    private Heap<Ciudad> mayorGanancia;
    private Heap<Ciudad> mayorPerdida;
    private Heap<Ciudad> mayorSuperavit; 

    public Ciudades(ArrayList<Ciudad> arr) {
        mayorGanancia = new Heap<>(arr, new ComparadorGanancia());
        mayorPerdida = new Heap<>(arr, new ComparadorPerdida());
        mayorSuperavit = new Heap<>(arr, new ComparadorSuperavit());
    }
}
