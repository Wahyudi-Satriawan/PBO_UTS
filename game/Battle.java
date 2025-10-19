package game;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class Battle {
    private final List<Character> teamA;
    private final List<Character> teamB;

    public Battle(List<Character> teamA, List<Character> teamB) {
        this.teamA = teamA; 
        this.teamB = teamB;
    }

    private boolean isTeamAlive(List<Character> team) {
        return team.stream().anyMatch(Character::isAlive);
    }
    
    private Optional<Character> getPlayerTarget(List<Character> enemyTeam) {
        return enemyTeam.stream()
                .filter(Character::isAlive)
                .sorted(Comparator.comparing((Character c) -> ((Enemy) c).getThreatLevel()).reversed()
                           .thenComparing(Character::getHealth))
                .findFirst();
    }

    private Optional<Character> getEnemyTarget(List<Character> playerTeam) {
        return playerTeam.stream()
                .filter(Character::isAlive)
                .sorted(Comparator.comparing(Character::getHealth).reversed())
                .findFirst();
    }

    public void run() {
        System.out.println("=== PERTARUNGAN DIMULAI ===");
        
        int turn = 1;
        while (isTeamAlive(teamA) && isTeamAlive(teamB)) {
            System.out.printf("\n--- TURN %d ---\n", turn);

            for (Character memberA : teamA) {
                if (memberA.isAlive() && isTeamAlive(teamB)) {
                    Optional<Character> targetOpt = getPlayerTarget(teamB);
                    if (targetOpt.isPresent()) {
                        memberA.performTurn(targetOpt.get());
                    }
                }
            }
            
            if (!isTeamAlive(teamB)) break;

            System.out.println();
            for (Character memberB : teamB) {
                if (memberB.isAlive() && isTeamAlive(teamA)) {
                    Optional<Character> targetOpt = getEnemyTarget(teamA);
                    if (targetOpt.isPresent()) {
                        memberB.performTurn(targetOpt.get());
                    }
                }
            }

            System.out.println();

            System.out.println("Status Akhir Turn:"); 
            
            teamA.forEach(c -> System.out.println("  " + c.toString()));
            teamB.forEach(c -> System.out.println("  " + c.toString()));

            turn++;
        }

        System.out.println("\n=== HASIL PERTARUNGAN ===");
        if (isTeamAlive(teamA)) {
            System.out.println("Team A Menang!");
        } else {
            System.out.println("Team B Menang!");
        }
        
        System.out.println("Sisa HP:");
        teamA.forEach(c -> System.out.println("  - " + c.toString()));
        teamB.forEach(c -> System.out.println("  - " + c.toString()));
    }
}