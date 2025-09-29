import java.util.List;

public class BossAres extends Ares {
    private int armaduraDivina;

    public BossAres(String nombre) {
        super(nombre);
        this.vidaMax += 40; this.vida = this.vidaMax;
        this.armaduraDivina = 5;
        this.resistencia += 3;
    }

    @Override
    public void usarHabilidad(Batalla b) {
        // TODO: decidir uso de terremoto
    }

    public void terremoto(List<Jugador> objetivos) {
        // TODO: daño en área físico
    }
}
