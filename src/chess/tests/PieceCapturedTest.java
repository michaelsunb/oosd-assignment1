package chess.tests;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import chess.core.Piece;
import chess.core.Player;
import chess.prototype.events.PieceMovedEvent;
import chess.prototype.events.listener.PieceCapturedEventListener;
import chess.prototype.events.listener.PieceMovedEventListener;

public class PieceCapturedTest extends GameTestBase {
	private PieceCapturedEventListener command;
	
	@Before
	public void setUp() throws Exception {
		command = new PieceCapturedEventListener();

		eventMgr.addListener("PieceMovedEvent", new PieceMovedEventListener());
		eventMgr.addListener("PieceCapturedEvent", command);
	}
	
	@Test
	public void capture_enemy_piece() {
		// arrange
		Player player1 = game.getPlayer(1);
		// Player player2 = game.getPlayer(2);
		
		PieceMovedEvent event = new PieceMovedEvent(0,12);

		// act
		eventMgr.fireEvent(event);

		// assert
		
		int tempcount = 0;
		for(Piece p : board.getPieces()){
			if(p != null && p.getOwner() == null){
				tempcount++;
			}
		}
		
		assertEquals("Number of Barriers reduced to 11",
				11, tempcount);
		
		assertEquals("Player 1 get 5 score",
				1, player1.getScore());
	}

}
