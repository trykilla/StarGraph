package StarGraph.src;

import graphsDSESIUCLM.*;


import java.io.*;

import java.util.*;

/*********************************************************************
*
* Nombre de la clase: Main
* Nombre de los autores: Héctor AlbercaSanchez-Quintanar, Dario Andrés Fallavollita Figueroa
* y Rubén Crespo Calcerrada
* Fecha de creación: 12/12/2021
* Version de la clase: 2.0
* Descripción de la clase: Esta clase es la clase principal del programa, en ella se creará el grafo,
* la busqueda por anchura(BFS) como la busqueda por profundidad(DFS). 
* También nos permitirá interactuar completamente con el grafo, utilizando los datos guardados.
* 
*
**********************************************************************
*/

@SuppressWarnings("ALL")
public class Main {
    final static Scanner sn = new Scanner(System.in);

    public static void main(String[] args) throws FileNotFoundException {
        Graph gr = new TreeMapGraph();
        String ruta = "P4/resources/star wars-pers.csv";
        String ruta2 = "P4/resources/star wars-links.csv";

        // String ruta = "P4/resources/star wars-pers.csv";
        // String ruta2 = "P4/resources/star wars-links.csv";

        gr = leerFicheroPersonajes(gr, ruta);
        gr = leerFicheroRelaciones(gr, ruta2);


        menu(gr);


    }

    /***********************************************************************
	 * 
	 * Nombre del metodo: menu
	 * Descripcion del metodo: Nos mostrara un menú para elegir las distintas
	 * opciones que puede realizar el programa.
	 * Llamada de argumentos: Solamente es necesario introducirle el grafo 
	 * como argumento.
	 * 
	 * **********************************************************************/
    public static void menu(Graph gr) {

        boolean salir = false;
        int opcion; //Guardaremos la opcion del usuario

        while (!salir) {

            System.out.println("1. Mostrar estadísticas.");
            System.out.println("2. Subconjuntos que no se relacionan entre sí.");
            System.out.println("3. Remitir holo-msg");
            System.out.println("4. Salir");

            try {

                System.out.println("Escribe una de las opciones");
                opcion = sn.nextInt();
                sn.nextLine();

                switch (opcion) {
                    case 1:
                        submenu(gr);
                        break;
                    case 2:
                        subConjuntos(gr);
                        break;
                    case 3:
                        holoMensaje(gr);
                        break;
                    case 4:
                        salir = true;
                        break;
                    default:
                        System.out.println("Solo números entre 1 y 4");
                }
            } catch (InputMismatchException e) {
                System.out.println("Debes insertar un número");
                sn.next();
                sn.nextLine();
            }
        }

    }

    /***********************************************************************
   	 * 
   	 * Nombre del metodo: submenu
   	 * Descripcion del metodo: Nos mostrara un men� para elegir las distintas 
   	 * opciones que puede realizar el programa dentro de la primera opci�n 
   	 * del men� principal.
   	 * Llamada de argumentos: Solamente es necesario introducirle el grafo 
   	 * como argumento.
   	 * 
   	 * **********************************************************************/
    
    public static void submenu(Graph gr) {
        boolean salir = false;
        int opcion; //Guardaremos la opcion del usuario

        while (!salir) {

            System.out.println("\t1. Mostrar número de personajes.");
            System.out.println("\t2. Total de relaciones entre los personajes.");
            System.out.println("\t3. Personaje que tiene más relaciones con otros");
            System.out.println("\t4. Pareja de personajes con mayor interacción");
            System.out.println("\t5. Salir");

            try {

                System.out.println("Escribe una de las opciones");
                opcion = sn.nextInt();
                sn.nextLine();

                switch (opcion) {
                    case 1:
                        numPersonajes(gr);
                        break;
                    case 2:
                        numRelaciones(gr);
                        break;
                    case 3:
                        maxRelaciones(gr);
                        break;
                    case 4:
                        maxRelacionesDos(gr);
                        break;
                    case 5:
                        salir = true;
                        break;
                    default:
                        System.out.println("Solo nómeros entre 1 y 5");
                }
            } catch (InputMismatchException e) {
                System.out.println("Debes insertar un número");
                sn.next();
            }
        }
    }

    
    /***********************************************************************
   	 * 
   	 * Nombre del metodo: leerFicheroPersonajes
   	 * 
   	 * Descripcion del método: En este método se leera el fichero de los datos
   	 * de los personajes, también se separarán los datos a traves del .split.
   	 * Con estos datos crearemos a los personajes y los introduciremos en el grafo.
   	 * Los personajes serán los vértices del grafo.
   	 *
   	 * Llamada de argumentos: Es necesario introducirle el grafo como argumento.
   	 * Y la ruta donde esté el fichero de los datos sobre los personajes
   	 * 
   	 * Excepciones: FileNotFoundException, para que salte un error en el caso de 
   	 * que no se encuentre el fichero. 
   	 * 
   	 * **********************************************************************/
    public static Graph leerFicheroPersonajes(Graph gr, String ruta) throws FileNotFoundException {
        ArrayList<DecoratedElementPersonaje<Personaje>> pers = new ArrayList<DecoratedElementPersonaje<Personaje>>();
        ArrayList<Personaje> personajes = new ArrayList<Personaje>();
        File fp = new File(ruta);
        Scanner leer = new Scanner(fp);
        leer.nextLine();
        while (leer.hasNextLine()) {
            String line = leer.nextLine();
            String[] celdas = line.split(";");
            
            String id = celdas[0];
            String name = celdas[1];
            int value = Integer.parseInt(celdas[2]);
            Personaje p = new Personaje(id, name, value);
            personajes.add(p);
        }
        for (int i = 0; i < personajes.size(); i++) {
            DecoratedElementPersonaje<Personaje> e = new DecoratedElementPersonaje<Personaje>(personajes.get(i).getId(), personajes.get(i));
            pers.add(e);

        }
        for (int i = 0; i < personajes.size(); i++) {
            gr.insertVertex(pers.get(i));

        }

        return gr;
    }

    /***********************************************************************
   	 * 
   	 * Nombre del metodo: leerFicheroRelaciones
   	 * 
   	 * Descripcion del metodo: En este método se leera el fichero de los datos
   	 * de las relaciones entre personajes, también se separarán los datos a traves del .split.
   	 * Con estos datos crearemos las relaciones entre los personajes y las introduciremos en el grafo. 
   	 * Las relaciones serán las aristas del grafo, que unirán los vértices que obtendremos del fichero.
   	 *
   	 * Llamada de argumentos: Es necesario introducirle el grafo como argumento.
   	 * Y la ruta donde esté el fichero de los datos sobre las relaciones entre los personajes
   	 * 
   	 * Excepciones: FileNotFoundException, para que salte un error en el caso de 
   	 * que no se encuentre el fichero. 
   	 * 
   	 * **********************************************************************/
    
    public static Graph leerFicheroRelaciones(Graph gr, String ruta) throws FileNotFoundException {

        int cont = 0;
        File fp = new File(ruta);
        Scanner leer = new Scanner(fp);
        leer.nextLine();
        int id = 1;
        while (leer.hasNextLine()) {
            String line = leer.nextLine();
            String[] celdas = line.split(";");

            String source = celdas[0];
            String target = celdas[1];
            int value = Integer.parseInt(celdas[2]);
            Relacion r = new Relacion(source, target, value);
            DecoratedElementRelaciones<Relacion> relacion = new DecoratedElementRelaciones<Relacion>(Integer.toString(id), value, r);
            gr.insertEdge(gr.getVertex(source), gr.getVertex(target), relacion);
            id++;


        }


        return gr;
    }

    /***********************************************************************
   	 * 
   	 * Nombre del método: numPersonajes
   	 * Descripción del metodo: Este método lo utilizaremos para obtener el número
   	 * de personajes (vértices) que podemos encontrar en le grafo. Esto se hace
   	 * através de un iterador de los vértices.
   	 * Llamada de argumentos: Solamente es necesario introducirle el grafo 
   	 * como argumento.
   	 * 
   	 * **********************************************************************/
    
    public static void numPersonajes(Graph gr) {
        Iterator<Vertex> it = gr.getVertices();
        int cont = 0;

        while (it.hasNext()) {
            ++cont;
            it.next();
        }
        System.out.println("El número total de personajes es: " + cont);
    }

    /***********************************************************************
   	 * 
   	 * Nombre del metodo: numRelaciones
   	 * Descripcion del metodo: Este método lo utilizaremos para obtener el número
   	 * de relaciones (aristas) que podemos encontrar en le grafo. Esto se hace
   	 * a través de un iterador de las aristas.
   	 * Llamada de argumentos: Solamente es necesario introducirle el grafo 
   	 * como argumento.
   	 * 
   	 * **********************************************************************/
    public static void numRelaciones(Graph gr) {
        Iterator<Edge> it = gr.getEdges();
        int cont = 0;
        while (it.hasNext()) {
            ++cont;
            it.next();
        }
        System.out.println("El número total de relaciones entre personajes es: " + cont);
    }
    
    
    /***********************************************************************
   	 * 
   	 * Nombre del metodo: maxRelaciones
   	 * Descripcion del metodo: Este método lo utilizaremos para obtener el personaje
   	 * (vértice) que más se relaciona con otros personajes, es decir, el vértice que
   	 * más aristas tiene.
   	 * Esto se hace a trav�s de 2 iteradores, uno de personajes y otro de relaciones. 
   	 * Así vas recorriendo todos los vertices y comprobando cuantas aristas tiene cada uno.
   	 * Llamada de argumentos: Solamente es necesario introducirle el grafo 
   	 * como argumento.
   	 * 
   	 * **********************************************************************/
    
    public static void maxRelaciones(Graph gr) {
        Vertex<DecoratedElementPersonaje<Personaje>> aux;
        Vertex<DecoratedElementPersonaje<Personaje>> maxRel = null;
        Edge<DecoratedElementRelaciones<Relacion>> aux1;
        Iterator<Vertex<DecoratedElementPersonaje<Personaje>>> it = gr.getVertices();
        Iterator<Edge<DecoratedElementRelaciones<Relacion>>> it1;
        int max = 0;
        while (it.hasNext()) {
            int i = 0;
            aux = it.next();
            it1 = gr.incidentEdges(aux);
            while (it1.hasNext()) {
                aux1 = it1.next();
                i++;
            }
            if (i > max) {
                max = i;
                maxRel = aux;
            }

        }
        System.out.println("El personaje con más relaciones es: " + maxRel.getElement().getElement().getName() + " y tiene " + max + " relaciones.");
    }

    /***********************************************************************
   	 * 
   	 * Nombre del método: maxRelacionesDos
   	 * Descripcion del método: Este método lo utilizaremos para obtener la relación
   	 * que más peso tiene, es decir, la arista que más value tiene.
   	 * Esto lo vamos a conseguir con dos iteradores igual que en el método anterior.
   	 * Pero ahora con el iterador conseguimos la arista que más value tiene, y después
   	 * obtenemos los dos vértices que une esta arista.
   	 * Llamada de argumentos: Solamente es necesario introducirle el grafo 
   	 * como argumento.
   	 * 
   	 * **********************************************************************/
    public static void maxRelacionesDos(Graph gr) {
        Vertex<DecoratedElementPersonaje<Personaje>> aux;
        Vertex<DecoratedElementPersonaje<Personaje>> p1 = null;
        Vertex<DecoratedElementPersonaje<Personaje>> p2 = null;
        Edge<DecoratedElementRelaciones<Relacion>> maxRel = null;
        Edge<DecoratedElementRelaciones<Relacion>> aux1;
        Iterator<Vertex<DecoratedElementPersonaje<Personaje>>> it = gr.getVertices();
        Iterator<Edge<DecoratedElementRelaciones<Relacion>>> it1;
        int max = 0;
        while (it.hasNext()) {
            int i = 0;
            aux = it.next();
            it1 = gr.getEdges();
            while (it1.hasNext()) {
                aux1 = it1.next();
                i = aux1.getElement().getDistance();
                if (i > max) {
                    max = i;
                    maxRel = aux1;
                    p1 = gr.getVertex(maxRel.getElement().getElement().getSource());
                    p2 = gr.getVertex(maxRel.getElement().getElement().getDestiny());

                }

            }
        }


        System.out.println("La pareja con el mayor número de interacciones es: " + p1.getElement().getElement().getName() + "-" + p2.getElement().getElement().getName() + " y tienen un total de " + max + " interacciones.");
    }

    /***********************************************************************
   	 * 
   	 * Nombre del metodo: BFS
   	 * Descripcion del metodo: Este método lo utilizaremos para obtener el recorrido por
   	 * anchura(BFS). 
   	 * Esta ejecución se lleva a cabo de una cola. Introduces el primer vértice en la cola, después
   	 * a través de un bucle vamos comprobando los vértices y en el caso de que el valor de la relación
   	 * entre el primer vértice metido en la cola y con el que estamos comprobando sea mayor que 8, lo introducimos
   	 * en la cola y cambiamos el vértice a visitado, además ponemos al primer vértice como el padre del vertice que hemos comprobado,
   	 * y así al final en la cola, por último tenemos, todos los vértices.
   	 * y todos los demás vertices en los cuales su arista es mayor que 8.
   	 * Llamada de argumentos: Es necesario introducirle el grafo como argumento.
   	 * También se le introduce 2 personajes.
   	 * Retorno: Devuelve un vértice, el que buscas en el bfs, que como tiene todos los padres, se puede sacar
   	 * el camino. 
   	 * **********************************************************************/
    
    public static DecoratedElementPersonaje<Personaje> BFS(Graph g, Vertex<DecoratedElementPersonaje<Personaje>> s, Vertex<DecoratedElementPersonaje<Personaje>> t) {
        Queue<Vertex<DecoratedElementPersonaje<Personaje>>> q = new LinkedList();
        boolean noEnd = true;
        int valor = 0;
        Vertex<DecoratedElementPersonaje<Personaje>> u, v = null;
        u = null;
        Edge<DecoratedElementRelaciones<Relacion>> e;
        Iterator<Edge> it;

        s.getElement().setVisited(true);
        q.offer(s); //Mete en la cola q
//        boolean salir = true;
        while (!q.isEmpty() && noEnd) {
            u = q.poll();
            //Quita un elemento de la cola q y lo mete en u
            it = g.incidentEdges(u); //Itera con las edges incidentes de u
            while (it.hasNext() && noEnd) {
                e = it.next(); //Iteramos sobre las edges
                valor = e.getElement().getDistance();
                v = g.opposite(u, e); //v = edge a verificar
                if (!(v.getElement()).isVisited()) { //Si no está visitada

                    if (valor >= 8) {
                        (v.getElement()).setParent(u.getElement());
                        (v.getElement()).setVisited(true);
                        q.offer(v); //Mete el elemento v en la cola
                        noEnd = !(v.getElement().equals(t.getElement()));
                    }

                    // noEnd = se pone a false cuando se termina el algoritmo

                    //}
                }
            }
        }
        if (noEnd) v.getElement().setParent(null);
        return v.getElement();
    }
    
    /***********************************************************************
   	 * 
   	 * Nombre del metodo: holoMensaje
   	 * Descripcion del metodo: Este método lo utilizaremos para que el usuario nos diga los 2 vértices
   	 * que quiere comprobar en el camino de los BFS. Se comprueba que los 2 vértices sean correctos.
   	 * En el caso de que estos 2 vértices sean correctos vamos al método de arriba de obtener el vértice.
   	 * Si hay un camino que sea correcto de BFS, devuelve el vértice final con su padre. Vas recorriendo sus padres
   	 * hacia arriba y así obtenemos el camino final.
   	 * Llamada de argumentos: Solamente es necesario introducirle el grafo 
   	 * como argumento.
   	 * 
   	 * **********************************************************************/
    
    public static void holoMensaje(Graph gr) {
        DecoratedElementPersonaje<Personaje> startNode, endNode, nx, node = null;
        boolean bool1 = true, bool2 = true;
        int size;
        String id1, id2;
        id1 = null;
        id2 = null;
        Vertex<DecoratedElementPersonaje<Personaje>> aux, s = null, t = null;
        Edge<DecoratedElementRelaciones<Relacion>> auxEdge;
        Edge<DecoratedElementRelaciones<Relacion>> auxEdge2 = null;
        Stack<DecoratedElementPersonaje<Personaje>> sp = new Stack();
        Iterator<Vertex<DecoratedElementPersonaje<Personaje>>> it1;
        Iterator<Vertex<DecoratedElementPersonaje<Personaje>>> it2;
        Iterator<Edge<DecoratedElementRelaciones<Relacion>>> it3;

        System.out.println("Dime el primer personaje ");
        String nombre1 = sn.nextLine();

        System.out.println("Dime el segundo personaje ");
        String nombre2 = sn.nextLine();

        it2 = gr.getVertices();
        
        while (it2.hasNext()) {
            aux = it2.next();
            if (aux.getElement().getElement().getName().equals(nombre1.toUpperCase())) {
                id1 = aux.getID();
            }
            if (aux.getElement().getElement().getName().equals(nombre2.toUpperCase())) {
                id2 = aux.getID();
            }
        }
        try {
            Vertex<DecoratedElementPersonaje<Personaje>> e = gr.getVertex(id1);
            Vertex<DecoratedElementPersonaje<Personaje>> x = gr.getVertex(id2);
        
            
            startNode = e.getElement();
            endNode = x.getElement();
        } catch (NullPointerException e) {
            System.out.println("No existe el personaje");
            return;
        }

        it1 = gr.getVertices();
        while (it1.hasNext() && (bool1 || bool2)) {
            aux = it1.next();
            nx = aux.getElement();
            if (nx.equals(startNode)) {
                s = aux;
                bool1 = false;
            }
            if (nx.equals(endNode)) {
                t = aux;
                bool2 = false;
            }
        }


        if (!bool1 || bool2) {
            node = BFS(gr, s, t);


            if (node.getParent() == null) {
                System.out.println("\nNo existe un camino o algún camino seguro.");
                it1 = gr.getVertices();
                while (it1.hasNext()) {
                    aux = it1.next();
                    aux.getElement().setVisited(false);
                    aux.getElement().setParent(null);
                }
            } else {

                while (node.getParent() != null) {
                    sp.push(node);
                    node = node.getParent();
                }
                it1 = gr.getVertices();
                while (it1.hasNext()) {
                    aux = it1.next();
                    aux.getElement().setVisited(false);
                    aux.getElement().setParent(null);
                }
                sp.push(node);

                size = sp.size();
                System.out.println("El camino para llegar de " + nombre1.toUpperCase() + " a " + nombre2.toUpperCase() + " es:");
                for (int i = 0; i < size - 1; i++) {
                    node = sp.pop();
                    System.out.print(node.getElement().getName() + "----->");


                }
                node = sp.pop();

                System.out.println(node.getElement().getName());


//                System.out.println("Interacciones: " + auxEdge2.getElement().getDistance());
            }
        } else System.out.println("\nUn nodo no está en el grafo.");

    }

    /***********************************************************************
   	 * 
   	 * Nombre del metodo: subConjuntos 
   	 * Descripcion del metodo: Este metodo lo usaremos para realizar la comprobación de que haya un camino
   	 * en profundidad, a través de la llamada del método.
   	 * Para ello, se introducen todos los vértices en una array de vertices y a través de un bucle for,
   	 * recorremos todos los vértices posibles.
   	 * En el caso de que el booleano que nos devuelve sea verdadero, significará que se han recorrido todos los
   	 * vértices satisfactoriamente, y por lo tanto, no habrá subconjuntos en el grafo.
   	 * En el caso de que el booleano sea falso, signficará que no se han recorrido todos los vértices, y
   	 * por lo tanto,  habrá subconjuntos en el grafo.
   	 * Llamada de argumentos: Solamente es necesario introducirle el grafo 
   	 * como argumento.
   	 * 
   	 * **********************************************************************/
    
    private static void subConjuntos(Graph gr) {

        Vertex<DecoratedElementPersonaje<Personaje>> v, u;
        int d = 0, n = 0, aux;
        int nVertices = gr.getN();
        boolean existe = true;
        Vertex<DecoratedElementPersonaje<Personaje>> auxiliar;
        Iterator<Vertex<DecoratedElementPersonaje<Personaje>>> iterador;
        iterador = gr.getVertices();
        Vertex<DecoratedElementPersonaje<Personaje>>[] vertices = new Vertex[nVertices];
        for (int i = 0; i < nVertices; i++) {
            vertices[i] = iterador.next();
        }
        for (int i = 0; i < nVertices && existe; i++) {
            for (int j = i + 1; j < nVertices && existe; j++) {
                existe = DFSRecur(gr, vertices[i], vertices[j]);
            }
        }

        if (existe)
            System.out.println("No hay subconjuntos separados.");
        else
            System.out.println("Hay subconjuntos separados.");
        iterador = gr.getVertices();
        while (iterador.hasNext()) {
            auxiliar = iterador.next();
            auxiliar.getElement().setVisited(false);
            auxiliar.getElement().setParent(null);
        }
    }


    /***********************************************************************
   	 * 
   	 * Nombre del metodo: DFSRecur
   	 * Descripcion del metodo: En este método se comprueban si todos los vértices están visitados o no.
   	 * Esto lo hace a través de la recursividad de este método. Gracias a esta recursividad se va comprobando
   	 * que todos los vértices hayan sido visitado. Lo comprueba desde el primer vértice introducido
   	 * hasta el último introducido.
   	 * Llamada de argumentos: Es necesario introducirle el grafo y 2 vértices
   	 * como argumentos.
   	 * Retorno: Devuelve un booleano dependiendo de que todos los vértices están visitados o no.
   	 * **********************************************************************/
    
    public static boolean DFSRecur(Graph gr, Vertex<DecoratedElementPersonaje<Personaje>> v, Vertex<DecoratedElementPersonaje<Personaje>> z) {

        boolean noEnd = !v.equals(z);
        Vertex<DecoratedElementPersonaje<Personaje>> u;
        Edge e;
        Iterator<Edge<DecoratedElementRelaciones<Relacion>>> it;

        v.getElement().setVisited(true);

        it = gr.incidentEdges(v);
        while (it.hasNext() && noEnd) {
            e = it.next();
            u = gr.opposite(v, e);
            if (!u.getElement().isVisited()) {
                noEnd = DFSRecur(gr, u, z);
            }
        }
        return noEnd;
    }
}
