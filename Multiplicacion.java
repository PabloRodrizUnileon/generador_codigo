public class Multiplicacion { 
private static int multiplicacion(int a, int b) {
int returnNumber = 0;
for(int i = 0; i < 4; i++){
returnNumber = returnNumber + a;
};
            return returnNumber;}
public static void main(String[] args) { 
   int a = Integer.parseInt(args[0]);
   int b = Integer.parseInt(args[1]);   System.out.println(multiplicacion(a, b)); }
}