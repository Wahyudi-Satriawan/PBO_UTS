package game;

public class LevelScaledStrategy implements AttackStrategy {
    private final int bonusPerLevel;

    public LevelScaledStrategy(int bonusPerLevel) {
        this.bonusPerLevel = bonusPerLevel;
    }

    @Override
    public int computeDamage(Character self, Character target) {
        if (self instanceof Player) {
            Player player = (Player) self;
            return player.getAttackPower() + (player.getLevel() * bonusPerLevel);
        }
        return self.getAttackPower();
    }
}