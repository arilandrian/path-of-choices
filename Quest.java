package Path_Of_Choices;

import java.util.Scanner;

public class Quest {
    private String title;
    private String description;
    private Enemy enemy;
    private int karmaReward;
    private int repReward;
    private boolean isCompleted;

    public Quest(String title, String description, Enemy enemy, int karmaReward, int repReward) {
        this.title = title;
        this.description = description;
        this.enemy = enemy;
        this.karmaReward = karmaReward;
        this.repReward = repReward;
        this.isCompleted = false;
    }

    public void start(Player player, boolean goodIntent) {
        Scanner sc = new Scanner(System.in);
        System.out.println("\n=== " + title.toUpperCase() + " ===");
        System.out.println(description);

        if (enemy != null) {
            System.out.println("\nMusuh: " + enemy.getName() + 
                             " (HP: " + enemy.getHealth() + 
                             ", Attack: " + enemy.getAttack() + ")");
            
            System.out.print("Mulai pertarungan? (y/n): ");
            if (sc.nextLine().equalsIgnoreCase("y")) {
                battle(player, goodIntent);
            }
        } else {
            complete(player, goodIntent);
        }
    }

    private void battle(Player player, boolean goodIntent) {
        Scanner sc = new Scanner(System.in);
        
        while (player.isAlive() && enemy.isAlive()) {
            showBattleStatus(player);
            
            int choice = sc.nextInt();
            sc.nextLine();
            
            switch (choice) {
                case 1:
                    enemy.takeDamage(player.getAttack());
                    if (!enemy.isAlive()) {
                        System.out.println("\nAnda menang!");
                        player.levelUp();
                        complete(player, goodIntent);
                        return;
                    }
                    enemy.attack(player);
                    break;
                case 2:
                    player.usePotion();
                    enemy.attack(player);
                    break;
                default:
                    System.out.println("Pilihan tidak valid!");
            }
        }
    }

    private void showBattleStatus(Player player) {
        System.out.println("\n=== STATUS PERTEMPURAN ===");
        System.out.println(player.getName() + ":");
        System.out.println("HP: " + player.getHealth() + "/" + player.getMaxHealth());
        System.out.println("Potion: " + player.getPotionCount() + "/" + player.getMaxPotions());
        System.out.println(enemy.getName() + ":");
        System.out.println("HP: " + enemy.getHealth());
        System.out.println("========================");
        System.out.println("1. Serang");
        System.out.println("2. Gunakan Potion");
        System.out.print("Pilihan: ");
    }

    public void complete(Player player, boolean goodIntent) {
        if (!isCompleted) {
            isCompleted = true;
            player.changeKarma(goodIntent ? karmaReward : -karmaReward);
            player.changeReputation(repReward);
            player.heal(30);
            
            System.out.println("\n=== QUEST SELESAI ===");
            System.out.println("Karma: " + (goodIntent ? "+" : "-") + karmaReward);
            System.out.println("Reputasi: +" + repReward);
        }
    }

    // Getters
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public boolean isCompleted() { return isCompleted; }
}