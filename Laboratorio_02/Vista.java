import java.util.Scanner;

public class Vista {
    private Scanner scanner;
    private Validador validador;

    public Vista() {
        this.scanner = new Scanner(System.in);
        this.validador = new Validador();
    }

    public String pedirNombreJugador() {
        System.out.print("Ingrese el nombre del jugador: ");
        return scanner.nextLine().trim();
    }

    public int[] pedirCoordenada(String mensaje, int maxFilas, int maxColumnas) throws ExcepcionEntradaInvalida {
        System.out.print(mensaje);
        String filaStr = scanner.next();
        String columnaStr = scanner.next();

        if (!validador.esNumeroEntero(filaStr) || !validador.esNumeroEntero(columnaStr)) {
            throw new ExcepcionEntradaInvalida("⚠️ Error: Las coordenadas deben ser números enteros.");
        }

        int fila = Integer.parseInt(filaStr);
        int columna = Integer.parseInt(columnaStr);

        if (!validador.coordenadaValida(fila, columna, maxFilas, maxColumnas)) {
            throw new ExcepcionEntradaInvalida("⚠️ Error: Coordenadas fuera del rango del tablero.");
        }

        return new int[]{fila, columna};
    }

    public void mostrarTablero(String representacion) {
        System.out.println(representacion);
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    public void mostrarPuntajeFinal(String nombre, int puntaje) {
        System.out.println("🎉 ¡Juego finalizado!");
        System.out.println("Jugador: " + nombre);
        System.out.println("Puntaje final: " + puntaje + " punto(s)");
    }
}
