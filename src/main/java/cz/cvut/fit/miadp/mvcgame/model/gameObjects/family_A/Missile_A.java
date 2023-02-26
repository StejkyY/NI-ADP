package cz.cvut.fit.miadp.mvcgame.model.gameObjects.family_A;

import cz.cvut.fit.miadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.miadp.mvcgame.model.Position;
import cz.cvut.fit.miadp.mvcgame.model.gameObjects.AbsEnemy;
import cz.cvut.fit.miadp.mvcgame.model.gameObjects.AbsMissile;
import cz.cvut.fit.miadp.mvcgame.strategy.IMovingStrategy;

import java.util.ArrayList;
import java.util.List;

public class Missile_A extends AbsMissile {

    private IMovingStrategy movingStrategy;
    private List<AbsEnemy> enemiesHit;

    public Missile_A( Position initialPosition, double initAngle, int initVelocity, IMovingStrategy movingStrategy ){
        super(initialPosition, initAngle, initVelocity);
        this.position = initialPosition;
        this.movingStrategy = movingStrategy;
        this.enemiesHit = new ArrayList<>();
    }

    @Override
    public void move( ) {
        this.movingStrategy.updatePosition( this );
    }

   @Override
    public boolean hit(AbsEnemy enemy) {
            if(enemiesHit.contains(enemy)) return false;
            int differenceX = this.position.getX() - enemy.getPosition().getX();
            int differenceY = this.position.getY() - enemy.getPosition().getY();
        /*int differenceX = (this.position.getX() + MvcGameConfig.MISSILE_HIT_RADIUS)
                                - (enemy.getPosition().getX() + MvcGameConfig.ENEMY_HIT_RADIUS);
        int differenceY = (this.position.getY() + MvcGameConfig.MISSILE_HIT_RADIUS)
                                - (enemy.getPosition().getY() + MvcGameConfig.ENEMY_HIT_RADIUS);*/

            double distance = differenceX * differenceX + differenceY * differenceY;
            if(distance <= Math.pow((MvcGameConfig.ENEMY_HIT_RADIUS + MvcGameConfig.MISSILE_HIT_RADIUS), 2)) {
                enemiesHit.add(enemy);
                return true;
            }
            return false;
    }

}
