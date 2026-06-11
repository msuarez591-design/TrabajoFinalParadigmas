public class Comentario {
    private int numero;
    private String texto;
    private Persona autorComentario; // Puede ser Usuario o Autor

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
