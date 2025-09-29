import java.util.ArrayList;
import java.util.List;

public class Inventario {
    private final List<Item> items = new ArrayList<>();
    private final int capacidadMax;

    public Inventario(int capacidadMax) {
        this.capacidadMax = Math.max(1, capacidadMax);
    }

    public boolean agregar(Item item) {
        if (item == null) return false;
        if (items.size() >= capacidadMax) return false;
        return items.add(item);
    }

    public boolean remover(Item item) { return items.remove(item); }

    public List<Item> listar() { return new ArrayList<>(items); }

    public boolean tiene(Item item) { return items.contains(item); }
}
