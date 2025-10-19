package game;

public class PiercingStrike implements Skill {
    private final double multiplier;

    public PiercingStrike(double multiplier) {
        this.multiplier = multiplier;
    }

    @Override
    public String name() {
        return "PiercingStrike (x" + multiplier + ")";
    }

    @Override
    public void apply(Character self, Character target) {
        int baseDamage = 0;
        if (self instanceof Player) {
            baseDamage = self.getAttackPower() + (((Player) self).getLevel() * 2);
        } else {
            baseDamage = self.getAttackPower();
        }
        
        int finalDamage = (int) (baseDamage * multiplier);
        
        System.out.printf("  %s menggunakan %s ke %s!\n", self.getName(), name(), target.getName());
        target.takeDamage(finalDamage);
    }
}