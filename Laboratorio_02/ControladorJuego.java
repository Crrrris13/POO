public class ControladorJuego {
    private Juego juego;
    private Validador validador;

    public ControladorJuego(String nombreJugador, int filas, int columnas) {
        Jugador jugador = new Jugador(nombreJugador);
        Tablero tablero = new Tablero(filas, columnas);
        this.juego = new Juego(jugador, tablero);
        this.validador = new Validador();
    }

    public String mostrarTablero() {
        return this.juego.mostrarTablero();
    }

    public Jugador getJugador() {
        return this.juego.getJugador();
    }

    public boolean validarCoordenada(int fila, int columna) {
        return validador.coordenadaValida(fila, columna,
                this.juego.getTablero().getFilas(),
                this.juego.getTablero().getColumnas());
    }

    public boolean fichaYaDescubierta(int fila, int columna) {
        return this.juego.getTablero().obtenerFicha(fila, columna).estaDescubierta();
    }

    public void descubrirFicha(int fila, int columna) {
        this.juego.getTablero().obtenerFicha(fila, columna).descubrir();
    }

    public void ocultarFicha(int fila, int columna) {
        this.juego.getTablero().obtenerFicha(fila, columna).ocultar();
    }

    public boolean comprobarEmparejamiento(int[] f1, int[] f2) {
        return this.juego.getTablero().esEmparejable(f1, f2);
    }

    public void emparejarFichas(int[] f1, int[] f2) {
        this.juego.getTablero().obtenerFicha(f1[0], f1[1]).emparejar();
        this.juego.getTablero().obtenerFicha(f2[0], f2[1]).emparejar();
        this.juego.getTablero().aumentarParesEmparejados();
        this.juego.getJugador().sumarPunto();
    }

    public boolean juegoFinalizado() {
        return this.juego.estaFinalizado();
    }
}
