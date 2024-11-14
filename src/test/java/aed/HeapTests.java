package aed;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Comparator;

public class HeapTests {
    
    private Heap<Ciudad> heapC;
    private ArrayList<Ciudad> ciudades;
    private ArrayList<Traslado> traslados;
    private Heap<Traslado> heapT;
    private Heap<Traslado> heapTR;


    @BeforeEach
    public void setUp() {
        // Crear un conjunto de ciudades para pruebas

        ciudades = new ArrayList<>();
        ciudades.add(new Ciudad(0));  // Ciudad(id) las ciudades se inicializan con ganancia,
        ciudades.add(new Ciudad(1)); // perdida y superavit 0 y la cantidad de ciudades es fija
        ciudades.add(new Ciudad(2));
        ciudades.add(new Ciudad(3));
        ciudades.add(new Ciudad(4));
        ciudades.add(new Ciudad(5));
        ciudades.add(new Ciudad(6));
        
        Ciudad ciudadParaActualizar = ciudades.get(0);
        Ciudad ciudadParaActualizar2 = ciudades.get(3);
        Ciudad ciudadParaActualizar3 = ciudades.get(5);
        ciudadParaActualizar.actualizarGanancia(200);  
        ciudadParaActualizar2.actualizarGanancia(300);
        ciudadParaActualizar3.actualizarGanancia(500);
        ciudadParaActualizar.actualizarPerdida(300);
        ciudadParaActualizar2.actualizarPerdida(35);
        ciudadParaActualizar3.actualizarPerdida(200);

        traslados = new ArrayList<>();
        traslados.add(new Traslado(1, 3, 4, 1, 7));
        traslados.add(new Traslado(7, 6, 5, 40, 6));
        traslados.add(new Traslado(6, 5, 6, 3, 5));
        traslados.add(new Traslado(2, 2, 1, 41, 4));
        traslados.add(new Traslado(3, 3, 4, 100, 3));
        traslados.add(new Traslado(4, 1, 2, 30, 2));
        traslados.add(new Traslado(5, 2, 1, 90, 1));
        

        

        // Crear el heap con las ciudades y el comparador
        heapC = new Heap<>(ciudades, new ComparadorSuperavit(), 0);
        // Crear el heap con los traslados y el comparador
        heapT = new Heap<>(traslados, new ComparadorMasAntiguo(), 1);
        heapTR = new Heap<>(traslados, new ComparadorGananciaNeta(), 0);
    }

    @Test
    public void testInicializacion() {
        // Verificar que el heap no está vacío después de la inicialización
        assertFalse(heapC.Vacio());
    }

    @Test
    public void testMaximo() {
        // Crear un conjunto de ciudades para pruebas
        ciudades = new ArrayList<>();
        ciudades.add(new Ciudad(0));  // Ciudad(id) las ciudades se inicializan con ganancia,
        ciudades.add(new Ciudad(1)); // perdida y superavit 0 y la cantidad de ciudades es fija
        ciudades.add(new Ciudad(2));
        ciudades.add(new Ciudad(3));
        ciudades.add(new Ciudad(4));
        ciudades.add(new Ciudad(5));
        ciudades.add(new Ciudad(6));
        
        Ciudad ciudadParaActualizar = ciudades.get(0);
        Ciudad ciudadParaActualizar2 = ciudades.get(3);
        Ciudad ciudadParaActualizar3 = ciudades.get(5);
        ciudadParaActualizar.actualizarGanancia(200);  
        ciudadParaActualizar2.actualizarGanancia(300);
        ciudadParaActualizar3.actualizarGanancia(500);
        ciudadParaActualizar.actualizarPerdida(300);
        ciudadParaActualizar2.actualizarPerdida(35);
        ciudadParaActualizar3.actualizarPerdida(200);
        

         // Crear el heap con las ciudades y el comparador
         heapC = new Heap<>(ciudades, new ComparadorSuperavit(), 0);
        // Verificar que el primer elemento del heap es el de mayor superavit
        
        Ciudad maxCiudad = heapC.Maximo();
        assertEquals(300, maxCiudad.superavit);
        assertEquals(5, maxCiudad.id);  // Ciudad con la ganancia más alta (ID 5)
    }


    @Test
    public void testSacarMaximo() {
    
    
        // Sacar el máximo y verificar que el siguiente máximo sea el siguiente en orden
        Ciudad maxCiudad = heapC.SacarMaximo();
        assertEquals(300, maxCiudad.superavit); // El máximo era la ciudad con ID 5

        // El nuevo máximo debería ser la ciudad con ID 3 (ganancia 300)
        maxCiudad = heapC.Maximo();
        assertEquals(265, maxCiudad.superavit);
        assertEquals(3, maxCiudad.id);
    }

    @Test
    public void testEliminarEn() {
        

        // Eliminar un elemento en un índice específico
        heapC.eliminarEn(4);  // Eliminar la ciudad con ID 0 (0)

        // Verificar que el nuevo máximo sea el siguiente (ID 3)
        Ciudad maxCiudad = heapC.Maximo();
        assertEquals(300, maxCiudad.superavit);
        assertEquals(5, maxCiudad.id);
        
        // Verificar que el heap tiene solo dos elementos después de la eliminación
        assertEquals(6, heapC.obtenerCantNodos());
    }

    @Test
    public void testModificarEn() {
        
        Ciudad ciudad = ciudades.get(1);  // Ciudad con ID 1  (ganancia 0)
        ciudad.actualizarGanancia(50);  // Cambiar la ganancia a 50
        
        // Modificar el heap y verificar que el nuevo máximo es el siguiente (ID 5)
        heapC.modificarEn(1, ciudad);

        // El nuevo máximo debería ser la ciudad con ID 3 (ganancia 500)
        Ciudad maxCiudad = heapC.Maximo();
        assertEquals(500, maxCiudad.ganancia);
        assertEquals(5, maxCiudad.id);
    }

    @Test
    public void testVacio() {
       int  c = heapC.cantidadNodos;

        // Verificar que el heap está vacío después de vaciarlo
        while (heapC.cantidadNodos>0) {
            heapC.SacarMaximo();
        }
        
        assertTrue(heapC.Vacio());
    }

    // hacemos test de los heaps traslados
    @Test
    public void trasladosTime() {
        // vemos si obtenemos el mas antiguo 
        Traslado masAntiguo = heapT.Maximo();
        assertEquals(1, masAntiguo.timestamp);
        assertEquals(5, masAntiguo.id);

        heapT.SacarMaximo();
        // vemos como queda el nuevo maxi
        masAntiguo = heapT.Maximo();
        assertEquals(2, masAntiguo.timestamp);
        assertEquals(4, masAntiguo.id);
        // agregamos un nuevo traslado
        Traslado nuevo = new Traslado(8, 0, 5, 300, 1);
        traslados.add(nuevo);
        heapT.Agregar(nuevo);
        // vemos que el nuevo maximo (el mas antiguo) se el traslado agregado
        Traslado mA = heapT.Maximo();
        assertEquals(1, mA.timestamp);
        assertEquals(8, mA.id);
    }
    @Test
    public void HandlesCorrectosAntiguedad(){
    // vemos que los handles coincidan
    for (int i=0; i < traslados.size(); i++){
        Traslado traslado= traslados.get(i);
        int indice = traslado.Handles.get(1);
        ArrayList heapEnArray = heapT.devolverHeapCoArrayList();
        Traslado posicionHenheap = (Traslado) heapEnArray.get(indice);
        assertEquals(traslado.id, posicionHenheap.id);
    }
        
        }
    @Test
    public void trasladosReditubles() {
        // vemos si obtenemos el mas redituable
        Traslado masRedi = heapTR.Maximo();
        assertEquals(100, masRedi.gananciaNeta);
        assertEquals(3, masRedi.id);

        heapTR.SacarMaximo();
        // vemos como queda el nuevo maxi
        masRedi = heapTR.Maximo();
        assertEquals(90, masRedi.gananciaNeta);
        assertEquals(5, masRedi.id);
        // agregamos un nuevo traslado
        Traslado nuevo = new Traslado(8, 0, 5, 50, 1);
        traslados.add(nuevo);
        heapTR.Agregar(nuevo);
        // vemos que el nuevo maximo  se el traslado agregado
        Traslado mA = heapTR.Maximo();
        assertEquals(90, mA.gananciaNeta);
        assertEquals(5, mA.id);
    }
    @Test
    public void HandlesCorrectosRedi(){
    // vemos que los handles coincidan
    for (int i=0; i < traslados.size(); i++){
        Traslado traslado= traslados.get(i);
        int indice = traslado.Handles.get(0);
        ArrayList heapEnArray = heapTR.devolverHeapCoArrayList();
        Traslado posicionHenheap = (Traslado) heapEnArray.get(indice);
        assertEquals(traslado.id, posicionHenheap.id);
    }

    }
}




