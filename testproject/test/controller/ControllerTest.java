package controller;

import model.Grid;
import model.Player;
import junit.framework.TestCase;

public class ControllerTest extends TestCase {

    private Controller controller;
    private Grid grid;
    private Player player;
    private static final String newLine = "\n";

    @Override
    protected void setUp() {

        grid = new Grid();
        player = new Player();
        controller = new Controller(grid, player);
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
