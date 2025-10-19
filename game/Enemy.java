package game;

public abstract class Enemy extends Character {
    private final int threatLevel;
    protected AttackStrategy strategy;

    protected Enemy(String name, int hp, int ap, int threatLevel, AttackStrategy strategy) {
        super(name, hp, ap);
        if (threatLevel < 1 || threatLevel > 5) {
            throw new IllegalArgumentException("Threat Level harus antara 1 dan 5.");
        }
        if (strategy == null) {
            throw new IllegalArgumentException("AttackStrategy tidak boleh null.");
        }
        this.threatLevel = threatLevel;
        this.strategy = strategy;
    }

    public final int getThreatLevel() { return threatLevel; }

    public final void setStrategy(AttackStrategy s) { 
        if (s != null) {
            this.strategy = s;
        }
    }
}