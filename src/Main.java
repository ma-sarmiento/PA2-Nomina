import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(System.in);
        int opcion;
        boolean repetir = true;

        List<Empleado> empleados = new ArrayList<>();
        Nomina nomina = new Nomina(empleados);

        do {
            System.out.println("\n--- Menú de Opciones ---");
            System.out.println("1. Cargar empleados desde archivo");
            System.out.println("2. Agregar empleado");
            System.out.println("3. Eliminar empleado");
            System.out.println("4. Agregar asignatura a profesor");
            System.out.println("5. Agregar asignatura a monitor");
            System.out.println("6. Calcular salario de empleado");
            System.out.println("7. Calcular salario de profesor");
            System.out.println("8. Calcular salario de monitor");
            System.out.println("9. Listar empleados por Retefuente");
            System.out.println("10. Generar reporte de nómina");
            System.out.println("11. Guardar nómina en binario");
            System.out.println("12. Listar empleados por tipo y salario base");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scan.nextInt();
            scan.nextLine(); // limpiar buffer

            switch (opcion) {
                case 1:
                    empleados = nomina.obtenerEmpleados();
                    break;

                case 2:
                    System.out.print("Nombre: ");
                    String nombre = scan.nextLine();
                    System.out.print("Documento: ");
                    String documento = scan.nextLine();
                    nomina.agregarEmpleado(nombre, documento);
                    break;

                case 3:
                    System.out.print("Nombre o documento del empleado a eliminar: ");
                    String criterio = scan.nextLine();
                    nomina.eliminarEmpleado(criterio);
                    break;

                case 4:
                    System.out.print("Documento del profesor: ");
                    String docProf = scan.nextLine();
                    System.out.print("Nombre de la asignatura: ");
                    String asigProf = scan.nextLine();
                    System.out.print("Horas: ");
                    int horasProf = scan.nextInt();
                    nomina.agregarAsignaturaProf(docProf, asigProf, horasProf);
                    break;

                case 5:
                    System.out.print("Documento del monitor: ");
                    String docMon = scan.nextLine();
                    System.out.print("Nombre de la asignatura: ");
                    String asigMon = scan.nextLine();
                    System.out.print("Horas: ");
                    int horasMon = scan.nextInt();
                    nomina.agregarAsignaturaMonitor(docMon, asigMon, horasMon);
                    break;

                case 6:
                    System.out.print("Documento del empleado: ");
                    String docEmp = scan.nextLine();
                    nomina.calcularSalarioEmpleado(docEmp);
                    break;

                case 7:
                    System.out.print("Documento del profesor: ");
                    String docP = scan.nextLine();
                    nomina.calcularSalarioProfesor(docP);
                    break;

                case 8:
                    System.out.print("Documento del monitor: ");
                    String docM = scan.nextLine();
                    nomina.calcularSalarioMonitor(docM);
                    break;

                case 9:
                    System.out.print("Valor base para retefuente: ");
                    double base = scan.nextDouble();
                    nomina.listarRetefuente(base);
                    break;

                case 10:
                    nomina.generarReporteNomina();
                    break;

                case 11:
                    nomina.guardarNomina(empleados);
                    break;

                case 12:
                    System.out.print("Dependencia (Empleado / Profesor / Monitor): ");
                    String dep = scan.nextLine();
                    System.out.print("Valor mínimo salario base: ");
                    double val = scan.nextDouble();
                    nomina.listarEmpleadosPorTipoYSalario(dep, val);
                    break;

                case 0:
                    repetir = false;
                    System.out.println("Saliendo del sistema...");
                    break;

                default:
                    System.out.println("Opción no válida.");
                    break;
            }

        } while (repetir);

        scan.close();
    }
}
