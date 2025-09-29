import java.util.List;

public interface VistaBatalla {
    void mostrarEstado(Batalla b);
    void mostrarRegistro(RegistroAcciones reg);
    AccionJugador pedirAccion(Jugador j, Batalla b);
    List<Combatiente> pedirObjetivos(List<Combatiente> candidatos, int cantidad);
    Item pedirItem(Inventario inventario);
}
