package chess.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import chess.core.Board;
import chess.core.Piece;
import chess.core.Player;
import chess.prototype.events.PieceMovedEvent;
import chess.prototype.events.listener.PieceCapturedEventListener;
import chess.prototype.events.listener.PieceJoinEventListener;
import chess.prototype.events.listener.PieceMovedEventListener;

public class CommandDecisionTest extends GameTestBase {
	private PieceMovedEventListener commandMove;
	private PieceJoinEventListener commandJoin;
	private PieceCapturedEventListener commandCapture;
	
	@Before
	public void setUp() throws Exception {

		commandMove = new PieceMovedEventListener();
		commandJoin = new PieceJoinEventListener();
		commandCapture = new PieceCapturedEventListener();
		
		eventMgr.addListener("PieceMovedEvent", commandMove);
		eventMgr.addListener("PieceJoinEvent", commandJoin);
		eventMgr.addListener("PieceCapturedEvent", commandCapture);
	}
	
	@Test
	public void capture_enemy_piece() {
		// arrange
		// Player player1 = game.getPlayer(1);
		Player player2 = game.getPlayer(2);
		
		PieceMovedEvent event = new PieceMovedEvent(30,18);

		// act
		eventMgr.fireEvent(event);

		
		// assert
		assertEquals("Player 2 get 1 score",
				1, player2.getScore());
		
		int tempcount = 0;
		for(Piece p : board.getPieces()){
			if(p != null && p.getOwner() == null){
				tempcount++;
			}
		}
		
		assertEquals("Barrier pieces is reduced to 11",
				11, tempcount);
	}
	
	@Test
	public void merge_friendly_piece() {
		// arrange
		Piece rook = board.getPiece(0);
		Piece knight = board.getPiece(1);
		
		PieceMovedEvent event = new PieceMovedEvent(0,1);

		// act
		eventMgr.fireEvent(event);

		// assert
		assertTrue("combine piece should not equal rook's score",
				(rook.getScore() != board.getPiece(1).getScore()));

		assertEquals("Combine piece should equal rook + knight score",
				rook.getScore() + knight.getScore(),
				board.getPiece(1).getScore());

		assertEquals("Should be nothing at position zero",
				null,
				board.getPiece(0));
	}

	@Test
	public void move_piece() {
		// arrange
		PieceMovedEvent event = new PieceMovedEvent(1,8);
		
		// act
		eventMgr.fireEvent(event);
		
		// assert
		assertTrue("Rook moved to empty position",
				!((Board)board).isSqureEmpty(8));
		assertEquals("Nothing should be in previous position",
				null, board.getPiece(1));
	}
	
	@Test
	public void piece_did_not_move() {
		// arrange
		Piece prevCurrentPiece = board.getPiece(0);
		Piece prevDestinePiece = board.getPiece(30);
		PieceMovedEvent event = new PieceMovedEvent(0,30);
		
		// act
		eventMgr.fireEvent(event);
		
		// assert
		assertEquals("Position 0 should still have a piece",
				prevCurrentPiece, board.getPiece(0));
		assertEquals("Position 30 should still have a piece",
				prevDestinePiece, board.getPiece(30));
	}
}
