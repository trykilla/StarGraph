package StarGraph.src;


/************************************************************************************************
 * 
 * Nombre de Clase: Personaje
 * Nombre de autores: Héctor AlbercaSanchez-Quintanar, Dario Andrés Fallavollita Figueroa
 * y Rubén Crespo Calcerrada
 * Fecha de creacion: 12/12/2021
 * Version de la Clase: 2.0
 * Descripcion de la Clase: Esta es la clase que nos va a permitir definir todos los atributos 
 * que van a tener los personajes, estos atributos los obtendremos del fichero. 
 * 
 ************************************************************************************************/

public class Personaje {
    private String id;
    private String name;
    private int value;

    public Personaje(String id, String name, int value) {
        this.id = id;
        this.name = name;
        this.value = value;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Personaje{" + "id=" + id + ", name='" + name + '\'' + ", value=" + value + "}";
    }
}
