import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Noticia implements Publicable {
    private String titulo;
    private String detalle;
    private int dia;
    private int mes;
    private int anio;
    private Autor autor; // Asociación con el periodista
    private List<Comentario> comentarios; // Composición

    public Noticia(String titulo, String detalle, int dia, int mes, int anio, Autor autor) {
        if (titulo == null || titulo.trim().isEmpty()) {
            throw new IllegalArgumentException("El título no puede estar vacío.");
        }
        if (detalle == null || detalle.trim().isEmpty()) {
            throw new IllegalArgumentException("El detalle no puede estar vacío.");
        }
        if (dia < 1 || dia > 31) {
            throw new IllegalArgumentException("El día no es válido.");
        }
        if (mes < 1 || mes > 12) {
            throw new IllegalArgumentException("El mes no es válido.");
        }
        if (anio < 1900) {
            throw new IllegalArgumentException("El año no es válido.");
        }
        if (autor == null) {
            throw new IllegalArgumentException("La noticia debe tener un autor.");
        }

        this.titulo = titulo;
        this.detalle = detalle;
        this.dia = dia;
        this.mes = mes;
        this.anio = anio;
        this.autor = autor;
        this.comentarios = new ArrayList<>();
    }

    public void agregarComentario(Comentario c) {
        if (c == null) {
            throw new IllegalArgumentException("No se puede agregar un comentario nulo.");
        }
        this.comentarios.add(c);
    }

    @Override
    public String obtenerFormato() {
        return "Noticia: " + this.titulo + " | Por: " + this.autor.getNombre() +
               " | Fecha: " + this.dia + "/" + this.mes + "/" + this.anio;
    }

    public String getTitulo() {
        return this.titulo;
    }

    public String getDetalle() {
        return this.detalle;
    }

    public int getDia() {
        return this.dia;
    }

    public int getMes() {
        return this.mes;
    }

    public int getAnio() {
        return this.anio;
    }

    public Autor getAutor() {
        return this.autor;
    }

    public List<Comentario> getComentarios() {
        return Collections.unmodifiableList(this.comentarios);
    }
}
