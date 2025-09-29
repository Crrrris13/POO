public class Faker extends Enemigo {
    protected int mana;
    protected int poderMagico;

    public Faker(String nombre) {
        super(nombre, 70, 8);
        this.mana = 50;
        this.poderMagico = 14;
        // habilidades.add(new HabilidadCurar());
        // habilidades.add(new HabilidadBuffAtaque());
    }

    @Override
    public void usarHabilidad(Batalla b) {
        // TODO: curarse si vida baja, buff si aliados, etc.
    }

    public void curar(Enemigo objetivo) {
        if (objetivo == null || mana < 10) return;
        // TODO: sumar vida al objetivo, cap en vidaMax
        mana -= 10;
    }

    public void rayoMagico(Jugador objetivo) {
        if (objetivo == null || mana < 8) return;
        objetivo.recibirDanio(poderMagico);
        mana -= 8;
    }

    @Override
    public void tomarTurno(Batalla batalla) {
        // TODO
    }
}
