public class Guerrero extends Jugador {
    public Guerrero(String nombre) {
        super(nombre, 120, 15); // puedes ajustar stats
    }

    @Override
    public void tomarTurno(Batalla batalla) {
        // TODO: decidir acción por IA o dejar que Controlador pida por Vista
    }
}
