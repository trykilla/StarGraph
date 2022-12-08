package StarGraph.src;

import graphsDSESIUCLM.Element;

/************************************************************************************************
 * 
 * Nombre de Clase: DecoratedElementRelaciones
 * Nombre de autores: Héctor AlbercaSanchez-Quintanar, Dario Andrés Fallavollita Figueroa
 * y Rubén Crespo Calcerrada
 * Fecha de creacion: 12/12/2021
 * Version de la Clase: 2.0
 * Descripcion de la Clase: Esta es la clase que nos va a permitir donde vamos a guardar las aristas,
 * es decir, al objeto Relaciones y algunos valores de interés para el grafo y su recorrido.
 * 
 ************************************************************************************************/

public class DecoratedElementRelaciones<T> implements Element {


    private String  ID;
    private int distance;
    private DecoratedElementRelaciones<T> parent;
    private T element;

    public DecoratedElementRelaciones(String key, int distance,T element) {
        this.ID = key;
        this.distance = distance;
        parent = null;
        this.element = element;
    }

    public T getElement() {
        return element;
    }

    public void setElement(T element) {
        this.element = element;
    }

    public DecoratedElementRelaciones<T> getParent() {
        return parent;
    }

    public void setParent(DecoratedElementRelaciones<T> parent) {
        this.parent = parent;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }


    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }


}




