import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class NoticiaTest {

    private Autor autor;

    @BeforeEach
    public void setUp() {
        autor = new Autor(12345678, "Prueba Autor", "Medio Test");
    }

    @Test
    public void testConstructorValidoYGetters() {
        Noticia n = new Noticia("Titulo", "Detalle", 10, 5, 2024, autor);
        assertEquals("Titulo", n.getTitulo());
        assertEquals("Detalle", n.getDetalle());
        assertEquals(10, n.getDia());
        assertEquals(5, n.getMes());
        assertEquals(2024, n.getAnio());
        assertEquals(autor, n.getAutor());
    }

    @Test
    public void testAgregarComentario() {
        Noticia n = new Noticia("T", "D", 1, 1, 2020, autor);
        Usuario u = new Usuario(11111111, "Lector", 30);
        Comentario c = new Comentario(1, "Buen artículo", u);
        n.agregarComentario(c);
        assertEquals(1, n.getComentarios().size());
        assertEquals("Buen artículo", n.getComentarios().get(0).getTexto());
    }

    @Test
    public void testSettersSimples() {
        Noticia n = new Noticia("A", "B", 1, 1, 1900, autor);
        n.setTitulo("Nuevo");
        n.setDetalle("Detalle nuevo");
        n.setDia(2);
        n.setMes(2);
        n.setAnio(2000);
        assertEquals("Nuevo", n.getTitulo());
        assertEquals("Detalle nuevo", n.getDetalle());
        assertEquals(2, n.getDia());
        assertEquals(2, n.getMes());
        assertEquals(2000, n.getAnio());
    }

    @Test
    public void testConstructorInvalido() {
        try {
            new Noticia(null, "D", 1, 1, 1900, autor);
            fail("Se esperaba IllegalArgumentException por título nulo");
        } catch (IllegalArgumentException e) {
            // OK
        }
    }
}
