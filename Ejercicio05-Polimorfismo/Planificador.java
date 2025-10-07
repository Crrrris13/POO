import java.util.ArrayList;
import java.util.List;

public class Planificador {
    private final List<Proceso> procesos;

    public Planificador() {
        this.procesos = new ArrayList<>();
    }

    public void registrar(Proceso proceso) {
        this.procesos.add(proceso);
    }


    public void registrar(Proceso... procesos) {
        for (Proceso proceso : procesos) {
            this.procesos.add(proceso);
        }
    }

    public void ejecutar(Proceso proceso) {
        proceso.ejecutar();
    }

    public void ejecutarTodos() {
        for (Proceso proceso : procesos) {
            proceso.ejecutar();
        }
    }

    public Proceso buscar(int pid) {
        for (Proceso proceso : procesos) {
            if (proceso.getPid() == pid) {
                return proceso; // Encontrado
            }
        }
        return null; // No encontrado
    }

    public List<Proceso> buscar(String nombre) {
        List<Proceso> res = new ArrayList<>();
        if (nombre == null) {
            return res;
            // Devuelve una lista vacía si el nombre es null
        }
        String q = nombre.toLowerCase();
        for (Proceso proceso : procesos) {
            if (proceso.getNombre() != null && proceso.getNombre().toLowerCase().contains(q)) {
                res.add(proceso);
                // Añade el proceso a la lista de resultados
            }
        }
        return res;
        // Devuelve una lista vacía si no se encuentra ninguno
    }

    // Devuelve una copia de la lista de procesos para evitar modificaciones externas
    public List<Proceso> getProcesos() {
        return new ArrayList<>(procesos);
    }
}