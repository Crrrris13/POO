import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        VistaConsola vista = new VistaConsola();
        int op = vista.mostrarMenuPrincipal();
        if (op == 2) return;

        String nombre = vista.pedirNombreJugador();
        int tipo = vista.pedirTipoJugador();

        Jugador jugador = (tipo == 1) ? new Guerrero(nombre) : new Explorador(nombre);
        // items de ejemplo
        jugador.getInventario().agregar(new PocionVida(25));
        jugador.getInventario().agregar(new IncrementoAtaque(4, 2));
        jugador.getInventario().agregar(new CuraEnArea(15));

        // enemigos de ejemplo
        List<Enemigo> enemigos = new ArrayList<>();
        enemigos.add(new Wolf("Lobo"));
        enemigos.add(new Faker("Faker"));
        enemigos.add(new Ares("Ares"));

        Batalla batalla = new Batalla(jugador, enemigos);
        ControladorBatalla ctrl = new ControladorBatalla(batalla, vista);
        ctrl.ejecutar();
    }
}
