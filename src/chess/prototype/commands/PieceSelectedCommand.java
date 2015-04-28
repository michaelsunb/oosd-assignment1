package chess.prototype.commands;

import chess.core.*;
import chess.mvc.models.*;
import chess.mvc.views.ChessboardViewPanel;
import chess.prototype.observer.*;

public class PieceSelectedCommand extends CommandBase {

	@Override
	public void update(ChessEvent event) {
		if (!((event instanceof PieceSelectedEvent))) return;
		
		PieceSelectedEvent selEvent = (PieceSelectedEvent)event;
		
		Piece piece = this.board.getPiece(selEvent.getPosition());
		if (piece == null) return;
		
		ChessboardViewPanel chessPane = selEvent.getChessboardViewPanel();
		int currPos = selEvent.getPosition();
		
		int movablePos[] = piece.getMovablePositions(currPos);
		
		chessPane.clearPath();
		
		for (int pos : movablePos) {
			chessPane.markPath(pos);
		}
	}

}
