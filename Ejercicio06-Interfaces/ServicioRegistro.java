public class ServicioRegistro {
    public String capturar(Registrable registrable) {
        if (registrable == null) {
            return "Dispositivo no valido";
        }

        return registrable.capturarRegistro();
    }

    public String[] historial(Registrable registrable) {
        if (registrable == null) {
            return new String[0]; //Array vacio pero no null
        }
        return registrable.obtenerHistorialRegistros()
    }
}
