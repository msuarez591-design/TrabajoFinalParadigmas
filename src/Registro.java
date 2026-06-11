import java.util.ArrayList;
import java.util.List;

/**
 * Clase controladora que gestiona el estado en memoria de todo el sistema.
 */
public class Registro {
    private List<Usuario> usuarios;
    private List<Autor> autores;
    private List<Noticia> noticias;
    private List<Comentario> comentarios;

    public Registro() {
        this.usuarios = new ArrayList<>();
        this.autores = new ArrayList<>();
        this.noticias = new ArrayList<>();
        this.comentarios = new ArrayList<>();
    }

    public void registrarUsuario(Usuario u) {
        if (u == null) throw new IllegalArgumentException("Usuario nulo.");

        for (Usuario registrado : this.usuarios) {
            if (registrado.getDni().equals(u.getDni())) {
                throw new IllegalStateException("El DNI ya está registrado.");
            }
        }
        this.usuarios.add(u);
    }

    public void registrarAutor(Autor a) {
        if (a == null) throw new IllegalArgumentException("Autor nulo.");

        for (Autor registrado : this.autores) {
            if (registrado.getDni().equals(a.getDni())) {
                throw new IllegalStateException("El DNI ya está registrado.");
            }
        }
        this.autores.add(a);
    }

    public void publicarNoticia(Noticia n) {
        if (n == null) throw new IllegalArgumentException("Noticia nula.");
        if (!this.autores.contains(n.getAutor())) {
            throw new IllegalStateException("El autor de la noticia no está registrado.");
        }
        this.noticias.add(n);
    }

    public void registrarComentario(Comentario c, Noticia noticia) {
        if (c == null) throw new IllegalArgumentException("Comentario nulo.");
        if (noticia == null) throw new IllegalArgumentException("Noticia nula.");
        if (!this.noticias.contains(noticia)) {
            throw new IllegalArgumentException("La noticia no está registrada.");
        }
        Persona autor = c.getAutorComentario();
        if (autor == null) {
            throw new IllegalArgumentException("El comentario debe tener un autor.");
        }
        boolean autorRegistrado = this.usuarios.contains(autor) || this.autores.contains(autor);
        if (!autorRegistrado) {
            throw new IllegalStateException("El autor del comentario no está registrado.");
        }

        noticia.agregarComentario(c);
        this.comentarios.add(c);
    }

    public List<Noticia> listarNoticiasPorAnio(int anio) {
        List<Noticia> resultado = new ArrayList<>();
        for (Noticia n : this.noticias) {
            if (n.getAnio() == anio) {
                resultado.add(n);
            }
        }
        return resultado;
    }

    public List<Noticia> listarNoticiasUltimoMes(int anio, int mes) {
        List<Noticia> resultado = new ArrayList<>();
        for (Noticia n : this.noticias) {
            if (n.getAnio() == anio && n.getMes() == mes) {
                resultado.add(n);
            }
        }
        return resultado;
    }

    public List<Noticia> listarNoticiasPorAutor(Autor autor) {
        if (autor == null) throw new IllegalArgumentException("Autor nulo.");
        List<Noticia> resultado = new ArrayList<>();
        for (Noticia n : this.noticias) {
            if (n.getAutor().getDni().equals(autor.getDni())) {
                resultado.add(n);
            }
        }
        return resultado;
    }

    public List<Comentario> obtenerComentariosPorNoticia(Noticia noticia) {
        if (noticia == null) throw new IllegalArgumentException("Noticia nula.");
        if (!this.noticias.contains(noticia)) {
            throw new IllegalArgumentException("La noticia no está registrada.");
        }
        return new ArrayList<>(noticia.getComentarios());
    }

    public List<Usuario> getUsuarios() {
        return new ArrayList<>(this.usuarios);
    }

    public List<Autor> getAutores() {
        return new ArrayList<>(this.autores);
    }

    public List<Noticia> getNoticias() {
        return new ArrayList<>(this.noticias);
    }

    public List<Comentario> getComentarios() {
        return new ArrayList<>(this.comentarios);
    }
}
