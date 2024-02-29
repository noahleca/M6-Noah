import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        verificarArchivo();

        int opcion;
        do {
            generateMenu();
            opcion = Util.obtenerOpcion(scanner);
            switch (opcion) {
                case 1:
                    List<Alumno> alumnos = leerAlumnosBinario();

                    for (Alumno alumno : alumnos) {
                        System.out.println(alumno.getNombre());
                    }
                    break;
                case 2:
                    List<Alumno> as = new ArrayList<Alumno>();
                    Alumno a = new Alumno(1, "Noah", 20, 8);
                    as.add(a);
                    escribirAlumnosBinario(as);
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

    public static List<Alumno> leerAlumnosBinario() throws IOException {
        List<Alumno> alumnos = new ArrayList<>();

        // Crear un flujo de entrada para el archivo "alumnes.bin".
        try (FileInputStream fis = new FileInputStream("alumnes.bin");
             ObjectInputStream ois = new ObjectInputStream(fis)) {

            // Leer el archivo binario hasta que se alcance el final del archivo.
            while (true) {
                // Leer un objeto del archivo binario.
                Alumno alumno = (Alumno) ois.readObject();

                // Añadir el alumno a la lista.
                alumnos.add(alumno);
            }
        } catch (EOFException e) {
            // Se ha alcanzado el final del archivo.
        } catch (IOException e) {
            // Manejar la excepción de E/S.
            throw new IOException("Error al leer datos de alumnos del archivo binario.", e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return alumnos;
    }


    public static void escribirAlumnosBinario(List<Alumno> alumnos) throws IOException {
        // Crear un flujo de salida para el archivo "alumnes.bin".
        try (FileOutputStream fos = new FileOutputStream("alumnes.bin", true);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {

            // Recorrer la lista de alumnos.
            for (Alumno alumno : alumnos) {
                // Escribir el alumno en el archivo binario.
                oos.writeObject(alumno);
            }
        } catch (IOException e) {
            // Manejar la excepción de E/S.
            throw new IOException("Error al escribir datos de alumnos en el archivo binario.", e);
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