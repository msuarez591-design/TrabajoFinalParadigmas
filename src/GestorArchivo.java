import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Clase utilitaria encargada de la persistencia de datos en archivos de texto.
 * @author Martin Suarez, Candela Guadalupe Bravo
 * @version 2.0
 */
public class GestorArchivo {

    public void guardarEstado(ArrayList<Noticia> noticias, ArrayList<Usuario> usuarios, ArrayList<Autor> autores) {
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
                fwNoticias.write(n.getTitulo().replace(";", ",") + ";" + n.getDetalle().replace(";", ",") + ";" + n.getDia() + ";" + n.getMes() + ";" + n.getAnio() + ";" + n.getAutor().getDni() + "\n");
                for (Comentario c : n.getComentarios()) {
                    fwComentarios.write(c.getNumero() + ";" + c.getTexto().replace(";", ",") + ";" + c.getAutorComentario().getDni() + ";" + n.getTitulo().replace(";", ",") + "\n");
                }
            }
            System.out.println("-> Datos guardados exitosamente en el disco.");

        } catch (IOException e) {
            System.out.println("Error crítico al guardar los archivos: " + e.getMessage());
        }
    }

    /**
     * Lee los archivos de texto y reconstruye las colecciones en memoria.
     */
    public void cargarEstado(ArrayList<Noticia> noticias, ArrayList<Usuario> usuarios, ArrayList<Autor> autores) {
        // Limpiamos las listas para evitar duplicados si se carga dos veces
        usuarios.clear();
        autores.clear();
        noticias.clear();

        // 1. Cargar Usuarios
        File fUsuarios = new File("usuarios.txt");
        if (fUsuarios.exists()) {
            try (Scanner sc = new Scanner(fUsuarios)) {
                while (sc.hasNextLine()) {
                    String linea = sc.nextLine();
                    if (!linea.trim().isEmpty()) {
                        String[] partes = linea.split(";");
                        int dni = Integer.parseInt(partes[0]);
                        String nombre = partes[1];
                        int edad = Integer.parseInt(partes[2]);
                        usuarios.add(new Usuario(dni, nombre, edad));
                    }
                }
            } catch (Exception e) {
                System.out.println("Error al leer usuarios: " + e.getMessage());
            }
        }

        // 2. Cargar Autores
        File fAutores = new File("autores.txt");
        if (fAutores.exists()) {
            try (Scanner sc = new Scanner(fAutores)) {
                while (sc.hasNextLine()) {
                    String linea = sc.nextLine();
                    if (!linea.trim().isEmpty()) {
                        String[] partes = linea.split(";");
                        int dni = Integer.parseInt(partes[0]);
                        String nombre = partes[1];
                        String medio = partes[2];
                        autores.add(new Autor(dni, nombre, medio));
                    }
                }
            } catch (Exception e) {
                System.out.println("Error al leer autores: " + e.getMessage());
            }
        }

        // 3. Cargar Noticias (Requiere buscar al Autor previamente cargado)
        File fNoticias = new File("noticias.txt");
        if (fNoticias.exists()) {
            try (Scanner sc = new Scanner(fNoticias)) {
                while (sc.hasNextLine()) {
                    String linea = sc.nextLine();
                    if (!linea.trim().isEmpty()) {
                        String[] partes = linea.split(";");
                        String titulo = partes[0];
                        String detalle = partes[1];
                        int dia = Integer.parseInt(partes[2]);
                        int mes = Integer.parseInt(partes[3]);
                        int anio = Integer.parseInt(partes[4]);
                        int dniAutor = Integer.parseInt(partes[5]);

                        // Buscamos el objeto Autor correspondiente en la lista
                        Autor autorEncontrado = null;
                        for (Autor a : autores) {
                            if (a.getDni() == dniAutor) {
                                autorEncontrado = a;
                                break;
                            }
                        }
                        if (autorEncontrado != null) {
                            noticias.add(new Noticia(titulo, detalle, dia, mes, anio, autorEncontrado));
                        }
                    }
                }
            } catch (Exception e) {
                System.out.println("Error al leer noticias: " + e.getMessage());
            }
        }

        // 4. Cargar Comentarios (Asociar a la Noticia y a la Persona correspondiente)
        File fComentarios = new File("comentarios.txt");
        if (fComentarios.exists()) {
            try (Scanner sc = new Scanner(fComentarios)) {
                while (sc.hasNextLine()) {
                    String linea = sc.nextLine();
                    if (!linea.trim().isEmpty()) {
                        String[] partes = linea.split(";");
                        int numero = Integer.parseInt(partes[0]);
                        String texto = partes[1];
                        int dniAutorComentario = Integer.parseInt(partes[2]);
                        String tituloNoticia = partes[3];

                        // Buscar la persona que comentó (puede ser Usuario o Autor)
                        Persona autorComentario = null;
                        for (Usuario u : usuarios) {
                            if (u.getDni() == dniAutorComentario) { autorComentario = u; break; }
                        }
                        if (autorComentario == null) {
                            for (Autor a : autores) {
                                if (a.getDni() == dniAutorComentario) { autorComentario = a; break; }
                            }
                        }

                        // Buscar la noticia a la que pertenece
                        for (Noticia n : noticias) {
                            if (n.getTitulo().equalsIgnoreCase(tituloNoticia)) {
                                if (autorComentario != null) {
                                    n.agregarComentario(new Comentario(numero, texto, autorComentario));
                                }
                                break;
                            }
                        }
                    }
                }
            } catch (Exception e) {
                System.out.println("Error al leer comentarios: " + e.getMessage());
            }
        }
        System.out.println("-> Estado recuperado desde los archivos .txt de forma exitosa.");
    }
}