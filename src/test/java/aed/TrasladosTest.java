package aed;

import java.util.ArrayList;
import java.util.Arrays;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class TrasladosTest {

    @Test
    void casoTrasladosNull(){
        Traslado[] traslados = new Traslado[]{};
        BestEffort sis = new BestEffort(7, traslados);
        
        assertArrayEquals(new int[1],sis.despacharMasRedituables(1));
        assertArrayEquals(new int[4],sis.despacharMasAntiguos(4));
    }
}