package cz.cvut.fit.miadp.mvcgame.model.gameObjects.family_B;

import cz.cvut.fit.miadp.mvcgame.abstractFactory.IGameObjectFactory;
import cz.cvut.fit.miadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.miadp.mvcgame.model.Position;
import cz.cvut.fit.miadp.mvcgame.model.Vector;
import cz.cvut.fit.miadp.mvcgame.model.gameObjects.AbsCannon;
import cz.cvut.fit.miadp.mvcgame.model.gameObjects.AbsMissile;

import java.util.ArrayList;
import java.util.List;

public class Cannon_B extends AbsCannon {

    private IGameObjectFactory goFact;

    private double angle;
    private int power;
    private List<AbsMissile> shootingBatch;
    private int multiplier;

    public Cannon_B(Position initialPosition, IGameObjectFactory goFact ){
        this.position = initialPosition;
        this.goFact = goFact;
        this.power = MvcGameConfig.INIT_POWER;
        this.angle = MvcGameConfig.INIT_ANGLE;
        this.shootingMode = AbsCannon.SINGLE_SHOOTING_MODE;
        this.shootingBatch = new ArrayList<AbsMissile>();
        multiplier = 2;
    }

    public Cannon_B(Cannon_B cannon ){
        this.position = cannon.position;
        this.goFact = cannon.goFact;
        this.power = cannon.power;
        this.angle = cannon.angle;
        this.shootingMode = cannon.shootingMode;
        this.shootingBatch = cannon.shootingBatch;
        multiplier = cannon.multiplier;
    }


    public void moveUp( ) {
        this.move( new Vector( 0, -1 * this.multiplier * MvcGameConfig.MOVE_STEP ) );
    }

    public void moveDown( ) {
        this.move( new Vector( 0, this.multiplier * MvcGameConfig.MOVE_STEP ) );
    }


    @Override
    public List<AbsMissile> shoot( ) {
        this.shootingBatch.clear( );
        this.shootingMode.shoot( this );
        return this.shootingBatch;
    }

    @Override
    public void aimUp() {
        this.angle -= MvcGameConfig.ANGLE_STEP;
    }

    @Override
    public void aimDown() {
        this.angle += MvcGameConfig.ANGLE_STEP;
    }

    @Override
    public void powerUp() {
        this.power += MvcGameConfig.POWER_STEP;
    }

    @Override
    public void powerDown() {
        if ( this.power - MvcGameConfig.POWER_STEP > 0 ){
            this.power -= MvcGameConfig.POWER_STEP;
        }
    }

    @Override
    public void primitiveShoot() {
        this.shootingBatch.add( 
            this.goFact.createMissile(
                this.angle,
                this.power
            )
        );        
    }

    @Override
    public double getAngle() {
        return this.angle;
    }

    @Override
    public int getPower() {
        return this.power;
    }

    @Override
    public int getAvailableMissiles() {
        return this.missilesAvailable;
    }

    @Override
    public void setAvailableMissiles(int missiles) {
        this.missilesAvailable = missiles;
    }

    @Override
    public void setAngle(double angle) {
        this.angle = angle;
    }

    @Override
    public void setPower(int power) {
        this.power = power;
    }

    @Override
    protected AbsCannon cloneCannon() {
        return new Cannon_B(this);
    }

}
