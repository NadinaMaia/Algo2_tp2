package aed;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import org.junit.jupiter.api.Test;

public class TrasladosTest {

    @Test
    void casoTrasladosNull(){
        Traslado[] traslados = new Traslado[]{};
        BestEffort sis = new BestEffort(7, traslados);
        
        assertArrayEquals(new int[1],sis.despacharMasRedituables(1));
        assertArrayEquals(new int[4],sis.despacharMasAntiguos(4));
    }
}