package aed;

import java.util.Comparator; 

public class ComparadorSuperavit implements Comparator<Ciudad> {
    @Override
    public int compare(Ciudad c1, Ciudad c2) {
        if (Integer.compare(c1.superavit,c2.superavit)==0) {
            return Integer.compare(c2.id,c2.id);
        }
        else {
            return Integer.compare(c1.superavit,c2.superavit);
        }
    }
}
