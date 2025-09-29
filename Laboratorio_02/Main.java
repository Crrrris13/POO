public class Main {
    public static void main(String[] args) {
        Vista vista = new Vista();
        int filas = 4;
        int columnas = 4;

        String nombreJugador1 = vista.pedirNombreJugador();
        ControladorJuego controlador = new ControladorJuego(nombreJugador1, filas, columnas);

        while (!controlador.juegoFinalizado()) {
            vista.mostrarTablero(controlador.mostrarTablero());

            // Mostrar turno actual
            String nombreActual = controlador.getJugadorDelTurno().getNombre();
            vista.mostrarMensaje("Turno de: " + nombreActual);

            // Primera coordenada
            int[] coord1 = vista.pedirCoordenada(nombreActual, filas, columnas);
            while (!controlador.validarCoordenada(coord1[0], coord1[1]) || controlador.fichaYaDescubierta(coord1[0], coord1[1])) {
                vista.mostrarMensaje("Coordenada inválida o ficha ya descubierta. Intente de nuevo.");
                coord1 = vista.pedirCoordenada(nombreActual, filas, columnas);
            }

            controlador.descubrirFicha(coord1[0], coord1[1]);
            vista.mostrarTablero(controlador.mostrarTablero());

            // Segunda coordenada
            int[] coord2 = vista.pedirCoordenada(nombreActual, filas, columnas);
            while (!controlador.validarCoordenada(coord2[0], coord2[1])
                    || controlador.fichaYaDescubierta(coord2[0], coord2[1])
                    || (coord1[0] == coord2[0] && coord1[1] == coord2[1])) {
                vista.mostrarMensaje("Coordenada inválida o ficha ya descubierta. Intente de nuevo.");
                coord2 = vista.pedirCoordenada(nombreActual, filas, columnas);
            }

            controlador.descubrirFicha(coord2[0], coord2[1]);
            vista.mostrarTablero(controlador.mostrarTablero());

            // Evaluar emparejamiento
            if (controlador.comprobarEmparejamiento(coord1, coord2)) {
                controlador.emparejarFichas(coord1, coord2);
                vista.mostrarMensaje("¡Correcto! Sigues jugando.");
            } else {
                controlador.ocultarFicha(coord1[0], coord1[1]);
                controlador.ocultarFicha(coord2[0], coord2[1]);
                vista.mostrarMensaje("Incorrecto. Turno del otro jugador.");
                controlador.cambiarTurno();
            }
        }

        // Mostrar resultados finales
        Jugador j1 = controlador.getJugador1();
        Jugador j2 = controlador.getJugador2();

        vista.mostrarMensaje("\n=== Juego terminado ===");
        vista.mostrarPuntajeFinal(j1.getNombre(), j1.getPuntaje());
        vista.mostrarPuntajeFinal(j2.getNombre(), j2.getPuntaje());

        if (j1.getPuntaje() > j2.getPuntaje()) {
            vista.mostrarMensaje("¡Ganador: " + j1.getNombre() + "!");
        } else if (j2.getPuntaje() > j1.getPuntaje()) {
            vista.mostrarMensaje("¡Ganador: " + j2.getNombre() + "!");
        } else {
            vista.mostrarMensaje("¡Empate!");
        }
    }
}
