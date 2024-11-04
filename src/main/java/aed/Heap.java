package aed;
import java.util.ArrayList;

public class Heap {

    private ArrayList<Integer> elementos;
    private int cantidadNodos;

    public Heap() {
        elementos = new ArrayList<>();
        cantidadNodos = 0;
    }

    public boolean Vacio() {
        return (cantidadNodos == 0);
    }

    public int Maximo() {
        return elementos.get(0);
        }

    public void Agregar(int elem) {
        elementos.add(elem);
        cantidadNodos = cantidadNodos + 1;
        SiftUp(cantidadNodos - 1);
    }
    
    private void SiftUp(int indiceActual){
        if (indiceActual == 0 ) {
            return;
        }
        int indicePadre = (indiceActual - 1) / 2;
        if (elementos.get(indiceActual) > elementos.get(indicePadre)) {
            int Actual = elementos.get(indiceActual);
            elementos.set(indiceActual, elementos.get(indicePadre));
            elementos.set(indicePadre, Actual);
            
            SiftUp(indicePadre);
        }
    }

    public int SacarMaximo() {
        int maximo = elementos.get(0);
        elementos.set(0, elementos.get(cantidadNodos - 1));
        elementos.remove(cantidadNodos - 1);
        cantidadNodos = cantidadNodos -1;
        SiftDown(0);
        return maximo;
    }

    private void SiftDown(int indice){
        int indiceHijoDer = 2 * indice + 1;
        int indiceHijoIzq = 2 * indice + 2;
        int indiceMaximo = indice;
        
        if (indiceHijoIzq < cantidadNodos && elementos.get(indiceHijoIzq) > elementos.get(indiceMaximo)) {
            indiceMaximo = indiceHijoIzq;
        }
        
        if (indiceHijoDer < cantidadNodos && elementos.get(indiceHijoDer) > elementos.get(indiceMaximo)) {
            indiceMaximo = indiceHijoDer;
        }

        if (indiceMaximo != indice) {
            int Actual = elementos.get(indice);
            elementos.set(indice, elementos.get(indiceMaximo));
            elementos.set(indiceMaximo, Actual);
            SiftDown(indiceMaximo);
        }
    }
}