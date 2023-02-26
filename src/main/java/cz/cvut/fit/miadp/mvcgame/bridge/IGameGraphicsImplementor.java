package cz.cvut.fit.miadp.mvcgame.bridge;

import cz.cvut.fit.miadp.mvcgame.model.Position;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface IGameGraphicsImplementor {

    public void drawImage( String path, Position pos);
    public void drawText( String text, Position pos );
    public void drawLine( Position beginPosition, Position endPosition );
    public void clear( );
}
