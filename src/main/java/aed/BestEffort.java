package aed;

import java.util.ArrayList;

public class BestEffort {
    private Traslados traslados;
    private Ciudades ciudades; 
    private int gananciaTotal;
    private int trasladosDespachados;

    public BestEffort(int cantCiudades, Traslado[] traslados){ //Complejidad O(|C| + |T|)
        this.trasladosDespachados = 0; //O(1)
        this.gananciaTotal = 0; //O(1)
        int i = 0; //O(1)
        int tamaño= traslados.length; //O(1)
        ArrayList<Traslado> arr = new ArrayList<Traslado>(tamaño); //O(1)
        while (i < traslados.length){ // O(|T|)
            arr.add( traslados[i]);
            i++;
        }
        this.traslados = new Traslados(arr); // O(|T|)
        int j = 0; //O(1)
        ArrayList<Ciudad> arrCiudades = new ArrayList<Ciudad>(cantCiudades); //O(1)
        while(j < cantCiudades){//complejidad O(|C|)
            Ciudad c = new Ciudad(j);
            arrCiudades.add(c);
            j++;
        }
        this.ciudades = new Ciudades(arrCiudades);//complejidad O(|C|)
    }

    public void registrarTraslados(Traslado[] traslados){ // Complejidad O(|traslados|log(|T|))
        this.traslados.registrarTraslados(traslados);//O(|traslados|log(|T|))
    }

    public int[] despacharMasRedituables(int n){ //O(n(log(|T|)+log(|C|)))
        int[] nuevo_array; //O(1)
        int CantidadDeNodos = traslados.masRedituable.obtenerCantNodos(); //O(1)

        if (n==0 || CantidadDeNodos==0){
            return nuevo_array = new int[0];
        }

        if( n > CantidadDeNodos){
            return despacharMasRedituables(CantidadDeNodos);
        } else{
            nuevo_array = new int[n];
            for (int i=0; i<n; i++){ // O(n) 
                //sacar el maximo y agregarlo al array
                Traslado max = traslados.masRedituable.Maximo(); //O(1)
                traslados.masRedituable.SacarMaximo(); //O(log|T|)
                nuevo_array[i]= max.id; //O(1)
                gananciaTotal = gananciaTotal + max.gananciaNeta; //O(1)
                trasladosDespachados++; //O(1)
                //tenemos que sacar el maximo a masAntiguo (lo saca solo?)
                int indice= max.Handles.get(1);//O(log|T|)
                traslados.masAntiguo.eliminarEn(indice);
                //modificamos las ganancias, perdidas, superavits de las ciudades despachadas 
                ActualizarCiudades(max);//O(log(|C|)
                }
            }  //O(n(log|T|) + O(log(|C|))
        return nuevo_array;
    }

    private void ActualizarCiudades (Traslado max){
        // actualizamos el arrayCiudades
        Ciudad ciudadOrigen = ciudades.ciudadesArray.get(max.origen); //O(1)
        Ciudad ciudadDestino =  ciudades.ciudadesArray.get(max.destino); //O(1)

        ciudades.actualizarMayorGanancia(ciudadOrigen, max.gananciaNeta); //O(1)
        ciudades.actualizarMayorPerdida(ciudadDestino, max.gananciaNeta);//O(1)

        // actualizamos el heap de superavit
        ciudades.actualizarHeap(ciudadOrigen); // O(log(|C|)
        ciudades.actualizarHeap(ciudadDestino);//O(log(|C|)
    }

    public int[] despacharMasAntiguos(int n){//O(n(log(|T|)+log(|C|)))
        int[] nuevo_array; //O(1)
        int CantidadDeNodos = traslados.masAntiguo.obtenerCantNodos(); //O(1)

        if (n==0 || CantidadDeNodos==0){
            return nuevo_array = new int[0];
        }

        if( n > CantidadDeNodos){
            return despacharMasAntiguos(CantidadDeNodos);
        } else {
            nuevo_array = new int[n];
            for (int i=0; i<n; i++){ // O(n) 
                //sacar el maximo y agregarlo al array
                Traslado max =traslados.masAntiguo.Maximo(); //O(1)
                traslados.masAntiguo.SacarMaximo(); //O(log|T|)
                nuevo_array[i]= max.id;
                gananciaTotal = gananciaTotal + max.gananciaNeta;
                trasladosDespachados++;
                //tenemos que sacar el maximo a masRedituables
                int indice = max.Handles.get(0); //O(1)
                traslados.masRedituable.eliminarEn(indice); // O(log|T|)
                //modificamos las ganancias, perdidas, superavits de las ciudades despachadas EN LOS HEAPS
                ActualizarCiudades(max); // O(log |C|);
            }
        } //O(n(log|T|+log(|C|))
        return nuevo_array;
    }

    public int ciudadConMayorSuperavit(){ //O(1) 
        return ciudades.obtenerMayorSuperavit().Maximo().id; //O(1)
    }

    public ArrayList<Integer> ciudadesConMayorGanancia(){
        return new ArrayList<Integer>(ciudades.mayorGanancia.subList(0, ciudades.cantidadCiudadesGananciaMax));
    }



    public ArrayList<Integer> ciudadesConMayorPerdida(){
        return new ArrayList<Integer>(ciudades.mayorPerdida.subList(0, ciudades.cantidadCiudadesPerdidaMax));
    }

    public int gananciaPromedioPorTraslado(){
        return gananciaTotal/trasladosDespachados;
    }
    
}
