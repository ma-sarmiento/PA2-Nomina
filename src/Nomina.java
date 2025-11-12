import java.io.*;
import java.util.*;

public class Nomina{
    private List<Empleado> empleados;
    Scanner scan = new Scanner(System.in);
    public Nomina(List<Empleado> empleados){
        this.empleados=empleados;
    }
    public List<Empleado> getEmpleados() {
        return empleados;
    }
    public void setEmpleados(List<Empleado> empleados) {
        this.empleados = empleados;
    }
    public List<Empleado> obtenerEmpleados() throws IOException {
        BufferedReader leer = null;
        try {
            leer = new BufferedReader(new FileReader("nomina.txt"));
            String linea;
            String lineaAsignatura;
            String nombre;
            String documento;
            String dependencia;
            String escalafon;
            String nombreAsignatura;
            List<Asignatura> asignaturas;
            Asignatura nuevaAsignatura;
            int horasAsignatura;
            int horasTotales;
            int salariosMinimos;

            while (!(linea = leer.readLine()).equals("FIN") && linea != null) {
                StringTokenizer token = new StringTokenizer(linea, "%");
                nombre = token.nextToken().trim();
                documento = token.nextToken().trim();
                dependencia = token.nextToken().trim();

                if (dependencia.equals("Profesor")) {
                    horasTotales = 0;
                    escalafon = leer.readLine();
                    asignaturas = new ArrayList<>();
                    while (!(lineaAsignatura = leer.readLine()).equals("#")) {
                        String infoAsignatura[] = lineaAsignatura.split(",");
                        nombreAsignatura = infoAsignatura[0].trim();
                        horasAsignatura = Integer.parseInt(infoAsignatura[1].trim());
                        horasTotales += horasAsignatura;
                        nuevaAsignatura = new Asignatura(nombreAsignatura, horasAsignatura);
                        asignaturas.add(nuevaAsignatura);
                    }
                    Profesor p = new Profesor(nombre, documento, dependencia, escalafon, asignaturas);
                    p.setAsignaturas(asignaturas);
                    empleados.add(p);
                } else if (dependencia.equals("Monitor")) {
                    horasTotales = 0;
                    asignaturas = new ArrayList<>();
                    while (!(lineaAsignatura = leer.readLine()).equals("#")) {
                        String infoAsignatura[] = lineaAsignatura.split(",");
                        nombreAsignatura = infoAsignatura[0].trim();
                        horasAsignatura = Integer.parseInt(infoAsignatura[1].trim());
                        nuevaAsignatura = new Asignatura(nombreAsignatura, horasAsignatura);
                        asignaturas.add(nuevaAsignatura);
                    }
                    Monitor m = new Monitor(nombre, documento, dependencia, asignaturas);
                    m.setAsignaturas(asignaturas);
                    empleados.add(m);
                } else if (dependencia.equals("Empleado")) {
                    salariosMinimos = Integer.parseInt(leer.readLine().trim());
                    Empleado e = new Empleado(nombre, documento, dependencia, salariosMinimos);
                    e.setSalariosMinimos(salariosMinimos);
                    empleados.add(e);
                    linea = leer.readLine();
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            leer.close();
            System.out.println("Se han cargado los empleados");
        }
        return empleados;
    }
    public void agregarEmpleado(String nombre, String documento) {
        boolean empleadoExists = false;
        Empleado nuevoEmpleado = null;

        for (Empleado e : this.empleados) {
            if (e.getDocumento().equals(documento)) {
                System.out.println("Este empleado ya existe");
                empleadoExists = true;
                break;
            }
        }
        if (!empleadoExists) {
            System.out.println("Ingrese el tipo de dependencia del empleado: ");
            String dependencia = scan.nextLine();
            if (dependencia.equalsIgnoreCase("Empleado")) {
                System.out.println("Ingrese los salarios minimos que gana: ");
                int salarioMin = scan.nextInt();
                nuevoEmpleado = new Empleado(nombre, documento, dependencia, salarioMin);
                System.out.println("Se ha agregado el empleado");
                scan.nextLine();
            } else if (dependencia.equalsIgnoreCase("Profesor")) {
                System.out.println("Ingrese el escalafon: ");
                String escalafon = scan.nextLine();
                nuevoEmpleado = new Profesor(nombre, documento, dependencia, escalafon, null);
                System.out.println("Se ha agregado el profesor");
            } else if (dependencia.equalsIgnoreCase("Monitor")) {
                nuevoEmpleado = new Monitor(nombre, documento, dependencia, null);
                System.out.println("Se ha agregado el monitor");
            }
        }

        if (nuevoEmpleado != null) {
            empleados.add(nuevoEmpleado);
        }
    }
    public void eliminarEmpleado(String opcion){
        Empleado empleadoE=null;
        for(Empleado e : this.empleados){
            if(e.getDocumento().equals(opcion)||e.getNombre().equals(opcion)){
                empleadoE=e;
                break;
            }
        }
        if(empleadoE!=null){
            this.empleados.remove(empleadoE);
            System.out.println("Se ha eliminado el empleado");
        }else{
            System.out.println("No se encontro el empleado");
        }
    }
    public void agregarAsignaturaProf(String documento, String nombreA, int numHora){
        Profesor profEncontrado = null;
        for (Empleado e : this.empleados) {
            if (e instanceof Profesor && e.getDocumento().equals(documento)) {
                profEncontrado = (Profesor) e;
                break;
            }
        }
        if (profEncontrado != null) {
            Asignatura nuevaAsignatura = new Asignatura(nombreA, numHora);
            profEncontrado.getAsignaturas().add(nuevaAsignatura);
            System.out.println("Se agregó la asignatura");
        } else {
            System.out.println("No se encontró al profesor");
        }
    }
    public void agregarAsignaturaMonitor(String documento, String nombreA, int numHora){
        Monitor mEncontrado = null;
        for (Empleado e : this.empleados) {
            if (e instanceof Monitor && e.getDocumento().equals(documento)) {
                mEncontrado = (Monitor) e;
                break;
            }
        }
        if (mEncontrado != null) {
            Asignatura nuevaAsignatura = new Asignatura(nombreA, numHora);
            mEncontrado.getAsignaturas().add(nuevaAsignatura);
            System.out.println("Se agregó la asignatura");
        } else {
            System.out.println("No se encontró al monitor");
        }
    }

    public void  calcularSalarioEmpleado(String documento){
        for(Empleado e : this.empleados){
            if(e.getDocumento().equals(documento)){
                e.calcularSalarioEmpleado();
                System.out.println("El salario del empleado es de: "+e.getSalarioTot());
            }
        }
    }
    public void calcularSalarioProfesor(String documento){
        for(Empleado e: this.empleados){
            if(e.getDocumento().equals(documento) && e instanceof Profesor){
                Profesor p = (Profesor) e;
                p.calcularSalarioEmpleado();
                System.out.println("El salario total del profesor es de: "+p.getSalarioTot());
            }
        }
    }
    public void calcularSalarioMonitor(String documento){
        for(Empleado e: this.empleados){
            if(e.getDocumento().equals(documento) && e instanceof Monitor){
                Monitor m=(Monitor)e;
                m.calcularSalarioEmpleado();
                System.out.println("El salario total del monitor es: "+m.getSalarioTot());
            }
        }
    }
    public void listarRetefuente(double valorBase){
        List<Empleado> retefuente = new ArrayList<>();
        for(Empleado e: this.empleados){
            e.calcularSalarioBase();
            if(e.getSalarioBase()>valorBase){
                retefuente.add(e);
            }
        }
        Collections.sort(retefuente, new Comparator<Empleado>() {
            @Override
            public int compare(Empleado e1, Empleado e2) {
                return e1.getNombre().compareToIgnoreCase(e2.getNombre());
            }
        });
        try(FileWriter out = new FileWriter("Retefuente.txt")){
            for(Empleado e: retefuente){
                double valorRete=0.0;
                valorRete=e.getSalarioBase()*0.18;
                System.out.println("Nombre: "+e.getNombre()+" "+"Tipo: "+e.getDependencia()+" "+ "Salario Base: "+e.getSalarioBase()+" "+"Retefuente: "+valorRete);
                out.write("Nombre: "+e.getNombre()+" "+"Tipo: "+e.getDependencia()+" "+ "Salario Base: "+e.getSalarioBase()+" "+"Retefuente: "+valorRete+System.lineSeparator());
            }
            System.out.println("Se ha generado la retefuente correctamente");
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void generarReporteNomina() {
        for (Empleado e : this.empleados) {
            e.calcularSalarioEmpleado();
        }
        Collections.sort(this.empleados, new Comparator<Empleado>() {
            @Override
            public int compare(Empleado e1, Empleado e2) {
                return Double.compare(e2.getSalarioTot(), e1.getSalarioTot());
            }
        });
        try (FileWriter out = new FileWriter("Reporte.txt")) {
            for (Empleado e : this.empleados) {
                String linea = String.format("%s,%s,$%,.1f%n", e.getNombre(), e.getDocumento(), e.getSalarioTot());
                out.write(linea);
            }
            System.out.println("Se ha generado la nómina correctamente");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void guardarNomina(List<Empleado> empleados){
        try (FileOutputStream fileOut = new FileOutputStream("Nomina.bin");
             ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {

            objectOut.writeObject(empleados);

            System.out.println("La nómina ha sido guardada en un archivo binario.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void listarEmpleadosPorTipoYSalario(String dependencia, double valorParametro) {
        List<Empleado> empleadosFiltrados = filtrarEmpleadosPorTipoYSalario(dependencia, valorParametro);
        ordenarEmpleadosPorNombreConBurbuja(empleadosFiltrados);
        escribirEmpleadosEnArchivo(empleadosFiltrados, "Sobresueldo.txt", valorParametro);
    }

    private List<Empleado> filtrarEmpleadosPorTipoYSalario(String dependencia, double valorParametro) {
        List<Empleado> filtrados = new ArrayList<>();
        for (Empleado empleado : this.empleados) {
            if (empleado != null && empleado.getDependencia().equalsIgnoreCase(dependencia) && empleado.getSalarioBase() > valorParametro) {
                filtrados.add(empleado);
            }
        }
        return filtrados;
    }

    private void ordenarEmpleadosPorNombreConBurbuja(List<Empleado> empleados) {
        boolean intercambiado;
        do {
            intercambiado = false;
            for (int i = 0; i < empleados.size() - 1; i++) {
                if (empleados.get(i).getNombre().compareToIgnoreCase(empleados.get(i + 1).getNombre()) > 0) {

                    Empleado temp = empleados.get(i);
                    empleados.set(i, empleados.get(i + 1));
                    empleados.set(i + 1, temp);
                    intercambiado = true;
                }
            }
        } while (intercambiado);
    }
    private void escribirEmpleadosEnArchivo(List<Empleado> empleados, String nombreArchivo, double valorParametro) {

    }

}
