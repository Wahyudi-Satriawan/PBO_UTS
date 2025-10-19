package game;

public class Monster extends Enemy {
    public Monster(String name, int hp, int ap, int threatLevel, AttackStrategy strategy) {
        super(name, hp, ap, threatLevel, strategy);
    }

    @Override
    public void attack(Character target) {
        int damage = strategy.computeDamage(this, target);
        System.out.printf("[Team B] %s menyerang %s.\n", getName(), target.getName());
        target.takeDamage(damage);
    }
}