package aed;

import java.util.Comparator; 

public class ComparadorGananciaNeta implements Comparator<Traslado> {
    @Override
    public int compare(Traslado t1, Traslado t2) { // Complejidad O(1)
        if (t1.gananciaNeta == t2.gananciaNeta) {
            return Integer.compare(t1.id,t2.id);
        }

        else {
            return Integer.compare(t1.gananciaNeta, t2.gananciaNeta);
        }
    }
}
