package controller;

import model.Globals;
import model.Grid;
import model.Player;
import junit.framework.TestCase;

public class ControllerTest extends TestCase {

    private Controller controller;
    private Grid grid;
    private Player player;
    private Globals glob;
    private static final String newLine = "\n";

    @Override
    protected void setUp() {

    	glob = new Globals(6, 7);
        grid = new Grid(true, glob);
        player = new Player();
        controller = new Controller(grid, player, glob);
    }

    public void testSetCellsLoading() {
        String input = "0000000" + newLine + "0000000" + newLine + "0000000"
                + newLine + "0000000" + newLine + "0000000" + newLine
                + "0121200" + newLine;
        controller.setCellsFromLoad(input);
        player.changePlayer();
        controller.setCellsFromLoad(input);

    }
}
