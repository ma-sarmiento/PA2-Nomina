import java.util.ArrayList;

public class Docente extends Empleado {
    private ArrayList<Asignatura> asignaturas;

    public Docente(String nombre, String apellido, String documento, double salarioBase) {
        super(nombre, apellido, documento, salarioBase);
        asignaturas = new ArrayList<>();
    }

    public void agregarAsignatura(Asignatura asignatura) {
        asignaturas.add(asignatura);
    }

    @Override
    public double calcularSalario() {
        int totalHoras = 0;
        for (Asignatura a : asignaturas) {
            totalHoras += a.getNumHoras();
        }
        return getSalarioBase() + (totalHoras * 10000);
    }

    public ArrayList<Asignatura> getAsignaturas() {
        return asignaturas;
    }
}
