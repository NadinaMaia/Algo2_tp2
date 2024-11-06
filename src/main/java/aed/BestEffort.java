package aed;

import java.util.ArrayList;

public class BestEffort {
    private Traslados traslados;
    private Ciudades ciudades; 

    public BestEffort(int cantCiudades, Traslado[] traslados){ //complejidad O(|C| + |T|)
        int i = 0;
        ArrayList<Traslado> arr = new ArrayList<Traslado>(traslados.length);
        while (i < traslados.length){ //complejidad O(|T|)
            arr.set(i, traslados[i]);
            i++;
        }
        this.traslados = new Traslados(arr); //complejidad O(|T|)
        int j = 0; 
        ArrayList<Ciudad> arrCiudades = new ArrayList<Ciudad>(cantCiudades);
        while(j < cantCiudades){//complejidad O(|C|)
            Ciudad c = new Ciudad(j);
            arrCiudades.set(j,c);
            j++;
        }
        this.ciudades = new Ciudades(arrCiudades);//complejidad O(|C|)
    }

    public void registrarTraslados(Traslado[] traslados){ //O(|traslados|log(|T|))
        this.traslados.registrarTraslados(traslados);//O(|traslados|log(|T|))
    }

    public int[] despacharMasRedituables(int n){ //O(n(log(|T|)+log(|C|)))
        int[] nuevo_array; //O(1)
        Heap<Traslados> trasladosR = traslados.obtenerMasRedituable(); //O(1)
        for (int i=0; i<n; i++){ // O(n)
            if (i>trasladosR.obtenerCantNodos()){ //O(1) 
                return nuevo_array; //O(1)    
            } else{
                //sacar el maximo y agregarlo al array
                Traslado max =trasladosR.Maximo(); //O(1)
                trasladosR.SacarMaximo(); //O(log|T|)
                nuevo_array[i]= max.id;

                //tenemos que sacar el maximo a masAntiguo

                //modificamos las ganancias, perdidas, superavits de las ciudades despachadas EN LOS HEAPS
                C = ciudades.obtenerCiudades();//O(1)
                C[max.destino].actualizarPerdida(max.gananciaNeta);//O(1)
                C[max.origen].actualizarGanancia(max.gananciaNeta);//O(1)
            }
        } //COMPLEJIDAD HASTA AHORA //O(n(log|T|))
        return nuevo_array;
    }

    public int[] despacharMasAntiguos(int n){//O(n(log(|T|)+log(|C|)))
        int[] nuevo_array; //O(1)
        Heap<Traslados> trasladosA = traslados.obtenerMasAntiguo(); //O(1)
        for (int i=0; i<n; i++){ // O(n)
            if (i>trasladosA.obtenerCantNodos()){ //O(1) 
                return nuevo_array; //O(1)    
            } else{
                //sacar el maximo y agregarlo al array
                Traslado max =trasladosA.Maximo(); //O(1)
                trasladosA.SacarMaximo(); //O(log|T|)
                nuevo_array[i]= max.id;

                //tenemos que sacar el maximo a masAntiguo

                //modificamos las ganancias, perdidas, superavits de las ciudades despachadas EN LOS HEAPS
                C = ciudades.obtenerCiudades();//O(1)
                C[max.destino].actualizarPerdida(max.gananciaNeta);//O(1)
                C[max.origen].actualizarGanancia(max.gananciaNeta);//O(1)
            }
        } //COMPLEJIDAD HASTA AHORA //O(n(log|T|))
        return nuevo_array;
    }

    public int ciudadConMayorSuperavit(){ //O(1)
        return ciudades.obtenerMayorSuperavit().Maximo().id; //O(1)
    }

    public ArrayList<Integer> ciudadesConMayorGanancia(){
        return null; 
    }

    public ArrayList<Integer> ciudadesConMayorPerdida(){
        // Implementar
        return null;
    }

    public int gananciaPromedioPorTraslado(){
        // Implementar
        return 0;
    }
    
}
