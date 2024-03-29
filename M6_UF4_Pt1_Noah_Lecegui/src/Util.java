import java.util.List;
import java.util.Scanner;

public class Util {

    public static int obtenerNumero(Scanner scanner) {
        int numero = 0;
        boolean valido = false;

        while (!valido) {
            System.out.print("Introduce un numero >> ");
            try {
                numero = Integer.parseInt(scanner.nextLine());
                if (numero >= 0) {
                    valido = true;
                } else {
                    System.out.println("Error: El numero introducido no puede ser negativo.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: El valor introducido no es un número.");
            }
        }
        System.out.println();
        return numero;
    }
    public static int obtenerOpcion(Scanner scanner) {
        int opcion = 0;
        boolean valido = false;

        while (!valido) {
            System.out.print("Introduce un numero del 1 al 6 >> ");
            try {
                opcion = Integer.parseInt(scanner.nextLine());
                if (opcion >= 1 && opcion <= 6) {
                    valido = true;
                } else {
                    System.out.println("Error: La opcion elegida tiene que estar entre el 1 y el 6.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: El valor introducido no es un número.");
            }
        }
        System.out.println();
        return opcion;
    }

    public static int obtenerCodigo(Scanner scanner, List<Alumno> alumnos, List<Integer> codigosCola) {
        int codigo = 0;
        boolean valido = false;

        while (!valido) {
            System.out.print("Codigo del alumno >> ");
            try {
                codigo = Integer.parseInt(scanner.nextLine());
                if (codigo >= -1 && !existeCodigo(alumnos, codigo) && !existeCodigoCola(codigosCola, codigo)) {
                    valido = true;
                } else {
                    if (codigo < -1) {
                        System.out.println("Error: El código debe ser mayor o igual a 0.");
                    } else {
                        System.out.println("Error: El código ya existe.");
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: El codigo introducido no es un número.");
            }
        }
        return codigo;
    }

    public static boolean existeCodigoCola(List<Integer> codigosCola, int nuevoCodigo) {
        for (int codigo : codigosCola) {
            if (codigo == nuevoCodigo) {
                return true;
            }
        }
        return false;
    }

    public static boolean existeCodigo(List<Alumno> alumnos, int nuevoCodigo) {
        for (Alumno alumno : alumnos) {
            if (alumno.getCodigo() == nuevoCodigo) {
                return true;
            }
        }
        return false;
    }

    public static String obtenerNombre(String nombre) {
        if (nombre == null || nombre.isEmpty()) {
            return nombre;
        }

        StringBuilder resultado = new StringBuilder();
        String[] palabras = nombre.split("\\s+");

        for (String palabra : palabras) {
            if (!palabra.isEmpty()) {
                resultado.append(palabra.substring(0, 1).toUpperCase());
                resultado.append(palabra.substring(1).toLowerCase());
                resultado.append(" ");
            }
        }

        return resultado.toString().trim();
    }


    public static int obtenerEdad(Scanner scanner) {
        int edad = 0;
        boolean valido = false;

        while (!valido) {
            System.out.print("Edad del alumno >> ");
            try {
                edad = Integer.parseInt(scanner.nextLine());
                if (edad >= 0) {
                    valido = true;
                } else {
                    System.out.println("Error: La edad del alumno no puede ser negativa.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: La edad introducida no es un número.");
            }
        }
        return edad;
    }

    public static double obtenerNotaMedia(Scanner scanner) {
        double notaMedia = 0;
        boolean valido = false;

        while (!valido) {
            System.out.print("Nota media del alumno >> ");
            try {
                notaMedia = Double.parseDouble(scanner.nextLine());
                if (notaMedia >= 0) {
                    valido = true;
                } else {
                    System.out.println("Error: La nota media del alumno no puede ser negativa.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: La nota media introducida no es un número.");
            }
        }
        return notaMedia;
    }
}
