import java.util.List;

public class CuraEnArea extends Item {
    private int cantidad;

    public CuraEnArea(int cantidad) {
        super("Cura en Área", "Cura a múltiples objetivos", 1);
        this.cantidad = Math.max(1, cantidad);
    }

    @Override
    public void usar(Jugador origen, List<Combatiente> objetivos, Batalla batalla) {
        if (usosRestantes <= 0 || objetivos == null || objetivos.isEmpty()) return;
        for (Combatiente c : objetivos) {
            if (c != null && c.estaVivo()) {
                c.vida = Math.min(c.vidaMax, c.vida + cantidad);
            }
        }
        decrementarUso();
    }
}
