import java.util.ArrayList;

/**
 * Orquestador principal del sistema de noticias NEWS.
 * @author Martin Suarez, Candela Guadalupe Bravo
 * @version 1.0
 */
public class Registro {
    private ArrayList<Noticia> listaNoticias;
    private ArrayList<Usuario> listaUsuarios;
    private ArrayList<Autor> listaAutores;
    private GestorArchivo gestorArchivo;

    public Registro() {
        this.listaNoticias = new ArrayList<>();
        this.listaUsuarios = new ArrayList<>();
        this.listaAutores = new ArrayList<>();
        this.gestorArchivo = new GestorArchivo();
    }

    public Registro(ArrayList<Noticia> noticias, ArrayList<Usuario> usuarios, ArrayList<Autor> autores) {
        this.listaNoticias = (noticias != null) ? noticias : new ArrayList<>();
        this.listaUsuarios = (usuarios != null) ? usuarios : new ArrayList<>();
        this.listaAutores = (autores != null) ? autores : new ArrayList<>();
        this.gestorArchivo = new GestorArchivo();
    }

    // --- MÉTODOS DE REGISTRO (REQUERIMIENTOS) ---

    public void registrarAutor(Autor autor) {
        if (autor == null) throw new IllegalArgumentException("El autor no puede ser nulo.");
        if (buscarAutorPorDni(autor.getDni()) == null) {
            this.listaAutores.add(autor);
        }
    }

    public void registrarUsuario(Usuario usuario) {
        if (usuario == null) throw new IllegalArgumentException("El usuario no puede ser nulo.");
        if (buscarUsuarioPorDni(usuario.getDni()) == null) {
            this.listaUsuarios.add(usuario);
        }
    }

    public void cargarNoticia(Noticia noticia) {
        if (noticia == null) throw new IllegalArgumentException("La noticia no puede ser nula.");
        // Validamos que la noticia tenga autor y que éste esté registrado
        if (noticia.getAutor() == null) {
            throw new IllegalArgumentException("La noticia debe tener un autor registrado.");
        }
        if (buscarAutorPorDni(noticia.getAutor().getDni()) == null) {
            throw new IllegalArgumentException("Error: El autor de la noticia no está registrado en el sistema.");
        }
        this.listaNoticias.add(noticia);
    }

    public void registrarComentarioEnNoticia(String tituloNoticia, int numero, String texto, int dniLector) {
        Noticia noticia = buscarNoticiaPorTitulo(tituloNoticia);
        if (noticia == null) {
            System.out.println("Error: No existe la noticia titulada: " + tituloNoticia);
            return;
        }

        // Buscamos si el que comenta es un Usuario registrado
        Persona lector = buscarUsuarioPorDni(dniLector);
        // Si no es un usuario lector, verificamos si es un Autor quien comenta
        if (lector == null) {
            lector = buscarAutorPorDni(dniLector);
        }

        if (lector == null) {
            System.out.println("Error: El DNI " + dniLector + " no corresponde a un usuario registrado. No puede comentar.");
            return;
        }

        noticia.agregarComentario(new Comentario(numero, texto, lector));
    }

    // --- MÉTODOS DE BÚSQUEDA INTERNOS (PROG. DEFENSIVA) ---

    private Autor buscarAutorPorDni(int dni) {
        for (Autor a : listaAutores) {
            if (a.getDni() == dni) return a;
        }
        return null;
    }

    private Usuario buscarUsuarioPorDni(int dni) {
        for (Usuario u : listaUsuarios) {
            if (u.getDni() == dni) return u;
        }
        return null;
    }

    private Noticia buscarNoticiaPorTitulo(String titulo) {
        for (Noticia n : listaNoticias) {
            if (n.getTitulo().equalsIgnoreCase(titulo)) return n;
        }
        return null;
    }

    // --- CONSULTAS SOLICITADAS POR LA CONSIGNA ---

    /**
     * Consulta 1: Listar noticias publicadas en el año
     */
    public void listarNoticiasPorAnio(int anio) {
        System.out.println("\n--- NOTICIAS PUBLICADAS EN EL AÑO " + anio + " ---");
        boolean encontro = false;
        for (Noticia n : listaNoticias) {
            if (n.getAnio() == anio) {
                System.out.println(n.obtenerFormato());
                encontro = true;
            }
        }
        if (!encontro) System.out.println("No se encontraron noticias en ese año.");
    }

    /**
     * Consulta 2: Listado de noticias publicadas el último mes (simulado mediante parámetros de fecha actual)
     */
    public void listarNoticiasUltimoMes(int mesActual, int anioActual) {
        System.out.println("\n--- NOTICIAS PUBLICADAS EL ÚLTIMO MES (" + mesActual + "/" + anioActual + ") ---");
        boolean encontro = false;
        for (Noticia n : listaNoticias) {
            if (n.getMes() == mesActual && n.getAnio() == anioActual) {
                System.out.println(n.obtenerFormato());
                encontro = true;
            }
        }
        if (!encontro) System.out.println("No hay publicaciones registradas para este periodo.");
    }

    /**
     * Consulta 3: Mostrar una noticia y sus comentarios asociados
     */
    public void mostrarNoticiaYComentarios(String titulo) {
        Noticia n = buscarNoticiaPorTitulo(titulo);
        System.out.println("\n--- VISTA DE ARTÍCULO ---");
        if (n == null) {
            System.out.println("La noticia solicitada no existe.");
            return;
        }
        System.out.println(n.obtenerFormato());
        System.out.println("Detalle: " + n.getDetalle());
        System.out.println("  ↳ Comentarios (" + n.getComentarios().size() + "):");
        if (n.getComentarios().isEmpty()) {
            System.out.println("    [Aún no hay comentarios en esta noticia]");
        } else {
            for (Comentario c : n.getComentarios()) {
                System.out.println("    * #" + c.getNumero() + " - " + c.getTexto() + " (Por: " + c.getAutorComentario().getNombre() + ")");
            }
        }
    }

    /**
     * Consulta 4: Artículos publicados por un determinado autor
     */
    public void listarNoticiasPorAutor(int dniAutor) {
        Autor autor = buscarAutorPorDni(dniAutor);
        if (autor == null) {
            System.out.println("\nEl autor con DNI " + dniAutor + " no existe.");
            return;
        }
        System.out.println("\n--- ARTÍCULOS PUBLICADOS POR: " + autor.getNombre() + " (" + autor.getMedio() + ") ---");
        boolean encontro = false;
        for (Noticia n : listaNoticias) {
            if (n.getAutor().getDni() == dniAutor) {
                System.out.println("- " + n.getTitulo() + " (" + n.getDia() + "/" + n.getMes() + "/" + n.getAnio() + ")");
                encontro = true;
            }
        }
        if (!encontro) System.out.println("Este autor aún no ha publicado artículos.");
    }

    // --- MÉTODOS DE DISCO ---

    public void guardarEnDisco() {
        this.gestorArchivo.guardarEstado(this.listaNoticias, this.listaUsuarios, this.listaAutores);
    }

    public void cargarDesdeDisco() {
        this.gestorArchivo.cargarEstado(this.listaNoticias, this.listaUsuarios, this.listaAutores);
    }

    // --- Getters y Setters 
    public ArrayList<Noticia> getListaNoticias() {
        return this.listaNoticias;
    }

    public void setListaNoticias(ArrayList<Noticia> listaNoticias) {
        this.listaNoticias = (listaNoticias != null) ? listaNoticias : new ArrayList<>();
    }

    public ArrayList<Usuario> getListaUsuarios() {
        return this.listaUsuarios;
    }

    public void setListaUsuarios(ArrayList<Usuario> listaUsuarios) {
        this.listaUsuarios = (listaUsuarios != null) ? listaUsuarios : new ArrayList<>();
    }

    public ArrayList<Autor> getListaAutores() {
        return this.listaAutores;
    }

    public void setListaAutores(ArrayList<Autor> listaAutores) {
        this.listaAutores = (listaAutores != null) ? listaAutores : new ArrayList<>();
    }

    public GestorArchivo getGestorArchivo() {
        return this.gestorArchivo;
    }

    public void setGestorArchivo(GestorArchivo gestorArchivo) {
        if (gestorArchivo != null) this.gestorArchivo = gestorArchivo;
    }
}