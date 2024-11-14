package aed;

import java.util.ArrayList;

public class Ciudades {
    ArrayList<Ciudad> ciudadesArray;
    ArrayList<Integer> mayorGanancia;
    ArrayList<Integer> mayorPerdida;
    Heap<Ciudad> mayorSuperavit;
    int cantidadCiudadesGananciaMax;
    int cantidadCiudadesPerdidaMax;
    int gananciaMax;
    int perdidaMax;

    public Ciudades(ArrayList<Ciudad> arr) { //O(C)
        mayorGanancia = new ArrayList<Integer>(arr.size()); //O(1)
        mayorPerdida = new ArrayList<Integer>(arr.size()); //O(1)
        cantidadCiudadesGananciaMax=0; //O(1)
        cantidadCiudadesPerdidaMax=0; //O(1)
        mayorSuperavit = new Heap<>(arr, new ComparadorSuperavit(),0); //O(C)
        ciudadesArray = new ArrayList<Ciudad> (arr); //O(1)
        gananciaMax = 0; //O(1)
        perdidaMax = 0; //O(1)
    }

    public void actualizarMayorGanancia(Ciudad ciudadOrigen, int GananciaNeta){ // Complejidad O(1)
        ciudadOrigen.actualizarGanancia(GananciaNeta); //O(1)
        if (cantidadCiudadesGananciaMax==0){ //O(1)
            mayorGanancia.add(ciudadOrigen.id);
            cantidadCiudadesGananciaMax ++;
            gananciaMax= ciudadOrigen.ganancia;
        }

        else{ 
            if (ciudadOrigen.ganancia == gananciaMax){ //O(1)
            mayorGanancia.add(ciudadOrigen.id);
            cantidadCiudadesGananciaMax ++;
            }

            else if (ciudadOrigen.ganancia> gananciaMax){ //O(1)
                mayorGanancia = new ArrayList<Integer>(ciudadesArray.size());
                mayorGanancia.add(ciudadOrigen.id);
                cantidadCiudadesGananciaMax = 1;
                gananciaMax= ciudadOrigen.ganancia;
            }
        }
    } 

    public void actualizarMayorPerdida(Ciudad ciudadDestino, int GananciaNeta){ // Complejidad O(1)
        ciudadDestino.actualizarPerdida(GananciaNeta); //O(1)
        if (cantidadCiudadesPerdidaMax==0){ //O(1)
                    mayorPerdida.add(ciudadDestino.id);
                    cantidadCiudadesPerdidaMax ++;
                    perdidaMax= ciudadDestino.perdida;
        }
                
        else{ //O(1)
            if (ciudadDestino.perdida == perdidaMax){ //O(1)
                mayorPerdida.add(ciudadDestino.id);
                cantidadCiudadesPerdidaMax ++;
            }

            else if (ciudadDestino.perdida > perdidaMax){ //O(1)
                mayorPerdida = new ArrayList<Integer>(ciudadesArray.size());
                mayorPerdida.add(ciudadDestino.id);
                cantidadCiudadesPerdidaMax = 1;
                perdidaMax= ciudadDestino.perdida;
            }
        }
    }

    public void actualizarHeap(Ciudad c){  // Complejidad O(log n)
        int indice = c.Handle; //O(1)
        mayorSuperavit.modificarEn(indice, c); //O(log n)
    } 

    public ArrayList<Integer> obtenerMayorGanancia(){ // Complejidad O(1)
        return mayorGanancia;
    }

    public ArrayList<Integer> obtenerMayorPerdida(){ // Complejidad O(1)
        return mayorPerdida;
    }

    public Heap<Ciudad> obtenerMayorSuperavit(){ // Complejidad O(1)
        return mayorSuperavit;
    }

    public ArrayList<Ciudad> obtenerCiudades(){ // Complejidad O(1)
        return ciudadesArray;
    }
}
