/**
 * Clase principal de pruebas para verificar las consignas del Sistema de Noticias.
 * @author Martin Suarez, Candela Guadalupe Bravo
 * @version 1.0
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("====================================================");
        System.out.println("      INICIANDO SIMULACIÓN - SISTEMA NEWS           ");
        System.out.println("====================================================\n");

        // 1. Creamos el orquestador original
        Registro sistemaNoticias = new Registro();

        // 2. Registro de Autores
        System.out.println("\n[1] REGISTRO DE AUTORES:");
        Autor autor1 = new Autor(35123456, "Martin Suarez", "La Voz del Interior");
        Autor autor2 = new Autor(38987654, "Candela Bravo", "Canal 12 Córdoba");
        sistemaNoticias.registrarAutor(autor1);
        System.out.println("   * Autor registrado: " + autor1.getNombre() + " (" + autor1.getMedio() + ")");
        sistemaNoticias.registrarAutor(autor2);
        System.out.println("   * Autor registrado: " + autor2.getNombre() + " (" + autor2.getMedio() + ")");

        // 3. Registro de Usuarios Lectores
        System.out.println("\n[2] REGISTRO DE USUARIOS:");
        Usuario user1 = new Usuario(45000111, "Juan Perez", 20);
        Usuario user2 = new Usuario(42111222, "Maria Gomez", 23);
        sistemaNoticias.registrarUsuario(user1);
        System.out.println("   * Usuario registrado: " + user1.getNombre() + " (Edad: " + user1.getEdad() + ")");
        sistemaNoticias.registrarUsuario(user2);
        System.out.println("   * Usuario registrado: " + user2.getNombre() + " (Edad: " + user2.getEdad() + ")");

        // 4. Carga de noticias por parte de un autor
        System.out.println("\n[3] CARGA DE NOTICIAS:");
        // Noticia en el año 2026, mes de Junio (Mes 6)
        Noticia n1 = new Noticia("Parcial de Paradigmas II aprobado", "Los alumnos Suarez y Bravo demostraron un excelente nivel.", 12, 6, 2026, autor1);
        // Noticia en otro año diferente
        Noticia n2 = new Noticia("Lanzamiento de Java 30", "Nueva actualización revolucionaria del lenguaje.", 15, 3, 2025, autor2);
        
        sistemaNoticias.cargarNoticia(n1);
        System.out.println("   * Noticia publicada: \"" + n1.getTitulo() + "\" por " + n1.getAutor().getNombre());
        sistemaNoticias.cargarNoticia(n2);
        System.out.println("   * Noticia publicada: \"" + n2.getTitulo() + "\" por " + n2.getAutor().getNombre());

        // 5. Registro de comentarios sobre las noticias por parte de usuarios
        System.out.println("\n[4] REGISTRO DE COMENTARIOS:");
        sistemaNoticias.registrarComentarioEnNoticia("Parcial de Paradigmas II aprobado", 1, "¡Excelente noticia, felicitaciones!", 45000111);
        System.out.println("   * Comentario registrado en: \"Parcial de Paradigmas II aprobado\" por Juan Perez");
        sistemaNoticias.registrarComentarioEnNoticia("Parcial de Paradigmas II aprobado", 2, "Merecido logro.", 42111222);
        System.out.println("   * Comentario registrado en: \"Parcial de Paradigmas II aprobado\" por Maria Gomez");
        sistemaNoticias.registrarComentarioEnNoticia("Lanzamiento de Java 30", 1, "Interesante actualización.", 45000111);
        System.out.println("   * Comentario registrado en: \"Lanzamiento de Java 30\" por Juan Perez");

        // 6. Guardamos todo el estado actual en los archivos de texto
        System.out.println("\n[5] GUARDANDO EN ARCHIVOS:");
        sistemaNoticias.guardarEnDisco();
        System.out.println("   * Estado guardado en: autores.txt, usuarios.txt, noticias.txt, comentarios.txt");

        System.out.println("\n====================================================");
        System.out.println("    SIMULACIÓN: REINICIO DE LA APLICACIÓN           ");
        System.out.println("====================================================\n");

        // Creamos una nueva instancia completamente vacía para simular que abrimos el programa de nuevo
        Registro sistemaNuevo = new Registro();
        
        // Recuperamos la información del disco (.txt)
        System.out.println("[6] CARGANDO DESDE ARCHIVOS:");
        sistemaNuevo.cargarDesdeDisco();
        System.out.println("   * Datos recuperados desde archivos de texto");

        // 7. EJECUCIÓN DE LAS CONSULTAS OBLIGATORIAS DE LA CONSIGNA
        System.out.println("\n====================================================");
        System.out.println("          PROBANDO CONSULTAS SOLICITADAS            ");
        System.out.println("====================================================");

        // Consulta A: Listar noticias publicadas en un determinado año
        System.out.println("\n[CONSULTA A] Noticias publicadas en 2026:");
        sistemaNuevo.listarNoticiasPorAnio(2026);

        // Consulta B: Listado de noticias publicadas el último mes (Junio de 2026)
        System.out.println("\n[CONSULTA B] Noticias publicadas en junio de 2026:");
        sistemaNuevo.listarNoticiasUltimoMes(6, 2026);

        // Consulta C: Mostrar una noticia concreta y todos sus comentarios asociados
        System.out.println("\n[CONSULTA C] Detalle de noticia con comentarios:");
        sistemaNuevo.mostrarNoticiaYComentarios("Parcial de Paradigmas II aprobado");

        // Consulta D: Artículos publicados por un determinado autor (Pasamos DNI de Martin)
        System.out.println("\n[CONSULTA D] Artículos de un autor específico:");
        sistemaNuevo.listarNoticiasPorAutor(35123456);
        
        System.out.println("\n====================================================");
        System.out.println("          FIN DE LA SIMULACIÓN - EXITOSA            ");
        System.out.println("====================================================");
    }
}