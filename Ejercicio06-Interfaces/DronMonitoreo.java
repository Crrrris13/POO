import java.util.ArrayList;

public class DronMonitoreo extends DispositivoAgricola 
                           implements Medible, Accionable, Registrable {
    private String resolucionCamara;
    private String tiposSensores;
    private ArrayList<String> historialRegistros;
    private String ultimaMedicion;
    
    public DronMonitoreo(String id, String nombre, String fabricante, 
                         double consumoElectrico, String ubicacion, 
                         String fechaInstalacion, String resolucionCamara, 
                         String tiposSensores) {
        super(id, nombre, fabricante, consumoElectrico, ubicacion, fechaInstalacion);
        this.resolucionCamara = resolucionCamara;
        this.tiposSensores = tiposSensores;
        this.historialRegistros = new ArrayList<>();
        this.ultimaMedicion = "NDVI: 0.75, Temp superficial: 28°C";
    }
    
    public String getResolucionCamara() {
        return resolucionCamara;
    }
    
    public String getTiposSensores() {
        return tiposSensores;
    }
    
    @Override
    public String getMedicion() {
        return ultimaMedicion;
    }
    
    @Override
    public String getTipoMedicion() {
        return "Análisis multiespectral y térmico";
    }
    
    @Override
    public boolean ejecutarAccion(String accion) {
        boolean exito = false;
        switch (accion.toLowerCase()) {
            case "capturar_imagen":
                historialRegistros.add("Imagen capturada - " + resolucionCamara);
                exito = true;
                break;
            case "escanear_parcela":
                historialRegistros.add("Escaneo completo - Sensores: " + tiposSensores);
                exito = true;
                break;
            case "volar":
                historialRegistros.add("Vuelo de monitoreo iniciado");
                exito = true;
                break;
            default:
                exito = false;
        }
        return exito;
    }
    
    @Override
    public String[] getAccionesDisponibles() {
        return new String[]{"capturar_imagen", "escanear_parcela", "volar"};
    }
    
    @Override
    public String capturarRegistro() {
        String registro = "Registro multiespectral - " + ultimaMedicion;
        historialRegistros.add(registro);
        return registro;
    }
    
    @Override
    public String[] obtenerHistorialRegistros() {
        return historialRegistros.toArray(new String[0]);
    }
    
    @Override
    public String toString() {
        return super.toString() + String.format(" | Cámara: %s | Sensores: %s", 
                                                resolucionCamara, tiposSensores);
    }
}
