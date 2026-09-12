package btd.towers.upgrades;

import btd.boards.Cell;
import btd.towers.projectiles.ExplosiveProjectile;
import btd.towers.projectiles.Projectile;
import btd.towers.towers.BombTower;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class ProjectileUpgradeTest {

    private BombTower tower;
    private ProjectileUpgrade upgrade;
    private Projectile newProj;

    @BeforeEach
    public void before() {
        tower = new BombTower(new Cell(0, 0));
        newProj = new ExplosiveProjectile("UltraBomb", 5, Cell.SIZE);
        upgrade = new ProjectileUpgrade(newProj, 400);
    }

    @Test
    public void applyTest() {
        upgrade.apply(tower);
        assertEquals(newProj, tower.getProjectile());
    }

    @Test
    public void removeTest() {
        Projectile initial = tower.getProjectile();
        upgrade.apply(tower);
        upgrade.remove(tower);
        assertEquals(initial, tower.getProjectile());
    }
}