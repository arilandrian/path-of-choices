package Path_Of_Choices;

import java.util.Scanner;

public class Game {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("=== PATH OF CHOICES ===");
        System.out.print("Masukkan nama karakter: ");
        Player player = new Player(sc.nextLine());
        
        // Inisialisasi musuh
        Enemy wolf = new Enemy("Serigala Buas", 40, 10, false);
        Enemy bandit = new Enemy("Bandit", 60, 15, false);
        Enemy darkLord = new Enemy("Raja Kegelapan", 150, 25, true);
        
        // Inisialisasi quest
        Quest[] quests = {
            new Quest("Pertemuan Awal", "Bicaralah dengan kepala desa", null, 10, 10),
            new Quest("Ancaman Serigala", "Kalahkan serigala yang mengganggu", wolf, 15, 15),
            new Quest("Gang Bandit", "Bersihkan desa dari bandit", bandit, 20, 20),
            new Quest("Konflik Terakhir", "Hadapi Raja Kegelapan", darkLord, 30, 30)
        };
        
        NPC chief = new NPC("Kepala Desa", "Pemimpin desa yang bijaksana", null);
        
        int currentQuest = 0;
        boolean gameOver = false;
        
        while (!gameOver && player.isAlive()) {
            System.out.println("\n=== MENU UTAMA ===");
            System.out.println("1. Temui Kepala Desa");
            System.out.println("2. Lihat Status");
            System.out.println("3. Keluar");
            System.out.print("Pilihan: ");
            
            try {
                int choice = sc.nextInt();
                sc.nextLine();
                
                switch (choice) {
                    case 1:
                        handleChiefInteraction(player, quests, currentQuest, sc);
                        if (currentQuest < quests.length && quests[currentQuest].isCompleted()) {
                            currentQuest++;
                        }
                        break;
                    case 2:
                        player.showStatus();
                        showQuestProgress(currentQuest, quests);
                        break;
                    case 3:
                        gameOver = true;
                        break;
                    default:
                        System.out.println("Pilihan tidak valid!");
                }
                
                if (currentQuest >= quests.length) {
                    System.out.println("\n=================================");
                    System.out.println("  SELAMAT! ANDA MENYELESAIKAN GAME");
                    System.out.println("=================================");
                    showEnding(player);
                    gameOver = true;
                }
            } catch (Exception e) {
                System.out.println("Harap masukkan angka yang valid!");
                sc.nextLine();
            }
        }
        
        if (!player.isAlive()) {
            System.out.println("\n=== GAME OVER ===");
            System.out.println("Karakter Anda telah gugur dalam pertempuran...");
        }
        
        sc.close();
    }

    private static void handleChiefInteraction(Player player, Quest[] quests, int currentQuest, Scanner sc) {
        if (currentQuest >= quests.length) return;
        
        NPC chief = new NPC("Kepala Desa", "Pemimpin desa yang bijaksana", quests[currentQuest]);
        System.out.println("\n=================================");
        chief.interact(player);
    }

    private static void showQuestProgress(int currentQuest, Quest[] quests) {
        System.out.println("\n=== PROGRESS QUEST ===");
        for (int i = 0; i < quests.length; i++) {
            String status;
            if (i < currentQuest) {
                status = "(Selesai)";
            } else if (i == currentQuest) {
                status = quests[i].isCompleted() ? "(Selesai)" : "(Sekarang)";
            } else {
                status = "(Terkunci)";
            }
            System.out.println((i+1) + ". " + quests[i].getTitle() + " " + status);
        }
    }

    private static void showEnding(Player player) {
        int karma = player.getKarma();
        int reputation = player.getReputation();
        
        System.out.println("\n=== AKHIR CERITA ===");
        System.out.println("Karma Akhir: " + karma);
        System.out.println("Reputasi Akhir: " + reputation);
        System.out.println("-------------------------------");
        
        if (karma >= 50 && reputation >= 50) {
            System.out.println("ENDING LEGENDA:");
            System.out.println("Anda menjadi pahlawan suci yang dikenang sepanjang masa.");
        }
        else if (karma >= 30 && reputation >= 30) {
            System.out.println("ENDING PAHLAWAN:");
            System.out.println("Anda dihormati sebagai penyelamat kerajaan.");
        }
        else if (karma <= -30 && reputation >= 40) {
            System.out.println("ENDING PENGUASA KEJAM:");
            System.out.println("Anda memerintah dengan tangan besi.");
        }
        else if (karma <= -20 && reputation <= 20) {
            System.out.println("ENDING PENJAHAT:");
            System.out.println("Anda diburu dan hidup dalam pelarian.");
        }
        else if (reputation >= 40) {
            System.out.println("ENDING TOKOH BERPENGARUH:");
            System.out.println("Anda menjadi politikus ulung.");
        }
        else if (karma >= 20) {
            System.out.println("ENDING ORANG BAIK:");
            System.out.println("Anda hidup tenang dengan hati bersih.");
        }
        else {
            System.out.println("ENDING BIASA SAJA:");
            System.out.println("Petualangan Anda berlalu tanpa kesan berarti.");
        }
        
        System.out.println("\nTerima kasih telah bermain!");
    }
}