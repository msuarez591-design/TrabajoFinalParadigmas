/**
 * Representa la entidad concreta para un autor del sistema.
 * @author Martin Suarez , Candela Guadalupe Bravo
 * @version 1.0
 */
public class Autor extends Persona {
    private String medio;

//Constructores
    public Autor() {
        super(); // Llama al constructor por defecto de Persona
        this.medio = "Desconocido"; // Valor por defecto para medio
    }

     /**
     * Constructor completo con programación defensiva.
     *
     * @param dni DNI del autor (no nulo/vacío).
     * @param nombre Nombre completo (no nulo/vacío).
     * @param medio Medio de publicación (no nulo/vacío).
     * @throws IllegalArgumentException si los datos son inválidos.
     * @author Martin Suarez , Candela Guadalupe Bravo
     * @version 1.0
     */
    public Autor(int dni, String nombre, String medio) {
        super(dni, nombre);
        if (medio == null || medio.trim().isEmpty()) {
            throw new IllegalArgumentException("El medio no puede estar vacío.");
        }
        this.medio = medio;
    }

    public String getMedio() {
        return this.medio;
    }

    public void setMedio(String medio) {
        if (medio == null || medio.trim().isEmpty()) {
            throw new IllegalArgumentException("El medio no puede estar vacío.");
        }
        this.medio = medio;
    }
}
