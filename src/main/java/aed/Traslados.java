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

    public void registrarTraslados(Traslado[] traslados) {
        for (Traslado t : traslados) {
            masRedituable.Agregar(t);    
            masAntiguo.Agregar(t);       
        }
    }
}
