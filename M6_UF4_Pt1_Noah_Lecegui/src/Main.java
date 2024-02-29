import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        verificarArchivo();

        int opcion;
        do {
            generateMenu();
            opcion = Util.obtenerOpcion(scanner);
            switch (opcion) {
                case 1:
                    System.out.println("Has triat l'opció 1");
                    break;
                case 2:
                    System.out.println("Has triat l'opció 2");
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

    public static void escribirAlumnos(List<Alumno> alumnos) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(System))
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