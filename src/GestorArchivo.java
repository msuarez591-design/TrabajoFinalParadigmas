import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Clase utilitaria encargada de la persistencia de datos.
 */
public class GestorArchivo {

    /**
     * Guarda las listas en archivos de texto.
     * Hace uso del bloque try-catch para atrapar errores de disco.
     */
    public void guardarEstado(List<Noticia> noticias, List<Usuario> usuarios, List<Autor> autores) {
        try (FileWriter fwUsuarios = new FileWriter("usuarios.txt");
             FileWriter fwAutores = new FileWriter("autores.txt");
             FileWriter fwNoticias = new FileWriter("noticias.txt");
             FileWriter fwComentarios = new FileWriter("comentarios.txt")) {

            for (Usuario u : usuarios) {
                fwUsuarios.write(u.getDni() + ";" + u.getNombre() + ";" + u.getEdad() + "\n");
            }

            for (Autor a : autores) {
                fwAutores.write(a.getDni() + ";" + a.getNombre() + ";" + a.getMedio() + "\n");
            }

            for (Noticia n : noticias) {
                fwNoticias.write(n.getTitulo() + ";" + n.getDetalle().replace(";", ",") + ";" + n.getDia() + ";" + n.getMes() + ";" + n.getAnio() + ";" + n.getAutor().getDni() + "\n");
                for (Comentario c : n.getComentarios()) {
                    fwComentarios.write(c.getNumero() + ";" + c.getTexto().replace(";", ",") + ";" + c.getAutorComentario().getDni() + ";" + n.getTitulo().replace(";", ",") + "\n");
                }
            }

        } catch (IOException e) {
            System.out.println("Error crítico al guardar los archivos: " + e.getMessage());
        }
    }

    // Método cargarEstado() aún puede implementarse según el formato de los archivos.
}
