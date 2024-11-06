package aed;

public class Ciudad {
    int id;
    int ganancia;
    int perdida;
    int superavit; 

    public Ciudad(int id){
        ganancia = 0;
        perdida = 0;
        superavit = 0; 
        this.id = id;
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

