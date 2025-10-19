package game;

public class Regen implements StatusEffect {
    private final int healPerTurn;
    private int duration;

    public Regen(int healPerTurn, int duration) {
        this.healPerTurn = healPerTurn;
        this.duration = duration;
    }

    @Override
    public void onTurnStart(Character self) {
    }

    @Override
    public void onTurnEnd(Character self) {
        if (duration > 0) {
            System.out.printf("  Efek Regen memulihkan %d HP %s.\n", healPerTurn, self.getName());
            self.heal(healPerTurn);
            duration--;
        }
    }

    @Override
    public boolean isExpired() {
        return duration <= 0;
    }
}