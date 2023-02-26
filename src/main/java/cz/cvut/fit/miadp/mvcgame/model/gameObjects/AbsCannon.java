package cz.cvut.fit.miadp.mvcgame.model.gameObjects;

import java.util.List;

import cz.cvut.fit.miadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.miadp.mvcgame.state.DoubleShootingMode;
import cz.cvut.fit.miadp.mvcgame.state.IShootingMode;
import cz.cvut.fit.miadp.mvcgame.state.SingleShootingMode;
import cz.cvut.fit.miadp.mvcgame.visitor.IVisitor;

public abstract class AbsCannon extends GameObject {

    protected IShootingMode shootingMode;
    protected static IShootingMode SINGLE_SHOOTING_MODE = new SingleShootingMode( );
    protected static IShootingMode DOUBLE_SHOOTING_MODE = new DoubleShootingMode( );
    protected int missilesAvailable;

    public abstract void moveUp( );
    public abstract void moveDown( );
    public abstract void aimUp( );
    public abstract void aimDown( );
    public abstract void powerUp( );
    public abstract void powerDown( );

    public abstract List<AbsMissile> shoot( );
    public abstract void primitiveShoot( );

    public abstract double getAngle( );
    public abstract int getPower( );

    public abstract int getAvailableMissiles();
    public abstract void setAvailableMissiles( int missiles);

    public abstract void setAngle(double angle);
    public abstract void setPower(int power);

    public IShootingMode getShootingMode () {return this.shootingMode;}
    public void setShootingMode (IShootingMode mode) {this.shootingMode = mode;}

    public void reload() {
        missilesAvailable = MvcGameConfig.MISSILE_CAPACITY;
    }

    @Override
    public void acceptVisitor( IVisitor visitor ) {
        visitor.visitCannon( this );
    }

    public void toggleShootingMode( ) {
        if( this.shootingMode instanceof SingleShootingMode ){
            this.shootingMode = DOUBLE_SHOOTING_MODE;
        }
        else if( this.shootingMode instanceof DoubleShootingMode ){
            this.shootingMode = SINGLE_SHOOTING_MODE;
        }
        else{

        }
    }

    public Boolean touchBound (AbsBound bound){
        if (this.getPosition().getY() < bound.getPosition().getY() + MvcGameConfig.BOUND_SIZE
            && MvcGameConfig.CANNON_SIZE_Y + this.getPosition().getY() > bound.getPosition().getY()) return true;
        return false;
    }

    protected abstract AbsCannon cloneCannon();
}
