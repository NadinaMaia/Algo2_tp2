package aed;

import java.util.ArrayList;

public class BestEffort {
    private Traslados traslados;
    private Ciudades ciudades; 

    public BestEffort(int cantCiudades, Traslado[] traslados){ //complejidad O(|C| + |T|)
        int i = 0;
        int tamaño= traslados.length;
        ArrayList<Traslado> arr = new ArrayList<Traslado>(tamaño);
        while (i < traslados.length){ //complejidad O(|T|)
            arr.add( traslados[i]);
            i++;
        }
        this.traslados = new Traslados(arr); //complejidad O(|T|)
        int j = 0; 
        ArrayList<Ciudad> arrCiudades = new ArrayList<Ciudad>(cantCiudades);
        while(j < cantCiudades){//complejidad O(|C|)
            Ciudad c = new Ciudad(j);
            arrCiudades.add(c);
            j++;
        }
        this.ciudades = new Ciudades(arrCiudades);//complejidad O(|C|)
    }

    public void registrarTraslados(Traslado[] traslados){ //O(|traslados|log(|T|))
        this.traslados.registrarTraslados(traslados);//O(|traslados|log(|T|))
    }

    public int[] despacharMasRedituables(int n){ //O(n(log(|T|)+log(|C|)))
        int[] nuevo_array = new int[n]; //O(1)
        for (int i=0; i<n; i++){ // O(n)
            if (i>traslados.masRedituable.obtenerCantNodos()){ //O(1) 
                return nuevo_array; //O(1)    
            } else{
                //sacar el maximo y agregarlo al array
                Traslado max = traslados.masRedituable.Maximo(); //O(1)
                traslados.masRedituable.SacarMaximo(); //O(log|T|)
                nuevo_array[i]= max.id;
                //tenemos que sacar el maximo a masAntiguo (lo saca solo?)
                traslados.masAntiguo.cantidadNodos= traslados.masAntiguo.cantidadNodos-1;//O(log|T|)
                //modificamos las ganancias, perdidas, superavits de las ciudades despachadas EN LOS HEAPS
                ciudades.ciudadesArray.get(max.destino).actualizarPerdida(max.gananciaNeta);//O(1)
                ciudades.ciudadesArray.get(max.origen).actualizarGanancia(max.gananciaNeta);//O(1)
                Ciudad ciudadOrigen = ciudades.ciudadesArray.get(max.origen);
                Ciudad ciudadDestino =  ciudades.ciudadesArray.get(max.destino);
                ciudades.actualizarMayorGanancia(ciudadOrigen); //O(1)
                ciudades.actualizarMayorPerdida(ciudadDestino);//O(1)
                ciudades.actualizarHeap(ciudadOrigen); // O(log(|C|)
                ciudades.actualizarHeap(ciudadDestino);//O(log(|C|)

            }
        } //COMPLEJIDAD HASTA AHORA //O(n(log|T|))
        return nuevo_array;
    }

    public int[] despacharMasAntiguos(int n){//O(n(log(|T|)+log(|C|)))
        int[] nuevo_array = new int[n];  //O(1)
        for (int i=0; i<n; i++){ // O(n)
            if (i>traslados.masAntiguo.obtenerCantNodos()){ //O(1) 
                return nuevo_array; //O(1)    
            } else{
                //sacar el maximo y agregarlo al array
                Traslado max =traslados.masAntiguo.Maximo(); //O(1)
                traslados.masAntiguo.SacarMaximo(); //O(log|T|)
                nuevo_array[i]= max.id;
                //tenemos que sacar el maximo a masRedituables
                traslados.masRedituable.cantidadNodos--;
                //modificamos las ganancias, perdidas, superavits de las ciudades despachadas EN LOS HEAPS
                ciudades.ciudadesArray.get(max.destino).actualizarPerdida(max.gananciaNeta);//O(1)
                ciudades.ciudadesArray.get(max.origen).actualizarGanancia(max.gananciaNeta);//O(1)
                Ciudad ciudadOrigen = ciudades.ciudadesArray.get(max.origen);
                Ciudad ciudadDestino =  ciudades.ciudadesArray.get(max.destino);
                ciudades.actualizarMayorGanancia(ciudadOrigen); //O(1)
                ciudades.actualizarMayorPerdida(ciudadDestino);//O(1)
                ciudades.actualizarHeap(ciudadOrigen); // O(log(|C|)
                ciudades.actualizarHeap(ciudadDestino);//O(log(|C|)

            }
        } //COMPLEJIDAD HASTA AHORA //O(n(log|T|+log(|C|))
        return nuevo_array;
    }

    public int ciudadConMayorSuperavit(){ //O(1)
        return ciudades.obtenerMayorSuperavit().Maximo().id; //O(1)
    }

    public ArrayList<Integer> ciudadesConMayorGanancia(){
        return new ArrayList<Integer>(ciudades.mayorGanancia.subList(0, ciudades.tamañoG));
    }



    public ArrayList<Integer> ciudadesConMayorPerdida(){
        return new ArrayList<Integer>(ciudades.mayorPerdida.subList(0, ciudades.tamañoP));
    }

    public int gananciaPromedioPorTraslado(){
        // Implementar
        return 0;
    }
    
}
