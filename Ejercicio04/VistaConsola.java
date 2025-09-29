import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VistaConsola implements VistaBatalla {
    private final Scanner scanner = new Scanner(System.in);

    public VistaConsola() { }

    @Override
    public void mostrarEstado(Batalla b) {
        System.out.println("=== ESTADO ===");
        System.out.println("Jugador: " + b.getJugador().getNombre() + " HP " + b.getJugador().getVida());
        System.out.println("Enemigos:");
        for (Enemigo e : b.getEnemigos()) {
            System.out.println(" - " + e.getNombre() + " HP " + e.getVida());
        }
    }

    @Override
    public void mostrarRegistro(RegistroAcciones reg) {
        System.out.println("=== REGISTRO ===");
        for (String s : reg.ultimasN(3)) System.out.println(" * " + s);
    }

    @Override
    public AccionJugador pedirAccion(Jugador j, Batalla b) {
        System.out.println("Accion: 1) ATACAR  2) USAR_ITEM  3) PASAR  4) HABILIDAD_ESPECIAL  5) SALIR");
        int op = leerInt(1, 5);
        AccionJugadorTipo tipo = switch (op) {
            case 1 -> AccionJugadorTipo.ATACAR;
            case 2 -> AccionJugadorTipo.USAR_ITEM;
            case 3 -> AccionJugadorTipo.PASAR;
            case 4 -> AccionJugadorTipo.HABILIDAD_ESPECIAL;
            default -> AccionJugadorTipo.SALIR;
        };
        return new AccionJugador(tipo, null, new ArrayList<>());
    }

    @Override
    public List<Combatiente> pedirObjetivos(List<Combatiente> candidatos, int cantidad) {
        List<Combatiente> sel = new ArrayList<>();
        for (int i = 0; i < candidatos.size(); i++) {
            System.out.println((i + 1) + ") " + candidatos.get(i).getNombre());
        }
        for (int c = 0; c < cantidad; c++) {
            System.out.print("Elige objetivo #" + (c + 1) + ": ");
            int idx = leerInt(1, candidatos.size()) - 1;
            sel.add(candidatos.get(idx));
        }
        return sel;
    }

    @Override
    public Item pedirItem(Inventario inventario) {
        List<Item> items = inventario.listar();
        if (items.isEmpty()) {
            System.out.println("No tienes items.");
            return null;
        }
        System.out.println("Items:");
        for (int i = 0; i < items.size(); i++) {
            System.out.println((i + 1) + ") " + items.get(i).getClass().getSimpleName());
        }
        int idx = leerInt(1, items.size()) - 1;
        return items.get(idx);
    }

    public int mostrarMenuPrincipal() {
        System.out.println("1) Iniciar batalla  2) Salir");
        return leerInt(1, 2);
    }

    public String pedirNombreJugador() {
        System.out.print("Nombre del jugador: ");
        return scanner.nextLine().trim();
    }

    public int pedirTipoJugador() {
        System.out.println("Tipo: 1) Guerrero  2) Explorador");
        return leerInt(1, 2);
    }

    private int leerInt(int min, int max) {
        while (true) {
            try {
                String line = scanner.nextLine().trim();
                int v = Integer.parseInt(line);
                if (v < min || v > max) throw new NumberFormatException();
                return v;
            } catch (Exception e) {
                System.out.print("Valor inválido, intenta de nuevo [" + min + "-" + max + "]: ");
            }
        }
    }
}
