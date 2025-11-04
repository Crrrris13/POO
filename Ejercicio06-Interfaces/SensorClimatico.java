public class SensorClimatico extends DispositivoAgricola implements Medible {
    private String variableMedida;
    private String ultimaMedicion;
    
    public SensorClimatico(String id, String nombre, String fabricante, 
                           double consumoElectrico, String ubicacion, 
                           String fechaInstalacion, String variableMedida) {
        super(id, nombre, fabricante, consumoElectrico, ubicacion, fechaInstalacion);
        this.variableMedida = variableMedida;
        this.ultimaMedicion = variableMedida + ": " + (800 + Math.random() * 400);
    }
    
    public String getVariableMedida() {
        return variableMedida;
    }
    
    @Override
    public String getMedicion() {
        return ultimaMedicion;
    }
    
    @Override
    public String getTipoMedicion() {
        return variableMedida;
    }
    
    @Override
    public String toString() {
        return super.toString() + String.format(" | Variable: %s | %s", 
                                                variableMedida, ultimaMedicion);
    }
}