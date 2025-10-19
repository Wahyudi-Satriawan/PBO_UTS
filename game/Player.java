package game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Player extends Character {
    private final int level;
    private AttackStrategy strategy;
    private final List<Skill> skills = new ArrayList<>();
    private final Random random = new Random();

    public Player(String name, int hp, int ap, int level, AttackStrategy strategy) {
        super(name, hp, ap);
        if (level <= 0) {
            throw new IllegalArgumentException("Level harus positif.");
        }
        if (strategy == null) {
            throw new IllegalArgumentException("Strategy tidak boleh null.");
        }
        this.level = level;
        this.strategy = strategy;
    }

    public int getLevel() {
        return level;
    }

    public void addSkill(Skill s) {
        if (s != null) {
            skills.add(s);
        }
    }

    @Override
    public void attack(Character target) {
        List<Skill> piercingSkills = skills.stream()
                .filter(s -> s instanceof PiercingStrike)
                .collect(Collectors.toList());

        if (!piercingSkills.isEmpty()) {
            Skill chosenSkill = piercingSkills.get(random.nextInt(piercingSkills.size()));
            System.out.printf("[Team A] %s memutuskan menggunakan skill!\n", getName());
            chosenSkill.apply(this, target);
        } else {
            int damage = strategy.computeDamage(this, target);
            System.out.printf("[Team A] %s menyerang %s.\n", getName(), target.getName());
            target.takeDamage(damage);
        }
    }
    
    @Override
    public String toString() {
        return String.format("%s(HP=%d/%d, AP=%d, Lv=%d)", getClass().getSimpleName(), getHealth(), getMaxHealth(), getAttackPower(), level);
    }
}