import java.util.Scanner;

public class Consola {

    private final Scanner sc;

    public Consola() {
        this.sc = new Scanner(System.in);
    }

    public void dibujarTablero(Tablero t) {
        int n = t.getN();
        print("  ");
        for (int i = 1; i <= n; i++) {
            print(i + " ");
        }
        println("");

        for (int i = 0; i < n; i++) {
            print((char)('A' + i) + " ");
            for (int j = 0; j < n; j++) {
                print(t.getCelda(i, j) + " ");
            }
            println("");
        }
        println("");
    }

    public void print(String s) {
        System.out.print(s);
    }

    public void println(String s) {
        System.out.println(s);
    }
}