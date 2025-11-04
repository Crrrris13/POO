import java.util.Collections;
import java.util.ArrayList;

public class RepositorioDispositivo {
    private ArrayList<DispositivoAgricola> catalogo;

    public RepositorioDispositivo() {
        this.catalogo = new ArrayList<>();
    }

    public void agregar(DispositivoAgricola dispositivo) {
        if (dispositivo != null) {
            catalogo.add(dispositivo);
        }
    }
    
    public ArrayList<DispositivoAgricola> listar() {
        return new ArrayList<>(catalogo);
    }

    public DispositivoAgricola buscarPorId(String id) {
        for (DispositivoAgricola dispositivo : catalogo) {
            if (dispositivo.getId.Equals(id)) {
                return dispositivo;
            }
        }

        return null;
    }

    public ArrayList<DispositivoAgricola> buscarPorNombre(String nombre) {
        ArrayList<DispositivoAgricola> resultados = new ArrayList<>();

        String nombreBusqueda = nombre.toLowerCase();

        for (DispositivoAgricola dispositivo : catalogo) {
            if (dispositivo.getNombre.toLowerCase().contains(nombreBusqueda)) {
                resultados.add(dispositivo);
            }
        }

        return resultados;
    }

    public ArrayList<DispositivoAgricola> ordenarPorConsumoAsc() {
        ArrayList<DispositivoAgricola> listaOrdenada = new ArrayList<>(catalogo);
        
        Collections.sort(listaOrdenada);

        return listaOrdenada;
    }

    public ArrayList<DispositivoAgricola> ordenarPorConsumoDesc() {
        // Crear copia
        ArrayList<DispositivoAgricola> listaOrdenada = new ArrayList<>(catalogo);
        
        // Ordenar en orden INVERSO (mayor a menor)
        Collections.sort(listaOrdenada, Collections.reverseOrder());
        
        return listaOrdenada;
    }
    
}

