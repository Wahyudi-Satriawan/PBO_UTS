package game;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class Character {
    private final String name;
    protected final int maxHealth;
    private int health;
    private final int attackPower;

    private final List<StatusEffect> effects = new ArrayList<>();

    protected Character(String name, int health, int attackPower) {
        if (health < 0) {
            throw new IllegalArgumentException("Health tidak boleh negatif.");
        }
        if (attackPower < 0) {
            throw new IllegalArgumentException("Attack Power tidak boleh negatif.");
        }
        this.name = name;
        this.maxHealth = health;
        this.health = health;
        this.attackPower = attackPower;
    }

    public final String getName() { return name; }
    public final int getAttackPower() { return attackPower; }
    public final int getHealth() { return health; }
    public final int getMaxHealth() { return maxHealth; }

    protected final void setHealth(int value) {
        this.health = Math.max(0, Math.min(value, this.maxHealth));
    }
    
    public final void heal(int amount) {
        setHealth(this.health + amount);
    }

    protected int onIncomingDamage(int base) {
        int modifiedDamage = base;
        for (StatusEffect effect : effects) {
            if (effect instanceof Shield) {
                modifiedDamage = ((Shield) effect).reduceDamage(modifiedDamage);
            }
        }
        return Math.max(0, modifiedDamage);
    }

    public final boolean isAlive() { 
        return health > 0; 
    }

    public final void takeDamage(int dmg) { 
        int finalDamage = onIncomingDamage(Math.max(0, dmg));
        System.out.printf("  %s menerima %d damage. HP: %d -> %d\n", getName(), finalDamage, getHealth(), getHealth() - finalDamage);
        setHealth(getHealth() - finalDamage); 
    }

    public final void addEffect(StatusEffect e) { 
        if (e != null) {
            this.effects.add(e);
            System.out.printf("  Efek %s ditambahkan ke %s.\n", e.getClass().getSimpleName(), getName());
        }
    }

    public final void performTurn(Character target) {
        if (!isAlive()) return;

        Iterator<StatusEffect> it = effects.iterator();
        while (it.hasNext()) {
            it.next().onTurnStart(this);
        }

        if (target.isAlive()) {
            attack(target);
        } else {
            System.out.printf("  %s tidak dapat menyerang, target %s sudah kalah.\n", getName(), target.getName());
        }

        it = effects.iterator();
        while (it.hasNext()) {
            StatusEffect e = it.next();
            e.onTurnEnd(this);
            if (e.isExpired()) {
                System.out.printf("  Efek %s pada %s telah berakhir.\n", e.getClass().getSimpleName(), getName());
                it.remove();
            }
        }
    }

    public abstract void attack(Character target);

    @Override
    public String toString() {
        return String.format("%s(HP=%d/%d, AP=%d)", getClass().getSimpleName(), health, maxHealth, attackPower);
    }
}