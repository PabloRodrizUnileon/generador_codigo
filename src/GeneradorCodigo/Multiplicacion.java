package GeneradorCodigo;

public class Multiplicacion {
    private static int multiplicacion(int a, int b) {
        int returnNumber = Operaciones.suma(a, a);
        if (b == 1) {
            return a;
        } else {
            for (int i = 0; i < b - 2; i++) {
                returnNumber = Operaciones.suma(returnNumber, a);
            }
        }
        return returnNumber;
    }
    //TODO:     Implementar en BasicOperations

    public static void main(String[] args) {
        int a = Integer.parseInt("6");
        int b = Integer.parseInt("10");
        System.out.println(multiplicacion(a, b));
    }
}