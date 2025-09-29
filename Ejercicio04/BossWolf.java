import java.util.List;

public class BossWolf extends Wolf {
    private int ferocidadExtra;

    public BossWolf(String nombre) {
        super(nombre);
        this.vidaMax += 30; this.vida = this.vidaMax;
        this.ataqueBase += 4;
        this.ferocidadExtra = 5;
    }

    @Override
    public void usarHabilidad(Batalla b) {
        // TODO: quizá priorizar aullido si hay varios enemigos aliados
    }

    public void aullidoDeGuerra(List<Enemigo> aliados) {
        if (aliados == null) return;
        // TODO: buff a ataque de aliados por N turnos
    }
}
