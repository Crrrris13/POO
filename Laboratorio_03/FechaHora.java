public class FechaHora {
    private final int dia;
    private final int mes;
    private final int anio;
    private final int hora;
    private final int minuto;

    public FechaHora(int dia, int mes, int anio, int hora, int minuto)
            throws EntradaInvalidaException {
        if (dia < 1 || dia > 31)
            throw new EntradaInvalidaException("Día inválido");
        if (mes < 1 || mes > 12)
            throw new EntradaInvalidaException("Mes inválido");
        if (anio < 2024)
            throw new EntradaInvalidaException("Año inválido");
        if (hora < 0 || hora > 23)
            throw new EntradaInvalidaException("Hora inválida");
        if (minuto < 0 || minuto > 59)
            throw new EntradaInvalidaException("Minuto inválido");

        this.dia = dia;
        this.mes = mes;
        this.anio = anio;
        this.hora = hora;
        this.minuto = minuto;
    }

    // Getters
    public int getDia() {
        return dia;
    }

    public int getMes() {
        return mes;
    }

    public int getAnio() {
        return anio;
    }

    public int getHora() {
        return hora;
    }

    public int getMinuto() {
        return minuto;
    }

    @Override
    public String toString() {
        return String.format("%02d/%02d/%d %02d:%02d", dia, mes, anio, hora, minuto);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof FechaHora)) return false;
        FechaHora f = (FechaHora) o;
        return this.dia == f.dia && this.mes == f.mes && this.anio == f.anio &&
                this.hora == f.hora && this.minuto == f.minuto;
    }

    @Override
    public int hashCode() {
        return (anio * 10000 + mes * 100 + dia) * 10000 + (hora * 100 + minuto);
    }

    //Compara si esta fecha es antes que otra
    public boolean esAntesDe(FechaHora otra) {
        if (this.anio != otra.anio) return this.anio < otra.anio;
        if (this.mes != otra.mes) return this.mes < otra.mes;
        if (this.dia != otra.dia) return this.dia < otra.dia;
        if (this.hora != otra.hora) return this.hora < otra.hora;
        return this.minuto < otra.minuto;
    }

    //Compara si esta fecha es después que otra
    public boolean esDespuesDe(FechaHora otra) {
        return !esAntesDe(otra) && !equals(otra);
    }
}