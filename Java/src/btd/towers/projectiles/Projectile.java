package btd.towers.projectiles;

/** Abstract projectile */
public abstract class Projectile {
    /** The damage of Projectile */
    protected double damage;
    /* Name of Projectile */
    protected final String projectileName;

    /**
     * Constructor of Projectile
     * @param projectileName String
     * @param damage double
     */
    public Projectile(String projectileName, double damage) {
        this.projectileName = projectileName;
        this.damage = damage;
    }
    
    /**
     * Getter of damage
     * @return damage : int
     */
    public double getDamage() {
        return this.damage;
    }
    
    /**
     * Getter of projectile name
     * @return Projectile Name : String
     */
    public String getName() {
        return this.projectileName;
    }

    /**
     * Return a String representation of the projectile
     */
    public String toString() {
        return projectileName + "(" + damage + ")";
    }
}
