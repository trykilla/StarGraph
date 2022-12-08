package StarGraph.src;

/************************************************************************************************
 * 
 * Nombre de Clase: Relacion
 * Nombre de autores: Héctor AlbercaSanchez-Quintanar, Dario Andrés Fallavollita Figueroa
 * y Rubén Crespo Calcerrada
 * Fecha de creación: 12/12/2021
 * Version de la Clase: 2.0
 * Descripcion de la Clase: Esta es la clase que nos va a permitir definir todos los atributos 
 * que van a tener las relaciones, estos atributos los obtendremos del fichero. 
 * 
 ************************************************************************************************/

public class Relacion {
    private String source;
    private String destiny;
    private int value;

    public Relacion(String source, String destiny, int value) {
        this.source = source;
        this.destiny = destiny;
        this.value = value;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestiny() {
        return destiny;
    }

    public void setDestiny(String destiny) {
        this.destiny = destiny;
    }

    public int  getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Relación{" + "source='" + source + '\'' + ", destiny='" + destiny + '\'' + ", value=" + value + '}';
    }
}
