import java.util.ArrayList;
import java.util.Random;

public class Tablero {
    private int filas;
    private int columnas;
    private Ficha[][] tablero;
    private int totalPares;
    private int paresEmparejados;

    public Tablero(int filas, int columnas) {
        this.filas = filas;
        this.columnas = columnas;
        this.totalPares = (filas * columnas) / 2;
        this.paresEmparejados = 0;
        this.tablero = new Ficha[filas][columnas];
        inicializarFichas();
    }

    private void inicializarFichas() {
        ArrayList<String> simbolos = new ArrayList<>();
        String[] baseSimbolos = {
                "🍎", "🍌", "🍇", "🍓", "🍉", "🍍", "🥝", "🍑", "🍒", "🥥"
        };

        for (int i = 0; i < totalPares; i++) {
            simbolos.add(baseSimbolos[i]);
            simbolos.add(baseSimbolos[i]);
        }

        Random rand = new Random();
        for (int i = 0; i < simbolos.size(); i++) {
            int j = rand.nextInt(simbolos.size());
            String temp = simbolos.get(i);
            simbolos.set(i, simbolos.get(j));
            simbolos.set(j, temp);
        }

        int indice = 0;
        for (int f = 0; f < filas; f++) {
            for (int c = 0; c < columnas; c++) {
                this.tablero[f][c] = new Ficha(simbolos.get(indice));
                indice++;
            }
        }
    }

    public boolean esEmparejable(int[] f1, int[] f2) {
        Ficha ficha1 = this.tablero[f1[0]][f1[1]];
        Ficha ficha2 = this.tablero[f2[0]][f2[1]];

        return !ficha1.estaEmparejada()
                && !ficha2.estaEmparejada()
                && ficha1.getSimbolo().equals(ficha2.getSimbolo());
    }

    public void aumentarParesEmparejados() {
        this.paresEmparejados++;
    }

    public boolean todasEmparejadas() {
        return this.paresEmparejados == this.totalPares;
    }

    public Ficha obtenerFicha(int fila, int columna) {
        return this.tablero[fila][columna];
    }

    public String mostrarTablero() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.filas; i++) {
            for (int j = 0; j < this.columnas; j++) {
                Ficha ficha = this.tablero[i][j];

                if (ficha.estaEmparejada()) {
                    sb.append("   ");
                } else if (ficha.estaDescubierta()) {
                    sb.append(" " + ficha.getSimbolo() + " ");
                } else {
                    sb.append(" * ");
                }
            }
            sb.append("\n");
        }

        return sb.toString();
    }

    public int getFilas() {
        return this.filas;
    }

    public int getColumnas() {
        return this.columnas;
    }
}
