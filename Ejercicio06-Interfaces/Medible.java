public interface Medible {
    //Obtiene la ultima medicion registrada por el dispositivo
    String obtener medicion();

    //Obtiene el tipo de medicion que realiza este dispositivo y permite ver que se esta midiendo
    String getTipoMedicion();

}