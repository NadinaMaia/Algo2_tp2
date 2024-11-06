package aed;

public class Ciudad {
    int ganancia;
    int perdida;
    int superavit; 

    public Ciudad(){
        ganancia = 0;
        perdida = 0;
        superavit = 0; 
    }
    public void actualizarGanancia(int cant){
        ganancia += cant;
        superavit = ganancia - perdida; 
    }
    public void actualizarPerdida(int cant){
        perdida += cant;
        superavit += ganancia - perdida;

    }

    }

