package game;

public class Shield implements StatusEffect {
    private final int flatReduction;
    private int duration;

    public Shield(int flatReduction, int duration) {
        this.flatReduction = flatReduction;
        this.duration = duration;
    }

    public int reduceDamage(int originalDamage) {
        System.out.printf("  Shield mengurangi %d damage.\n", flatReduction);
        return originalDamage - flatReduction;
    }

    @Override
    public void onTurnStart(Character self) {
    }

    @Override
    public void onTurnEnd(Character self) {
        if (duration > 0) {
            duration--;
        }
    }

    @Override
    public boolean isExpired() {
        return duration <= 0;
    }
}