import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException{
        Scanner scan = new Scanner(System.in);
        int opcion=0;
        boolean repetir = true;
        List<Empleado> empleados = new ArrayList<>();
        Nomina nomina = new Nomina(empleados);
        do{
            System.out.println("Ingrese alguna de las opciones: ");
            System.out.println("\n1.Cargar empleados");
            System.out.println("\n2.Agregar empleado");
            System.out.println("\n3.Eliminar empleado");
            System.out.println("\n4.Adicionar una asignatura a un profesor");
            System.out.println("\n5.Adicionar una asignatura a un monitor");
            System.out.println("\n6.Calcular el salario de un empleado");
            System.out.println("\n7.Calcular el salario de un profesor");
            System.out.println("\n8.Calcular el salario de un monitor");
            System.out.println("\n9.Listar empleados por Retefuente");
            System.out.println("\n10.Generar nomina");
            System.out.println("\n11.Persistir nomina");
            System.out.println("\n12.Listar por tipo y salario");
            System.out.println("\n0. Salir");
            opcion = scan.nextInt();
            scan.nextLine();
            switch (opcion){
                case 1:
                    empleados = nomina.obtenerEmpleados();
                    break;
                case 2:
                    String nombreE="";
                    String documentoE = "";
                    System.out.println("Ingrese el nombre del empleado: ");
                    nombreE = scan.nextLine();
                    System.out.println("Ingrese el documento del empleado: ");
                    documentoE = scan.nextLine();
                    nomina.agregarEmpleado(nombreE, documentoE);
                    break;
                case 3:
                    String opcionEl="";
                    System.out.println("Ingrese el nombre o el documento del empleado a eliminar");
                    opcionEl= scan.nextLine();
                    nomina.eliminarEmpleado(opcionEl);
                    break;
                case 4:
                    String documentoP ="";
                    String nombreAP="";
                    int numHorasP=0;
                    System.out.println("Ingrese el documento del profesor: ");
                    documentoP= scan.nextLine();
                    System.out.println("Ingrese el nombre de la asignatura: ");
                    nombreAP= scan.nextLine();
                    System.out.println("Ingrese el numero de horas trabajadas: ");
                    numHorasP=scan.nextInt();
                    nomina.agregarAsignaturaProf(documentoP, nombreAP, numHorasP);
                    break;
                case 5:
                    String documentoM ="";
                    String nombreAM="";
                    int numHorasM=0;
                    System.out.println("Ingrese el documento del monitor: ");
                    documentoM= scan.nextLine();
                    System.out.println("Ingrese el nombre de la asignatura: ");
                    nombreAM= scan.nextLine();
                    System.out.println("Ingrese el numero de horas trabajadas: ");
                    numHorasM=scan.nextInt();
                    nomina.agregarAsignaturaMonitor(documentoM, nombreAM, numHorasM);
                    break;
                case 6:
                    String documentoEE="";
                    System.out.println("Ingrese el documento del empleado: ");
                    documentoEE=scan.nextLine();
                    nomina.calcularSalarioEmpleado(documentoEE);
                    break;
                case 7:
                    String documentoEP="";
                    System.out.println("Ingrese el documento del profesor: ");
                    documentoEP=scan.nextLine();
                    nomina.calcularSalarioProfesor(documentoEP);
                    break;
                case 8:
                    String documentoEM="";
                    System.out.println("Ingrese el documento del monitor: ");
                    documentoEM=scan.nextLine();
                    nomina.calcularSalarioEmpleado(documentoEM);
                    break;
                case 9:
                    double valorBase=0.0;
                    System.out.println("Ingrese el valor base por parametro: ");
                    valorBase=scan.nextDouble();
                    nomina.listarRetefuente(valorBase);
                    break;
                case 10:
                    nomina.generarReporteNomina();
                    break;
                case 11:
                    nomina.guardarNomina(empleados);
                    break;
                case 12:
                    String dependencia="";
                    double valorParametro=0.0;
                    System.out.println("Ingrese la dependencia del empleado: ");
                    dependencia=scan.nextLine();
                    System.out.println("Ingrese el valor por parametro: ");
                    valorParametro=scan.nextDouble();
                    nomina.listarEmpleadosPorTipoYSalario(dependencia, valorParametro);
                    break;
                case 0:
                    repetir =  false;
                    break;
            }
        }while(repetir);
    }
}
