/**
 * Representa la entidad concreta para un comentario del sistema.
 * @author Martin Suarez , Candela Guadalupe Bravo
 * @version 1.0
 */
public class Comentario {
    private int numero;
    private String texto;
    private Persona autorComentario; // Puede ser Usuario o Autor

    //Constructores
    public Comentario() {
        this.numero = 0; // Valor por defecto para número
        this.texto = "Sin comentario"; // Valor por defecto para texto
        this.autorComentario = null; // Sin autor por defecto
    }

     /**
     * Constructor completo con programación defensiva.
     *
     * @param numero Número del comentario (puede ser 0 o positivo).
     * @param texto Texto del comentario (no nulo/vacío).
     * @param autorComentario Autor del comentario (no nulo).
     * @throws IllegalArgumentException si los datos son inválidos.
     * @author Martin Suarez , Candela Guadalupe Bravo
     * @version 1.0
     */

    public Comentario(int numero, String texto, Persona autorComentario) {
        if (texto == null || texto.trim().isEmpty()) {
            throw new IllegalArgumentException("El comentario no puede estar vacío.");
        }
        if (autorComentario == null) {
            throw new IllegalArgumentException("El comentario debe tener un autor.");
        }
        this.numero = numero;
        this.texto = texto;
        this.autorComentario = autorComentario;
    }

    public int getNumero() {
        return this.numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getTexto() {
        return this.texto;
    }

    public void setTexto(String texto) {
        if (texto == null || texto.trim().isEmpty()) {
            throw new IllegalArgumentException("El comentario no puede estar vacío.");
        }
        this.texto = texto;
    }

    public Persona getAutorComentario() {
        return this.autorComentario;
    }

    public void setAutorComentario(Persona autorComentario) {
        if (autorComentario == null) {
            throw new IllegalArgumentException("El comentario debe tener un autor.");
        }
        this.autorComentario = autorComentario;
    }
}
