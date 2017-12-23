import java.io.*;
import java.util.regex.Pattern;


public class BasicOperations {

    /**
     * Variables de programa
     */
    private static final int _RESTA = 1;
    private static final int _MULTIPLICAR = 2;
    private static final int _DIVISION = 3;
    private static final int _POTENCIA = 4;
    private static final int _FACTORIAL = 5;
    private static final int _PORCENTAJE = 6;
    private static final int _MEDIA_ARITMETICA = 7;
    private static final int _RAIZ_CUADRADA = 8;


    private static int repeticiones;
    private static String sourceJava = "Resta.java";
    private static String sourceClass = "Resta";

    private static BufferedReader reader;


    public static void main(String[] args) throws IOException {
        reader = new BufferedReader(new InputStreamReader(System.in));
        String readedLine = "";
        printMenu();
        int a, b;
        switch (readOperation()) {
            case "1":
                printLine("Introduce la suma, Ejemplo: ( 5 + 4) con espacios y presiona ENTER");
                readedLine = readOperation();
                a = Integer.parseInt(readedLine.split(Pattern.quote("+"))[0].trim());
                b = Integer.parseInt(readedLine.split(Pattern.quote("+"))[1].trim());
                printLine("Suma = " + Operaciones.suma(a, b));
                break;
            case "2":

                break;
            case "3":

                printLine("Introduce la resta, Ejemplo: ( 5 - 4) con espacios y presiona ENTER");
                readedLine = readOperation();
                a = Integer.parseInt(readedLine.split(Pattern.quote("-"))[0].trim());
                b = Integer.parseInt(readedLine.split(Pattern.quote("-"))[1].trim());
                generateResta(a, b);

                break;
        }


        printLine("Mas operaciones?, teclea NO para acabar");
        String line = "";
        while (!(line = reader.readLine()).toLowerCase().equals("no")) {
            System.out.println(line);
        }

    }


    public static void printLine(String print) {
        System.out.println(print);
    }

    public static void printMenu() {
        printLine("\t GENERADOR DE CODIGO 2.0;");
        printLine("Introduzca una operación a realizar y presione ENTER");
        printLine("GeneradorCodigo.Operaciones:");
        printLine("1- Suma");
        printLine("2- Negacion");
        printLine("3- Resta");
        printLine("4- Multiplicacion");
        printLine("5- Division");
    }

    public static String readOperation() {
        String returnString = "";
        try {
            returnString = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return returnString;
    }


    private static void generateResta(int a, int b) {
        Process theProcess = null;
        File file = new File(sourceJava);
//        file.getParentFile().mkdirs();
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(file);

            fileWriter.write(
                    "public class Resta { \n" +
                            "public static void main(String[] args) { \n" +
                            "   int a = Integer.parseInt(args[0]);\n" +
                            "   int b = Integer.parseInt(args[1]);" +
                            "   System.out.println(\"Resultado=\" + Operaciones.suma(a, Operaciones.negar(b)));" +
                            " }\n" +
                            "}"
            );


            fileWriter.close();

            Runtime.getRuntime().exec("javac " + sourceJava);
            printLine("Espera...");
            Thread.sleep(1000);
            theProcess = Runtime.getRuntime().exec("java " + sourceClass + " " + a + " " + b + "\n");


        } catch (IOException e) {
            System.err.println("Error en el método exec()");
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Se lee en la corriente de salida estándar del programa llamado.
        try {
            BufferedReader inStream = new BufferedReader(
                    new InputStreamReader(theProcess.getInputStream()));
            System.out.println(inStream.readLine());
        } catch (IOException e) {
            System.err.println("Error en inStream.readLine()");
            e.printStackTrace();
        }
    }



    




}
