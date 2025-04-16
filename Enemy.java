package Path_Of_Choices;

public class Enemy {
    private String name;
    private int health;
    private int attack;
    private boolean isBoss;
    private int expReward;

    public Enemy(String name, int health, int attack, boolean isBoss) {
        this.name = name;
        this.health = health;
        this.attack = attack;
        this.isBoss = isBoss;
        this.expReward = isBoss ? 50 : (health + attack) / 3;
    }

    public void takeDamage(int damage) {
        health -= damage;
        if (health <= 0) {
            health = 0;
            System.out.println(name + " dikalahkan!");
        }
    }

    public void attack(Player player) {
        System.out.println(name + " menyerang!");
        player.takeDamage(attack);
    }

    public boolean isAlive() {
        return health > 0;
    }

    // Getters
    public String getName() { return name; }
    public int getHealth() { return health; }
    public int getAttack() { return attack; }
    public boolean isBoss() { return isBoss; }
    public int getExpReward() { return expReward; }
}