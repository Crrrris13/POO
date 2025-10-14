import java.util.ArrayList;
import java.util.List;

public class Cirujano extends Medico {
    private final ArrayList<String> tiposOperaciones;
    private int horasCirugiaPeriodo;
    private double tarifaHoraCirugia;
    private double bonoPorRiesgo;

    public Cirujano(String idEmpleado, String nombre, String departamento, double salarioBase, double tarifaHoraCirugia,double bonoPorRiesgo)
            throws EntradaInvalidaException {
        super(idEmpleado, nombre, departamento, salarioBase);

        if (tarifaHoraCirugia < 0)
            throw new EntradaInvalidaException("Tarifa por hora negativa");
        if (bonoPorRiesgo < 0)
            throw new EntradaInvalidaException("Bono negativo");

        this.tiposOperaciones = new ArrayList<>();
        this.horasCirugiaPeriodo = 0;
        this.tarifaHoraCirugia = tarifaHoraCirugia;
        this.bonoPorRiesgo = bonoPorRiesgo;
    }

    //Se calcula salario
    @Override
    public double calcularSalario() {
        return getSalarioBase() + (horasCirugiaPeriodo * tarifaHoraCirugia) + bonoPorRiesgo;
    }

    public void agregarTipoOperacion(String tipo) throws EntradaInvalidaException {
        if (tipo == null || tipo.trim().isEmpty())
            throw new EntradaInvalidaException("Tipo de operación vacío");
        if (!tiposOperaciones.contains(tipo.trim())) {
            tiposOperaciones.add(tipo.trim());
        }
    }

    public void registrarHorasCirugia(int horas) throws EntradaInvalidaException {
        if (horas < 0)
            throw new EntradaInvalidaException("Horas negativas");
        this.horasCirugiaPeriodo += horas;
    }

    public void resetearHoras() {
        this.horasCirugiaPeriodo = 0;
    }

    // Getters
    public List<String> getTiposOperaciones() {
        return new ArrayList<>(tiposOperaciones);
    }

    public int getHorasCirugiaPeriodo() {
        return horasCirugiaPeriodo;
    }

    public double getTarifaHoraCirugia() {
        return tarifaHoraCirugia;
    }

    public double getBonoPorRiesgo() {
        return bonoPorRiesgo;
    }

    // Setters
    public void setTarifaHoraCirugia(double tarifaHoraCirugia)
            throws EntradaInvalidaException {
        if (tarifaHoraCirugia < 0)
            throw new EntradaInvalidaException("Tarifa negativa");
        this.tarifaHoraCirugia = tarifaHoraCirugia;
    }

    public void setBonoPorRiesgo(double bonoPorRiesgo) throws EntradaInvalidaException {
        if (bonoPorRiesgo < 0)
            throw new EntradaInvalidaException("Bono negativo");
        this.bonoPorRiesgo = bonoPorRiesgo;
    }

    @Override
    public String toString() {
        return "Cirujano{" +
                "id='" + getIdEmpleado() + "', " +
                "nombre='" + getNombre() + "', " +
                "operaciones=" + tiposOperaciones.size() + ", " +
                "horas=" + horasCirugiaPeriodo + ", " +
                "tarifa/h=Q" + String.format("%.2f", tarifaHoraCirugia) + ", " +
                "bono=Q" + String.format("%.2f", bonoPorRiesgo) + ", " +
                "salarioTotal=Q" + String.format("%.2f", calcularSalario()) +
                "}";
    }
}