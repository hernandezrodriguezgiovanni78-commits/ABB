package Arboles;

public class Producto {
    public String nombre;    // Llave alfabética única que rige el orden del ABB
    public int stock;        // Cantidad disponible en el almacén
    public double precio;    // Costo unitario del producto
    public Producto izq;     // Apuntador al hijo izquierdo
    public Producto der;     // Apuntador al hijo derecho

    // Constructor
    public Producto(String nombre, int stock, double precio) {
        this.nombre = nombre;
        this.stock = stock;
        this.precio = precio;
        this.izq = null;
        this.der = null;
    }
}
