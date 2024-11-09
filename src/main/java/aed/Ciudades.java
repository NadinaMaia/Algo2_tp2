package aed;

import java.util.ArrayList;

public class Ciudades {
    ArrayList<Ciudad> ciudadesArray;
    ArrayList<Integer> mayorGanancia;
    ArrayList<Integer> mayorPerdida;
    Heap<Ciudad> mayorSuperavit;
    int tamañoG;
    int tamañoP;
    int gananciaMax;
    int perdidaMax;

    public Ciudades(ArrayList<Ciudad> arr) {
        mayorGanancia = new ArrayList<Integer>(arr.size());
        mayorPerdida = new ArrayList<Integer>(arr.size());
        tamañoG=0;
        tamañoP=0;
        mayorSuperavit = new Heap<>(arr, new ComparadorSuperavit(),0);
        ciudadesArray = new ArrayList<Ciudad> (arr);
        gananciaMax = 0;
        perdidaMax=0;
    }

    public void actualizarMayorGanancia(Ciudad ciudadOrigen){
        if (tamañoG==0){
                    mayorGanancia.add(ciudadOrigen.id);
                    tamañoG ++;
                    gananciaMax= ciudadOrigen.ganancia;
                }
                else{
                    
                    if (ciudadOrigen.ganancia == gananciaMax){
                        mayorGanancia.add(ciudadOrigen.id);
                        tamañoG ++;
                    }
                    else if (ciudadOrigen.ganancia> gananciaMax){
                        mayorGanancia = new ArrayList<Integer>(ciudadesArray.size());
                        mayorGanancia.add(ciudadOrigen.id);
                        tamañoG = 1;
                        gananciaMax= ciudadOrigen.ganancia;

                    }

                }
    } 

    public void actualizarMayorPerdida(Ciudad ciudadDestino){
        if (tamañoP==0){
                    mayorPerdida.add(ciudadDestino.id);
                    tamañoP ++;
                    perdidaMax= ciudadDestino.perdida;
                }
                else{
                    if (ciudadDestino.perdida == perdidaMax){
                        mayorPerdida.add(ciudadDestino.id);
                        tamañoP ++;

                    }
                    else if (ciudadDestino.perdida > perdidaMax){
                        mayorPerdida = new ArrayList<Integer>(ciudadesArray.size());
                        mayorPerdida.add(ciudadDestino.id);
                        tamañoP = 1;
                        perdidaMax= ciudadDestino.perdida;

                    }

                }
    }
    public void actualizarHeap(Ciudad c){
        int indice = c.Handle;
        mayorSuperavit.modificarEn(indice, c);

    } 
    public ArrayList<Integer> obtenerMayorGanancia(){ //O(1)
        return mayorGanancia;
    }

    public ArrayList<Integer> obtenerMayorPerdida(){ //O(1)
        return mayorPerdida;
    }

    public Heap<Ciudad> obtenerMayorSuperavit(){ //O(1)
        return mayorSuperavit;
    }

    public ArrayList<Ciudad> obtenerCiudades(){ //O(1)
        return ciudadesArray;
    }
}
