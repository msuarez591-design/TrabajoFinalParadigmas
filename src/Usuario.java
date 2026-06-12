/**
 * Representa la entidad concreta para un usuario del sistema.
 * @author Martin Suarez , Candela Guadalupe Bravo
 * @version 1.0
 */
public class Usuario extends Persona {
    private int edad;

    //Constructores
    public Usuario() {
        super(); // Llama al constructor por defecto de Persona
        this.edad = 0; // Valor por defecto para edad
    }

     /**
     * Constructor completo con programación defensiva.
     *
     * @param dni DNI del usuario (no nulo/vacío).
     * @param nombre Nombre completo (no nulo/vacío).
     * @param edad Edad del usuario (mayor a 0).
     * @throws IllegalArgumentException si los datos son inválidos.
     * @author Martin Suarez , Candela Guadalupe Bravo
     * @version 1.0
     */
    public Usuario(int dni, String nombre, int edad) {
        super(dni, nombre); // Llama al constructor de Persona
        if (edad <= 0) {
            throw new IllegalArgumentException("La edad debe ser mayor a 0.");
        }
        this.edad = edad;
    }

    public int getEdad() {
        return this.edad;
    }

    public void setEdad(int edad) {
        if (edad <= 0) {
            throw new IllegalArgumentException("La edad debe ser mayor a 0.");
        }
        this.edad = edad;
    }
}
