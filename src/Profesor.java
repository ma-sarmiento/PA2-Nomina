import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Profesor extends Empleado implements Serializable {
    private static double valorHora = 50.00;
    private String escalafon;
    private List<Asignatura> asignaturas;

    public Profesor(String nombre, String documento, String dependencia, String escalafon, List<Asignatura> asignaturas) {
        super(nombre, documento, dependencia, 0);
        this.escalafon = escalafon;
        if (asignaturas == null) {
            this.asignaturas = new ArrayList<>();
        } else {
            this.asignaturas = asignaturas;
        }
    }

    public enum Escalafon {
        Catedra("Catedra", 1),
        Instructor("Instructor", 2),
        Asistente("Asistente", 2.5),
        Asociado("Asociado", 3),
        Titular("Titular", 3.5),
        ;
        private final String escalafon;
        private final double salarioMin;

        private Escalafon(String escalafon, double salarioMin) {
            this.escalafon = escalafon;
            this.salarioMin = salarioMin;
        }

        public String getEscalafon() {
            return escalafon;
        }

        public double getSalarioMin() {
            return salarioMin;
        }
    }

    public static double getValorHora() {
        return valorHora;
    }

    public static void setValorHora(double valorHora) {
        Profesor.valorHora = valorHora;
    }

    public String getEscalafon() {
        return escalafon;
    }

    public void setEscalafon(String escalafon) {
        this.escalafon = escalafon;
    }

    public List<Asignatura> getAsignaturas() {
        return asignaturas;
    }

    public void setAsignaturas(List<Asignatura> asignaturas) {
        this.asignaturas = asignaturas;
    }
    private boolean escalafonInvalido(String escalafon) {
        for (Escalafon e : Escalafon.values()) {
            if (e.getEscalafon().equals(escalafon)) {
                return true;
            }
        }
        return false;
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
        if (!escalafonInvalido(escalafon)) {
            throw new IllegalStateException("Escalafon inválido: " + escalafon);
        }else{
            if (escalafon.equals(Escalafon.Catedra.getEscalafon())) {
                salarioBase = (Escalafon.Catedra.getSalarioMin()* Empleado.SMVL) + (valorHora * totalHorasTrabajadas(asignaturas));
            } else if (escalafon.equals(Escalafon.Instructor.getEscalafon())) {
                salarioBase = (Escalafon.Instructor.getSalarioMin() * Empleado.SMVL) + (valorHora * totalHorasTrabajadas(asignaturas));
            } else if (escalafon.equals(Escalafon.Asistente.getEscalafon())) {
                salarioBase = (Escalafon.Asistente.getSalarioMin() * Empleado.SMVL) + (valorHora * totalHorasTrabajadas(asignaturas));
            } else if (escalafon.equals(Escalafon.Asociado.getEscalafon())) {
                salarioBase = (Escalafon.Asociado.getSalarioMin() * Empleado.SMVL) + (valorHora * totalHorasTrabajadas(asignaturas));
            } else if (escalafon.equals(Escalafon.Titular.getEscalafon())) {
                salarioBase = (Escalafon.Titular.getSalarioMin() * Empleado.SMVL) + (valorHora * totalHorasTrabajadas(asignaturas));
            }
        }
    }

    @Override
    public void calcularSalarioEmpleado() {
        if (!escalafonInvalido(escalafon)) {
            throw new IllegalStateException("Escalafon inválido: " + escalafon);
        }else {
            if(escalafon.equals(Escalafon.Catedra.getEscalafon())){
                salarioTot = ((Escalafon.Catedra.getSalarioMin()*Empleado.SMVL)+(valorHora*totalHorasTrabajadas(asignaturas)))*0.85;
            }else if(escalafon.equals(Escalafon.Instructor.getEscalafon())){
                salarioTot=((Escalafon.Instructor.getSalarioMin()*Empleado.SMVL)+(valorHora*totalHorasTrabajadas(asignaturas)))*0.85;
            }else if(escalafon.equals(Escalafon.Asistente.getEscalafon())){
                salarioTot=((Escalafon.Asistente.getSalarioMin()*Empleado.SMVL)+(valorHora*totalHorasTrabajadas(asignaturas)))*0.85;
            }else if(escalafon.equals(Escalafon.Asociado.getEscalafon())){
                salarioTot=((Escalafon.Asociado.getSalarioMin()*Empleado.SMVL)+(valorHora*totalHorasTrabajadas(asignaturas)))*0.85;
            }else if(escalafon.equals(Escalafon.Titular.getEscalafon())){
                salarioTot=((Escalafon.Titular.getSalarioMin()*Empleado.SMVL)+(valorHora*totalHorasTrabajadas(asignaturas)))*0.85;
            }
        }
    }
}
