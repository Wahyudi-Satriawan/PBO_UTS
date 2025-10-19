package game;

public class BossMonster extends Enemy {
    private int turnCounter = 0;

    public BossMonster(String name, int hp, int ap, int threatLevel, AttackStrategy strategy) {
        super(name, hp, ap, threatLevel, strategy);
    }

    @Override
    public void attack(Character target) {
        turnCounter++;
        
        boolean isHealthRage = (double) getHealth() / getMaxHealth() < 0.5;
        boolean isTurnRage = (turnCounter % 3 == 0);

        int baseDamage = strategy.computeDamage(this, target);
        
        if (isHealthRage || isTurnRage) {
            int rageDamage = (int) (baseDamage * 2.0);

            System.out.printf("[Team B] %s menggunakan RAGE STRIKE (x2.0) ke %s!\n", getName(), target.getName());
            target.takeDamage(rageDamage);
        } else {

            System.out.printf("[Team B] %s menyerang %s.\n", getName(), target.getName());
            target.takeDamage(baseDamage);
        }
    }
}