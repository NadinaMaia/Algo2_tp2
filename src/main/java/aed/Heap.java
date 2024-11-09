package aed;
import java.util.ArrayList;
import java.util.Comparator;

public class Heap<T> {

    private ArrayList<T> elementos;
    private int cantidadNodos;
    private Comparator<T> comparador; 
    private Integer tipo;

    
    public Heap(ArrayList<T> arr, Comparator<T> comp, int tipo) {
        cantidadNodos = arr.size();
        elementos = arr;
        comparador = comp;
        this.tipo = tipo;
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
            T ElemIndicePadre = elementos.get(indicePadre);
            elementos.set(indiceActual, elementos.get(indicePadre));
            elementos.set(indicePadre, Actual);
            if (Actual instanceof Traslado && ElemIndicePadre instanceof Traslado) {
                // Caso de traslado
                ((Traslado) Actual).Handles.set(tipo, indicePadre);
                ((Traslado) ElemIndicePadre).Handles.set(tipo, indiceActual);
            } else {
                ((Ciudad) Actual).Handle = indicePadre;
                ((Ciudad) ElemIndicePadre).Handle = indiceActual;
            }
            SiftUp(indicePadre);
        }

        else {
            T Actual = elementos.get(indiceActual);
            if (Actual instanceof Traslado){
                ((Traslado) Actual).Handles.set(tipo, indiceActual);
            } else {
                ((Ciudad) Actual).Handle = indiceActual;
            }
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

    private void SiftDown(int indice) {
        int indiceHijoDer = 2 * indice + 1;
        int indiceHijoIzq = 2 * indice + 2;
        int indiceMaximo = indice;

        if (indiceHijoIzq < cantidadNodos &&  comparador.compare(elementos.get(indiceHijoIzq), elementos.get(indiceMaximo))<0) {
            indiceMaximo = indiceHijoIzq;
        }

        if (indiceHijoDer < cantidadNodos && 0<comparador.compare(elementos.get(indiceHijoDer), elementos.get(indiceMaximo))) {
            indiceMaximo = indiceHijoDer;
        }

        if (indiceMaximo != indice) {
            T Actual = elementos.get(indice);
            T ElemIndiceMax = elementos.get(indiceMaximo);
            elementos.set(indice, elementos.get(indiceMaximo));
            elementos.set(indiceMaximo, Actual);

            if (Actual instanceof Traslado && ElemIndiceMax instanceof Traslado) {
                // Caso de traslado
                ((Traslado) Actual).Handles.set(tipo, indiceMaximo);
                ((Traslado) ElemIndiceMax).Handles.set(tipo, indice);
            } else {
                ((Ciudad) Actual).Handle = indiceMaximo;
                ((Ciudad) ElemIndiceMax).Handle = indice;
            }
            SiftDown(indiceMaximo);
        } else {
            T Actual = elementos.get(indice);
            if (Actual instanceof Traslado){
                ((Traslado) Actual).Handles.set(tipo, indice);
            } else {
                ((Ciudad) Actual).Handle = indice;
            }
        }
    }
    public void eliminarEn (int indice){
        if (indice < 0 || indice>= cantidadNodos){
            
        }
        else {
            T ultimoElemento= elementos.get(cantidadNodos-1);
            elementos.set(indice,ultimoElemento);
            SiftDown(indice);
            elementos.remove(cantidadNodos-1);
            cantidadNodos--;
        }
    }

    public void modificarEn (int indice, T elem){
        if (indice < 0 || indice>= cantidadNodos){
            
        }
        else {
            T elementoAmodificar = elementos.get(indice);
            elementos.set(indice,elem);
            //no se si cuando en comparador en menor a 0 el primero es mas chico?
            if (comparador.compare(elementoAmodificar, elem)< 0) {
                SiftDown(indice);
            }
            // Si el nuevo valor es menor, puede necesitar un SiftDown
            else if (0 < comparador.compare(elementoAmodificar, elem)) {
                SiftUp(indice);

            }
        }
    }
}
