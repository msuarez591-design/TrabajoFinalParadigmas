/**
 * Interfaz que define el comportamiento obligatorio para cualquier 
 * elemento del sistema que pueda ser publicado y mostrado por pantalla.
 * @author Martin Suarez , Candela Guadalupe Bravo
 * @version 1.0
 */
public interface Publicable {
    /**
     * Devuelve el contenido formateado listo para impresión.
     * @return String con el formato de la publicación.
     */
    String obtenerFormato();
}
