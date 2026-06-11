public class Autor extends Persona {
    private String medio;

    public Autor(String dni, String nombre, String medio) {
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
