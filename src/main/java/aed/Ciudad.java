package aed;
import java.util.ArrayList;

public class Ciudad {
    int id;
    int ganancia;
    int perdida;
    int superavit; 
    int Handle;

    public Ciudad(int id){
        ganancia = 0;
        perdida = 0;
        superavit = 0; 
        this.id = id;
        this.Handle = 0;
    }
    public void actualizarGanancia(int cant){ //O(1)
        ganancia += cant;
        superavit = ganancia - perdida; 
    }
    public void actualizarPerdida(int cant){ //O(1)
        perdida += cant;
        superavit += ganancia - perdida;

    }

    }

