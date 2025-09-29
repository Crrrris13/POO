import java.util.ArrayList;
import java.util.List;

public class AccionJugador {
    private final AccionJugadorTipo tipo;
    private final Item item;
    private final List<Combatiente> objetivos;

    public AccionJugador(AccionJugadorTipo tipo, Item item, List<Combatiente> objetivos) {
        this.tipo = tipo;
        this.item = item;
        this.objetivos = (objetivos == null) ? new ArrayList<>() : objetivos;
    }

    public AccionJugadorTipo getTipo() { return tipo; }
    public Item getItem() { return item; }
    public List<Combatiente> getObjetivos() { return objetivos; }
}
