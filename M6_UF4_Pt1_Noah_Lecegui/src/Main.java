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
                        leerAlumnoEspecifico(raf);
                        System.out.println();
                        break;
                    case 5:
                        calcularEdadMediana(raf);
                        System.out.println();
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

    public static void calcularEdadMediana(RandomAccessFile raf) {
        double edadTotal = 0;
        int nAlumnos = 0;
        Alumno alumno;
        try {
            raf.seek(0);
            if (raf.length() == 0) {
                System.out.println("Aún no hay registros de alumnos.");
            } else {
                while (raf.getFilePointer() < raf.length()) {
                    alumno = leerRegistro(raf);
                    edadTotal += alumno.getEdad();
                    nAlumnos++;
                }
                System.out.println("La media de edad de los alumnos es de: " + (edadTotal / nAlumnos));
            }
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    public static void leerAlumnoEspecifico(RandomAccessFile raf) {
        Alumno alumno;
        boolean existe = false;

        System.out.print("Codigo del alumno >> ");
        int codigo = scanner.nextInt();
        System.out.println();
        try {
            raf.seek(0);
            if (raf.length() == 0) {
                System.out.println("Aún no hay registros de alumnos.");
            } else {
                while (raf.getFilePointer() < raf.length()) {
                    alumno = leerRegistro(raf);
                    if (alumno.getCodigo() == codigo) {
                        System.out.println("Codigo     Nombre                 Edad     Nota media");
                        System.out.printf("%-10d %-22s %-8d %.2f", alumno.getCodigo(), alumno.getNombre(), alumno.getEdad(), alumno.getMediaNotas());
                        System.out.println();
                        existe = true;
                    }
                }
                if (!existe) {
                    System.out.println("No existe un registro con este codigo.");
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());

        }
        scanner.nextLine();
    }

    public void eliminarAlumno(RandomAccessFile raf) {
        System.out.print("Introduce el codigo del alumno que deseas eliminar: ");
        int codigo = scanner.nextInt();
        boolean encontrado = false;

        try {
            File tempFile = new File("alumnes.bin");
            RandomAccessFile tempRaf = new RandomAccessFile(tempFile, "rw");
            raf.seek(0);
            while (raf.getFilePointer() < raf.length()) {
                long posInicio = raf.getFilePointer();
                Alumno alumno = leerRegistro(raf);
                if (alumno.getCodigo() == codigo) {
                    encontrado = true;
                    System.out.println("Información del alumno encontrado:");
                    System.out.println("Código: " + alumno.getCodigo());
                    System.out.println("Nombre: " + alumno.getNombre());
                    System.out.println("Edad: " + alumno.getEdad());
                    System.out.println("Nota Media: " + alumno.getMediaNotas());

                    System.out.print("¿Estás seguro de eliminar este alumno? (Introduce -1 para confirmar): ");
                    int confirmacion = scanner.nextInt();
                    if (confirmacion == -1) {

                    } else {
                        tempRaf.seek();
                    }
                }
            }

        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    public static void leerAlumnos(RandomAccessFile raf) {
        Alumno alumno;
        try {
            raf.seek(0);
            if (raf.length() == 0) {
                System.out.println("Aún no hay registros de alumnos.");
            } else {
                System.out.println("Contenido del archivo: ");
                System.out.println();
                System.out.println("Codigo     Nombre                 Edad     Nota media");
                while (raf.getFilePointer() < raf.length()) {
                    alumno = leerRegistro(raf);
                    System.out.printf("%-10d %-22s %-8d %.2f", alumno.getCodigo(), alumno.getNombre(), alumno.getEdad(), alumno.getMediaNotas());
                    System.out.println();
                }
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
        List<Integer> codigosCola = new ArrayList<>();

        try {
            RandomAccessFile rafLeer = new RandomAccessFile("alumnes.bin", "r");

            while (true) {
                int codigo = Util.obtenerCodigo(scanner, ObtenerAlumnos(rafLeer), codigosCola);
                codigosCola.add(codigo);
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
            System.out.println("\nSe han añadido " + alumnos.size() + " alumno/s al archivo " + f.getPath());
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