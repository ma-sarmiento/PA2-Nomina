import java.io.Serializable;

public abstract class Empleado implements Serializable {
    private String nombre;
    private String apellido;
    private String documento;
    private double salarioBase;

    public Empleado(String nombre, String apellido, String documento, double salarioBase) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.documento = documento;
        this.salarioBase = salarioBase;
    }

    public abstract double calcularSalario();

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getDocumento() {
        return documento;
    }

    public double getSalarioBase() {
        return salarioBase;
    }

    @Override
    public String toString() {
        return nombre + " " + apellido + " - " + documento;
    }
}
