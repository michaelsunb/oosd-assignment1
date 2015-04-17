/*
 * AUTHOR: Sokun, CHORN
 * Number: S3455783
 */
package chess.core;

import java.util.*;

import java.awt.Color;

public class Player {

	private Color colour;
	private List<Piece> pieces;
	private int numberOfMove = 0;
	private Boolean turn = false;
	private int score = 0;

	public Player() {
		pieces = new ArrayList<Piece>();
	}

	public Boolean isTurn() {
		return turn;
	}

	public void setTurn(Boolean turn) {
		this.turn = turn;
	}

	public void move(int prevPos, int nextPos) {
		this.numberOfMove++;
	}

	public List<Piece> getPieces() {
		return pieces;
	}

	public void setPieces(List<Piece> pieces) {
		this.pieces = pieces;
	}

	public int getNumberOfMove() {
		return numberOfMove;
	}

	public void increaseMove() {
		this.numberOfMove++;
	}

	public int getScore() {
		return this.score;
	}

	public Color getColour() {
		return colour;
	}

	public void setColour(Color black) {
		this.colour = black;
	}

	public void addScore(int value) {
		if (value > 1 && value % 5 != 0) {
			return;
		}

		this.score += value;
	}
}
