import java.util.List;
import java.util.Scanner;

public class Main {
    private final Scanner scanner = new Scanner(System.in);
    private final ControladorProceso controlador;

    // Constructor que inicializa el controlador de procesos con un planificador
    public Main() {
        this.controlador = new ControladorProceso(new Planificador());
    }

    public static void main(String[] args) {
        Main app = new Main();
        app.mostrarMenu();
    }

    public void mostrarMenu() {
        int opcion;
        boolean salir = false;
        while (salir == false) {
            mostrarOpciones();
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer


                switch (opcion) {
                    case 1:
                        registrarProceso();
                        break;
                    case 2:
                        registrarProcesoDetallado();
                        break;
                    case 3:
                        listarProcesos();
                        break;
                    case 4:
                        ejecutarProcesoPorPid();
                        break;
                    case 5:
                        ejecutarTodos();
                        break;
                    case 0:
                        System.out.println("Saliendo del programa.");
                        salir = true;
                        break;
                    default:
                        System.out.println("Opción no válida. Intente de nuevo.");
                }
        }
    }

    private void mostrarOpciones() {
        System.out.println("\n--- Menú de Gestión de Procesos ---");
        System.out.println("1. Registrar Proceso (básico)");
        System.out.println("2. Registrar Proceso (detallado)");
        System.out.println("3. Listar Procesos");
        System.out.println("4. Ejecutar Proceso por PID");
        System.out.println("5. Ejecutar Todos los Procesos");
        System.out.println("0. Salir");
        System.out.print("Seleccione una opción: ");
    }

    private void registrarProceso() {
        System.out.print("Ingrese PID: ");
        int pid = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer

        System.out.print("Ingrese Nombre del Proceso: ");
        String nombre = scanner.nextLine();

        System.out.println("Seleccione el tipo de proceso (1. CPU, 2. IO, 3. Daemon): ");
        int opcionTipo = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer

        TipoProceso tipo;
        if (opcionTipo == 1) {
            tipo = TipoProceso.CPU;
        } else if (opcionTipo == 2) {
            tipo = TipoProceso.IO;
        } else if (opcionTipo == 3) {
            tipo = TipoProceso.DAEMON;
        } else {
            System.out.println("Tipo de proceso no válido.");
            return;
        }

        // Crear el proceso con la fábrica y registrarlo
        Proceso nuevo = controlador.getFabrica().crear(tipo, pid, nombre);
        controlador.registrarProceso(nuevo);

        System.out.println("Proceso registrado exitosamente.");
    }


    private void registrarProcesoDetallado() {
        System.out.print("Ingrese PID: ");
        int pid = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer

        System.out.print("Ingrese Nombre del Proceso: ");
        String nombre = scanner.nextLine();

        System.out.println("Seleccione el tipo de proceso (1. CPU, 2. IO, 3. Daemon): ");
        int tipo = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer

        switch (tipo) {
            case 1: // CPU
                System.out.print("Ingrese ciclos necesarios: ");
                int ciclos = scanner.nextInt();
                scanner.nextLine();
                Proceso cpu = new ProcesoCPU(pid, nombre, ciclos);
                controlador.registrarProceso(cpu);
                break;

            case 2: // IO
                System.out.print("Seleccione dispositivo (1. TECLADO, 2. PANTALLA, 3. RED): ");
                int disp = scanner.nextInt();
                DispositivoIO dispositivo;
                if (disp == 1) dispositivo = DispositivoIO.TECLADO;
                else if (disp == 2) dispositivo = DispositivoIO.PANTALLA;
                else dispositivo = DispositivoIO.RED;

                System.out.print("Ingrese bytes a procesar: ");
                int bytes = scanner.nextInt();
                scanner.nextLine();

                Proceso io = new ProcesoIO(pid, nombre, dispositivo, bytes);
                controlador.registrarProceso(io);
                break;

            case 3: // Daemon
                System.out.print("Ingrese servicio del Daemon: ");
                String servicio = scanner.nextLine();
                System.out.print("¿Está activo? (true/false): ");
                boolean activo = scanner.nextBoolean();
                scanner.nextLine();

                Proceso daemon = new Daemon(pid, nombre, servicio, activo);
                controlador.registrarProceso(daemon);
                break;

            default:
                System.out.println("Tipo de proceso no válido.");
                return;
        }


        System.out.println("Proceso registrado exitosamente.");
    }

    private void listarProcesos() {
        System.out.println("\n--- Lista de Procesos ---");
        List<Proceso> lista = controlador.listar();

        if (lista.isEmpty()) {
            System.out.println("No hay procesos registrados.");
        } else {
            for (Proceso proceso : lista) {
                System.out.println(proceso);
            }
        }
    }

    private void ejecutarProcesoPorPid() {
        System.out.print("Ingrese PID del proceso a ejecutar: ");
        int pid = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer

        controlador.ejecutarPorPid(pid);

    }

    private void ejecutarTodos() {
        controlador.ejecutarTodos();
    }
}