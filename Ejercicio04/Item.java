import java.util.List;

public abstract class Item {
    protected String nombre;
    protected String descripcion;
    protected int usosRestantes;

    public Item(String nombre, String descripcion, int usos) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.usosRestantes = Math.max(0, usos);
    }

    public abstract void usar(Jugador origen, List<Combatiente> objetivos, Batalla batalla);

    public int getUsosRestantes() { return usosRestantes; }

    protected void decrementarUso() {
        if (usosRestantes > 0) usosRestantes--;
    }
}
