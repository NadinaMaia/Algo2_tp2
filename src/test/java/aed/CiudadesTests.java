package aed;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

public class CiudadesTests {
    
    private Ciudades ciudades;
    private ArrayList<Ciudad> ciudadesList;

    @BeforeEach
    public void setUp() {
        // datos para probar ciudades 
        ciudadesList = new ArrayList<>();
        ciudadesList.add(new Ciudad(1));  // Ciudad(id) las ciudades se inicializan con ganancia,
        ciudadesList.add(new Ciudad(2)); // perdida y superavit 0
        ciudadesList.add(new Ciudad(3));

        // Crear objeto Ciudades
        ciudades = new Ciudades(ciudadesList);
    }

    @Test
    public void testInicializacion() {
        // Comprobar que la clase se inicializa correctamente
        assertNotNull(ciudades.obtenerMayorGanancia());
        assertNotNull(ciudades.obtenerMayorPerdida());
        assertNotNull(ciudades.obtenerMayorSuperavit());
        assertNotNull(ciudades.obtenerCiudades());
    }

    @Test
    public void testActualizarMayorGanancia() {
        // datos para probar ciudades 
        ciudadesList = new ArrayList<>();
        ciudadesList.add(new Ciudad(1));  // Ciudad(id) las ciudades se inicializan con ganancia,
        ciudadesList.add(new Ciudad(2)); // perdida y superavit 0 y la cantidad de ciudades es fija
        ciudadesList.add(new Ciudad(3));
        ciudadesList.add(new Ciudad(4));
        ciudadesList.add(new Ciudad(5));
        ciudadesList.add(new Ciudad(6));
        ciudadesList.add(new Ciudad(7));

        // Crear objeto Ciudades
        ciudades = new Ciudades(ciudadesList);

        // Verificar que se actualiza correctamente la lista de mayor ganancia
        Ciudad ciudadNueva = new Ciudad(4);
        ciudadNueva.actualizarGanancia(500);

        ciudades.actualizarMayorGanancia(ciudadNueva);
        
        // El id de la ciudad con mayor ganancia debería ser 4
        ArrayList<Integer> mayorGanancia = ciudades.obtenerMayorGanancia();
        assertEquals(1, mayorGanancia.size());  // Debería haber solo una ciudad
        assertTrue(mayorGanancia.contains(4));  // La ciudad con mayor ganancia tiene id 4
        
        // Verificar que la ciudad con ganancia de 250 no sobrescribe la ciudad con ganancia 500
        ciudadNueva = new Ciudad(5 );
        ciudadNueva.actualizarGanancia(250);
        ciudades.actualizarMayorGanancia(ciudadNueva);
        mayorGanancia = ciudades.obtenerMayorGanancia();
        assertEquals(1, mayorGanancia.size());  // Debe seguir siendo solo una ciudad
        assertTrue(mayorGanancia.contains(4));  // Seguimos con la ciudad id 4
    }

    @Test
    public void testActualizarMayorPerdida() {
        // datos para probar ciudades 
        ciudadesList = new ArrayList<>();
        ciudadesList.add(new Ciudad(0));  // Ciudad(id) las ciudades se inicializan con ganancia,
        ciudadesList.add(new Ciudad(1)); // perdida y superavit 0 y la cantidad de ciudades es fija
        ciudadesList.add(new Ciudad(2));
        ciudadesList.add(new Ciudad(3));
        ciudadesList.add(new Ciudad(4));
        ciudadesList.add(new Ciudad(5));
        ciudadesList.add(new Ciudad(6));

        // Crear objeto Ciudades
        ciudades = new Ciudades(ciudadesList);
        // Verificar que se actualiza correctamente la lista de mayor perdida
        Ciudad ciudadNueva = new Ciudad(6);
        ciudadNueva.actualizarPerdida(250);
        ciudades.actualizarMayorPerdida(ciudadNueva);

        // El id de la ciudad con mayor perdida debería ser 6
        ArrayList<Integer> mayorPerdida = ciudades.obtenerMayorPerdida();
        assertEquals(1, mayorPerdida.size());  // Debería haber solo una ciudad
        assertTrue(mayorPerdida.contains(6));  // La ciudad con mayor pérdida tiene id 6

        // Verificar que una ciudad con pérdida mayor a 250 sobrescribe la anterior
        ciudadNueva = new Ciudad(7);
        ciudadNueva.actualizarPerdida(300);

        ciudades.actualizarMayorPerdida(ciudadNueva);
        mayorPerdida = ciudades.obtenerMayorPerdida();
        assertEquals(1, mayorPerdida.size());  // Solo una ciudad debe estar presente
        assertTrue(mayorPerdida.contains(7));  // La ciudad con mayor pérdida tiene id 7
    }

    @Test
    public void testActualizarHeap() {
        // datos para probar ciudades 
        ciudadesList = new ArrayList<>();
        ciudadesList.add(new Ciudad(0));  // Ciudad(id) las ciudades se inicializan con ganancia,
        ciudadesList.add(new Ciudad(1)); // perdida y superavit 0 y la cantidad de ciudades es fija
        ciudadesList.add(new Ciudad(2));
        ciudadesList.add(new Ciudad(3));
        ciudadesList.add(new Ciudad(4));
        ciudadesList.add(new Ciudad(5));
        ciudadesList.add(new Ciudad(6));
        

        // Crear objeto Ciudades
        ciudades = new Ciudades(ciudadesList);

        // Verificar que se actualiza correctamente el heap
        Ciudad ciudadParaActualizar = ciudades.ciudadesArray.get(0);
        Ciudad ciudadParaActualizar2 = ciudades.ciudadesArray.get(3);
        Ciudad ciudadParaActualizar3 = ciudades.ciudadesArray.get(5);
        ciudadParaActualizar.actualizarGanancia(200);  
        ciudadParaActualizar2.actualizarGanancia(300);
        ciudadParaActualizar3.actualizarGanancia(500);
        ciudadParaActualizar.actualizarPerdida(300);
        ciudadParaActualizar2.actualizarPerdida(35);
        ciudadParaActualizar3.actualizarPerdida(200);

        ciudades.actualizarHeap(ciudadParaActualizar); 
        ciudades.actualizarHeap(ciudadParaActualizar2);
        ciudades.actualizarHeap(ciudadParaActualizar3); // Actualizar el heap

        // Verificar que la ciudad se actualizó en el heap
        Heap<Ciudad> heap = ciudades.obtenerMayorSuperavit();
        Ciudad max = heap.Maximo();
        assertEquals(5, max.id);


    }

    @Test
    public void testObtenerMayorGanancia() {
        ciudadesList = new ArrayList<>();
        ciudadesList.add(new Ciudad(0));  // Ciudad(id) las ciudades se inicializan con ganancia,
        ciudadesList.add(new Ciudad(1)); // perdida y superavit 0 y la cantidad de ciudades es fija
        ciudadesList.add(new Ciudad(2));
        ciudadesList.add(new Ciudad(3));
        ciudadesList.add(new Ciudad(4));
        ciudadesList.add(new Ciudad(5));
        ciudadesList.add(new Ciudad(6));
        // Crear objeto Ciudades
        ciudades = new Ciudades(ciudadesList);

        ArrayList<Integer> mayorGanancia = ciudades.obtenerMayorGanancia();
        assertEquals(0, mayorGanancia.size());  // no hay ninguna ciudad en el array
        Ciudad ciudadParaActualizar = ciudades.ciudadesArray.get(3);
        Ciudad ciudadParaActualizar2 = ciudades.ciudadesArray.get(2);
        Ciudad ciudadParaActualizar3 = ciudades.ciudadesArray.get(5);
        ciudadParaActualizar.actualizarGanancia(200);  // actualizamos las ganancias
        ciudadParaActualizar2.actualizarGanancia(300);
        ciudadParaActualizar3.actualizarGanancia(500);

        ciudades.actualizarMayorGanancia(ciudadParaActualizar);
        ciudades.actualizarMayorGanancia(ciudadParaActualizar2);
        ciudades.actualizarMayorGanancia(ciudadParaActualizar3);

        ArrayList<Integer> CiudadesmayorG=  ciudades.mayorGanancia;
        int maxG= ciudades.gananciaMax;
        assertEquals(1, CiudadesmayorG.size());
        assertTrue(CiudadesmayorG.contains(5));
        assertEquals(500, maxG);



    }

    @Test
    public void testObtenerMayorPerdida() {
        ciudadesList = new ArrayList<>();
        ciudadesList.add(new Ciudad(0));  // Ciudad(id) las ciudades se inicializan con ganancia,
        ciudadesList.add(new Ciudad(1)); // perdida y superavit 0 y la cantidad de ciudades es fija
        ciudadesList.add(new Ciudad(2));
        ciudadesList.add(new Ciudad(3));
        ciudadesList.add(new Ciudad(4));
        ciudadesList.add(new Ciudad(5));
        ciudadesList.add(new Ciudad(6));
        // Crear objeto Ciudades
        ciudades = new Ciudades(ciudadesList);

        ArrayList<Integer> mayorPerdida= ciudades.obtenerMayorPerdida();
        assertEquals(0, mayorPerdida.size());  // no hay ninguna ciudad en el array
        Ciudad ciudadParaActualizar = ciudades.ciudadesArray.get(3);
        Ciudad ciudadParaActualizar2 = ciudades.ciudadesArray.get(2);
        Ciudad ciudadParaActualizar3 = ciudades.ciudadesArray.get(5);
        ciudadParaActualizar.actualizarPerdida(300);
        ciudadParaActualizar2.actualizarPerdida(35);
        ciudadParaActualizar3.actualizarPerdida(200);
        ciudades.actualizarMayorPerdida(ciudadParaActualizar);
        ciudades.actualizarMayorPerdida(ciudadParaActualizar2);
        ciudades.actualizarMayorPerdida(ciudadParaActualizar3);
        ArrayList<Integer> CiudadesmayorP=  ciudades.obtenerMayorPerdida();
        int maxG= ciudades.perdidaMax;
        assertEquals(1, CiudadesmayorP.size());
        assertTrue(CiudadesmayorP.contains(3));
        assertEquals(300, maxG);
    }
    @Test
    public void testObtenerCiudades() {
        ciudadesList = new ArrayList<>();
        ciudadesList.add(new Ciudad(0));  // Ciudad(id) las ciudades se inicializan con ganancia,
        ciudadesList.add(new Ciudad(1)); // perdida y superavit 0 y la cantidad de ciudades es fija
        ciudadesList.add(new Ciudad(2));
        ciudadesList.add(new Ciudad(3));
        ciudadesList.add(new Ciudad(4));
        ciudadesList.add(new Ciudad(5));
        ciudadesList.add(new Ciudad(6));
        // Crear objeto Ciudades
        ciudades = new Ciudades(ciudadesList);
        ArrayList<Ciudad> ciudadesObt = ciudades.obtenerCiudades();
        assertEquals(ciudadesList.size(), ciudadesObt.size());  // Deberían ser el mismo número de ciudades
        assertTrue(ciudadesObt.containsAll(ciudadesList));  // Todas las ciudades deben estar presentes
    }
}

