import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static Scanner scanner = new Scanner(System.in);
    public static final int SIZE_OF_STRING = 50;
    public static final int SIZE_OF_INT = Integer.BYTES;
    public static final int SIZE_OF_DOUBLE = Double.BYTES;
    public static final int SIZE_OF_RECORD = SIZE_OF_INT + SIZE_OF_STRING + SIZE_OF_INT + SIZE_OF_DOUBLE;
    public static File f = new File("alumnes.bin");

    public static void main(String[] args) throws IOException {
        verificarArchivo();
        try {

            RandomAccessFile raf = new RandomAccessFile(f, "rw");
            int opcion;
            do {
                generateMenu();
                opcion = Util.obtenerOpcion(scanner);
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

    public static void leerAlumnos(RandomAccessFile raf) {
        Alumno alumno;
        try {
            raf.seek(0);
            System.out.println("Contenido del archivo: ");
            System.out.println("Medida del archivo: " + raf.length());
            System.out.println("Total de registros: " + raf.length() / SIZE_OF_RECORD);
            System.out.println();
            int cont = 0;
            while (raf.getFilePointer() < raf.length()) {
                System.out.printf("Registre: %d Posició: %d\n", cont, raf.getFilePointer());
                alumno = leerRegistro(raf);
                System.out.println(alumno.toString());
                System.out.println();
                cont++;
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static Alumno leerRegistro(RandomAccessFile raf) {
        Alumno alumno = new Alumno();

        try {
            alumno.setCodigo(raf.readInt());
            alumno.setNombre(leerString(raf));
            alumno.setEdad(raf.readInt());
            alumno.setMediaNotas(raf.readDouble());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return alumno;
    }

    public static String leerString(RandomAccessFile raf) {
        try {
            StringBuilder str = new StringBuilder();
            for (int i = 0; i < SIZE_OF_STRING; i++) {
                char c = raf.readChar();
                if (c != 0) {
                    str.append(c);
                }
            }
            return str.toString();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }

    public static void escribirAlumnos(RandomAccessFile raf) {
        List<Alumno> alumnos = new ArrayList<>();
        try {
            RandomAccessFile rafLeer = new RandomAccessFile("alumnes.bin", "r");

            while (true) {
                int codigo = Util.obtenerCodigo(scanner, ObtenerAlumnos(rafLeer));
                if (codigo == -1) {
                    break;
                }
                System.out.print("Nombre del alumno >> ");
                String nombre = Util.obtenerNombre(scanner.nextLine());
                int edad = Util.obtenerEdad(scanner);
                double notaMedia = Util.obtenerNotaMedia(scanner);

                Alumno a = new Alumno(codigo, nombre, edad, notaMedia);
                alumnos.add(a);
                System.out.println("El alumno: " + a.getNombre() + " ha sido añadido a la cola.\n");
            }

            for (Alumno alumno : alumnos) {
                escribirRegistro(raf, alumno);
            }
            System.out.println("\nSe han añadido " + alumnos.size() + " alumnos al archivo " + f.getPath());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void escribirRegistro(RandomAccessFile raf, Alumno alumno) {
        try {
            raf.writeInt(alumno.getCodigo());
            escribirString(raf, alumno.getNombre());
            raf.writeInt(alumno.getEdad());
            raf.writeDouble(alumno.getMediaNotas());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static boolean escribirString(RandomAccessFile raf, String str) {
        try {
            int numChars = str.length();
            if (numChars > SIZE_OF_STRING) {
                numChars = SIZE_OF_STRING;
            }
            for (int i = 0; i < numChars; i++) {
                raf.writeChar(str.charAt(i));
            }
            char NULL = 0;

            for (int i = 0; i < (SIZE_OF_STRING - numChars); i++) {
                raf.writeChar(NULL);
            }
            return true;
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return false;
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

    public static List<Alumno> ObtenerAlumnos(RandomAccessFile raf) {
        Alumno alumno;
        List<Alumno> alumnos = new ArrayList<>();
        try {
            raf.seek(0);
            while (raf.getFilePointer() < raf.length()) {
                alumno = leerRegistro(raf);
                alumnos.add(alumno);
            }
            return alumnos;
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }
}