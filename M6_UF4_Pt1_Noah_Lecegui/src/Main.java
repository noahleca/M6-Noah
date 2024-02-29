import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static Scanner scanner = new Scanner(System.in);
    public final static int MAXLEN = 30;

    public static void main(String[] args) throws IOException {
        verificarArchivo();
        try {
            File f = new File("alumnes.bin");
            RandomAccessFile raf = new RandomAccessFile(f, "rw");
            int opcion;
            do {
                generateMenu();
                opcion = Util.obtenerOpcion(scanner);
                switch (opcion) {
                    case 1:
                        leerAlumnos("alumnes.bin");
                        System.out.println();
                        break;
                    case 2:
                        escribirAlumnos(raf);
                        System.out.println();
                        break;
                    case 3:
                        System.out.println("Has triat l'opció 3");
                        break;
                    case 4:
                        System.out.println("Has triat l'opció 4");
                        break;
                    case 5:
                        System.out.println("Has triat l'opció 5");
                        break;
                    case 6:
                        System.out.println("Gracias por utilizar el programa. Hasta pronto!");
                        break;
                    default:
                        System.out.println("Opcion no valida. Porfavor elige una opcion valida.");
                        break;
                }
            } while (opcion != 6);
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    public static void generateMenu() {
        System.out.println("MENU FITXERS BINARIS");
        System.out.println("1. Llegir dades del fitxer alumnes.bin");
        System.out.println("2. Escriure les dades de alumnes al fitxer alumnes.bin");
        System.out.println("3. Esborrar un alumne del fitxer alumnes.bin");
        System.out.println("4. Visualitzar un alumne del fitxer alumnes.bin");
        System.out.println("5. Calcular mitjana d'edat dels alumnes del fitxer alumnes.bin");
        System.out.println("6. Sortir");
    }

    public static void leerAlumnos(String nombreArchivo) {

    }

    public static void escribirAlumnos(RandomAccessFile raf) {
        List<Alumno> listaAlumnos = new ArrayList<>();

        while (true) {
            System.out.println("Codigo del alumno >> ");
            int codigo = scanner.nextInt();
            scanner.nextLine();
            if (codigo == -1) {
                break;
            }

            System.out.println("Nombre del alumno >> ");
            String nombre = scanner.nextLine();

            System.out.println("Edad del alumno >> ");
            int edad = scanner.nextInt();

            System.out.println("Nota media del alumno >> ");
            double notaMedia = scanner.nextDouble();

            listaAlumnos.add(new Alumno(codigo, nombre, edad, notaMedia));
        }

        try {
            for (Alumno alumno : listaAlumnos) {
                escribirRegistro(raf, alumno);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void escribirRegistro(RandomAccessFile raf, Alumno alumno) {
        try {
            raf.write(alumno.getCodigo());

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void verificarArchivo() {
        String filename = "alumnes.bin";
        File file = new File(filename);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            System.err.println("Error al crear el archivo: " + e.getMessage());
        }
    }
}