package aed;
import java.util.ArrayList;
import java.util.Comparator;

public class Heap<T> {

    private ArrayList<T> elementos;
    int cantidadNodos;
    private Comparator<T> comparador; 
    private Integer tipo;

    
    public Heap(ArrayList<T> arr, Comparator<T> comp, int tipo) { // O(n)
        cantidadNodos = arr.size();
        elementos = new ArrayList<T>(arr);
        comparador = comp;
        this.tipo = tipo;
        for (int i = cantidadNodos / 2 - 1; i >= 0; i--) {
            SiftDown(i);
        }
        // al realizar la construccion del heap de abajo hacia arriba,tiene una complejidad amortizada de O(n),
        // y no O(n log n), ya que los nodos más cercanos a la raíz necesitan menos tiempo para reorganizarse

        // Ahora recorremos todos los elementos para actualizar los handles
        for (int i = 0; i < cantidadNodos; i++) { // O(n)
            T Actual = elementos.get(i); //O(1)
            if (Actual instanceof Traslado){
                ((Traslado) Actual).Handles.set(tipo, i);//O(1)
            } else {
                ((Ciudad) Actual).Handle = i; //O(1)
            }
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
        if (cantidadNodos==1){
            elementos.remove(0);
        }
        else{ 
            elementos.set(0, elementos.get(cantidadNodos - 1));
            elementos.remove(cantidadNodos - 1);
            cantidadNodos = cantidadNodos -1;
            SiftDown(0);
        }
        return maximo;
    }

    public int obtenerCantNodos(){
        return cantidadNodos;
    }

    private void SiftDown(int indice) {
        int indiceHijoDer = 2 * indice + 1;
        int indiceHijoIzq = 2 * indice + 2;
        int indiceMaximo = indice;

        if (indiceHijoIzq < cantidadNodos && comparador.compare(elementos.get(indiceHijoIzq), elementos.get(indiceMaximo)) > 0) {
            indiceMaximo = indiceHijoIzq;
        }

        if (indiceHijoDer < cantidadNodos && comparador.compare(elementos.get(indiceHijoDer), elementos.get(indiceMaximo)) > 0) {
            indiceMaximo = indiceHijoDer;
        }

        if (indiceMaximo != indice) {
            T Actual = elementos.get(indice);
            T ElemIndiceMax = elementos.get(indiceMaximo);
            elementos.set(indice, ElemIndiceMax);
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
            return;
        }
        else {
            T elementoAmodificar = elementos.get(indice);
            //no se si cuando en comparador en menor a 0 el primero es mas chico?
            SiftDown(indice);
            // Si el nuevo valor es menor, puede necesitar un SiftDown
            
            SiftUp(indice);

            
        }
    }
}
