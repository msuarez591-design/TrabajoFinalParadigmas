
/**
 * Representa la entidad abstracta base para cualquier individuo en el sistema.
 * @author Martin Suarez , Candela Guadalupe Bravo
 * @version 1.0
 */
public abstract class Persona {

    private int dni; 
    private String nombre;

//Constructores
    public Persona() {
        this.dni = 0; // Valor por defecto para DNI
        this.nombre = "Desconocido"; // Valor por defecto para nombre
    }

    /**
     * Constructor base con programación defensiva.
     *
     * @param dni DNI de la persona (no nulo/vacío).
     * @param nombre Nombre completo (no nulo/vacío).
     * @throws IllegalArgumentException si los datos son inválidos.
     * @author Martin Suarez , Candela Guadalupe Bravo
     * @version 1.0
     */
    public Persona( int dni, String nombre) {
        if (dni == 0) {
            throw new IllegalArgumentException("El DNI no puede ser cero.");
        }
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede ser nulo o vacío.");
        }
        this.dni = dni;
        this.nombre = nombre;
    }

//metodos
    public int getDni() {
        return this.dni;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede ser nulo o vacío.");
        }
        this.nombre = nombre;
    }

    public void setDni(int dni) {
        if (dni == 0) {
            throw new IllegalArgumentException("El DNI no puede ser cero.");
        }
        this.dni = dni;
    }
}
