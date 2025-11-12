import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Monitor extends Empleado implements Serializable {
    private static double valorHoras = 30.00;
    private List<Asignatura> asignaturas;

    public Monitor(String nombre, String documento, String dependencia, List<Asignatura> asignaturas) {
        super(nombre, documento, dependencia, 0);
        if (asignaturas == null) {
            this.asignaturas = new ArrayList<>();
        } else {
            this.asignaturas = asignaturas;
        }
    }

    public static double getValorHoras() {
        return valorHoras;
    }

    public static void setValorHoras(double valorHoras) {
        Monitor.valorHoras = valorHoras;
    }

    public List<Asignatura> getAsignaturas() {
        return asignaturas;
    }

    public void setAsignaturas(List<Asignatura> asignaturas) {
        this.asignaturas = asignaturas;
    }

    public int totalHorasTrabajadas(List<Asignatura> asignaturas) {
        int horasTot = 0;
        for (Asignatura a : asignaturas) {
            horasTot += a.getNumHoras();
        }
        return horasTot;
    }

    @Override
    public void calcularSalarioBase() {
        this.salarioBase = (valorHoras * totalHorasTrabajadas(asignaturas));
    }

    @Override
    public void calcularSalarioEmpleado() {
        this.salarioTot = (valorHoras * totalHorasTrabajadas(asignaturas));
    }
}
