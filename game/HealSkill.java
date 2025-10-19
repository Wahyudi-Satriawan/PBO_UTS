package game;

public class HealSkill implements Skill {
    private final int amount;

    public HealSkill(int amount) {
        this.amount = amount;
    }

    @Override
    public String name() {
        return "HealSkill (+" + amount + " HP)";
    }

    @Override
    public void apply(Character self, Character target) {
        System.out.printf("  %s menggunakan %s.\n", self.getName(), name());
        self.heal(amount);
    }
}