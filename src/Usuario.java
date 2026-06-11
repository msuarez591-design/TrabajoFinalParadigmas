public class Usuario extends Persona {
    private int edad;

    public Usuario(String dni, String nombre, int edad) {
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
