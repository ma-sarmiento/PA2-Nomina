import java.io.Serializable;

public class Empleado implements Serializable {
    public static final double SMVL=290;
    protected String nombre;
    protected String documento;
    protected String dependencia;
    protected int salariosMinimos;
    protected double salarioBase;
    protected double salarioTot;
    public Empleado(String nombre, String documento, String dependencia, int salariosMinimos){
        if (salariosMinimos < 0) {
            throw new IllegalArgumentException("Los salarios mínimos no pueden ser negativos");
        }
        this.nombre=nombre;
        this.documento=documento;
        this.dependencia=dependencia;
        this.salariosMinimos=0;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getDependencia() {
        return dependencia;
    }

    public void setDependencia(String dependencia) {
        this.dependencia = dependencia;
    }

    public int getSalariosMinimos() {
        return salariosMinimos;
    }

    public void setSalariosMinimos(int salariosMinimos) {
        if (salariosMinimos < 0) {
            throw new IllegalArgumentException("Los salarios mínimos no pueden ser negativos");
        }
        this.salariosMinimos = salariosMinimos;
    }

    public double getSalarioBase() {
        return salarioBase;
    }

    public void setSalarioBase(double salarioBase) {
        this.salarioBase = salarioBase;
    }

    public double getSalarioTot() {
        return salarioTot;
    }

    public void setSalarioTot(double salarioTot) {
        this.salarioTot = salarioTot;
    }
    public void calcularSalarioBase(){
        this.salarioBase=this.salariosMinimos*SMVL;
    }
    public void calcularSalarioEmpleado(){
        this.salarioTot=(this.salariosMinimos*SMVL)*0.65;
    }
}
