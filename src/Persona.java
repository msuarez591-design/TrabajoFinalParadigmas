/**
 * Representa la entidad abstracta base para cualquier individuo en el sistema.
 */
public abstract class Persona {
    private final String dni; // final: la identidad no cambia
    private String nombre;

    /**
     * Constructor base con programación defensiva.
     * @param dni DNI de la persona (no nulo/vacío).
     * @param nombre Nombre completo (no nulo/vacío).
     * @throws IllegalArgumentException si los datos son inválidos.
     */
    public Persona(String dni, String nombre) {
        if (dni == null || dni.trim().isEmpty()) {
            throw new IllegalArgumentException("El DNI no puede ser nulo o vacío.");
        }
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede ser nulo o vacío.");
        }
        this.dni = dni;
        this.nombre = nombre;
    }

    public String getDni() {
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
}
