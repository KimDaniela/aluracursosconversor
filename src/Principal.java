import java.util.Scanner;

public class Principal {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        ConversorMonedas conversor = new ConversorMonedas();

        while (true) {
            System.out.println("\n*** 💱 Conversor de Monedas 💱 ***");
            System.out.println("\n1. Dólar a Pesos Argentinos");
            System.out.println("2. Dólar a Reales Brasileños");
            System.out.println("3. Dólar a Pesos Colombianos");
            System.out.println("4. Dólar a Pesos Uruguayos");
            System.out.println("5. Pesos Argentinos a Dólar");
            System.out.println("6. Reales Brasileños a Dólar");
            System.out.println("7. Pesos Colombianos a Dólar");
            System.out.println("8. Pesos Uruguayos a Dólar");
            System.out.println("9. Salir ❌");
            System.out.print("\nElige una opción: ");

            int opcion;
            try {
                opcion = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Entrada no válida. Debes ingresar un número del 1 al 9.");
                continue;
            }

            if (opcion == 9) {
                System.out.println("Saliendo del programa...");
                break;
            }

            String from = "", to = "";

            switch (opcion) {
                case 1 -> { from = "USD"; to = "ARS"; }
                case 2 -> { from = "USD"; to = "BRL"; }
                case 3 -> { from = "USD"; to = "COP"; }
                case 4 -> { from = "USD"; to = "UYU"; }
                case 5 -> { from = "ARS"; to = "USD"; }
                case 6 -> { from = "BRL"; to = "USD"; }
                case 7 -> { from = "COP"; to = "USD"; }
                case 8 -> { from = "UYU"; to = "USD"; }
                default -> {
                    System.out.println("Opción no válida");
                    continue;
                }
            }

            System.out.print("Ingresa la cantidad a convertir: ");
            String entrada = scanner.nextLine().replace(",", ".");
            double cantidad;

            try {
                cantidad = Double.parseDouble(entrada);
            } catch (NumberFormatException e) {
                System.out.println("Entrada no válida. Debes ingresar un número.");
                continue;
            }

            double resultado = conversor.convertir(from, to, cantidad);
            if (resultado >= 0) {
                System.out.printf("Resultado: %.2f %s = %.2f %s%n", cantidad, from, resultado, to);
            } else {
                System.out.println("No se pudo realizar la conversión.");
            }
        }
    }
}
