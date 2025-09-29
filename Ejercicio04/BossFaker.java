import java.util.List;

public class BossFaker extends Faker {
    private int sabiduriaOscura;

    public BossFaker(String nombre) {
        super(nombre);
        this.vidaMax += 25; this.vida = this.vidaMax;
        this.poderMagico += 4;
        this.sabiduriaOscura = 10;
    }

    @Override
    public void usarHabilidad(Batalla b) {
        // TODO: decidir entre tormentaArcana, curar, rayoMagico
    }

    public void tormentaArcana(List<Jugador> objetivos) {
        // TODO: daño en área
    }
}
