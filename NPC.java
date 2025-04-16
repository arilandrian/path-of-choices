package Path_Of_Choices;

import java.util.Scanner;

public class NPC {
    private String name;
    private String description;
    private Quest quest;

    public NPC(String name, String description, Quest quest) {
        this.name = name;
        this.description = description;
        this.quest = quest;
    }

    public void interact(Player player) {
        Scanner sc = new Scanner(System.in);
        System.out.println("\n=== INTERAKSI DENGAN " + name.toUpperCase() + " ===");
        System.out.println(description);

        if (quest != null && !quest.isCompleted()) {
            System.out.println("\n[QUEST] " + quest.getTitle());
            System.out.println(quest.getDescription());
            
            System.out.print("\nTerima quest ini? (y/n): ");
            if (sc.nextLine().equalsIgnoreCase("y")) {
                System.out.println("\nPilih cara menyelesaikan:");
                System.out.println("1. Jujur (+karma)");
                System.out.println("2. Licik (-karma)");
                System.out.print("Pilihan moral: ");
                
                int choice = sc.nextInt();
                sc.nextLine();
                
                boolean goodIntent = (choice == 1);
                quest.start(player, goodIntent);
            }
        } else if (quest != null) {
            System.out.println("\n(Quest '" + quest.getTitle() + "' sudah selesai)");
        }
    }

    // Getters
    public String getName() { return name; }
    public String getDescription() { return description; }
}