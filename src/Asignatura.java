public class Asignatura {
    private String nombre;
    private int numHoras;

    public Asignatura(String nombre, int numHoras) {
        this.nombre = nombre;
        this.numHoras = numHoras;
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public int getNumHoras() { return numHoras; }
    public void setNumHoras(int numHoras) { this.numHoras = numHoras; }
}
