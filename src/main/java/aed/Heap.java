package aed;
import java.util.ArrayList;
import java.util.Comparator;

public class Heap<T> {

    private ArrayList<T> elementos;
    int cantidadNodos;
    private Comparator<T> comparador; 
    private Integer tipo;

    // n = cantidadNodos
    public Heap(ArrayList<T> arr, Comparator<T> comp, int tipo) { // Complejidad O(n)
        cantidadNodos = arr.size(); //O(1)
        elementos = new ArrayList<T>(arr); //O(1)
        comparador = comp; //O(1)
        this.tipo = tipo; //O(1)
        for (int i = cantidadNodos / 2 - 1; i >= 0; i--) { //O(log n)
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

    public boolean Vacio() { // Complejidad O(1)
        return (cantidadNodos == 0);
    }

    public T Maximo() { // Complejidad O(1)
        return elementos.get(0);
        }

    public void Agregar(T elem) { // Complejidad O(log n)
        elementos.add(elem); //O(1)
        cantidadNodos = cantidadNodos + 1; //O(1)
        SiftUp(cantidadNodos - 1); //O(log n)
    }
    
    private void SiftUp(int indiceActual){ // Complejidad O(log n)
        if (indiceActual == 0 ) { //O(1)
            return;
        }
        int indicePadre = (indiceActual - 1) / 2; 
        if (comparador.compare(elementos.get(indiceActual), elementos.get(indicePadre)) > 0) { //O(log n) (por llamadas recursivas)
            T Actual = elementos.get(indiceActual); //O(1)
            T ElemIndicePadre = elementos.get(indicePadre);//O(1)
            elementos.set(indiceActual, elementos.get(indicePadre));//O(1)
            elementos.set(indicePadre, Actual);//O(1)
            if (Actual instanceof Traslado && ElemIndicePadre instanceof Traslado) { //O(1)
                ((Traslado) Actual).Handles.set(tipo, indicePadre);
                ((Traslado) ElemIndicePadre).Handles.set(tipo, indiceActual);
            } 
            
            else { //O(1)
                ((Ciudad) Actual).Handle = indicePadre;
                ((Ciudad) ElemIndicePadre).Handle = indiceActual;
            }

            SiftUp(indicePadre); //O(log n) (por llamadas recursivas)
        }

        else { //O(1)
            T Actual = elementos.get(indiceActual); //O(1)
            if (Actual instanceof Traslado){ //O(1)
                ((Traslado) Actual).Handles.set(tipo, indiceActual);
            } 
            
            else { //O(1)
                ((Ciudad) Actual).Handle = indiceActual;
            }
        }    
    }

    public T SacarMaximo() { // Complejidad O(log n)
        T maximo = elementos.get(0); //O(1)
        if (cantidadNodos==1){ //O(1)
            elementos.remove(0); //O(1) (pues tiene un unico nodo, sino no seria esa complejidad)
            cantidadNodos--; //O(1)
        }
        else{  //O(1)
            elementos.set(0, elementos.get(cantidadNodos - 1)); //O(1)
            elementos.remove(cantidadNodos - 1); //O(1) (pues estamos sacando el ultimo elemento)
            cantidadNodos = cantidadNodos -1; //O(1)
            SiftDown(0); //O(log n)
        }
        return maximo;
    }

    public int obtenerCantNodos(){ //Complejidad O(1)
        return cantidadNodos;
    }

    private void SiftDown(int indice) { //COmplejidad O(log n)
        int indiceHijoDer = 2 * indice + 1; //O(1)
        int indiceHijoIzq = 2 * indice + 2; //O(1)
        int indiceMaximo = indice; //O(1)

        if (indiceHijoIzq < cantidadNodos && comparador.compare(elementos.get(indiceHijoIzq), elementos.get(indiceMaximo)) > 0) { //O(1)
            indiceMaximo = indiceHijoIzq;
        }

        if (indiceHijoDer < cantidadNodos && comparador.compare(elementos.get(indiceHijoDer), elementos.get(indiceMaximo)) > 0) { //O(1)
            indiceMaximo = indiceHijoDer;
        }

        if (indiceMaximo != indice) { //O(log n)
            T Actual = elementos.get(indice); //O(1)
            T ElemIndiceMax = elementos.get(indiceMaximo); //O(1)
            elementos.set(indice, ElemIndiceMax); //O(1)
            elementos.set(indiceMaximo, Actual); //O(1)

            if (Actual instanceof Traslado && ElemIndiceMax instanceof Traslado) { //O(1)
                ((Traslado) Actual).Handles.set(tipo, indiceMaximo);
                ((Traslado) ElemIndiceMax).Handles.set(tipo, indice);
            } 
            
            else { //O(1)
                ((Ciudad) Actual).Handle = indiceMaximo; 
                ((Ciudad) ElemIndiceMax).Handle = indice;
            }
            SiftDown(indiceMaximo); //O(log n) (por llamadas recursivas)
        } 
        
        else { //O(1)
            T Actual = elementos.get(indice); //O(1)
            if (Actual instanceof Traslado){ //O(1)
                ((Traslado) Actual).Handles.set(tipo, indice);
            } 
            
            else { //O(1)
                ((Ciudad) Actual).Handle = indice;
            }
        }
    }

    public void eliminarEn (int indice){ //Complejidad O(log n)
        if (indice < 0 || indice>= cantidadNodos){ //O(1) 
        }

        else {
            T ultimoElemento= elementos.get(cantidadNodos-1); //O(1)
            elementos.set(indice,ultimoElemento); //O(1)
            SiftDown(indice); //O(log n)
            elementos.remove(cantidadNodos-1); //O(1) (porque eliminamos el ultimo elemento)
            cantidadNodos--; //O(1)
        }
    }

    public void modificarEn (int indice, T elem){ // Complejidad O(log n)
        if (indice < 0 || indice>= cantidadNodos){ //O(1)
            return;
        }
        else { //O(log n)
            // Reemplazar el elemento en la posición indicada con el nuevo valor
            elementos.set(indice, elem); //O(1)

            SiftDown(indice); //O(log n)
            // Si el nuevo valor es menor, puede necesitar un SiftDown
            
            SiftUp(indice); //O(log n)

            
        }
    }

    public ArrayList<T> devolverHeapCoArrayList(){ // Complejidad O(1)
        return elementos;
    }
}
