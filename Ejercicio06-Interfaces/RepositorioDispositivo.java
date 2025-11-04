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
    
}

