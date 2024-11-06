package aed;

import java.util.Comparator; 

public class ComparadorSuperavit implements Comparator<Ciudad> {
    @Override
    public int compare(Ciudad c1, Ciudad c2) {
        return Integer.compare(c1.superavit, c2.superavit);
    }
}
