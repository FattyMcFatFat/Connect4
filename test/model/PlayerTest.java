package model;

import java.awt.Color;

import model.Player;
import junit.framework.TestCase;

public class PlayerTest extends TestCase {
    private Player player;

    @Override
    protected void setUp() {
        player = new Player();
    }

    public void testSetPlayer() {
        assertEquals(1, player.getPlayer());
        assertEquals(2, player.getOtherPlayer());
        player.changePlayer();
        assertEquals(2, player.getPlayer());
        assertEquals(1, player.getOtherPlayer());
    }

    public void testSetPlayerNames() {
        player.setPlayerNames("P1", "Player 2");
        assertEquals("P1", player.getPlayerName());
        player.changePlayer();
        assertEquals("Player 2", player.getPlayerName());
        assertEquals("P1", player.getPlayerOneName());
        assertEquals("Player 2", player.getPlayerTwoName());
    }

    public void testSetColors() {
        player.setColors(Color.GREEN, Color.BLUE);
        assertEquals(Color.GREEN, player.getPlayerOneColor());
        assertEquals(Color.BLUE, player.getPlayerTwoColor());
        player.setColors(Color.YELLOW, null);
        player.setColors(null, Color.RED);
        assertEquals(Color.RED, player.getPlayerTwoColor());
        assertEquals(Color.YELLOW, player.getPlayerOneColor());

    }
}
