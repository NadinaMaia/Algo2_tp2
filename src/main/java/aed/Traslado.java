package aed;

import java.util.ArrayList;

public class Traslado {
    
    int id;
    int origen;
    int destino;
    int gananciaNeta;
    int timestamp;
    ArrayList<Integer> Handles;

    public Traslado(int id, int origen, int destino, int gananciaNeta, int timestamp){
        this.id = id;
        this.origen = origen;
        this.destino = destino;
        this.gananciaNeta = gananciaNeta;
        this.timestamp = timestamp;
        this.Handles = new ArrayList<>(2);
        Handles.add(0);
        Handles.add(0);
    }
}
