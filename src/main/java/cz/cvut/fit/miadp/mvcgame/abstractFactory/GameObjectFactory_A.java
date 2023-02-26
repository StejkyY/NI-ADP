package cz.cvut.fit.miadp.mvcgame.abstractFactory;

import cz.cvut.fit.miadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.miadp.mvcgame.model.IGameModel;
import cz.cvut.fit.miadp.mvcgame.model.Position;
import cz.cvut.fit.miadp.mvcgame.model.gameObjects.*;
import cz.cvut.fit.miadp.mvcgame.model.gameObjects.family_A.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameObjectFactory_A implements IGameObjectFactory {

    private IGameModel model;

    public GameObjectFactory_A( IGameModel model ){
        this.model = model;
    }

    @Override
    public Cannon_A createCannon( ) {
        return new Cannon_A( new Position( MvcGameConfig.CANNON_POS_X, MvcGameConfig.CANNON_POS_Y ), this );
    }

    @Override
    public Enemy_A createEnemy( int lives ) {
        Random random = new Random();
        int randomPosX = random.nextInt((MvcGameConfig.MAX_X - (MvcGameConfig.ENEMY_OFFSET_X / 2))
                - (MvcGameConfig.CANNON_POS_X + MvcGameConfig.ENEMY_OFFSET_X) ) + (MvcGameConfig.CANNON_POS_X + MvcGameConfig.ENEMY_OFFSET_X);
        int randomPosY = random.nextInt((MvcGameConfig.MAX_Y
                - MvcGameConfig.ENEMY_OFFSET_Y) - MvcGameConfig.ENEMY_OFFSET_Y ) + MvcGameConfig.ENEMY_OFFSET_Y;
        return new Enemy_A( new Position(randomPosX, randomPosY),this, lives );
    }

    @Override
    public AbsCollision createCollision(Position pos) {
        return new Collision_A(pos, MvcGameConfig.COLLISION_DELETE_TIME);
    }

    @Override
    public AbsGameInfo createGameInfo(AbsCannon cannon) {
        return new GameInfo_A(new Position(MvcGameConfig.GAME_INFO_POS_X, MvcGameConfig.GAME_INFO_POS_Y),
                cannon, this.model);
    }

    @Override
    public AbsBound createBound() {
        Random random = new Random();
        int posY = 0;
        if(random.nextInt(2) == 1)
            posY = random.nextInt((MvcGameConfig.MAX_Y - MvcGameConfig.BOUND_SIZE)
                        - (MvcGameConfig.BOUND_Y_UPPER_BORDER)) + MvcGameConfig.BOUND_Y_UPPER_BORDER;
        else
            posY = random.nextInt((MvcGameConfig.BOUND_Y_LOWER_BORDER - MvcGameConfig.BOUND_SIZE));
        return new Bound_A(
                new Position(
                        MvcGameConfig.BOUND_POS_X,
                        posY
                ));
    }

    @Override
    public AbsEndScene createEndScene() {
        return new EndScene_A(new Position(
                MvcGameConfig.MAX_X/2 - 40, MvcGameConfig.END_SCENE_POS_Y),
                this.model.getScore());
    }

    @Override
    public Missile_A createMissile( double initAngle, int initVelocity ) {
        return new Missile_A( 
            new Position(
                model.getCannonPosition( ).getX( ), 
                model.getCannonPosition( ).getY( )
            ),
            initAngle, 
            initVelocity, 
            this.model.getMovingStrategy( )
            );
    }
    
}
