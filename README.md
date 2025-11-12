# PA2-Nomina
Sistema de gestión de nómina en Java orientado a objetos. Incluye registro de empleados por tipo, cálculo de sobresueldo, y generación de reportes personalizados.
Proyecto académico desarrollado para la materia de **Programación Avanzada** (2023-30).  
El sistema permite gestionar la nómina de empleados, profesores y monitores, utilizando archivos **binarios y de texto** para almacenamiento persistente y generación de reportes automatizados.

---

## 📌 Funcionalidades

- Cargar empleados desde archivo (`nomina.txt`).
- Registrar nuevos empleados (empleado base, profesor, monitor).
- Asignar asignaturas a profesores y monitores.
- Calcular salarios según reglas de negocio por tipo de empleado.
- Generar reportes de nómina, retefuente y sobresueldo.
- Guardar y recuperar información utilizando archivos binarios (`Nomina.bin`).

---

## 🛠️ Tecnologías y herramientas

- Lenguaje: **Java**
- Programación Orientada a Objetos (POO)
- Serialización y manejo de archivos (`.txt`, `.bin`)
- IDE recomendado: **IntelliJ IDEA**, **Eclipse**, **VS Code**
- Sistema de control de versiones: **Git + GitHub**

---

## 📁 Estructura del proyecto

```plaintext
📦 ProyectoNomina/
├── src/
│   ├── Main.java
│   ├── Empleado.java
│   ├── Profesor.java
│   ├── Monitor.java
│   ├── Asignatura.java
│   └── Nomina.java
├── recursos/
│   ├── nomina.txt
│   ├── Reporte.txt
│   ├── Retefuente.txt
│   └── Sobresueldo.txt
├── README.md
├── LICENSE
└── .gitignore
```
---

##  🧪 Estado del Proyecto

Este código fue funcional y aprobado en su momento.
Fue revisado y restaurado para su publicación en GitHub con fines académicos y de portafolio personal.

---

## 📁 Archivos incluidos

- **Main.java**: Lógica de menú y control de flujo principal.

- **Empleado.java**: Clase base con lógica compartida.

- **Profesor.java**, **Monitor.java**: Subclases especializadas con reglas propias.

- **Asignatura.java**: Modelo para las asignaturas asociadas.

- **Nomina.java**: Controlador general con toda la lógica de negocio.

- **nomina.txt**: Archivo de entrada con empleados predefinidos.

- **Reporte.txt**, Retefuente.txt, Sobresueldo.txt: Archivos de salida generados automáticamente.

- **gitignore**: Archivos y carpetas ignoradas por Git para mantener limpio el repositorio.

---

## 🚀 Cómo ejecutar el proyecto

1. **Clona este repositorio**:

   ```bash
   git clone https://github.com/ma-sarmiento/PA2-Nomina.git
   cd PA2-Nomina
Requiere tener instalado un compilador compatible con Java.

2. Compila los archivo fuente:
   
   ```bash
   javac -d bin src/*.java

3. Ejecuta el programa:
   
   ```bash
   java -cp bin Main

---

💡 También puedes abrir el proyecto directamente desde IntelliJ IDEA, Eclipse o cualquier otro IDE y ejecutar el Main.java desde allí.

---

📌 Nota: Por razones de derechos académicos, el enunciado original del proyecto no será publicado en este repositorio.
