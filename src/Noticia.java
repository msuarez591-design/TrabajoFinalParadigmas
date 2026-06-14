import java.util.ArrayList;

/**
 * Clase que representa una noticia en el sistema de noticias.
 * @author Martin Suarez , Candela Guadalupe Bravo
 * @version 1.0
 */
public class Noticia implements Publicable {
    private String titulo;
    private String detalle;
    private int dia;
    private int mes;
    private int anio;
    private Autor autor; // Asociación con el periodista
    private ArrayList<Comentario> comentarios; // Composición

    public Noticia() {
      this.titulo = "";
      this.detalle = "";
      this.dia = 1;
      this.mes = 1;
      this.anio = 1900;
      this.autor = null;
      this.comentarios = new ArrayList<>();
    }


  /**
     * Constructor base con programación defensiva.
     *
     * @param titulo Título de la noticia (no nulo/vacío).
     * @param detalle Detalle de la noticia (no nulo/vacío).
     * @param dia Día de la publicación (entre 1 y 31).
     * @param mes Mes de la publicación (entre 1 y 12).
     * @param anio Año de la publicación (mayor o igual a 1900).
     * @param autor Autor de la noticia (no nulo).
     * @throws IllegalArgumentException si los datos son inválidos.
     * @author Martin Suarez , Candela Guadalupe Bravo
     * @version 1.0
     */
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

    // Setters sencillos 
    public void setTitulo(String titulo) {
        if (titulo == null || titulo.trim().isEmpty()) {
            throw new IllegalArgumentException("El título no puede estar vacío.");
        }
        this.titulo = titulo;
    }

    public void setDetalle(String detalle) {
        if (detalle == null || detalle.trim().isEmpty()) {
            throw new IllegalArgumentException("El detalle no puede estar vacío.");
        }
        this.detalle = detalle;
    }

    public void setDia(int dia) {
        if (dia < 1 || dia > 31) {
            throw new IllegalArgumentException("El día no es válido.");
        }
        this.dia = dia;
    }

    public void setMes(int mes) {
        if (mes < 1 || mes > 12) {
            throw new IllegalArgumentException("El mes no es válido.");
        }
        this.mes = mes;
    }

    public void setAnio(int anio) {
        if (anio < 1900) {
            throw new IllegalArgumentException("El año no es válido.");
        }
        this.anio = anio;
    }

    public void setAutor(Autor autor) {
        if (autor == null) {
            throw new IllegalArgumentException("La noticia debe tener un autor.");
        }
        this.autor = autor;
    }

    
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

    public ArrayList<Comentario> getComentarios() {
        // Método simple: devolvemos la lista interna (ArrayList) según metodología de estudiante
        return this.comentarios;
    }
}
