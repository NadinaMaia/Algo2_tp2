package aed;

import java.util.ArrayList;

public class Ciudades {
    ArrayList<Ciudad> ciudadesArray;
    ArrayList<Integer> mayorGanancia;
    ArrayList<Integer> mayorPerdida;
    Heap<Ciudad> mayorSuperavit;
    int tamañoG;
    int tamañoP;

    public Ciudades(ArrayList<Ciudad> arr) {
        mayorGanancia = new ArrayList<Integer>(arr.size());
        mayorPerdida = new ArrayList<Integer>(arr.size());
        tamañoG=0;
        tamañoP=0;
        mayorSuperavit = new Heap<>(arr, new ComparadorSuperavit(),2);
        ciudadesArray = arr;
    }

    public void actualizarMayorGanancia(Ciudad ciudadOrigen){
        if (tamañoG==0){
                    mayorGanancia.add(ciudadOrigen.id);
                    tamañoG ++;
                }
                else{
                    
                    int ciudadG = mayorGanancia.get(0);
                    if (ciudadOrigen.ganancia == ciudadesArray.get(ciudadG).ganancia){
                        mayorGanancia.add(ciudadOrigen.id);
                        tamañoG ++;
                    }
                    else if (ciudadOrigen.ganancia> ciudadesArray.get(ciudadG).ganancia){
                        mayorGanancia = new ArrayList<Integer>(ciudadesArray.size());
                        mayorGanancia.add(ciudadOrigen.id);
                        tamañoG = 1;

                    }

                }
    } 

    public void actualizarMayorPerdida(Ciudad ciudadDestino){
        if (tamañoP==0){
                    mayorPerdida.add(ciudadDestino.id);
                    tamañoG ++;
                }
                else{
                    int ciudadP = mayorGanancia.get(0);
                    if (ciudadDestino.perdida == ciudadesArray.get(ciudadP).perdida){
                        mayorPerdida.add(ciudadDestino.id);
                        tamañoG ++;
                    }
                    else if (ciudadDestino.ganancia> ciudadesArray.get(ciudadP).perdida){
                        mayorPerdida = new ArrayList<Integer>(ciudadesArray.size());
                        mayorPerdida.add(ciudadDestino.id);
                        tamañoG = 1;

                    }

                }
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
