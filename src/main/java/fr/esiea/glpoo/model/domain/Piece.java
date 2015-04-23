package fr.esiea.glpoo.model.domain;

import java.util.List;

public class Piece {

	private List<Face> faces;

	private int id_piece;
	private int id_forme_nord;
	private int id_forme_est;
	private int id_forme_sud;
	private int id_forme_ouest;
	
	/* pour la sauvegarde*/
//	private Orientation o;
	
	public Piece(int id_piece, int id_forme_nord, int id_forme_est,
			int id_forme_sud, int id_forme_ouest) {
		super();
		this.id_piece = id_piece;
		this.id_forme_nord = id_forme_nord;
		this.id_forme_est = id_forme_est;
		this.id_forme_sud = id_forme_sud;
		this.id_forme_ouest = id_forme_ouest;
	}

	public int getId_piece() {
		return id_piece;
	}

	public void setId_piece(int id_piece) {
		this.id_piece = id_piece;
	}

	public int getId_forme_nord() {
		return id_forme_nord;
	}

	public void setId_forme_nord(int id_forme_nord) {
		this.id_forme_nord = id_forme_nord;
	}

	public int getId_forme_est() {
		return id_forme_est;
	}

	public void setId_forme_est(int id_forme_est) {
		this.id_forme_est = id_forme_est;
	}

	public int getId_forme_sud() {
		return id_forme_sud;
	}

	public void setId_forme_sud(int id_forme_sud) {
		this.id_forme_sud = id_forme_sud;
	}

	public int getId_forme_ouest() {
		return id_forme_ouest;
	}

	public void setId_forme_ouest(int id_forme_ouest) {
		this.id_forme_ouest = id_forme_ouest;
	}
	

	
	
}
