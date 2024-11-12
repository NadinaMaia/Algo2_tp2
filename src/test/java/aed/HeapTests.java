package aed;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class HeapTests {
    
    @Test
    void heapVacio() {
        Heap h = new Heap();

        assertTrue(h.Vacio());  // Cambié a assertTrue para comprobar que el heap está vacío
    }
    
    @Test
    void obtenerMaximo(){
        Heap h = new Heap();

        h.Agregar(7);
        h.Agregar(2);
        h.Agregar(3);
        h.Agregar(6);

        assertEquals(7, h.Maximo());
    }
    
    @Test
    void obtenerMaximoDelVacio(){
        Heap h = new Heap();

        assertEquals(0, h.Maximo());
    }

    @Test
    void obtenerMaximoLuegoDeQuitarMaximo(){
        Heap h = new Heap();

        h.Agregar(7);
        h.Agregar(2);
        h.Agregar(3);
        h.Agregar(6);
        h.SacarMaximo();

        assertEquals(6, h.Maximo());
    }

    @Test
    void obtenerMaximoDeTodosNegativos(){
        Heap h = new Heap();

        h.Agregar(-7);
        h.Agregar(-3);
        h.Agregar(-15);
        h.Agregar(-2);
        h.Agregar(-24);
        h.Agregar(-1);

        assertEquals(-1, h.Maximo());
    }
