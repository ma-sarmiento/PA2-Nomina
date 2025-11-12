public class Directivo extends Empleado {
    private String cargo;

    public Directivo(String nombre, String apellido, String documento, double salarioBase, String cargo) {
        super(nombre, apellido, documento, salarioBase);
        this.cargo = cargo;
    }

    @Override
    public double calcularSalario() {
        return getSalarioBase() + (cargo.equalsIgnoreCase("Rector") ? 2000000 : 1000000);
    }

    public String getCargo() {
        return cargo;
    }
}
