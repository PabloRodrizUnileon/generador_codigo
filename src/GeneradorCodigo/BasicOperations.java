package GeneradorCodigo;

import java.io.*;
import java.util.regex.Pattern;


public class BasicOperations {


    private static int repeticiones;
    private static String sourceRestaJava = "Resta.java";
    private static String sourceRestaClass = "Resta";
    private static String sourceMultiplicacionJava = "Multiplicacion.java";
    private static String sourceMultiplicaionClass = "Multiplicacion";

    private static BufferedReader reader;
    private static Process theProcess;
    private static File fileToJava;


    public static void main(String[] args) throws IOException {
        reader = new BufferedReader(new InputStreamReader(System.in));
        String readedLine = "";
        printMenu();
        int a, b;


        String line = "";
        while (!(line = reader.readLine()).toLowerCase().equals("no")) {
            switch (readOperation()) {
                case "MENU":
                    printMenu();
                    break;
                case "1":
                    printLine("Introduce la suma, Ejemplo: ( 5 + 4) con espacios y presiona ENTER");
                    readedLine = readOperation();
                    a = Integer.parseInt(readedLine.split(Pattern.quote("+"))[0].trim());
                    b = Integer.parseInt(readedLine.split(Pattern.quote("+"))[1].trim());
                    printLine("Suma = " + Operaciones.suma(a, b));
                    break;

                case "2":
                    printLine("Introduce la resta, Ejemplo: ( 5 - 4) con espacios y presiona ENTER");
                    readedLine = readOperation();
                    a = Integer.parseInt(readedLine.split(Pattern.quote("-"))[0].trim());
                    b = Integer.parseInt(readedLine.split(Pattern.quote("-"))[1].trim());
                    generateResta(a, b);
                    break;

                case "3":
                    printLine("Introduce la multiplicacion, Ejemplo: ( 5 * 4) con espacios y presiona ENTER");
                    readedLine = readOperation();
                    a = Integer.parseInt(readedLine.split(Pattern.quote("*"))[0].trim());
                    b = Integer.parseInt(readedLine.split(Pattern.quote("*"))[1].trim());
                    generateMultiplicacion(a, b);
                    break;
                case "4":
                    printLine("Introduce la division, Ejemplo: ( 5 / 4) con espacios y presiona ENTER");
                    readedLine = readOperation();
                    a = Integer.parseInt(readedLine.split(Pattern.quote("/"))[0].trim());
                    b = Integer.parseInt(readedLine.split(Pattern.quote("/"))[1].trim());
                    generateDivision(a, b);

            }
            printLine("Mas operaciones?, teclea NO para acabar; MENU para menú.");
        }

    }


    public static void printLine(String print) {
        System.out.println(print);
    }

    public static void printMenu() {
        printLine("\t GENERADOR DE CODIGO 2.0;");
        printLine("Introduzca una operación a realizar y presione ENTER");
        printLine("Operaciones:");
        printLine("1- Suma");
        printLine("2- Resta");
        printLine("3- Multiplicacion");
        printLine("4- Division");
        printLine("5- Potencia");
        printLine("6- Factorial");
        printLine("7- Porcentaje");
        printLine("8- Media aritmética");
        printLine("9- Raíz cuadrada");

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


    private static String generateRepetir(int veces, String codigoARepetir) {
        StringBuffer b = new StringBuffer(codigoARepetir.length() * veces);
        for (int i = 0; i < veces; i++) {
            b.append(codigoARepetir);
        }
        return b.toString();
    }

    private static String generateRepetirLoop(int veces, String codigoARepetir) {
        return "for(int i = 0; i < " + veces + "; i++){\n" +
                codigoARepetir + ";\n" +
                "}";
    }
//Todo: arreglar el generateRepetirLoop

    private static void generateResta(int a, int b) {
        theProcess = null;
        fileToJava = new File(sourceRestaJava);
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(fileToJava);

            fileWriter.write(
                    "public class Resta { \n" +
                            "private static int suma(int a, int b) {\n" +
                            "   return a + b;\n" +
                            "}\n" +
                            "private static int negar(int a) {\n" +
                            "   return Math.negateExact(a);\n" +
                            "}\n" +
                            "public static void main(String[] args) { \n" +
                            "   int a = Integer.parseInt(args[0]);\n" +
                            "   int b = Integer.parseInt(args[1]);" +
                            "   System.out.println(suma(a, negar(b)));" +
                            " }\n" +
                            "}"
            );


            fileWriter.close();

            Runtime.getRuntime().exec("javac " + sourceRestaJava);
            printLine("Espera...");
            Thread.sleep(1000);
            theProcess = Runtime.getRuntime().exec("java " + sourceRestaClass + " " + a + " " + b + "\n");


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

    private static void generateMultiplicacion(int a, int veces) {
        Process theProcess = null;
        File file = new File(sourceMultiplicacionJava);
//        file.getParentFile().mkdirs();
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(file);

            fileWriter.write(
                    "public class Multiplicacion { \n" +
                            "private static int multiplicacion(int a, int b) {\n" +
                            "int returnNumber = 0;\n" +
                            //Todo: arreglar ESTOOOO
                            generateRepetirLoop(veces, "returnNumber = returnNumber + a") + ";\n" +
                            "            return returnNumber;" +
                            "}\n" +
                            "public static void main(String[] args) { \n" +
                            "   int a = Integer.parseInt(args[0]);\n" +
                            "   int b = Integer.parseInt(args[1]);" +
                            "   System.out.println(multiplicacion(a, b));" +
                            " }\n" +
                            "}"
            );


            fileWriter.close();

            Runtime.getRuntime().exec("javac " + sourceMultiplicacionJava);
            printLine("Espera...");
            Thread.sleep(1000);
            theProcess = Runtime.getRuntime().exec("java " + sourceMultiplicaionClass + " " + a + " " + veces + "\n");


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

    private static void generateDivision(int a, int b) {

    }



}
