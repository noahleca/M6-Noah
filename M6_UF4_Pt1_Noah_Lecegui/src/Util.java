import java.util.Scanner;

public class Util {
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

    public static int obtenerCodigo() {
        return -1;
    }

    public static String obtenerNombre() {
        return "";
    }

    public static int obtenerEdad() {
        return -1;
    }

    public static double obtenerNotaMedi() {
        return -1;
    }


}
