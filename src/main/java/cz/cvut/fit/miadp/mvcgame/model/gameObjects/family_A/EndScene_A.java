package cz.cvut.fit.miadp.mvcgame.model.gameObjects.family_A;

import cz.cvut.fit.miadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.miadp.mvcgame.model.Position;
import cz.cvut.fit.miadp.mvcgame.model.gameObjects.AbsEndScene;

import java.util.ArrayList;
import java.util.List;

public class EndScene_A extends AbsEndScene {
    public EndScene_A(Position pos, int score) {
        super(pos, score);
    }

    @Override
        public String getText() {
        return "Vyhrál jsi, pro restart stiskni ENTER";
    }
}
