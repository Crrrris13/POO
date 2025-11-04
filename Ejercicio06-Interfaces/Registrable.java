public interface Registrable {
    //Captura un nuevo registro en el momento actual y lo guarda
    String capturarRegistro();

    //Obtiene el historial completo de los registros almacenados
    String[] obtenerHistorialRegistros();
}