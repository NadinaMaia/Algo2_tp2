package aed;

import java.util.ArrayList;
import java.util.Comparator;

public class Traslados {
    private Heap<Traslado> masRedituable;   
    private Heap<Traslado> masAntiguo;      

    public Traslados(ArrayList<Traslado> infoTraslados) { 
        masRedituable = new Heap<>(infoTraslados, new ComparadorGananciaNeta());
        masAntiguo = new Heap<>(infoTraslados, new ComparadorMasAntiguo());
    }

    public void registrarTraslados(Traslado[] traslados) {//O(|traslados|log(|T|))
        for (Traslado t : traslados) {//O(|traslados|)
            masRedituable.Agregar(t);//O(log|T|)   
            masAntiguo.Agregar(t);//O(log|T|)       
        }
    }

    public Heap<Traslados> obtenerMasRedituable() {//O(1)
        return masRedituable; //O(1)
    }
    
    public Heap<Traslados> obtenerMasAntiguo(){//O(1)
        return masAntiguo;//O(1)
    }
}
