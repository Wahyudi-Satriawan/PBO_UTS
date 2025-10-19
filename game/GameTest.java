package game;

import java.util.ArrayList;
import java.util.List;

public class GameTest {
    public static void main(String[] args) {
        Player player = new Player("Wahyu", 200, 25, 10, new LevelScaledStrategy(2));
        
        player.addSkill(new HealSkill(15));
        player.addSkill(new PiercingStrike(1.2));
        
        player.addEffect(new Shield(10, 3));
        player.addEffect(new Regen(8, 4));

        List<Character> teamA = new ArrayList<>();
        teamA.add(player);

        BossMonster boss = new BossMonster("Drake", 150, 28, 5, new FixedStrategy());
        Monster goblin = new Monster("Goblin", 80, 12, 2, new FixedStrategy());
        
        List<Character> teamB = new ArrayList<>();
        teamB.add(boss);
        teamB.add(goblin);

        Battle battle = new Battle(teamA, teamB);
        battle.run();
    }
}