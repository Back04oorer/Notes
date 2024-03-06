package WizardTD;

import WizardTD.App;
import WizardTD.Manager;
import WizardTD.Tiles.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import processing.core.PApplet;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class GameboardTest {
    private App test_p;

    private Manager test_manager;
    private GameBoard gameBoard;



    @BeforeEach
    public void setUp() {
        test_p = new App();
        test_p.loop();
        PApplet.runSketch(new String[] {"App"}, test_p);
        test_p.setup();
        test_p.delay(1000);

        gameBoard = new GameBoard(test_p,32,"level3.txt");
        gameBoard.loadMap();
        gameBoard.findPaths();

    }

    @Test
    public void testGetTile(){
        Tile ExistTile = gameBoard.getTile(0,40);
        assert ExistTile instanceof shrub: "this is not a shrub";

        Tile NullTile = gameBoard.getTile(1000,1000);
        assertNull(NullTile);

        assertNull(gameBoard.getTile(1000,40));
        assertNull(gameBoard.getTile(0,1000));
        assertNull(gameBoard.getTile(0,0));
        assertNull(gameBoard.getTile(-10,64));

    }


    @Test
    public void testFindpathNull(){

    }



}