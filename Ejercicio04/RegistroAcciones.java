import java.util.ArrayList;
import java.util.List;

public class RegistroAcciones {
    private final List<String> ultimas = new ArrayList<>();
    private final int limite;

    public RegistroAcciones() { this(3); }

    public RegistroAcciones(int limite) {
        this.limite = Math.max(1, limite);
    }

    public void registrar(String mensaje) {
        if (mensaje == null || mensaje.isBlank()) return;
        // Insertar al inicio (más reciente primero)
        ultimas.add(0, mensaje);
        // Recortar si excede el límite
        while (ultimas.size() > limite) {
            ultimas.remove(ultimas.size() - 1);
        }
    }

    public List<String> ultimasN(int n) {
        int k = Math.max(0, Math.min(n, ultimas.size()));
        // Devolver una copia inmutable de las k primeras (recientes)
        return new ArrayList<>(ultimas.subList(0, k));
    }
}
