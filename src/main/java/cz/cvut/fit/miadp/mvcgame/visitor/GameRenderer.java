package cz.cvut.fit.miadp.mvcgame.visitor;

import cz.cvut.fit.miadp.mvcgame.Composite.Composite;
import cz.cvut.fit.miadp.mvcgame.bridge.IGameGraphics;
import cz.cvut.fit.miadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.miadp.mvcgame.model.Position;
import cz.cvut.fit.miadp.mvcgame.model.gameObjects.*;

import java.io.IOException;
import java.util.List;

public class GameRenderer implements IVisitor {

    private IGameGraphics gr;

    public void setGraphicContext( IGameGraphics gr ) {
        this.gr = gr;
    }

    @Override
    public void visitCannon(AbsCannon cannon) {
        this.gr.drawImage( "images/cannon.png", cannon.getPosition( ) );
        
    }

    @Override
    public void visitMissile(AbsMissile missile) {
        this.gr.drawImage( "images/missile.png", missile.getPosition( ) );
        
    }

    @Override
    public void visitCollision(AbsCollision collision) {
        this.gr.drawImage( "images/collision.png", collision.getPosition( ) );
    }

    @Override
    public void visitEnemy(AbsEnemy enemy) {
        this.gr.drawImage( "images/enemy1.png", enemy.getPosition( ) );
    }

    @Override
    public void visitGameInfo(AbsGameInfo gameInfo) {
        if (gameInfo.getTexts() == null) return;
        int i = 0;
        for (String text : gameInfo.getTexts()){
            this.gr.drawText(text, new Position(gameInfo.getPosition().getX(),
                                                    gameInfo.getPosition().getY() + gameInfo.getSpacing() * i ));
            i++;
        }
    }

    @Override
    public void visitBound(AbsBound bound) {
        this.gr.drawImage( "images/bound.png", bound.getPosition( ) );
    }

    @Override
    public void visitEndScene(AbsEndScene endScene) {
        if (endScene.getText() == null) return;
        this.gr.drawText(endScene.getText(), new Position(endScene.getPosition().getX(),
                endScene.getPosition().getY()));
    }

    @Override
    public void visitComposite(Composite composite) {

    }

}
