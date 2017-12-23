package GeneradorCodigo;

public class Division {
    private static String dividir(int a, int b) {
        String returnString ="";
        int i = 0;
        int resto = 0;
        while((resto = (a = a - b)) >= b){
            i++;
            if(resto == b){
                returnString = (i + 1) + " " + (resto - b);
            }else {
                returnString = (i + " " + resto);
            }
        }
        return returnString;
    }


    public static void main(String[] args) {
        int a = Integer.parseInt("12");
        int b = Integer.parseInt("2");
        System.out.println(dividir(a, b));
    }
}
