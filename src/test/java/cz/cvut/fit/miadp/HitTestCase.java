package cz.cvut.fit.miadp;

import cz.cvut.fit.miadp.mvcgame.command.MoveCannonUpCmd;
import cz.cvut.fit.miadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.miadp.mvcgame.model.GameModel;
import cz.cvut.fit.miadp.mvcgame.model.IGameModel;
import cz.cvut.fit.miadp.mvcgame.model.Position;
import cz.cvut.fit.miadp.mvcgame.model.gameObjects.AbsCannon;
import cz.cvut.fit.miadp.mvcgame.model.gameObjects.AbsEnemy;
import cz.cvut.fit.miadp.mvcgame.model.gameObjects.AbsMissile;
import cz.cvut.fit.miadp.mvcgame.model.gameObjects.family_A.Enemy_A;
import cz.cvut.fit.miadp.mvcgame.model.gameObjects.family_A.Missile_A;
import cz.cvut.fit.miadp.mvcgame.strategy.IMovingStrategy;
import cz.cvut.fit.miadp.mvcgame.strategy.SimpleMovingStrategy;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class HitTestCase {

    @Test
    public void simpleEnemyHitTest( ){
        Position enemyPosition = new Position (500, 500);
        AbsEnemy enemy = mock(AbsEnemy.class);
        when (enemy.getPosition()).thenReturn(enemyPosition);

        Position missilePosition = new Position (500, 500);
        double initAngle = MvcGameConfig.INIT_ANGLE;
        int power = MvcGameConfig.INIT_POWER;
        IMovingStrategy strat = new SimpleMovingStrategy();
        AbsMissile missile = new Missile_A(missilePosition, initAngle, power, strat);

        Assert.assertEquals( missile.hit(enemy), true);
    }

    /*
     * When the bullet travels the hit on enemy should only count once
     */
    @Test
    public void noDoubleHitTest( ){
        Position enemyPosition = new Position (500, 500);
        AbsEnemy enemy = mock(AbsEnemy.class);
        when (enemy.getPosition()).thenReturn(enemyPosition);

        Position missilePosition = new Position (500, 500);
        double initAngle = MvcGameConfig.INIT_ANGLE;
        int power = MvcGameConfig.INIT_POWER;
        IMovingStrategy strat = new SimpleMovingStrategy();
        AbsMissile missile = new Missile_A(missilePosition, initAngle, power, strat);

        Assert.assertEquals( missile.hit(enemy), true);
        Assert.assertEquals( missile.hit(enemy), false);
    }

    @Test
    public void enemiesWithinRadiusHitTest( ){
        Position missilePosition = new Position (500, 500);
        double initAngle = MvcGameConfig.INIT_ANGLE;
        int power = MvcGameConfig.INIT_POWER;
        IMovingStrategy strat = new SimpleMovingStrategy();
        AbsMissile missile = new Missile_A(missilePosition, initAngle, power, strat);

        Position enemy1Position = new Position (500 - MvcGameConfig.ENEMY_HIT_RADIUS, 500);
        Position enemy2Position = new Position (500 + MvcGameConfig.ENEMY_HIT_RADIUS, 500);
        Position enemy3Position = new Position (500 + MvcGameConfig.ENEMY_HIT_RADIUS, 500 + MvcGameConfig.ENEMY_HIT_RADIUS);
        Position enemy4Position = new Position (500, 500 - MvcGameConfig.ENEMY_HIT_RADIUS);
        Position enemy5Position = new Position (500 - MvcGameConfig.ENEMY_HIT_RADIUS, 500 - MvcGameConfig.ENEMY_HIT_RADIUS);

        AbsEnemy enemy1 = mock(AbsEnemy.class);
        AbsEnemy enemy2 = mock(AbsEnemy.class);
        AbsEnemy enemy3 = mock(AbsEnemy.class);
        AbsEnemy enemy4 = mock(AbsEnemy.class);
        AbsEnemy enemy5 = mock(AbsEnemy.class);

        when (enemy1.getPosition()).thenReturn(enemy1Position);
        when (enemy2.getPosition()).thenReturn(enemy1Position);
        when (enemy3.getPosition()).thenReturn(enemy1Position);
        when (enemy4.getPosition()).thenReturn(enemy1Position);
        when (enemy5.getPosition()).thenReturn(enemy1Position);

        Assert.assertEquals( missile.hit(enemy1), true);
        Assert.assertEquals( missile.hit(enemy2), true);
        Assert.assertEquals( missile.hit(enemy3), true);
        Assert.assertEquals( missile.hit(enemy4), true);
        Assert.assertEquals( missile.hit(enemy5), true);
    }

    @Test
    public void enemiesNotWithinRadiusHitTest( ){
        Position missilePosition = new Position (500, 500);
        double initAngle = MvcGameConfig.INIT_ANGLE;
        int power = MvcGameConfig.INIT_POWER;
        IMovingStrategy strat = new SimpleMovingStrategy();
        AbsMissile missile = new Missile_A(missilePosition, initAngle, power, strat);

        Position enemy1Position = new Position (700, 600);
        Position enemy2Position = new Position (500 + 2*MvcGameConfig.ENEMY_HIT_RADIUS, 500);
        Position enemy3Position = new Position (500 + 2 *MvcGameConfig.ENEMY_HIT_RADIUS, 500 + 3 * MvcGameConfig.ENEMY_HIT_RADIUS);
        Position enemy4Position = new Position (500, 500 - 2 * MvcGameConfig.ENEMY_HIT_RADIUS);
        Position enemy5Position = new Position (500 - 2 * MvcGameConfig.ENEMY_HIT_RADIUS, 500 - 2 * MvcGameConfig.ENEMY_HIT_RADIUS);

        AbsEnemy enemy1 = mock(AbsEnemy.class);
        AbsEnemy enemy2 = mock(AbsEnemy.class);
        AbsEnemy enemy3 = mock(AbsEnemy.class);
        AbsEnemy enemy4 = mock(AbsEnemy.class);
        AbsEnemy enemy5 = mock(AbsEnemy.class);

        when (enemy1.getPosition()).thenReturn(enemy1Position);
        when (enemy2.getPosition()).thenReturn(enemy1Position);
        when (enemy3.getPosition()).thenReturn(enemy1Position);
        when (enemy4.getPosition()).thenReturn(enemy1Position);
        when (enemy5.getPosition()).thenReturn(enemy1Position);

        Assert.assertEquals( missile.hit(enemy1), false);
        Assert.assertEquals( missile.hit(enemy2), false);
        Assert.assertEquals( missile.hit(enemy3), false);
        Assert.assertEquals( missile.hit(enemy4), false);
        Assert.assertEquals( missile.hit(enemy5), false);
    }
}
