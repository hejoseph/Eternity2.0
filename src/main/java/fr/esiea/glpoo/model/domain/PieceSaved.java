package fr.esiea.glpoo.model.domain;

public class PieceSaved extends Piece{
	
	private int x,y;

	public PieceSaved(int piece_id, int north_face_id, int east_face_id,
			int south_face_id, int west_face_id) {
		super(piece_id, north_face_id, east_face_id, south_face_id, west_face_id);
	}
	
	public PieceSaved(int id, int x, int y, Orientation o){
		super(id, o);
		this.x = x;
		this.y = y;
	}

}
