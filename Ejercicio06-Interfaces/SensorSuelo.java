import java.util.ArrayList;

// ==========================================
// CLASE: SensorSuelo
// Implementa: Medible
// ==========================================
public class SensorSuelo extends DispositivoAgricola implements Medible {
    private double profundidad;
    private String ultimaMedicion;
    
    public SensorSuelo(String id, String nombre, String fabricante, 
                       double consumoElectrico, String ubicacion, 
                       String fechaInstalacion, double profundidad) {
        super(id, nombre, fabricante, consumoElectrico, ubicacion, fechaInstalacion);
        this.profundidad = profundidad;
        this.ultimaMedicion = "Humedad: " + (Math.random() * 100) + "%";
    }
    
    public double getProfundidad() {
        return profundidad;
    }
    
    @Override
    public String getMedicion() {
        return ultimaMedicion;
    }
    
    @Override
    public String getTipoMedicion() {
        return "Humedad del suelo";
    }
    
    @Override
    public String toString() {
        return super.toString() + String.format(" | Profundidad: %.1f cm | %s", 
                                                profundidad, ultimaMedicion);
    }
}