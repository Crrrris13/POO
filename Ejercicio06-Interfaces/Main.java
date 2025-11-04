import java.util.ArrayList;
import java.util.Scanner;
import java.util.InputMismatchException;

public class Main {
    
    // ==========================================
    // ATRIBUTOS
    // ==========================================
    private GestorDispositivos gestor;
    private Scanner scanner;
    
    // ==========================================
    // CONSTRUCTOR
    // ==========================================
    
    public Main() {
        this.gestor = new GestorDispositivos();
        this.scanner = new Scanner(System.in);
    }
    
    // ==========================================
    // MÉTODO MAIN - PUNTO DE ENTRADA
    // ==========================================

    public static void main(String[] args) {
        // Crear instancia de la aplicación
        Main app = new Main();
        
        // Iniciar la aplicación
        app.iniciar();
        
        // Cuando iniciar() termina, el programa finaliza
    }
    
    // ==========================================
    // MÉTODO: INICIAR APLICACIÓN
    // ==========================================

    public void iniciar() {
        // ==========================================
        // PASO 1: BANNER DE BIENVENIDA
        // ==========================================
        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║  SISTEMA DE GESTIÓN DE DISPOSITIVOS AGRO-TECNOLÓGICOS     ║");
        System.out.println("║  Cooperativa Agro-Tecnológica                              ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝");
        System.out.println();
        System.out.println("Inicializando sistema...");
        
        // ==========================================
        // PASO 2: CARGA INICIAL (INIT)
        // ==========================================
        // Aquí se crea el catálogo con los dispositivos
        gestor.inicializarCatalogo();
        
        System.out.println("✓ Catálogo de dispositivos cargado exitosamente");
        System.out.println();
        
        // ==========================================
        // PASO 3: CICLO PRINCIPAL DEL MENÚ
        // ==========================================
        
        // Variable de control para el ciclo
        // IMPORTANTE: Esto NO es while(true), evita penalización
        boolean continuar = true;
        
        // do-while: ejecuta al menos una vez
        do {
            try {
                // Mostrar menú de opciones
                mostrarMenu();
                
                // Leer opción del usuario
                int opcion = scanner.nextInt();
                scanner.nextLine(); // Limpiar buffer (importante!)
                
                // Procesar la opción seleccionada
                procesarOpcion(opcion);
                
                // Preguntar si desea continuar
                // IMPORTANTE: Solo si no eligió salir (opción 0)
                if (opcion != 0) {
                    System.out.println("\n¿Desea realizar otra operación? (S/N): ");
                    String respuesta = scanner.nextLine().trim().toUpperCase();
                    
                    // Si responde N o NO, cambiar continuar a false
                    if (respuesta.equals("N") || respuesta.equals("NO")) {
                        continuar = false;
                    }
                    // Si responde otra cosa (S, SI, etc.), continuar sigue en true
                } else {
                    // Si eligió opción 0 (salir), terminar el ciclo
                    continuar = false;
                }
                
            } catch (InputMismatchException e) {
                // MANEJO DE EXCEPCIONES:
                // Si el usuario ingresa texto cuando esperábamos número
                System.out.println("\n⚠ Error: Debe ingresar un número válido");
                scanner.nextLine(); // Limpiar buffer
                System.out.println("Presione Enter para continuar...");
                scanner.nextLine();
                // continuar sigue siendo true, el ciclo continúa
            }
            
        } while (continuar);  // Repetir mientras continuar sea true
        
        // ==========================================
        // PASO 4: DESPEDIDA
        // ==========================================
        System.out.println("\n╔════════════════════════════════════════════════════════════╗");
        System.out.println("║  Gracias por usar el sistema                               ║");
        System.out.println("║  ¡Hasta pronto!                                            ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝");
        
        // Cerrar el scanner para liberar recursos
        scanner.close();
    }
    
    // ==========================================
    // MÉTODO: MOSTRAR MENÚ
    // ==========================================
    
    private void mostrarMenu() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("                    MENÚ PRINCIPAL");
        System.out.println("=".repeat(60));
        System.out.println("1. Listar todos los dispositivos");
        System.out.println("2. Buscar dispositivo por ID");
        System.out.println("3. Buscar dispositivos por nombre");
        System.out.println("4. Ordenar dispositivos por consumo eléctrico");
        System.out.println("5. Ver capacidades detalladas de un dispositivo");
        System.out.println("6. Ejecutar acción en un dispositivo");
        System.out.println("7. Capturar registro de un dispositivo");
        System.out.println("0. Salir del sistema");
        System.out.println("=".repeat(60));
        System.out.print("Seleccione una opción: ");
    }
    
    // ==========================================
    // MÉTODO: PROCESAR OPCIÓN
    // ==========================================
    
    private void procesarOpcion(int opcion) {
        System.out.println(); // Línea en blanco para mejor legibilidad
        
        // Switch para decidir qué hacer según la opción
        switch (opcion) {
            case 1:
                listarDispositivos();
                break;
            case 2:
                buscarDispositivoPorId();
                break;
            case 3:
                buscarDispositivoPorNombre();
                break;
            case 4:
                ordenarPorConsumo();
                break;
            case 5:
                verCapacidadesDispositivo();
                break;
            case 6:
                ejecutarAccion();
                break;
            case 7:
                capturarRegistro();
                break;
            case 0:
                System.out.println("Cerrando sistema...");
                break;
            default:
                System.out.println("⚠ Opción no válida. Por favor, intente nuevamente.");
        }
    }
    
    // ==========================================
    // OPCIÓN 1: LISTAR TODOS LOS DISPOSITIVOS
    // ==========================================

    private void listarDispositivos() {
        // Banner de la opción
        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║           LISTADO COMPLETO DE DISPOSITIVOS                 ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝");
        
        // DELEGACIÓN: Pedir lista al controlador
        ArrayList<DispositivoAgricola> dispositivos = gestor.listarTodosDispositivos();
        
        // Verificar si hay dispositivos
        if (dispositivos.isEmpty()) {
            System.out.println("No hay dispositivos registrados en el sistema.");
        } else {
            // Mostrar cantidad total
            System.out.println("Total de dispositivos: " + dispositivos.size());
            System.out.println();
            
            // Mostrar cada dispositivo
            int contador = 1;
            for (DispositivoAgricola dispositivo : dispositivos) {
                // Aquí se usa el toString() del dispositivo (polimorfismo)
                // Se ejecuta la versión correcta según el tipo real
                System.out.println(contador + ". " + dispositivo.toString());
                contador++;
            }
        }
    }
    
    // ==========================================
    // OPCIÓN 2: BUSCAR POR ID
    // ==========================================
    
    private void buscarDispositivoPorId() {
        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║              BÚSQUEDA POR ID                               ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝");
        
        // Pedir ID al usuario
        System.out.print("Ingrese el ID del dispositivo: ");
        String id = scanner.nextLine().trim();  // .trim() quita espacios
        
        // DELEGACIÓN: Buscar en el controlador
        DispositivoAgricola dispositivo = gestor.buscarPorId(id);
        
        // Verificar resultado
        if (dispositivo == null) {
            // No se encontró
            System.out.println("\n⚠ No se encontró ningún dispositivo con el ID: " + id);
        } else {
            // Se encontró
            System.out.println("\n✓ Dispositivo encontrado:");
            mostrarInformacionDispositivo(dispositivo);
        }
    }
    
    // ==========================================
    // OPCIÓN 3: BUSCAR POR NOMBRE
    // ==========================================

    private void buscarDispositivoPorNombre() {
        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║           BÚSQUEDA POR NOMBRE                              ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝");
        
        // Pedir nombre al usuario
        System.out.print("Ingrese el nombre (o parte del nombre) a buscar: ");
        String nombre = scanner.nextLine().trim();
        
        // DELEGACIÓN: Buscar en el controlador
        ArrayList<DispositivoAgricola> resultados = gestor.buscarPorNombre(nombre);
        
        // Verificar resultados
        if (resultados.isEmpty()) {
            System.out.println("\n⚠ No se encontraron dispositivos que coincidan con: " + nombre);
        } else {
            // Mostrar cantidad de resultados
            System.out.println("\n✓ Se encontraron " + resultados.size() + " dispositivo(s):");
            System.out.println();
            
            // Mostrar cada resultado
            int contador = 1;
            for (DispositivoAgricola dispositivo : resultados) {
                System.out.println(contador + ". " + dispositivo.toString());
                contador++;
            }
        }
    }
    
    // ==========================================
    // OPCIÓN 4: ORDENAR POR CONSUMO
    // ==========================================
    
    private void ordenarPorConsumo() {
        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║      ORDENAMIENTO POR CONSUMO ELÉCTRICO                    ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝");
        
        // Preguntar tipo de ordenamiento
        System.out.println("Seleccione el tipo de ordenamiento:");
        System.out.println("1. Ascendente (menor a mayor consumo)");
        System.out.println("2. Descendente (mayor a menor consumo)");
        System.out.print("Opción: ");
        
        int tipoOrden = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer
        
        // Determinar si es ascendente
        boolean ascendente = (tipoOrden == 1);
        
        // DELEGACIÓN: Pedir lista ordenada al controlador
        ArrayList<DispositivoAgricola> ordenados = gestor.ordenarPorConsumo(ascendente);
        
        // Mostrar resultados
        System.out.println("\n✓ Dispositivos ordenados por consumo eléctrico " + 
                          (ascendente ? "(Ascendente)" : "(Descendente)") + ":");
        System.out.println();
        
        int contador = 1;
        for (DispositivoAgricola dispositivo : ordenados) {
            System.out.println(contador + ". " + dispositivo.toString());
            contador++;
        }
    }
    
    // ==========================================
    // OPCIÓN 5: VER CAPACIDADES DETALLADAS
    // ==========================================

    private void verCapacidadesDispositivo() {
        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║         CAPACIDADES DETALLADAS DE DISPOSITIVO              ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝");
        
        // Pedir ID
        System.out.print("Ingrese el ID del dispositivo: ");
        String id = scanner.nextLine().trim();
        
        // Buscar dispositivo
        DispositivoAgricola dispositivo = gestor.buscarPorId(id);
        
        // Verificar si existe
        if (dispositivo == null) {
            System.out.println("\n⚠ No se encontró ningún dispositivo con el ID: " + id);
        } else {
            // DELEGACIÓN: Pedir capacidades al controlador
            // El controlador hace toda la lógica de verificación de interfaces
            String capacidades = gestor.verCapacidades(dispositivo);
            System.out.println("\n" + capacidades);
        }
    }
    
    // ==========================================
    // OPCIÓN 6: EJECUTAR ACCIÓN
    // ==========================================

    private void ejecutarAccion() {
        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║          EJECUTAR ACCIÓN EN DISPOSITIVO                    ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝");
        
        // Pedir ID
        System.out.print("Ingrese el ID del dispositivo: ");
        String id = scanner.nextLine().trim();
        
        // Buscar dispositivo
        DispositivoAgricola dispositivo = gestor.buscarPorId(id);
        
        // VALIDACIÓN 1: Verificar que existe
        if (dispositivo == null) {
            System.out.println("\n⚠ No se encontró ningún dispositivo con el ID: " + id);
            return;  // Salir del método
        }
        
        // VALIDACIÓN 2: Verificar que es Accionable
        if (!(dispositivo instanceof Accionable)) {
            System.out.println("\n⚠ Este dispositivo no puede ejecutar acciones");
            return;  // Salir del método
        }
        
        // Cast para acceder a métodos de Accionable
        Accionable accionable = (Accionable) dispositivo;
        String[] acciones = accionable.getAccionesDisponibles();
        
        // Mostrar acciones disponibles
        System.out.println("\nAcciones disponibles:");
        for (int i = 0; i < acciones.length; i++) {
            System.out.println((i + 1) + ". " + acciones[i]);
        }
        
        // Pedir qué acción ejecutar
        System.out.print("\nIngrese el nombre de la acción a ejecutar: ");
        String accion = scanner.nextLine().trim();
        
        // DELEGACIÓN: Ejecutar en el controlador
        String resultado = gestor.ejecutarAccionEnDispositivo(id, accion);
        System.out.println("\n" + resultado);
    }
    
    // ==========================================
    // OPCIÓN 7: CAPTURAR REGISTRO
    // ==========================================
    
    private void capturarRegistro() {
        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║         CAPTURAR REGISTRO DE DISPOSITIVO                   ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝");
        
        // Pedir ID
        System.out.print("Ingrese el ID del dispositivo: ");
        String id = scanner.nextLine().trim();
        
        // Buscar dispositivo
        DispositivoAgricola dispositivo = gestor.buscarPorId(id);
        
        // VALIDACIÓN 1: Verificar que existe
        if (dispositivo == null) {
            System.out.println("\n⚠ No se encontró ningún dispositivo con el ID: " + id);
            return;
        }
        
        // VALIDACIÓN 2: Verificar que es Registrable
        if (!(dispositivo instanceof Registrable)) {
            System.out.println("\n⚠ Este dispositivo no puede capturar registros");
            return;
        }
        
        // DELEGACIÓN: Capturar en el controlador
        String resultado = gestor.capturarRegistroDeDispositivo(id);
        System.out.println("\n✓ " + resultado);
    }
    
    // ==========================================
    // MÉTODO AUXILIAR: MOSTRAR INFORMACIÓN
    // ==========================================
    
    private void mostrarInformacionDispositivo(DispositivoAgricola dispositivo) {
        System.out.println("-".repeat(60));
        System.out.println(dispositivo.toString());
        System.out.println("-".repeat(60));
    }
}
