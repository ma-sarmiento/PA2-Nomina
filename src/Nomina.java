import java.io.*;
import java.util.*;

public class Nomina {
    private List<Empleado> empleados;
    Scanner scan = new Scanner(System.in);

    public Nomina(List<Empleado> empleados) {
        this.empleados = empleados;
    }

    public List<Empleado> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(List<Empleado> empleados) {
        this.empleados = empleados;
    }

    // Cargar empleados desde archivo de texto
    public List<Empleado> obtenerEmpleados() throws IOException {
        BufferedReader leer = null;
        try {
            leer = new BufferedReader(new FileReader("nomina.txt"));
            String linea, lineaAsignatura;
            while (!(linea = leer.readLine()).equals("FIN") && linea != null) {
                StringTokenizer token = new StringTokenizer(linea, "%");
                String nombre = token.nextToken().trim();
                String documento = token.nextToken().trim();
                String dependencia = token.nextToken().trim();

                if (dependencia.equals("Profesor")) {
                    String escalafon = leer.readLine();
                    List<Asignatura> asignaturas = new ArrayList<>();
                    while (!(lineaAsignatura = leer.readLine()).equals("#")) {
                        String[] infoAsignatura = lineaAsignatura.split(",");
                        asignaturas.add(new Asignatura(infoAsignatura[0].trim(), Integer.parseInt(infoAsignatura[1].trim())));
                    }
                    Profesor p = new Profesor(nombre, documento, dependencia, escalafon, asignaturas);
                    empleados.add(p);
                } else if (dependencia.equals("Monitor")) {
                    List<Asignatura> asignaturas = new ArrayList<>();
                    while (!(lineaAsignatura = leer.readLine()).equals("#")) {
                        String[] infoAsignatura = lineaAsignatura.split(",");
                        asignaturas.add(new Asignatura(infoAsignatura[0].trim(), Integer.parseInt(infoAsignatura[1].trim())));
                    }
                    Monitor m = new Monitor(nombre, documento, dependencia, asignaturas);
                    empleados.add(m);
                } else if (dependencia.equals("Empleado")) {
                    int salariosMinimos = Integer.parseInt(leer.readLine().trim());
                    Empleado e = new Empleado(nombre, documento, dependencia, salariosMinimos);
                    empleados.add(e);
                    leer.readLine(); // Salta posible línea vacía
                }
            }
            System.out.println("Se han cargado los empleados.");
        } finally {
            if (leer != null) leer.close();
        }
        return empleados;
    }

    // Agregar empleado por tipo
    public void agregarEmpleado(String nombre, String documento) {
        for (Empleado e : empleados) {
            if (e.getDocumento().equals(documento)) {
                System.out.println("Este empleado ya existe");
                return;
            }
        }

        System.out.println("Ingrese el tipo de dependencia (Empleado / Profesor / Monitor): ");
        String dependencia = scan.nextLine();

        if (dependencia.equalsIgnoreCase("Empleado")) {
            System.out.println("Ingrese los salarios mínimos que gana: ");
            int salarioMin = scan.nextInt();
            empleados.add(new Empleado(nombre, documento, dependencia, salarioMin));
            scan.nextLine(); // Limpia el buffer
        } else if (dependencia.equalsIgnoreCase("Profesor")) {
            System.out.println("Ingrese el escalafón: ");
            String escalafon = scan.nextLine();
            empleados.add(new Profesor(nombre, documento, dependencia, escalafon, null));
        } else if (dependencia.equalsIgnoreCase("Monitor")) {
            empleados.add(new Monitor(nombre, documento, dependencia, null));
        }

        System.out.println("Empleado agregado correctamente.");
    }

    // Eliminar empleado por nombre o documento
    public void eliminarEmpleado(String criterio) {
        Empleado encontrado = null;
        for (Empleado e : empleados) {
            if (e.getDocumento().equals(criterio) || e.getNombre().equals(criterio)) {
                encontrado = e;
                break;
            }
        }
        if (encontrado != null) {
            empleados.remove(encontrado);
            System.out.println("Empleado eliminado.");
        } else {
            System.out.println("Empleado no encontrado.");
        }
    }

    // Agregar asignatura a profesor
    public void agregarAsignaturaProf(String documento, String nombreA, int numHoras) {
        for (Empleado e : empleados) {
            if (e instanceof Profesor && e.getDocumento().equals(documento)) {
                ((Profesor) e).getAsignaturas().add(new Asignatura(nombreA, numHoras));
                System.out.println("Asignatura agregada al profesor.");
                return;
            }
        }
        System.out.println("Profesor no encontrado.");
    }

    // Agregar asignatura a monitor
    public void agregarAsignaturaMonitor(String documento, String nombreA, int numHoras) {
        for (Empleado e : empleados) {
            if (e instanceof Monitor && e.getDocumento().equals(documento)) {
                ((Monitor) e).getAsignaturas().add(new Asignatura(nombreA, numHoras));
                System.out.println("Asignatura agregada al monitor.");
                return;
            }
        }
        System.out.println("Monitor no encontrado.");
    }

    // Calcular salario según tipo
    public void calcularSalarioEmpleado(String documento) {
        for (Empleado e : empleados) {
            if (e.getDocumento().equals(documento)) {
                e.calcularSalarioEmpleado();
                System.out.println("Salario total: $" + e.getSalarioTot());
            }
        }
    }

    public void calcularSalarioProfesor(String documento) {
        for (Empleado e : empleados) {
            if (e instanceof Profesor && e.getDocumento().equals(documento)) {
                ((Profesor) e).calcularSalarioEmpleado();
                System.out.println("Salario profesor: $" + e.getSalarioTot());
            }
        }
    }

    public void calcularSalarioMonitor(String documento) {
        for (Empleado e : empleados) {
            if (e instanceof Monitor && e.getDocumento().equals(documento)) {
                ((Monitor) e).calcularSalarioEmpleado();
                System.out.println("Salario monitor: $" + e.getSalarioTot());
            }
        }
    }

    // Reporte de retefuente
    public void listarRetefuente(double valorBase) {
        List<Empleado> filtrados = new ArrayList<>();
        for (Empleado e : empleados) {
            e.calcularSalarioBase();
            if (e.getSalarioBase() > valorBase) {
                filtrados.add(e);
            }
        }
        filtrados.sort(Comparator.comparing(Empleado::getNombre, String.CASE_INSENSITIVE_ORDER));

        try (FileWriter fw = new FileWriter("Retefuente.txt")) {
            for (Empleado e : filtrados) {
                double rete = e.getSalarioBase() * 0.18;
                String linea = String.format("Nombre: %s | Tipo: %s | Salario Base: %.2f | Retefuente: %.2f%n",
                        e.getNombre(), e.getDependencia(), e.getSalarioBase(), rete);
                fw.write(linea);
                System.out.print(linea);
            }
            System.out.println("Archivo Retefuente.txt generado.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Generar nómina general
    public void generarReporteNomina() {
        for (Empleado e : empleados) {
            e.calcularSalarioEmpleado();
        }
        empleados.sort(Comparator.comparingDouble(Empleado::getSalarioTot).reversed());

        try (FileWriter fw = new FileWriter("Reporte.txt")) {
            for (Empleado e : empleados) {
                fw.write(String.format("%s,%s,$%.2f%n", e.getNombre(), e.getDocumento(), e.getSalarioTot()));
            }
            System.out.println("Archivo Reporte.txt generado.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Guardar en archivo binario
    public void guardarNomina(List<Empleado> empleados) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("Nomina.bin"))) {
            out.writeObject(empleados);
            System.out.println("Nómina guardada en Nomina.bin");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Listar empleados por tipo y salario con orden burbuja
    public void listarEmpleadosPorTipoYSalario(String dependencia, double valorParametro) {
        List<Empleado> filtrados = new ArrayList<>();
        for (Empleado e : empleados) {
            if (e.getDependencia().equalsIgnoreCase(dependencia) && e.getSalarioBase() > valorParametro) {
                filtrados.add(e);
            }
        }

        // Orden burbuja
        boolean ordenado;
        do {
            ordenado = true;
            for (int i = 0; i < filtrados.size() - 1; i++) {
                if (filtrados.get(i).getNombre().compareToIgnoreCase(filtrados.get(i + 1).getNombre()) > 0) {
                    Empleado temp = filtrados.get(i);
                    filtrados.set(i, filtrados.get(i + 1));
                    filtrados.set(i + 1, temp);
                    ordenado = false;
                }
            }
        } while (!ordenado);

        try (FileWriter fw = new FileWriter("Sobresueldo.txt")) {
            for (Empleado e : filtrados) {
                fw.write(String.format("Nombre: %s | Salario Base: %.2f%n", e.getNombre(), e.getSalarioBase()));
            }
            System.out.println("Archivo Sobresueldo.txt generado.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
