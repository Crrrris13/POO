import java.util.ArrayList;

public class EstacionMeteorologica extends DispositivoAgricola 
                                   implements Medible, Registrable {
    private double alturaInstalacion;
    private String ultimaMedicion;
    private ArrayList<String> historialRegistros;
    
    public EstacionMeteorologica(String id, String nombre, String fabricante, 
                                 double consumoElectrico, String ubicacion, 
                                 String fechaInstalacion, double alturaInstalacion) {
        super(id, nombre, fabricante, consumoElectrico, ubicacion, fechaInstalacion);
        this.alturaInstalacion = alturaInstalacion;
        this.ultimaMedicion = String.format("Temp: %.1f°C, Humedad: %.1f%%", 
                                           20 + Math.random() * 15, 
                                           40 + Math.random() * 40);
        this.historialRegistros = new ArrayList<>();
        historialRegistros.add("Registro inicial: " + ultimaMedicion);
    }
    
    public double getAlturaInstalacion() {
        return alturaInstalacion;
    }
    
    @Override
    public String getMedicion() {
        return ultimaMedicion;
    }
    
    @Override
    public String getTipoMedicion() {
        return "Temperatura y humedad ambiental";
    }
    
    @Override
    public String capturarRegistro() {
        String nuevoRegistro = "Registro " + (historialRegistros.size() + 1) + ": " + ultimaMedicion;
        historialRegistros.add(nuevoRegistro);
        return nuevoRegistro;
    }
    
    @Override
    public String[] obtenerHistorialRegistros() {
        return historialRegistros.toArray(new String[0]);
    }
    
    @Override
    public String toString() {
        return super.toString() + String.format(" | Altura: %.1f m | %s", 
                                                alturaInstalacion, ultimaMedicion);
    }
}