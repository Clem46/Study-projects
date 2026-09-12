package btd.towers.projectiles;

/** Explosive projectile */
public class ExplosiveProjectile extends Projectile {
    /** This Explosive projectile range of explosion */
    private int range;

    /**
     * Creates an explosive projectile
     * @param projectileName name
     * @param damage damage
     * @param radius radius
     */
    public ExplosiveProjectile(String projectileName, double damage, int radius) {
        super(projectileName, damage);
        this.range = radius / 2; // /2 pour toucher que la case actuelle
    }
    
    /**
     * Gets the explosion radius
     * @return explosion radius
     */
    public int getExplosionRadius() {
        return range;
    }

    @Override
    public String toString(){
        return projectileName + "(" + (int) damage +")"; 
    }
}
