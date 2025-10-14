import java.util.ArrayList;
import java.util.List;

public class Radiologo extends Medico {
    private final ArrayList<String> equiposCertificados;
    private double tarifaPorEstudio;
    private int estudiosRealizados;

    public Radiologo(String idEmpleado, String nombre, String departamento, double salarioBase, double tarifaPorEstudio)
            throws EntradaInvalidaException {
        super(idEmpleado, nombre, departamento, salarioBase);

        if (tarifaPorEstudio < 0)
            throw new EntradaInvalidaException("Tarifa negativa");

        this.equiposCertificados = new ArrayList<>();
        this.tarifaPorEstudio = tarifaPorEstudio;
        this.estudiosRealizados = 0;
    }

    //Se calcula el salario
    @Override
    public double calcularSalario() {
        return getSalarioBase() + (estudiosRealizados * tarifaPorEstudio);
    }

    public void agregarEquipoCertificado(String equipo) throws EntradaInvalidaException {
        if (equipo == null || equipo.trim().isEmpty())
            throw new EntradaInvalidaException("Equipo vacío");
        if (!equiposCertificados.contains(equipo.trim())) {
            equiposCertificados.add(equipo.trim());
        }
    }

    public void registrarEstudio() {
        this.estudiosRealizados++;
    }

    public void resetearEstudios() {
        this.estudiosRealizados = 0;
    }

    // Getters
    public List<String> getEquiposCertificados() {
        return new ArrayList<>(equiposCertificados);
    }

    public double getTarifaPorEstudio() {
        return tarifaPorEstudio;
    }

    public int getEstudiosRealizados() {
        return estudiosRealizados;
    }

    // Setters
    public void setTarifaPorEstudio(double tarifaPorEstudio)
            throws EntradaInvalidaException {
        if (tarifaPorEstudio < 0)
            throw new EntradaInvalidaException("Tarifa negativa");
        this.tarifaPorEstudio = tarifaPorEstudio;
    }

    @Override
    public String toString() {
        return "Radiologo{" +
                "id='" + getIdEmpleado() + "', " +
                "nombre='" + getNombre() + "', " +
                "equipos=" + equiposCertificados.size() + ", " +
                "tarifa=Q" + String.format("%.2f", tarifaPorEstudio) + ", " +
                "estudios=" + estudiosRealizados + ", " +
                "salarioTotal=Q" + String.format("%.2f", calcularSalario()) +
                "}";
    }
}