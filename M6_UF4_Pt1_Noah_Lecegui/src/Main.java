import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static Scanner scanner = new Scanner(System.in);
    public static final int SIZE_OF_STRING = 50;
    public static final int SIZE_OF_INT = Integer.BYTES;
    public static final int SIZE_OF_DOUBLE = Double.BYTES;
    public static final int SIZE_OF_RECORD = SIZE_OF_INT + SIZE_OF_STRING + SIZE_OF_INT + SIZE_OF_DOUBLE;

    public static void main(String[] args) throws IOException {
        verificarArchivo();
        try {
            File f = new File("alumnes.bin");
            RandomAccessFile raf = new RandomAccessFile(f, "rw");
            int opcion;
            do {
                generateMenu();
                opcion = obtenerOpcion(scanner);
                switch (opcion) {
                    case 1:
                        leerAlumnos(raf);
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

    public static int obtenerOpcion(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            System.out.println("Por favor, introduce un número válido.");
            scanner.next();
        }
        return scanner.nextInt();
    }

    public static void leerAlumnos(RandomAccessFile raf) {
        try {
            long numRegistros = raf.length() / SIZE_OF_RECORD;
            for (int i = 0; i < numRegistros; i++) {
                raf.seek((long) i * SIZE_OF_RECORD);
                Alumno alumno = leerRegistro(raf);
                if (alumno != null) {
                    System.out.println(alumno);
                }
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static Alumno leerRegistro(RandomAccessFile raf) {
        try {
            int codigo = raf.readInt();
            String nombre = readFixedLengthString(raf, SIZE_OF_STRING);
            int edad = raf.readInt();
            double mediaNotas = raf.readDouble();
            return new Alumno(codigo, nombre, edad, mediaNotas);
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }

    public static String readFixedLengthString(RandomAccessFile raf, int length) throws IOException {
        byte[] bytes = new byte[length];
        raf.readFully(bytes);
        return new String(bytes, StandardCharsets.UTF_8).trim();
    }

    public static void escribirAlumnos(RandomAccessFile raf) {
        try {
            raf.seek(raf.length()); // Posiciona el puntero al final del archivo
            List<Alumno> alumnos = new ArrayList<>();
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

                Alumno alumno = new Alumno(codigo, nombre, edad, notaMedia);
                alumnos.add(alumno);
            }
            for (Alumno a : alumnos) {
                escribirRegistro(raf, a);
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void escribirRegistro(RandomAccessFile raf, Alumno alumno) {
        try {
            raf.writeInt(alumno.getCodigo());
            writeFixedLengthString(raf, alumno.getNombre(), SIZE_OF_STRING);
            raf.writeInt(alumno.getEdad());
            raf.writeDouble(alumno.getMediaNotas());
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void writeFixedLengthString(RandomAccessFile raf, String string, int length) throws IOException {
        byte[] bytes = new byte[length];
        byte[] stringBytes = string.getBytes(StandardCharsets.UTF_8);
        System.arraycopy(stringBytes, 0, bytes, 0, Math.min(stringBytes.length, length));
        raf.write(bytes);
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