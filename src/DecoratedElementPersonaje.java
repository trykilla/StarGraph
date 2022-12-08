package StarGraph.src;

import graphsDSESIUCLM.*;

/************************************************************************************************
 * 
 * Nombre de Clase: DecoratedElementPersonaje
 * Nombre de autores: Héctor AlbercaSanchez-Quintanar, Dario Andrés Fallavollita Figueroa
 * y Rubén Crespo Calcerrada
 * Fecha de creacion: 12/12/2021
 * Version de la Clase: 2.0
 * Descripcion de la Clase: Esta es la clase que nos va a permitir donde vamos a guardar los vértices,
 * es decir, al objeto Personaje y algunos valores de interés para el grafo y su recorrido.
 * 
 ************************************************************************************************/

public class DecoratedElementPersonaje<T> implements Element {
    private String ID;
    private T element;
    private boolean visited;
    private DecoratedElementPersonaje<T> parent;
    private int distance;

    public DecoratedElementPersonaje(String key, T element) {
        ID = key;
        this.element = element;
        visited = false;
        parent = null;
        distance = 0;
    }

    public T getElement() {
        return element;
    }

    public void setElement(T element) {
        this.element = element;
    }

    @Override
    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public DecoratedElementPersonaje<T> getParent() {
        return parent;
    }

    public void setParent(DecoratedElementPersonaje<T> parent) {
        this.parent = parent;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    @Override
    public String toString() {
        return "DecoratedElementPersonaje{" + "ID='" + ID + '\'' + ", visited=" + visited + ", parent=" + parent + ", distance=" + distance + '}';
    }
}


