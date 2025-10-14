import java.util.Scanner;

/**
 * Vista del sistema - Vista en MVC
 * Maneja la interacción con el usuario
 * @author [Tu nombre]
 */
public class Vista {
    private final Scanner scanner;

    public Vista() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Muestra el menú principal
     */
    public void mostrarMenu() {
        System.out.println("\n" + "╔" + "=".repeat(78) + "╗");
        System.out.println("║" + centrar("SISTEMA DE GESTIÓN HOSPITALARIA", 78) + "║");
        System.out.println("╚" + "=".repeat(78) + "╝");
        System.out.println(" 1.  Registrar Médico");
        System.out.println(" 2.  Programar Cita");
        System.out.println(" 3.  Reagendar Cita");
        System.out.println(" 4.  Cambiar Estado de Cita");
        System.out.println(" 5.  Buscar Personal Disponible");
        System.out.println(" 6.  Reporte de Personal");
        System.out.println(" 7.  Reporte de Citas por Estado");
        System.out.println(" 8.  Cálculo de Nómina por Departamento");
        System.out.println(" 9.  Historial de Cambios de Cita");
        System.out.println("10.  Registrar Actividad de Médico");
        System.out.println(" 0.  Salir");
        System.out.println("─".repeat(80));
    }

    /**
     * Submenú para tipo de médico
     */
    public int mostrarMenuTipoMedico() {
        System.out.println("\n┌" + "─".repeat(48) + "┐");
        System.out.println("│" + centrar("TIPO DE MÉDICO A REGISTRAR", 48) + "│");
        System.out.println("└" + "─".repeat(48) + "┘");
        System.out.println("1. Doctor General");
        System.out.println("2. Cirujano");
        System.out.println("3. Enfermero/a");
        System.out.println("4. Radiólogo");
        System.out.println("5. Farmacéutico");
        System.out.println("6. Terapeuta");
        System.out.print("Opción: ");
        return leerEntero();
    }

    /**
     * Centra un texto en un ancho dado
     */
    private String centrar(String texto, int ancho) {
        int espacios = (ancho - texto.length()) / 2;
        return " ".repeat(Math.max(0, espacios)) + texto +
                " ".repeat(Math.max(0, ancho - texto.length() - espacios));
    }

    /**
     * Muestra mensaje
     */
    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    /**
     * Muestra error
     */
    public void mostrarError(String error) {
        System.out.println("ERROR: " + error);
    }

    /**
     * Muestra éxito
     */
    public void mostrarExito(String mensaje) {
        System.out.println(mensaje);
    }

    /**
     * Lee string del usuario
     */
    public String leerString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    /**
     * Lee entero con validación
     */
    public int leerEntero() {
        while (true) {
            try {
                String input = scanner.nextLine();
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.print("Entrada inválida. Ingrese un número: ");
            }
        }
    }

    /**
     * Lee entero con prompt
     */
    public int leerEntero(String prompt) {
        System.out.print(prompt);
        return leerEntero();
    }

    /**
     * Lee double con validación
     */
    public double leerDouble() {
        while (true) {
            try {
                String input = scanner.nextLine();
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.print("Entrada inválida. Ingrese un número: ");
            }
        }
    }

    /**
     * Lee double con prompt
     */
    public double leerDouble(String prompt) {
        System.out.print(prompt);
        return leerDouble();
    }

    /**
     * Lee boolean
     */
    public boolean leerBoolean(String prompt) {
        System.out.print(prompt + " (si/no): ");
        String respuesta = scanner.nextLine().toLowerCase();
        return respuesta.equals("si") || respuesta.equals("s") ||
                respuesta.equals("yes") || respuesta.equals("y");
    }

    /**
     * Selecciona TipoCita
     */
    public TipoCita seleccionarTipoCita() {
        System.out.println("\nTipos de Cita:");
        System.out.println("1. CONSULTA_GENERAL");
        System.out.println("2. CIRUGIA");
        System.out.println("3. TERAPIA");
        System.out.println("4. DIAGNOSTICO");
        System.out.print("Seleccione: ");
        int opcion = leerEntero();

        switch (opcion) {
            case 1: return TipoCita.CONSULTA_GENERAL;
            case 2: return TipoCita.CIRUGIA;
            case 3: return TipoCita.TERAPIA;
            case 4: return TipoCita.DIAGNOSTICO;
            default: return TipoCita.CONSULTA_GENERAL;
        }
    }

    /**
     * Selecciona EstadoCita
     */
    public EstadoCita seleccionarEstadoCita() {
        System.out.println("\nEstados de Cita:");
        System.out.println("1. PROGRAMADA");
        System.out.println("2. CONFIRMADA");
        System.out.println("3. EN_PROCESO");
        System.out.println("4. COMPLETADA");
        System.out.println("5. CANCELADA");
        System.out.println("6. REAGENDADA");
        System.out.print("Seleccione: ");
        int opcion = leerEntero();

        switch (opcion) {
            case 1: return EstadoCita.PROGRAMADA;
            case 2: return EstadoCita.CONFIRMADA;
            case 3: return EstadoCita.EN_PROCESO;
            case 4: return EstadoCita.COMPLETADA;
            case 5: return EstadoCita.CANCELADA;
            case 6: return EstadoCita.REAGENDADA;
            default: return EstadoCita.PROGRAMADA;
        }
    }

    /**
     * Selecciona Turno
     */
    public Turno seleccionarTurno() {
        System.out.println("\nTurnos:");
        System.out.println("1. DIURNO");
        System.out.println("2. NOCTURNO");
        System.out.print("Seleccione: ");
        int opcion = leerEntero();
        return (opcion == 2) ? Turno.NOCTURNO : Turno.DIURNO;
    }

    /**
     * Lee fecha y hora del usuario
     */
    public FechaHora leerFechaHora() throws EntradaInvalidaException {
        System.out.println("\nIngrese fecha y hora:");
        int dia = leerEntero("Día (1-31): ");
        int mes = leerEntero("Mes (1-12): ");
        int anio = leerEntero("Año: ");
        int hora = leerEntero("Hora (0-23): ");
        int minuto = leerEntero("Minuto (0-59): ");


        return new FechaHora(dia, mes, anio, hora, minuto);
    }

    /**
     * Retorna fecha actual (simulada)
     */
    public FechaHora obtenerFechaActual() {
        try {
            return new FechaHora(13, 10, 2025, 12, 0);
        } catch (EntradaInvalidaException e) {
            // No debería pasar con valores fijos válidos
            return null;
        }
    }

    /**
     * Muestra separador
     */
    public void mostrarSeparador() {
        System.out.println("─".repeat(80));
    }

    /**
     * Pausa hasta Enter
     */
    public void pausar() {
        System.out.print("\nPresione ENTER para continuar...");
        scanner.nextLine();
    }

    /**
     * Cierra el scanner
     */
    public void cerrar() {
        scanner.close();
    }
}