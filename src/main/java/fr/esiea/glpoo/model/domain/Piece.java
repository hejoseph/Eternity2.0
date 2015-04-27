package fr.esiea.glpoo.model.domain;

import java.util.List;

public class Piece {

	private List<Face> faces;

	private int piece_id;
	private int north_face_id;
	private int south_face_id;
	private int east_face_id;
	private int west_face_id;
	
	/* pour la sauvegarde*/
//	private Orientation o;
	
	

	public Piece(int piece_id, int north_face_id, int south_face_id,
			int east_face_id, int west_face_id) {
		super();
		this.piece_id = piece_id;
		this.north_face_id = north_face_id;
		this.south_face_id = south_face_id;
		this.east_face_id = east_face_id;
		this.west_face_id = west_face_id;
	}
	
	public List<Face> getFaces() {
		return faces;
	}
	
	public void setFaces(List<Face> faces) {
		this.faces = faces;
	}
	public int getPiece_id() {
		return piece_id;
	}
	public void setPiece_id(int piece_id) {
		this.piece_id = piece_id;
	}
	public int getNorth_face_id() {
		return north_face_id;
	}
	public void setNorth_face_id(int north_face_id) {
		this.north_face_id = north_face_id;
	}
	public int getSouth_face_id() {
		return south_face_id;
	}
	public void setSouth_face_id(int south_face_id) {
		this.south_face_id = south_face_id;
	}
	public int getEast_face_id() {
		return east_face_id;
	}
	public void setEast_face_id(int east_face_id) {
		this.east_face_id = east_face_id;
	}
	public int getWest_face_id() {
		return west_face_id;
	}
	public void setWest_face_id(int west_face_id) {
		this.west_face_id = west_face_id;
	}
	


	
	
}
