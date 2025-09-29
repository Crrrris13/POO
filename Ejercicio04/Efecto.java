public interface Efecto {
    void aplicar(Combatiente objetivo);
    void onInicioTurno(Combatiente objetivo);
    void onFinTurno(Combatiente objetivo);
    int restante();
    boolean estaActivo();
}
