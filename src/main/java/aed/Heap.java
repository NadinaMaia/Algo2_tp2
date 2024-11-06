package aed;
import java.util.ArrayList;
import java.util.Comparator;

public class Heap<T> {

    private ArrayList<T> elementos;
    private int cantidadNodos;
    private Comparator<T> comparador; 

    
    public Heap(ArrayList<T> arr, Comparator<T> comp) {
        cantidadNodos = arr.size();
        elementos = arr;
        comparador = comp;
        for (int i = cantidadNodos / 2 - 1; i >= 0; i--) {
            SiftDown(i);
        }
    }

    public boolean Vacio() {
        return (cantidadNodos == 0);
    }

    public T Maximo() { //O(1)
        return elementos.get(0);
        }

    public void Agregar(T elem) {
        elementos.add(elem);
        cantidadNodos = cantidadNodos + 1;
        SiftUp(cantidadNodos - 1);
    }
    
    private void SiftUp(int indiceActual){
        if (indiceActual == 0 ) {
            return;
        }
        int indicePadre = (indiceActual - 1) / 2;
        if (comparador.compare(elementos.get(indiceActual), elementos.get(indicePadre)) > 0) {
            T Actual = elementos.get(indiceActual);
            elementos.set(indiceActual, elementos.get(indicePadre));
            elementos.set(indicePadre, Actual);
            
            SiftUp(indicePadre);
        }
    }

    public T SacarMaximo() {
        T maximo = elementos.get(0);
        elementos.set(0, elementos.get(cantidadNodos - 1));
        elementos.remove(cantidadNodos - 1);
        cantidadNodos = cantidadNodos -1;
        SiftDown(0);
        return maximo;
    }

    public int obtenerCantNodos(){
        return cantidadNodos;
    }

    private void SiftDown(int indice){
        int indiceHijoDer = 2 * indice + 1;
        int indiceHijoIzq = 2 * indice + 2;
        int indiceMaximo = indice;
        
        if (indiceHijoIzq < cantidadNodos && comparador.compare(elementos.get(indiceHijoIzq), elementos.get(indiceMaximo)) < 0) {
            indiceMaximo = indiceHijoIzq;
        }
        
        if (indiceHijoDer < cantidadNodos && comparador.compare(elementos.get(indiceHijoDer), elementos.get(indiceMaximo)) < 0) {
            indiceMaximo = indiceHijoDer;
        }

        if (indiceMaximo != indice) {
            T Actual = elementos.get(indice);
            elementos.set(indice, elementos.get(indiceMaximo));
            elementos.set(indiceMaximo, Actual);
            SiftDown(indiceMaximo);
        }
    }
    
}