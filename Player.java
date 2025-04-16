package Path_Of_Choices;

public class Player {
    private String name;
    private int karma;
    private int reputation;
    private int health;
    private int maxHealth;
    private int baseAttack;
    private int currentAttack;
    private int potionCount;
    private int maxPotions = 3;

    public Player(String name) {
        this.name = name;
        this.karma = 0;
        this.reputation = 0;
        this.maxHealth = 100;
        this.health = maxHealth;
        this.baseAttack = 15;
        this.currentAttack = baseAttack;
        this.potionCount = maxPotions;
    }

    public void levelUp() {
        maxHealth += 20;
        baseAttack += 5;
        health = maxHealth;
        currentAttack = baseAttack;
        potionCount = maxPotions;
        System.out.println("\n=== LEVEL UP ===");
        System.out.println("HP Maks: " + maxHealth);
        System.out.println("Attack: " + baseAttack);
        System.out.println("Potion diisi ulang!");
    }

    public void usePotion() {
        if (potionCount > 0) {
            heal(30);
            potionCount--;
            System.out.println("Potion tersisa: " + potionCount + "/" + maxPotions);
        } else {
            System.out.println("Potion habis!");
        }
    }

    public void changeKarma(int amount) {
        karma += amount;
        System.out.println("Karma " + (amount >= 0 ? "bertambah" : "berkurang") + 
                         " " + Math.abs(amount) + ". Total: " + karma);
    }

    public void changeReputation(int amount) {
        reputation += amount;
        System.out.println("Reputasi " + (amount >= 0 ? "bertambah" : "berkurang") + 
                         " " + Math.abs(amount) + ". Total: " + reputation);
    }

    public void takeDamage(int damage) {
        health -= damage;
        if (health <= 0) {
            health = 0;
            System.out.println(name + " kalah dalam pertarungan!");
        } else {
            System.out.println(name + " menerima " + damage + " damage. HP: " + health + "/" + maxHealth);
        }
    }

    public void heal(int amount) {
        health = Math.min(health + amount, maxHealth);
        System.out.println(name + " pulih " + amount + " HP. HP sekarang: " + health + "/" + maxHealth);
    }

    public boolean isAlive() {
        return health > 0;
    }

    public void showStatus() {
        System.out.println("\n=== STATUS ===");
        System.out.println("Nama      : " + name);
        System.out.println("HP        : " + health + "/" + maxHealth);
        System.out.println("Attack    : " + currentAttack);
        System.out.println("Potion    : " + potionCount + "/" + maxPotions);
        System.out.println("Karma     : " + karma);
        System.out.println("Reputasi  : " + reputation);
        System.out.println("=================");
    }

    // Getters
    public String getName() { return name; }
    public int getHealth() { return health; }
    public int getMaxHealth() { return maxHealth; }
    public int getAttack() { return currentAttack; }
    public int getKarma() { return karma; }
    public int getReputation() { return reputation; }
    public int getPotionCount() { return potionCount; }
    public int getMaxPotions() { return maxPotions; }
}