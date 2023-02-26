package cz.cvut.fit.miadp.mvcgame.command;

import cz.cvut.fit.miadp.mvcgame.model.IGameModel;

public class CannonReloadCmd extends AbstractGameCommand {

    public CannonReloadCmd( IGameModel model ){
        this.subject = model;
    }

    @Override
    protected void execute() {
        this.subject.cannonReload();
    }
}
