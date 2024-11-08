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
        mayorGanancia = new ArrayList<Ciudad>(arr.size());
        mayorPerdida = new ArrayList<Ciudad>(arr.size());
        tamañoG=0;
        tamañoP=0;
        mayorSuperavit = new Heap<>(arr, new ComparadorSuperavit(),2);
        ciudadesArray = arr;
    }

    public void actualizarMayorGanancia(Ciudad ciudadOrigen){
        if (tamañoG==0){
                    mayorGanancia.add(ciudadesArray.get(max.origen).id);
                    tamañoG ++;
                }
                else{
                    Ciudad ciudadOrigen =  ciudadesArray.get(max.origen);
                    int ciudadG = mayorGanancia.get(0);
                    if (ciudadOrigen.ganancia == ciudadesArray.get(ciudadG).ganancia){
                        mayorGanancia.add(ciudadOrigen.id);
                        tamañoG ++;
                    }
                    else if (ciudadOrigen.ganancia> ciudades.mayorGanancia.get(0).ganancia){
                        mayorGanancia = new ArrayList<Ciudad>(ciudades.ciudadesArray.size());
                        mayorGanancia.add(ciudadOrigen.id);
                        tamañoG = 1;

                    }

                }
    } 

    public void actualizarMayorPerdida(Ciudad ciudadDestino){
        if (ciudades.tamañoP==0){
                    ciudades.mayorPerdida.add(ciudades.ciudadesArray.get(max.destino).id);
                    ciudades.tamañoG ++;
                }
                else{
                    Ciudad ciudadDestino =  ciudades.ciudadesArray.get(max.destino);
                    int ciudadG = ciudades.mayorGanancia.get(0);
                    if (ciudadDestino.perdida == ciudades.ciudadesArray.get(ciudadG).perdida){
                        ciudades.mayorPerdida.add(ciudadDestino.id);
                        ciudades.tamañoG ++;
                    }
                    else if (ciudadDestino.ganancia> ciudades.mayorGanancia.get(0).perdida){
                        ciudades.mayorPerdida = new ArrayList<Ciudad>(ciudades.ciudadesArray.size());
                        ciudades.mayorPerdida.add(ciudadDestino.id);
                        ciudades.tamañoG = 1;

                    }

                }
    }
    public ArrayList<Ciudad> obtenerMayorGanancia(){ //O(1)
        return mayorGanancia;
    }

    public ArrayList<Ciudad> obtenerMayorPerdida(){ //O(1)
        return mayorPerdida;
    }

    public Heap<Ciudad> obtenerMayorSuperavit(){ //O(1)
        return mayorSuperavit;
    }

    public ArrayList<Ciudad> obtenerCiudades(){ //O(1)
        return ciudades;
    }
}
