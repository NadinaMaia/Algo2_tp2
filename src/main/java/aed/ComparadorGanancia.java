package aed;

import java.util.Comparator; 

public class ComparadorGanancia implements Comparator<Ciudad> {
    @Override
    public int compare(Ciudad c1, Ciudad c2) {
        return Integer.compare(c1.ganancia, c2.ganancia);
    }
}