import java.util.List;
import java.util.ArrayList;

public abstract class Medico {
    private final String idEmpleado;
    private String nombre;
    private String departamento;
    private double salarioBase;
    private final ArrayList<Cita> citasAsignadas;

    //Constructor
    public Medico(String idEmpleado, String nombre, String departamento, double salarioBase)
            throws EntradaInvalidaException {
        if (idEmpleado == null || idEmpleado.trim().isEmpty())
            throw new EntradaInvalidaException("ID vacío");
        if (nombre == null || nombre.trim().isEmpty())
            throw new EntradaInvalidaException("Nombre vacío");
        if (departamento == null || departamento.trim().isEmpty())
            throw new EntradaInvalidaException("Departamento vacío");
        if (salarioBase < 0)
            throw new EntradaInvalidaException("Salario base negativo");

        this.idEmpleado = idEmpleado.trim();
        this.nombre = nombre.trim();
        this.departamento = departamento.trim();
        this.salarioBase = salarioBase;
        this.citasAsignadas = new ArrayList<Cita>();
    }

    // Getters
    public String getIdEmpleado() {
        return this.idEmpleado;
    }

    public String getNombre() {
        return this.nombre;
    }

    public String getDepartamento() {
        return this.departamento;
    }
    public double getSalarioBase() {
        return this.salarioBase;
    }

    // Exponer copia para proteger encapsulación
    public List<Cita> getCitasAsignadas() {
        return new ArrayList<Cita>(citasAsignadas);
    }

    // Reglas de agenda (choques se validan en la clase Cita/HospitalManager)
    public void asignarCita(Cita c) throws EntradaInvalidaException {
        if (c == null) throw new EntradaInvalidaException("Cita nula");
        this.citasAsignadas.add(c);
    }

    public void removerCita(Cita c) {
        this.citasAsignadas.remove(c);
    }

    // Polimorfismo: cada subclase define su cálculo
    public abstract double calcularSalario();

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "id='" + idEmpleado + '\'' +
                ", nombre='" + nombre + '\'' +
                ", depto='" + departamento + '\'' +
                ", base=" + salarioBase +
                '}';
    }

    //Se define cuando dos objetos se consideran iguales con idMedico
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Medico)) return false;
        Medico m = (Medico) o;
        return this.idEmpleado.equals(m.idEmpleado);
    }

    //Se usa para evitar duplicados y buscar rapido
    @Override
    public int hashCode() {
        return idEmpleado.hashCode();
    }
}
