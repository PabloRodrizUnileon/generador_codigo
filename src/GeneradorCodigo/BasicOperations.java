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
        printLine("2- Resta");
        printLine("3- Multiplicacion");
        printLine("4- Division");
        printLine("4- Potencia");
        printLine("4- Factorial");
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


    private static String generateRepetir(int veces, String codigoARepetir){
        StringBuffer b = new StringBuffer(codigoARepetir.length() * veces);
        for(int i = 0; i<veces; i++){
            b.append(codigoARepetir);
        }
        return b.toString();
    }

//    public static void generateOperation(int operation){
//        switch (operation){
//            case _RESTA:
//                generateResta();
//                break;
//            case _MULTIPLICAR:
//                // sadfasd
//                break;
//
//        }
//    }

    private static void generateResta(int a, int b) {
        Process theProcess = null;
        File file = new File(sourceRestaJava);
//        file.getParentFile().mkdirs();
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(file);

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






}
