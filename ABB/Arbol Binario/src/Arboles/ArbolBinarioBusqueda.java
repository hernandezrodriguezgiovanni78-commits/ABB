package Arboles;

public class ArbolBinarioBusqueda {
    public Producto raiz;

    public ArbolBinarioBusqueda() {
        this.raiz = null;
    }

    // Verifica si el árbol está vacío
    public boolean estaVacio() {
        return raiz == null;
    }

    // 1. Inserción de un nuevo producto respetando la propiedad del ABB
    public void insertar(String nombre, int stock, double precio) {
        Producto nuevo = new Producto(nombre, stock, precio);
        if (estaVacio()) {
            raiz = nuevo;
            System.out.println("¡Producto registrado como raíz exitosamente!");
        } else {
            insertarRecursivo(raiz, nuevo);
        }
    }

    private void insertarRecursivo(Producto actual, Producto nuevo) {
        int comparacion = nuevo.nombre.compareTo(actual.nombre);

        if (comparacion < 0) { // Va a la rama izquierda
            if (actual.izq == null) {
                actual.izq = nuevo;
                System.out.println("¡Producto registrado en la rama izquierda!");
            } else {
                insertarRecursivo(actual.izq, nuevo);
            }
        } else if (comparacion > 0) { // Va a la rama derecha
            if (actual.der == null) {
                actual.der = nuevo;
                System.out.println("¡Producto registrado en la rama derecha!");
            } else {
                insertarRecursivo(actual.der, nuevo);
            }
        } else {
            System.out.println("Error: El producto ya existe en el inventario (evitando duplicados).");
        }
    }

    // 2. Búsqueda de un producto por su nombre (Divide y Vencerás)
    public Producto buscar(String nombre) {
        return buscarRecursivo(raiz, nombre);
    }

    private Producto buscarRecursivo(Producto actual, String nombre) {
        if (actual == null) {
            return null;
        }
        int comparacion = nombre.compareTo(actual.nombre);
        if (comparacion == 0) {
            return actual;
        } else if (comparacion < 0) {
            return buscarRecursivo(actual.izq, nombre);
        } else {
            return buscarRecursivo(actual.der, nombre);
        }
    }

    // 3. Recorrido InOrder (Muestra los productos ordenados alfabéticamente)
    public void mostrarInOrder() {
        if (estaVacio()) {
            System.out.println("El inventario está vacío.");
        } else {
            System.out.println("\n--- INVENTARIO COMPLETO (ORDEN ALFABÉTICO) ---");
            inOrderRecursivo(raiz);
            System.out.println("----------------------------------------------");
        }
    }

    private void inOrderRecursivo(Producto nodo) {
        if (nodo != null) {
            inOrderRecursivo(nodo.izq);
            System.out.println("Producto: " + nodo.nombre + " | Stock: " + nodo.stock + " | Precio: $" + nodo.precio);
            inOrderRecursivo(nodo.der);
        }
    }

    // 4. Eliminación de un producto (Maneja los 3 casos)
    public void eliminar(String nombre) {
        raiz = eliminarRecursivo(raiz, nombre);
    }

    private Producto eliminarRecursivo(Producto actual, String nombre) {
        if (actual == null) {
            System.out.println("No se encontró el producto en el inventario.");
            return null;
        }

        int comparacion = nombre.compareTo(actual.nombre);

        if (comparacion < 0) {
            actual.izq = eliminarRecursivo(actual.izq, nombre);
        } else if (comparacion > 0) {
            actual.der = eliminarRecursivo(actual.der, nombre);
        } else {
            // ¡Nodo encontrado! Evaluamos los 3 casos de eliminación:

            // CASO 1: Nodo hoja (sin hijos) o nodo con un solo hijo
            if (actual.izq == null) {
                System.out.println("Producto '" + actual.nombre + "' eliminado (Caso: Hoja o un hijo derecho).");
                return actual.der;
            } else if (actual.der == null) {
                System.out.println("Producto '" + actual.nombre + "' eliminado (Caso: Un hijo izquierdo).");
                return actual.izq;
            }

            // CASO 3: Nodo con dos hijos (usando el sucesor inorden / menor de la rama derecha)
            Producto sucesor = obtenerMinimo(actual.der);
            actual.nombre = sucesor.nombre;
            actual.stock = sucesor.stock;
            actual.precio = sucesor.precio;
            actual.der = eliminarRecursivo(actual.der, sucesor.nombre);
            System.out.println("Producto '" + nombre + "' eliminado (Caso: Dos hijos mediante sucesor inorden).");
        }
        return actual;
    }

    // Método auxiliar para encontrar el mínimo absoluto de la rama derecha (Sucesor Inorden)
    private Producto obtenerMinimo(Producto nodo) {
        Producto actual = nodo;
        while (actual.izq != null) {
            actual = actual.izq;
        }
        return actual;
    }
}
