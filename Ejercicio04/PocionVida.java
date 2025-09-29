import java.util.List;

public class PocionVida extends Item {
    private int cantidad;

    public PocionVida(int cantidad) {
        super("Poción de Vida", "Restaura vida a un objetivo", 1);
        this.cantidad = Math.max(1, cantidad);
    }

    @Override
    public void usar(Jugador origen, List<Combatiente> objetivos, Batalla batalla) {
        if (usosRestantes <= 0 || objetivos == null || objetivos.isEmpty()) return;
        Combatiente c = objetivos.get(0);
        if (c != null && c.estaVivo()) {
            // curación simple: sumar y cap a vidaMax
            int nueva = Math.min(c.vidaMax, c.vida + cantidad);
            c.vida = nueva;
            decrementarUso();
        }
    }
}
