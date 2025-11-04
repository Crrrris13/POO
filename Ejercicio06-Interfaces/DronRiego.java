import java.util.ArrayList;

public class DronRiego extends DispositivoAgricola 
                       implements Accionable, Registrable {
    private double capacidadTanque;
    private int autonomiaVuelo;
    private ArrayList<String> historialRegistros;
    
    public DronRiego(String id, String nombre, String fabricante, 
                     double consumoElectrico, String ubicacion, 
                     String fechaInstalacion, double capacidadTanque, 
                     int autonomiaVuelo) {
        super(id, nombre, fabricante, consumoElectrico, ubicacion, fechaInstalacion);
        this.capacidadTanque = capacidadTanque;
        this.autonomiaVuelo = autonomiaVuelo;
        this.historialRegistros = new ArrayList<>();
    }
    
    public double getCapacidadTanque() {
        return capacidadTanque;
    }
    
    public int getAutonomiaVuelo() {
        return autonomiaVuelo;
    }
    
    @Override
    public boolean ejecutarAccion(String accion) {
        boolean exito = false;
        switch (accion.toLowerCase()) {
            case "regar":
                historialRegistros.add("Riego completado - " + capacidadTanque + "L dispensados");
                exito = true;
                break;
            case "volar":
                historialRegistros.add("Vuelo iniciado - Autonomía: " + autonomiaVuelo + " min");
                exito = true;
                break;
            case "despegar":
                historialRegistros.add("Despegue exitoso");
                exito = true;
                break;
            case "aterrizar":
                historialRegistros.add("Aterrizaje completado");
                exito = true;
                break;
            default:
                exito = false;
        }
        return exito;
    }
    
    @Override
    public String[] getAccionesDisponibles() {
        return new String[]{"regar", "volar", "despegar", "aterrizar"};
    }
    
    @Override
    public String capturarRegistro() {
        String registro = "Misión completada - Tanque: " + capacidadTanque + "L";
        historialRegistros.add(registro);
        return registro;
    }
    
    @Override
    public String[] obtenerHistorialRegistros() {
        return historialRegistros.toArray(new String[0]);
    }
    
    @Override
    public String toString() {
        return super.toString() + String.format(" | Tanque: %.1fL | Autonomía: %d min", 
                                                capacidadTanque, autonomiaVuelo);
    }
}

