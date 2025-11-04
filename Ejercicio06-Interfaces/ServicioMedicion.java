public class ServicioMedicion {
    public String obtenerMedicionDe(Medible medible) {
        if (medible == null) {
            return "Dispositivo no valido";
        }
        
        // Llamar al método obtenerMedicion() del dispositivo
        // Gracias al polimorfismo, se ejecuta la versión correcta
        // según el tipo real del objeto
        return medible.getMedicion();
    }

    public String tipoDeMedicion(Medible medible) {
        if (medible == null) {
            return "Dispositivo no valido";
        }

        return medible.getTipoMedicion();
    }

}
